package by.hes.testTask.controller;

import by.hes.testTask.entity.*;
import by.hes.testTask.entity.builder.UserAccountBuilder;
import by.hes.testTask.service.ValidationService;
import by.hes.testTask.service.exception.InvalidNameException;
import by.hes.testTask.service.exception.InvalidPasswordException;
import by.hes.testTask.service.exception.InvalidUserNameException;
import by.hes.testTask.service.impl.ValidationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@SessionAttributes(value = {"userId","userRole"})
public class UserAccountController {

    private Logger log = LogManager.getLogger(UserAccountController.class);
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StatusRepository statusRepository;




    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }




    @GetMapping("/login")
     public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String signIn(@RequestParam String userName, @RequestParam String password, Map<String, Object> model) {
        try {
            UserAccount userAccount = userAccountRepository.getUser(userName);

            if (userAccount!=null) {
                if (!passwordEncoder.matches(password, userAccount.getPassword())) {
                    model.put("message","Check your password");
                    return "login";
                }
                model.put("userId",userAccount.getId());
                model.put("userRole",userAccount.getRole());
                return "index";
            } else {
                model.put("message","Check your login");
                return "login";
            }
        } catch (NoSuchBeanDefinitionException e) {
            log.info(e);
        }
        model.put("message","Something goes wrong.. try later");
        return "index";
    }

    @GetMapping("/new")
    public String newUser(Map<String, Object> model) {
        try {
            List<Role> roleList = roleRepository.getRoleList();
            List<Status> statusList = statusRepository.getStatusList();
            model.put("roleList", roleList);
            model.put("statusList", statusList);
            return "new";
        } catch (NoSuchBeanDefinitionException e) {
            log.info(e);
        }
        model.put("message","Something goes wrong.. try later");
        return "index";
    }

    @PostMapping("/new")
    public String addUser(@RequestParam String userName,
                          @RequestParam String password,
                          @RequestParam String role,
                          @RequestParam String status,
                          @RequestParam String firstName,
                          @RequestParam String lastName,
                          Map<String, Object> model) {
        UserAccount userAccount = new UserAccountBuilder()
                                    .setUsername(userName)
                                    .setPassword(password)
                                    .setRole(roleRepository.getRoleByName(role).getId())
                                    .setStatus(statusRepository.getStatusByName(status).getId())
                                    .setFirstName(firstName)
                                    .setLastName(lastName)
                                    .build();
        ValidationService validationService = new ValidationServiceImpl();
        try {
            if (validationService.checkUserAccount(userAccount)) {
                userAccount.setPassword(passwordEncoder.encode(password));
                userAccountRepository.save(userAccount);
                model.put("message", "ok");
                return "index";
            }
        } catch (InvalidPasswordException e) {
            model.put("message","Invalid password");
            log.info(e);
        } catch (InvalidNameException e) {
            model.put("message","Invalid userName");
            log.info(e);
        } catch (InvalidUserNameException e) {
            model.put("message","Invalid First name or Last name");
            log.info(e);
        }
        model.put("user",userAccount);
        model.put("roleList",roleRepository.getRoleList());
        model.put("statusList",statusRepository.getStatusList());
        return "new";

    }

    @GetMapping("/user")
    public String list(Map<String, Object> model) {
        List<UserAccount> userList = (List<UserAccount>) userAccountRepository.findAll();
        model.put("userList", userList);
        model.put("roleList", roleRepository.getRoleList());
        model.put("statusList", statusRepository.getStatusList());
        return "list";
    }

    @PostMapping("/user/{id}")
    public String seeDetails(@RequestParam String id, Map<String, Object> model) {
        Optional<UserAccount> userAccount = userAccountRepository.findById(Integer.parseInt(id));
        model.put("roleList",roleRepository.getRoleList());
        model.put("statusList",statusRepository.getStatusList());
        model.put("user",userAccount);
        return "edit";
    }

    @PostMapping("/user/{id}/edit")
    public String editUser(@RequestParam String id,
                           @RequestParam String userName,
                          @RequestParam String role,
                          @RequestParam String status,
                          @RequestParam String firstName,
                          @RequestParam String lastName,
                          Map<String, Object> model) {
        UserAccount userAccount = new UserAccountBuilder()
                                            .setId(Integer.parseInt(id))
                                            .setUsername(userName)
                                            .setFirstName(firstName)
                                            .setLastName(lastName)
                                            .setRole(roleRepository.getRoleByName(role).getId())
                                            .setStatus(statusRepository.getStatusByName(status).getId())
                                            .build();
        ValidationService validationService = new ValidationServiceImpl();
        try {
            if (validationService.checkUserAccount(userAccount)) {
                try{
                    userAccountRepository.updateUserName(userAccount.getUserName(),userAccount.getId());
                    userAccountRepository.updateFirstName(userAccount.getFirstName(),userAccount.getId());
                    userAccountRepository.updateLastName(userAccount.getLastName(),userAccount.getId());
                    userAccountRepository.updateRole(userAccount.getRole(),userAccount.getId());
                    userAccountRepository.updateStatus(userAccount.getStatus(),userAccount.getId());

                } catch (Exception e) {
                    log.info(e);
                }

                model.put("message","User was successfully updated");
                return "index";
            }

        } catch (InvalidPasswordException e) {
            model.put("message","Invalid password");
        } catch (InvalidNameException e) {
            model.put("message","Invalid userName");
        } catch (InvalidUserNameException e) {
            model.put("message","Invalid First name or Last name");
        }
        return "user/{id}/edit";
    }

    @GetMapping("/logout")
    public String logout(Map<String, Object> model, HttpSession session){
        if (model.containsKey("userId")) {
            model.remove("userId");
            session.removeAttribute("userId");
        }
        if (model.containsKey("userRole")) {
            model.remove("userRole");
            session.removeAttribute("userRole");
        }
        return "index";

    }



}

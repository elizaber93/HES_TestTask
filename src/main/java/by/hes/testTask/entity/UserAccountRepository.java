package by.hes.testTask.entity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
    public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {
        @Query(value = "FROM  UserAccount u WHERE u.userName LIKE :keyword"
                + " OR u.lastName LIKE :keyword"
                + " OR u.firstName LIKE :keyword")
        public List<UserAccount> search(@Param("keyword") String keyword);

        @Query(value = "FROM UserAccount u WHERE u.userName LIKE :userName")
        public UserAccount getUser(@Param("userName") String username);

        @Query(value = "UPDATE useraccount  SET username = ?1 WHERE id = ?2" +
                "",nativeQuery = true)
        public int updateUserName(String value, int id);

        @Query(value = "UPDATE useraccount  SET firstname = ?1 WHERE id = ?2",nativeQuery = true)
        public int updateFirstName(String value, int id);

        @Query(value = "UPDATE useraccount  SET lastname = ?1 WHERE id = ?2",nativeQuery = true)
        public int updateLastName(String value, int id);

        @Query(value = "UPDATE useraccount  SET role = ?1 WHERE id = ?2",nativeQuery = true)
        public int updateRole(int value, int id);

        @Query(value = "UPDATE useraccount  SET status = ?1 WHERE id = ?2",nativeQuery = true)
        public int updateStatus(int value, int id);






}


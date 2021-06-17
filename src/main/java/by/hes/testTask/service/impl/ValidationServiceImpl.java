package by.hes.testTask.service.impl;

import by.hes.testTask.entity.UserAccount;
import by.hes.testTask.service.ValidationService;
import by.hes.testTask.service.exception.InvalidNameException;
import by.hes.testTask.service.exception.InvalidPasswordException;
import by.hes.testTask.service.exception.InvalidUserNameException;

public class ValidationServiceImpl implements ValidationService {
    private final String USERNAME_REGEX = "[A-Za-z]{3,16}";
    private final String PASSWORD_REGEX = "((?=.*[a-zA-Z])(?=.*[@#$_+\\-%]).{3,16})";
    private final String NAME_REGEX = "[A-Za-z]{1,16}";

    @Override
    public boolean checkUserAccount(UserAccount userAccount) throws InvalidUserNameException, InvalidPasswordException, InvalidNameException {
        if (!userAccount.getUserName().matches(USERNAME_REGEX)) {
            throw new InvalidUserNameException();
        }
        if (userAccount.getPassword() != null && !userAccount.getPassword().matches(PASSWORD_REGEX)) {
            throw new InvalidPasswordException();
        }
        if (!userAccount.getFirstName().matches(NAME_REGEX) || !userAccount.getLastName().matches(NAME_REGEX)) {
            throw new InvalidNameException();
        }
        return true;
    }
}

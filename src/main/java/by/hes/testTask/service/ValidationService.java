package by.hes.testTask.service;

import by.hes.testTask.entity.UserAccount;
import by.hes.testTask.service.exception.InvalidNameException;
import by.hes.testTask.service.exception.InvalidPasswordException;
import by.hes.testTask.service.exception.InvalidUserNameException;

public interface ValidationService {
    boolean checkUserAccount(UserAccount userAccount) throws InvalidUserNameException, InvalidPasswordException, InvalidNameException;
}

package by.hes.testTask.entity.builder;

import by.hes.testTask.entity.UserAccount;

import java.sql.Timestamp;

public class UserAccountBuilder {
    private UserAccount userAccount = new UserAccount();

    public UserAccountBuilder setId(int id) {
        this.userAccount.setId(id);
        return this;
    }

    public UserAccountBuilder setUsername(String username) {
        this.userAccount.setUserName(username);
        return this;
    }

    public UserAccountBuilder setPassword(String password) {
        this.userAccount.setPassword(password);
        return this;
    }

    public UserAccountBuilder setFirstName(String firstName) {
        this.userAccount.setFirstName(firstName);
        return this;
    }

    public UserAccountBuilder setLastName(String lastName) {
        this.userAccount.setLastName(lastName);
        return this;
    }

    public UserAccountBuilder setRole(int role) {
        this.userAccount.setRole(role);
        return this;
    }

    public UserAccountBuilder setStatus(int status) {
        this.userAccount.setStatus(status);
        return this;
    }

    public UserAccountBuilder setCreationDate(Timestamp date) {
        this.userAccount.setCreationDate(date);
        return this;
    }

    public UserAccount build() {
        return this.userAccount;
    }

}

package pl.multishop.model;

import java.io.Serializable;

/**
 * This is the enum class that creates roles of users.
 * @author Michal Stawarski
 */
public enum UserProfileType implements Serializable {

    CLIENT("CLIENT"), // Role of client
    USER("USER"), // Role of user-employer
    DBA("DBA"), // Role of database admin
    ADMIN("ADMIN"); // Role of admin

    String userProfileType;

    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }
}

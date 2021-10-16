package com.example.textit.Models;

public class Users {
    String profilepic, userName, email, password, userId, lastmessage, status;

    public Users(String profilepic, String userName, String email, String password, String userId, String lastmessage, String status) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.lastmessage = lastmessage;
        this.status = status;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserId(String key) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public Users(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public String getProfilepic(String key) {
        return null;
    }

    public String getUserName(String key) {
        return null;
    }
}







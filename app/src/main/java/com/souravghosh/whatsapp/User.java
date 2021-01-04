package com.souravghosh.whatsapp;

public class User {
    public String fullName, age, email, getprofilepic, userId;

    public User(){

    }

    public User(String fullName, String age, String email, String userId){
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.userId = userId;
    }

    public User(String fullname, String age, String email) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }

    public String getUserId(String key) {
        return userId;
    }

    //////////////////
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
/*public class User {
    String getprofilepic , fullName, age, email , userId , lastMassage;

    public User(String getprofilepic, String fullName, String email,  String userId, String lastMassage) {

        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.userId = userId;
        this.lastMassage = lastMassage;
    }

    public User(){}

    // SignUp Constructpr
    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;


    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String mail) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUserId(String key) {
        return userId;
    }


    public String getGetprofilepic() {
        return getprofilepic;
    }

    public void setGetprofilepic(String getprofilepic) {
        this.getprofilepic = getprofilepic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastMassage() {
        return lastMassage;
    }

    public void setLastMassage(String lastMassage) {
        this.lastMassage = lastMassage;
    }
}*/


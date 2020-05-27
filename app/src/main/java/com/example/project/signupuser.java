package com.example.project;

public class signupuser {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVotercardnumber() {
        return votercardnumber;
    }

    public void setVotercardnumber(String votercardnumber) {
        this.votercardnumber = votercardnumber;
    }

    public Boolean getVarify() {
        return varify;
    }

    public void setVarify(Boolean varify) {
        this.varify = varify;
    }

    String name;
    String email;
    String aadhar;

    String mobile;
    String votercardnumber;
    Boolean varify;


    signupuser(){}


    signupuser(Boolean varify,String useradhar,String useremail,String usermobile,String username,String uservotercardnumber){
        this.varify=varify;
        this.aadhar=useradhar;
        this.email=useremail;
        this.mobile=usermobile;
        this.name=username;
        this.votercardnumber=uservotercardnumber;
    }
}

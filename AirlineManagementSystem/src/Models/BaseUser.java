package Models;

import CODE.AppException;
import CODE.SD;

import java.security.PublicKey;

public class BaseUser {
    private String uuid;
    private String email;
    private String password;
    private String userName;
    private UserType userType;

    public String GetUUID(){
        return  this.uuid;
    }
    public void SetUUID(String uuid){
        this.uuid = uuid;
    }
    public void SetEmail(String email) throws Exception{
        String pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        if(!email.matches(pattern)) throw new AppException(SD.InvalidEmailErrorMessage ,false);
        this.email = email;
    }
    public String GetEmail(){
        return this.email;
    }
    public UserType GetUserType(){
        return  this.userType;
    }
    public void SetUserType(UserType userType){
        this.userType = userType;
    }
    public String GetPassword(){
        return  this.password;
    }
    public void SetPassword(String password) throws Exception{
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        if(!password.matches(pattern))  throw new AppException(SD.WeakPasswordErrorMessage ,false);
        this.password = password;
    }
    public void SetUserName(String userName) throws Exception{
        if(userName.length() < 3 ||  userName.length() >30){
            throw new AppException(SD.InvalidUserName ,false);
        }
        this.userName= userName;
    }
    public String GetUserName(){
        return  this.userName;
    }
}

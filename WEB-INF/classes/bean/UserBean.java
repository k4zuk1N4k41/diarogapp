package bean;

import java.io.Serializable;

public class UserBean implements Serializable{
    
    private String userName;
    private String pass;

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPass()
    {
        return pass;
    }
}

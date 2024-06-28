package bean;

import java.io.Serializable;

public class DiarogBean implements Serializable{
    
    private String userName;
    private String date;
    private String content;

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getDate()
    {
        return date;
    }

    public String getContent()
    {
        return content;
    }
}

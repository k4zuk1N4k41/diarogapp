package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDTO implements Serializable{

    private ArrayList<UserBean> list;

    public UserDTO()
    {
        list = new ArrayList<UserBean>();
    }

    public void add(UserBean ub)
    {
        list.add(ub);
    }

    public UserBean get(int i)
    {
        return list.get(i);
    }

    public int size()
    {
        return list.size();
    }
    
    //名前が重複していないか
    public boolean inspection(String userName)
    {
        boolean result = false;

        for (int i = 0; i < size(); i++)
        {
            UserBean ub = get(i);
            if( userName.equals(ub.getUserName()) )
            {
                result = true;
            } 
        }

        return result;
    }

    //ログイン情報が正しいか
    public int certification(String userName, String pass)
    {
        int result = 0;

        for (int i = 0; i < size(); i++)
        {
            UserBean ub = get(i);
            //ユーザー名があるかを確認する
            if(userName.equals(ub.getUserName()))
            {
                //パスワードが正しいかを確認する。
                if(pass.equals(ub.getPass()))
                {
                    result = 1;

                    return result;
                }
                else
                {
                    result = 2;

                    return result;
                }
            }
        }
        result = 3;

        return result;
    }
}

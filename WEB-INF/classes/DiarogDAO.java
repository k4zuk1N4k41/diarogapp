import java.sql.*;
import bean.*;

public class DiarogDAO {
    final String URL = "jdbc:mysql://localhost/diarogdb";
    final String USER = "root";
    final String PASS = "pass";
    private Connection con = null;

    //データベースに接続
    public void connect()
    {
        try
        {
            con = DriverManager.getConnection(URL, USER, PASS);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public DiarogDTO select (String userName)
    {
        Statement stmt = null;
        ResultSet rs = null;
        DiarogDTO ddto = new DiarogDTO();

        //ユーザー名が一致している行のみ抽出
        String sql = "SELECT * FROM diarogInfo WHERE userName = '" + userName + "'";

        try
        {
            connect();
            //ステートメントを生成
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            //検索結果の処理
            while(rs.next())
            {
                DiarogBean db = new DiarogBean();
                db.setUserName(rs.getString("userName"));
                db.setDate(rs.getString("date"));
                db.setContent(rs.getString("content"));
                ddto.add(db);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();;
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        disconnect();
        return ddto;
    }

    public UserDTO select()
    {
        Statement stmt = null;
        ResultSet rs = null;
        UserDTO udto = new UserDTO();
        String sql = "SELECT * FROM userInfo";

        try
        {
            connect();
            //ステートメントを生成
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            //検索結果の処理
            while(rs.next())
            {
                UserBean ub = new UserBean();
                ub.setUserName(rs.getString("userName"));
                ub.setPass(rs.getString("pass"));
                udto.add(ub);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();;
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        disconnect();
        return udto;
    }
    //ユーザー情報を登録する
    public int registration(String userName, String password)
    {
        String sql = "INSERT INTO userInfo VALUES('" + userName + "', " + password + ")";

        return executeSql(sql);
    }

    //日記情報を登録する
    public int registration(String userName, String date, String daily, DiarogDTO ddto)
    {
        boolean result = true;

        //同じ日付がないか検索する
        for (int i = 0; i < ddto.size(); i++)
        {
            DiarogBean db = ddto.get(i);
            if (date.equals(db.getDate()))
            {
                result = false;
                break;
            }
        }

        //同じ日付が存在しない場合は追加する。
        if (result)
        {
            String sql = "INSERT INTO diarogInfo VALUES('" + userName + "', '" + date + "', '" + daily + "')";

            return executeSql(sql); //更新数がかえるはず
        }

        //同じ日付がある場合はエラーとして-1を返す
        return -1;   //エラー
    }

    //登録情報を修正する
    public int correction(String date, String daily)
    {
        String sql = "UPDATE diarogInfo SET content = '" + daily + "' WHERE date = '" + date + "'";

        return executeSql(sql);
    }

    public int delete(String date)
    {
        String sql = "DELETE FROM diarogInfo WHERE date = '" + date + "'";

        return executeSql(sql);
    }
    //SQLへの登録処理を実行する
    public int executeSql(String sql)
    {
        Statement stmt = null;
        int result = 0;
        try
        {
            connect();
            //ステートメントを生成
            stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(stmt != null) stmt.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        disconnect();

        return result;
    }

    //データベースとの接続を切断する
    public void disconnect()
    {
        try
        {
           if(con != null) con.close(); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import bean.UserDTO;
import bean.DiarogDTO;

@WebServlet("/diarog")
public class DiarogSurvlet extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException
    {
        //宣言
        String msg = "日記を表示します";
        String url = "/userRegistration.jsp";

        //入力情報を受け取る
        req.setCharacterEncoding("utf-8");
        String userName = req.getParameter("userName");
        String pass = req.getParameter("pass");        
        String btn = req.getParameter("btn");

        //DiarogDAOオブジェクトを生成
        DiarogDAO ddao = new DiarogDAO();

        //押下ボタンによる分岐処理
        if(btn.equals("登録"))
        {
            
            //SQLの登録状況を取得する
            UserDTO udto = ddao.select();
            
            //userNameが重複していないかを確認する
            if (udto.inspection(userName))
            {
                msg = "ユーザー名が重複しているため、情報の登録に失敗しました。";
            }
            else
            {
                //SQLに登録する
                ddao.registration(userName, pass);
                msg = userName + "の情報が登録されました";
            }
            //登録完了のJSPのurlを登録する
            url = "/userRegistration.jsp";
        }
        else if(btn.equals("ログイン"))
        {
            //SQLの登録状況を取得する
            UserDTO udto = ddao.select();

            //入力されたユーザー名が登録されているかを確認する。
            int result = udto.certification(userName, pass);
        
            if (result == 1) //ユーザ名もパスワードも正しい場合
            {
                msg = userName + "さんようこそ！";
                DiarogDTO ddto = ddao.select(userName);

                //リクエストスコープにDTOを格納
                req.setAttribute("ddto", ddto);

                //日記画面に飛ぶように設定する
                url = "/diarog.jsp";
            }
            else if(result == 2) //パスワードが間違っている場合
            {
                msg = "パスワードが間違っています。";
            }
            else //ユーザー名が間違っている場合
            {
                msg = "ユーザー名が見つかりません。";
            }
        }
        else if(btn.equals("追加"))
        {

        }

        //メッセージをリクエストスコープに格納
        req.setAttribute("msg", msg);
        req.setAttribute("userName", userName);

        //JSPにフォワード
        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException
    {
        doPost(req, res);
    }
}
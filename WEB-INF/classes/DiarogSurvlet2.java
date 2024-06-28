import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import bean.UserDTO;
import bean.DiarogDTO;

@WebServlet("/diarog2")
public class DiarogSurvlet2 extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException
    {
        //宣言
        String msg = "";
        String url = "/diarog.jsp";
        String userName= null;
        DiarogDTO ddto = null;
        
        //入力情報を受け取る
        req.setCharacterEncoding("utf-8");
        
        //userNameを受け取る
        userName = req.getParameter("userName");

        String date = req.getParameter("date");        
        String daily = req.getParameter("daily");
        String btn = req.getParameter("btn");

        //DiarogDAOオブジェクトを生成
        DiarogDAO ddao = new DiarogDAO();

        //更新前のDTOを生成
        ddto =  ddao.select(userName);

        //msgに正常に動作した場合のメッセージを登録しておく
        msg = userName + "さんようこそ！";

        //押下ボタンによる分岐処理
        if(btn.equals("追加"))
        {
            //SQLに登録する
            int result = ddao.registration(userName, date, daily, ddto);
            if (result == -1)       //-1はエラーとする
            {
                msg = "追加に失敗しました";
            }
        }
        else if(btn.equals("修正"))
        {
            int result = ddao.correction(date, daily);
            if (result == 0)
            {
                msg = "修正に失敗しました";
            }
        }
        else
        {
            int result = ddao.delete(date);
            if (result == 0)
            {
                msg = "削除に失敗しました";
            }
        }

        //ユーザー名が同一なデータだけを取り出す。
        ddto = ddao.select(userName);

        //メッセージをリクエストスコープに格納
        req.setAttribute("msg", msg);
        req.setAttribute("ddto", ddto);
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
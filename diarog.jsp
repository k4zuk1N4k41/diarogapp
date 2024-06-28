<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>
<jsp:useBean id="msg" scope="request" class="java.lang.String" />
<jsp:useBean id="ddto" scope="request" class="bean.DiarogDTO" />
<html>
    <head>
        <style>
            h2
            {
                text-align:center;
            }                 
        </style>
        <title>日記画面</title>
    </head>
    <body>
        <h2><%= msg %></h2>
        <h3>日記を記録する</h3>
        <form action="/diarogapp/diarog2" method="post">
            日付：<input type="date" name="date" size="20"/><br/>
            日記：<br/>
            <textarea name="daily" rows="5" cols="50">一日の記録を入力してください！</textarea>
            <input type="hidden" name="userName" value="${requestScope.userName}"><br/><br/>
            <input type="submit" name="btn" value="追加" />
            <input type="submit" name="btn" value="修正" />
            <input type="submit" name="btn" value="削除" />
            <br/><br/>        
        <h3>日記を見る</h3>
        <table border="1">
            <tr>

                <th width="100">日付</th>
                <th width="250">一日の記録</th>
            </tr>
        <%
            for(int i = 0; i < ddto.size(); i++)
            {
                DiarogBean db = ddto.get(i);
        %>
            <tr>
                <td align="center"><%= db.getDate() %></td>
                <td align="center"><%= db.getContent() %></td>
            </tr>
        <% } %>
        </table></br>
        <a href="/diarogapp/diarog.html">ログアウト</a>
    </body>
</html>
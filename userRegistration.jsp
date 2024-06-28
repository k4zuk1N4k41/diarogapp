<%@page contentType="text/html;charset=utf-8" %>
<jsp:useBean id="msg" scope="request" class="java.lang.String" />
<html>
    <head>
        <title>認証画面</title>
    </head>
    <body>
        <h2><%= msg %></h2>
        
        <a href="/diarogapp/diarog.html">戻る</a>
    </body>
</html>
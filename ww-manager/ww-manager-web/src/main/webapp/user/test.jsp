<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="add" action="useradd" method="post">
    <table class="table-edit" width="80%" align="center">

        <tr>
            <td>姓名</td>
            <td><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td>手机</td>
            <td>
                <input type="text" name="phone" /></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input type="text" name="email"/></td>
        </tr>

        <tr>

            <td><input type="submit" value="提交"/></td>
        </tr>

    </table>
</form>

</body>
</html>

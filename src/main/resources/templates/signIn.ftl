<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登入</title>
</head>
<body>

<form action="/user/userLogin" method="post">
    用户名： <input type="text" name="username">
    密码： <input type="password" name="password">
    <input type="submit" value="提交">
${msg!}
</form>

</body>
</html>
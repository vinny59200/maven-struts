<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login</h2>
    <html:form action="/login">
        <table>
            <tr>
                <td>Username:</td>
                <td><html:text property="username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><html:password property="password" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <html:submit value="Login" />
                </td>
            </tr>
        </table>
    </html:form>

    <p>${message}</p>
</body>
</html>

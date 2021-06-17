<html>
    <head>
        <title>
            HES TestTask
        </title>
    </head>
    <body>
        <nav>
            <a href="/login" >Login</a>
            <a href="/view">View</a>
        </nav>
        <table>
            <#list userList as user>
                <tr>
                    <td>
                        ${user.userName}
                    </td>
                    <td>
                        ${user.role}
                        ${user.status}
                    </td>
                </tr>
            <#list>
        </table>
    </body>
</html>
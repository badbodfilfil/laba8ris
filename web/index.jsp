<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>JMS</title>
    </head>
    <body>
        <form method="POST" action="MessageClient">
            <table border="1">
                <thead>
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>
                    
                </thead>
                <tbody>
                    <tr>
                    <td>Your message:</td>
                    <td>
                        <input type="text" name="msg" value="" size="50"/>
                    </td>
                </tr>
                    
                </tbody>
                
            </table>
            <input type="submit" name="submit" value="Submit"/>
        </form>
    </body>
</html>

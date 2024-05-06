<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tic_Tac_Toe</title>
    <link href="static/Main.css" rel="stylesheet">
</head>
<body>
<table>
    <tr>
        <td onclick="window.location='/tic_tac_war/logic?click=0'">${data.get(0).getSign()}</td>
        <td onclick="window.location='/tic_tac_war/logic?click=1'">${data.get(1).getSign()}</td>
        <td onclick="window.location='/tic_tac_war/logic?click=2'">${data.get(2).getSign()}</td>
    </tr>
    <tr>
        <td onclick="window.location='/tic_tac_war/logic?click=3'">${data.get(3).getSign()}</td>
        <td onclick="window.location='/tic_tac_war/logic?click=4'">${data.get(4).getSign()}</td>
        <td onclick="window.location='/tic_tac_war/logic?click=5'">${data.get(5).getSign()}</td>
    </tr>
    <tr>
        <td onclick="window.location='/tic_tac_war/logic?click=6'">${data.get(6).getSign()}</td>
        <td onclick="window.location='/tic_tac_war/logic?click=7'">${data.get(7).getSign()}</td>
        <td onclick="window.location='/tic_tac_war/logic?click=8'">${data.get(8).getSign()}</td>
    </tr>
</table>

</body>
</html>
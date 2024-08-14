<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to ${course}</title>
    <%-- css file kept resources/static folder --%>
    <link rel="stylesheet" href="calculator.css">
</head>
<body>
    <%--  Commenting Below Code --%>
    <!-- <h2>Result is <%= session.getAttribute("result") %> -- </h2><br/> -->

    <%-- this gets result by searching result key in session/request object etc --%>
    <h2>Result is ${result} </h2>
</body>
</html>

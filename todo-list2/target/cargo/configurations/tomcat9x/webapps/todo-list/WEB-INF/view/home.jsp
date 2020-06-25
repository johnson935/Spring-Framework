<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.example.util.Mappings" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>To-do List Application</title>
</head>
<body>
    <div align="center">
        <c:url var="itemsLink" value="${Mappings.ITEMS}" />
        <h2><a href="${itemsLink}">Show Todo Items</a></h2>
    </div>
</body>
</html>
<!DOCTYPE html>

  <head>
    <title>LIBRARY</title>
    <link href="resources/styles/style.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ page isELIgnored="false" %>
    <fmt:setLocale value="${language}"/>
    <fmt:setBundle basename="messages"/>
  </head>

  <body>
    <div id="logo">
      <img id="logo" src="resources/images/logo.png" />
    </div>

    <ul class="menu-main">
      <li><a href="http://localhost:8080/library/books"> <fmt:message key="label.books" /></a></li>
      <c:if test="${user==NULL}">
         <li><a href="http://localhost:8080/library/book"> <fmt:message key="label.authorization" /></a></li>
      </c:if>
      <li><a href="http://localhost:8080/library/language"><fmt:message key="label.language" /></a></li>
      <c:if test="${user!=NULL}">
         <c:if test="${user.role=='READER'}">
            <li><a href="http://localhost:8080/library/booksbox"><fmt:message key="label.mybooks" /></a></li>
         </c:if>
         <c:if test="${user.role=='LIBRARIAN'}">
            <li><a href="http://localhost:8080/library/admin"><fmt:message key="label.administration" /></a></li>
         </c:if>
            <li><a href="http://localhost:8080/library/logout"><fmt:message key="label.exit" /></a></li>
      </c:if>
    </ul>

    <c:if test="${user!=null}">
      <div class= "info">
        <h2><fmt:message key="label.welcome" />, ${user.name}</h2>
      </div>
    </c:if>



    <form method="post" action="http://localhost:8080/library/books">
        <input type="text" placeholder=<fmt:message key="label.tittle" /> name="name"><br />
        <input type="text" placeholder= <fmt:message key="label.author" /> name="author"><br />
        <button id="log" type="submit"><fmt:message key="label.searchbooks" /></button>
    </form>
  </body>

</html>
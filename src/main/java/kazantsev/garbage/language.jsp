
<!DOCTYPE html>

 <head>
    <title>LANGUAGE</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="resources/styles/style.css" rel="stylesheet">
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ page isELIgnored="false" %>
    <fmt:setLocale value="${language}"/>
    <fmt:setBundle basename="messages"/>
</head>
<body>


  <ul class="menu-main">
    <li><a href="http://localhost:8080/library"><fmt:message key="label.homepage" /></a></li>
    <li><a href="http://localhost:8080/library/books" ><fmt:message key="label.books" /></a></li>
    <c:if test="${user==NULL}">
       <li><a href="http://localhost:8080/library/book"><fmt:message key="label.authorization" /></a></li>
    </c:if>
    <c:if test="${user!=NULL}">
       <c:if test="${user.role=='READER'}">
         <li><a href="http://localhost:8080/library/booksbox"><fmt:message key="label.mybooks" /></a></li>
       </c:if>
       <c:if test="${user.role=='LIBRARIAN'}">
          <li><a href="http://localhost:8080/library/admin"><fmt:message key="label.administration" /></a></li>
       </c:if>
       <li><a href="http://localhost:8080/library/logout"><fmt:message key="label.exit" /></a></li>
    </c:if>
    <li><a href="http://localhost:8080/library/language" class="current"><fmt:message key="label.language" /></a></li>
  </ul>


  <c:if test="${forminfo!=NULL}">
     <div class= "info">
         <h2>${forminfo}</h2>
     </div>
  </c:if>

  <div class="frameform">
     <form method="post" action="http://localhost:8080/library/language"   >
        <div class="container">
           <button type="submit" id="log" name= "lang" value="en">English</button>
           <button type="submit" id="log" name= "lang" value="ru">Русский</button>
        </div>
    </div>
 </body>
</html>

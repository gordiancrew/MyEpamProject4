<!DOCTYPE html>

<head>
    <title>BOOKS</title>
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
    <li><a href="http://localhost:8080/library/books" class="current"><fmt:message key="label.books" /></a></li>
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
    <li><a href="http://localhost:8080/library/language"><fmt:message key="label.language" /></a></li>
  </ul>


  <div id="inline">
     <form  method="post" action="http://localhost:8080/library/books">
        <table class="content">
           <td><input type="text" placeholder=<fmt:message key="label.tittle" /> name="name"></td>
           <td><input type="text" placeholder= <fmt:message key="label.author" /> name="author"></td>
           <td><button  type="submit"><fmt:message key="label.searchbooks" /></button></td>
        </table>
     </form>
  </div>
  <c:if test="${result!=NULL}">
     <table class="content">
        <tr>
           <th><fmt:message key="label.tittle" /></th>
           <th><fmt:message key="label.author" /></th>
           <th><fmt:message key="label.year" /></th>
           <th><fmt:message key="label.availability" /></th>
        </tr>
        <c:forEach items="${result}" var="book">
           <tr>
              <td>
                <c:if test="${user.role=='READER'}">
                   <a href="http://localhost:8080/library/book?id=${book.id}">
                </c:if>
                       <c:out value="${book.name}" />
                <c:if test="${user.role=='READER'}">
                    </a>
                </c:if>
              </td>
              <td>${book.author}</td>
              <td>${book.year}</td>
              <td>
                <c:if test="${book.number==1}">
                  <fmt:message key="label.instock" />
                </c:if>
              </td>
           </tr>
        </c:forEach>
     </table>
  </c:if>
</body>

</html>
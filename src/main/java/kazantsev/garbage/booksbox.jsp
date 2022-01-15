<!DOCTYPE html>
<head>
    <title>BOOKS</title>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <link href="style.css" rel="stylesheet">
</head>
<body>
    <ul class="menu-main">
           <li><a href="http://localhost:8080/library">Домашняя страница</a></li>
           <li><a href="http://localhost:8080/library/books" >Книги</a></li>
         <c:if test="${user==NULL}">
           <li><a href="http://localhost:8080/library/book">Авторизация</a></li>
         </c:if>
         <c:if test="${user!=NULL}">
            <li><a href="http://localhost:8080/library/booksbox" class="current">Мои книги</a></li>
                <c:if test="${user.role=='LIBRARIAN'}">
                   <li><a href="http://localhost:8080/library/admin">Администрирование</a></li>
                </c:if>
            <li><a href="http://localhost:8080/library/logout">Выход</a></li>
           </c:if>
   </ul>

<div class="info">
<h2>
Книги в вашем наличии, ${user.name}
</h2>
</div>

    <table class="content">



               <tr>

                   <th>Название книги</th>
                   <th>Автор</th>
                   <th>Дата получения</th>
               </tr>
               <c:forEach items="${result}" var="operation">
                   <tr>
                       <td>
                                <a href="http://localhost:8080/library/bookreturn?id=${operation.id}">
                           <c:out value="${operation.book.name}" />
                               </a>
                       </td>
                       <td>${operation.book.author}</td>
                       <td>${operation.dateGet}</td>
                   </tr>
               </c:forEach>
           </table>

</body>
</html>
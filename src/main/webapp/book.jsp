<!DOCTYPE html>


<head>
  <title>BOOK</title>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <link href="style.css" rel="stylesheet">
</head>

<body>
<ul class="menu-main">
        <li><a href="http://localhost:8080/library">Домашняя страница</a></li>
        <li><a href="http://localhost:8080/library/books" class="current">Книги</a></li>
      <c:if test="${user==NULL}">
        <li><a href="http://localhost:8080/library/book">Авторизация</a></li>
      </c:if>
      <c:if test="${user!=NULL}">
         <li><a href="http://localhost:8080/library/booksbox">Мои книги</a></li>
             <c:if test="${user.role=='LIBRARIAN'}">
                <li><a href="http://localhost:8080/library/admin">Администрирование</a></li>
             </c:if>
         <li><a href="http://localhost:8080/library/logout">Выход</a></li>
        </c:if>

</ul>


  <c:if test="${booktype.equals('result')}" >
    <div class="info">
      <h2>
        ${resultbook}
      </h2>
    </div>
  </c:if>


  <c:if test="${!booktype.equals('result')}" >

    <div class="infobook">
      <h2>"${book.name}"  ${book.author}</h1>
      <h3>год издания: ${book.year}</h2>
    </div>
  <c:if test="${booktype.equals('view')}" >
   <c:if test="${book.number==1}" >
     <form method="post" action="http://localhost:8080/library/book" >
       <button type="submit" name="idbook" value="${book.id}">ВЗЯТЬ КНИГУ</button>
     </form>
   </c:if>
  <c:if test="${book.number==0}" >
    <div class="info">
      <h2>К сожалению данная книга на данный момент недоступна, Вы можете взять ее позже!</h2>
    </div>
   </c:if>





  </c:if>


   <c:if test="${booktype.equals('returnview')}" >
        <form method="post" action="http://localhost:8080/library/bookreturn" >
            <button type="submit" name="idoperation" value="${operation.id}">ВЕРНУТЬ КНИГУ</button>
        </form>
    </c:if>

   </c:if>
</body>
</html>
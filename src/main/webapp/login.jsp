
<!DOCTYPE html>

  <head>
   <link href="style.css" rel="stylesheet">
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  </head>
 <body>
 <ul class="menu-main">
         <li><a href="http://localhost:8080/library">Домашняя страница</a></li>
         <li><a href="http://localhost:8080/library/books" >Книги</a></li>
       <c:if test="${user==NULL}">
         <li><a href="http://localhost:8080/library/book" class="current">Авторизация</a></li>
       </c:if>
       <c:if test="${user!=NULL}">
          <li><a href="http://localhost:8080/library/booksbox">Мои книги</a></li>
              <c:if test="${user.role=='LIBRARIAN'}">
                 <li><a href="http://localhost:8080/library/admin">Администрирование</a></li>
              </c:if>
          <li><a href="http://localhost:8080/library/logout">Выход</a></li>
         </c:if>
 </ul>
   <div class="frame">
     <form method="post" action="" >
       <div class="container">
         <input type="text" placeholder="Enter Username" name="login" required>
         <input type="password" placeholder="Enter Password" name="password" required>
         <button type="submit" id="log">ВОЙТИ</button>
         <div id="reg">
         <a href="http://localhost:8080/library/registration">
           Зарегистрироваться?</a>
         </div>
       </div>
     </form>
   </div>
 </body>
</html>

<!DOCTYPE html>

<head>
    <title>BOOKS</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link href="resources/styles/style.css" rel="stylesheet">
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ page isELIgnored="false" %>
                <fmt:setLocale value="${language}" />
                <fmt:setBundle basename="messages" />
</head>

<body>

<!--!Блок меню-->
    <ul class="menu-main">
        <li>
            <a href="http://localhost:8080/library">
                <fmt:message key="label.homepage" />
            </a>
        </li>
        <li>
            <a href="http://localhost:8080/library/books" class="current">
                <fmt:message key="label.books" />
            </a>
        </li>
        <c:if test="${user==NULL}">
            <li>
                <a href="http://localhost:8080/library/book">
                    <fmt:message key="label.authorization" />
                </a>
            </li>
        </c:if>
        <c:if test="${user!=NULL}">
            <c:if test="${user.role=='READER'}">
                <li>
                    <a href="http://localhost:8080/library/booksbox">
                        <fmt:message key="label.mybooks" />
                    </a>
                </li>
            </c:if>
            <c:if test="${user.role=='LIBRARIAN'}">
                <li>
                    <a href="http://localhost:8080/library/admin">
                        <fmt:message key="label.administration" />
                    </a>
                </li>
            </c:if>
            <li>
                <a href="http://localhost:8080/library/logout">
                    <fmt:message key="label.exit" />
                </a>
            </li>
        </c:if>
        <li>
            <a href="http://localhost:8080/library/language">
                <fmt:message key="label.language" />
            </a>
        </li>
    </ul>
<!--finish MENU-->


<!--Block ADMIN-->
<c:if test="${pagetype.equals('admin')}">
<ul>
<li><a method="post" href="http://localhost:8080/library/addbook">Добавить книгу</a></li>
<li><a href="http://localhost:8080/library/deletebook">Удалить книгу</a></li>
<li><a href="http://localhost:8080/library/addreader.jsp">Добавить читателя </a></li>
<li><a href="http://localhost:8080/library/deletereader.jsp">Удалить читателя</li>
<li><a href="http://localhost:8080/library/operation.jsp">Оформить заказ</li>
<li><a href="http://localhost:8080/library">Домашняя страница</li>
</ul>
</c:if>
<!--finish ADMIN-->

<!--Block DELETEBOOK-->
  <c:if test="${pagetype.equals('deletebookview')}">
      <div class="infobook">
          <h2>"${book.name}" ${book.author}</h2>
          <h3>год издания: ${book.year}</h3>
            <c:if test="${book.number!=0}">
              <form method="post" action="http://localhost:8080/library/deletebook">
                  <button type="submit" id="log" name="idbook" value="${book.id}">?УДАЛИТЬ КНИГУ</button>
              </form>
            </c:if>
      </div>
  </c:if>


  <c:if test="${pagetype.equals('deletebook')}">
     <div class="infobook">
        <h2>
          ${info}
        </h2>
     </div>
  </c:if>
<!--Finish DELETEBOOK-->



</body>
</html>
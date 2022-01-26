<!DOCTYPE html>

<head>
    <title>BOOKS</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link href="resources/styles/style.css" rel="stylesheet">
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ page isELIgnored="false" %>
                <fmt:setLocale value="${language}" />
                <fmt:setBundle basename="messages" />
                <%@ taglib prefix="ex" uri="/WEB-INF/custom.tld"%>
</head>

<body>
 <!--START MENU ADM-->
      <ul class="menu-main">
          <li>
              <a href="http://localhost:8080/library">
                  <fmt:message key="label.homepage" />
              </a>
          </li>
          <li>
              <a href="http://localhost:8080/library/books">
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
              <c:if test="${user.role=='READER'&&user.isConfirm()}">
                  <li>
                      <a href="http://localhost:8080/library/booksbox">
                          <fmt:message key="label.mybooks" />
                      </a>
                  </li>
              </c:if>
              <c:if test="${user.role=='LIBRARIAN'}">
                  <li>
                     <a href="http://localhost:8080/library/readers">
                        <fmt:message key="readers" />
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
  <!--FINISH MENU-->

<!--START books-->
    <c:if test="${pagetype.equals('books')}">
        <div id="inline">
            <table class="content">
                <form method="post" action="http://localhost:8080/library/books">
                    <td><input type="text" placeholder=<fmt:message key="label.tittle" /> name="name"></td>
                    <td><input type="text" placeholder=< fmt:message key="label.author" /> name="author"></td>
                    <td><button type="submit"><fmt:message key="label.searchbooks" /></button></td>
                </form>
                <c:if test="${user.role=='LIBRARIAN'&&user.isConfirm()}">
                    <form action="http://localhost:8080/library/addbook">
                        <td>
                            <button type="submit"><fmt:message key="addbook" /></button>
                        </td>
                    </form>
                </c:if>
            </table>
        </div>

        <c:if test="${resultlist!=NULL}">
            <table class="content">
                <tr>
                    <th>N.</th>
                    <th>
                        <fmt:message key="label.tittle" />
                    </th>
                    <th>
                        <fmt:message key="label.author" />
                    </th>
                    <th>
                        <fmt:message key="label.year" />
                    </th>
                    <th>
                        <fmt:message key="label.availability" />
                    </th>
                </tr>
                <c:forEach begin="${page+0}" end="${page+4}" items="${resultlist}" var="book">
                    <tr>
                        <td>${resultlist.indexOf(book)+1}</td>
                        <td>
                            <c:if test="${user.role=='READER'&&user.isConfirm()}">
                                <a href="http://localhost:8080/library/book?id=${book.id}">
                            </c:if>
                            <c:if test="${user.role=='LIBRARIAN'}">
                                <a href="http://localhost:8080/library/deletebook?id=${book.id}">
                            </c:if>
                            <c:out value="${book.name}" />
                            <c:if test="${user.role=='READER'&&user.isConfirm()}">
                                </a>
                            </c:if>
                            <c:if test="${user.role=='LIBRARIAN'}">
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

        <div id="nex">
            <table class="content">
                <form method="post" action="http://localhost:8080/library/books">
                    <div class="container">
                        <c:if test="${page!=0&&page!=null}">
                            <td>
                                <button type="submit" name="page" value="${page-5}"><fmt:message key="label.previous" /></button>
                            </td>
                        </c:if>
                        <c:if test="${page!=null&&page/5<resultlist.size()/5-1}">
                            <td>
                                <button type="submit" name="page" value="${page+5}"><fmt:message key="label.next" /></button>
                            </td>
                        </c:if>
                    </div>
                </form>
            </table>
        </div>
    </c:if>
 <!--FINISH books-->

 <!--START login-->
    <c:if test="${pagetype.equals('login')}">
        <div class="frame">
            <form method="post" action="">
                <!--div class="container"-->
                <input type="text" placeholder=<fmt:message key="label.login" /> name="login" required>
                <input type="password" placeholder=<fmt:message key="label.password" /> name="password" required>
                <button type="submit" id="log"><fmt:message key="label.signin" /></button>
                <div id="reg">
                    <a href="http://localhost:8080/library/registration">
                        <fmt:message key="label.registration" />
                    </a>
                </div>
            </form>
        </div>
    </c:if>
<!--!FINISH login-->

<!--START language-->
    <c:if test="${pagetype.equals('language')}">

        <div class="frameform">
            <form method="post" action="http://localhost:8080/library/language">
                <div class="container">
                    <button type="submit" id="log" name="lang" value="en">English</button>
                    <button type="submit" id="log" name="lang" value="ru">Русский</button>
                </div>
            </form>
        </div>
    </c:if>
<!--FINISH language-->

<!--START BOOK-->
    <c:if test="${pagetype.equals('book')}">
        <c:if test="${booktype.equals('result')}">
            <div class="info">
                <h2>
                    ${resultbook}
                </h2>
            </div>
        </c:if>

        <c:if test="${!booktype.equals('result')}">
            <div class="infobook">
                <h2>"${book.name}" ${book.author}</h2>
                <h3><fmt:message key="label.year" />: ${book.year}</h3>
                <h3>${book.description}</h3>

                <c:if test="${booktype.equals('view')}">
                    <c:if test="${book.number==1}">
                        <form method="post" action="http://localhost:8080/library/book">
                            <button type="submit" id="log" name="idbook" value="${book.id}"><fmt:message key="getbook"/></button>
                        </form>
                    </c:if>

                    <c:if test="${book.number==0}">
                        <div class="info">
                            <h2><fmt:message key="book.unavailable"/></h2>
                        </div>
                    </c:if>

                </c:if>

                <c:if test="${booktype.equals('returnview')}">

                    <form method="post" action="http://localhost:8080/library/bookreturn">
                        <button type="submit" name="idoperation" value="${operation.id}"><fmt:message key="returnbook"/></button>
                    </form>

                </c:if>
            </div>
        </c:if>
    </c:if>
<!--FINISH BOOK-->

<!--START registration-->
    <c:if test="${pagetype.equals('registration')}">
        <c:if test="${error!=NULL}">
            <div class="infoerror">
                <h2>${error}</h2>
            </div>
        </c:if>

        <div class="frameform">
            <form method="post" action="http://localhost:8080/library/registration">
                <div class="container">
                    <input type="text" placeholder=<fmt:message key="label.name" /> name="name" required>
                    <input type="text" placeholder=<fmt:message key="label.surename" /> name="surename" required>
                    <input type="tel" placeholder=<fmt:message key="label.phone" /> name="phone" required>
                    <textarea type="text" placeholder=<fmt:message key="address" /> name="address" required></textarea>
                    <input type="text" placeholder=<fmt:message key="label.login" /> name="login" required>
                    <input type="password" placeholder=<fmt:message key="label.password" /> name="password" required>
                    <button type="submit" id="log"><fmt:message key="label.registration" /></button>
                </div>
            </form>
        </div>
    </c:if>
<!--Finish  registration-->

<!--Block BOOKSBOX-->
    <c:if test="${pagetype.equals('booksbox')}">
        <c:if test="${result.size()==0}">
            <div class="info">
                <h2>
                    <fmt:message key="booksbox.nosubscribes" />, ${user.name}
                </h2>
            </div>
        </c:if>

        <c:if test="${result.size()!=0}">
            <div class="info">
                <h2>
                    <fmt:message key="booksbox.subscribes" />, ${user.name}
                </h2>
            </div>
            <table class="content">
                <tr>
                    <th>
                        <fmt:message key="label.tittle" />
                    </th>
                    <th>
                        <fmt:message key="label.author" />
                    </th>
                    <th><fmt:message key="datereceipt" /></th>
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
        </c:if>
    </c:if>
<!--Finish block BOOKSBOX-->

<!--Block RULES-->
    <c:if test="${pagetype.equals('rules')}">
        <div class="infobook">
            <p>
                <ex:Docs>${language}</ex:Docs>
            </p>
        </div>
    </c:if>
<!--Finish RULES-->
</body>

</html>
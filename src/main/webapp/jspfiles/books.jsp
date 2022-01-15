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

<!--!Блок books-->
    <c:if test="${pagetype.equals('books')}">
        <div id="inline">
        <table class="content">
            <form method="post" action="http://localhost:8080/library/books">

                    <td><input type="text" placeholder=<fmt:message key="label.tittle" /> name="name"></td>
                    <td><input type="text" placeholder=< fmt:message key="label.author" /> name="author"></td>
                    <td><button type="submit"><fmt:message key="label.searchbooks" /></button></td>
                <c:if test="${user.role=='LIBRARIAN'}">
                          <td>
                              <form  action="http://localhost:8080/library/addbook">
                                  <button type="submit"><fmt:message key="addbook" /></button>
                              </form>
                          </td>
                       </c:if>


                </table>
            </form>
           </table>
        </div>





        <c:if test="${resultlist!=NULL}">
            <table class="content">
                <tr>
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
                <c:forEach items="${resultlist}" var="book">
                    <tr>
                        <td>
                            <c:if test="${user.role=='READER'}">
                                <a href="http://localhost:8080/library/book?id=${book.id}">
                            </c:if>

                             <c:if test="${user.role=='LIBRARIAN'}">
                                <a href="http://localhost:8080/library/deletebook?id=${book.id}">
                             </c:if>

                            <c:out value="${book.name}" />
                            <c:if test="${user.role=='READER'}">
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
    </c:if>
<!--!Конец books-->

<!--!блок login-->
    <c:if test="${pagetype.equals('login')}">
        <div class="frame">
            <form method="post" action="">
                <div class="container">
                    <input type="text" placeholder=<fmt:message key="label.login" /> name="login" required>
                    <input type="password" placeholder=<fmt:message key="label.password" /> name="password" required>
                    <button type="submit" id="log"><fmt:message key="label.signin" /></button>
                    <div id="reg">
                        <a href="http://localhost:8080/library/registration">
                            <fmt:message key="label.registration" />
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </c:if>
<!--!Конец login-->

<!--!Блок language-->
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
<!--!Конец блока language-->

<!--Блок BOOK-->
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
                <h3>год издания: ${book.year}</h3>

                <c:if test="${booktype.equals('view')}">
                    <c:if test="${book.number==1}">
                        <form method="post" action="http://localhost:8080/library/book">
                            <button type="submit" id="log" name="idbook" value="${book.id}">ВЗЯТЬ КНИГУ</button>
                        </form>
                    </c:if>

                    <c:if test="${book.number==0}">
                         <div class="info">
                             <h2>К сожалению данная книга на данный момент недоступна, Вы можете взять ее позже!</h2>
                         </div>
                    </c:if>

                </c:if>

            <c:if test="${booktype.equals('returnview')}">
                <form method="post" action="http://localhost:8080/library/bookreturn">
                    <button type="submit" id="log" name="idoperation" value="${operation.id}">ВЕРНУТЬ КНИГУ</button>
                </form>
            </c:if>
            </div>
        </c:if>
    </c:if>
<!--Конец блока BOOK-->

<!--block registration-->
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
<!--Finish block registration-->

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
        </c:if>
    </c:if>
<!--Finish block BOOKSBOX-->
</body>
</html>
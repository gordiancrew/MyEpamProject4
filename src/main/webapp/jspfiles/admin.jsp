<!DOCTYPE html>

<head>
    <title>Library</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="resources/styles/style.css" rel="stylesheet">
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ page isELIgnored="false" %>
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="messages" />
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
                <a href="http://localhost:8080/library/authorization">
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

<!--START DELETEBOOK-->
  <c:if test="${pagetype.equals('deletebookview')}">
      <div class="infobook">
          <h2>"${book.name}" ${book.author}</h2>
          <h3> <fmt:message key="label.year"/>: ${book.year}</h3>
            <c:if test="${book.number!=0}">
              <form method="post" action="http://localhost:8080/library/deletebook">
                  <button type="submit" id="log" name="idbook" value="${book.id}"> <fmt:message key="deletebook" /></button>
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


<!--Block DELETEREADER-->
<c:if test="${pagetype.equals('deleteuser')}">
     <div class="infobook">
        <h2>
          ${info}
        </h2>
     </div>
  </c:if>

<!--Finish DELETEREADER-->

<!--Block ADDBOOK-->
   <c:if test="${pagetype.equals('addbook')}">
         <div class="frameform">
                    <form method="post" action="http://localhost:8080/library/addbook">
                        <div class="container">
                            <input type="text" maxlength="20"  placeholder=<fmt:message key="label.tittle" /> name="tittle" required oninvalid="this.setCustomValidity('enter tittle!')"  oninput="setCustomValidity('')">
                            <input type="text" maxlength="20" pattern="[а-яА-Яa-zA-Z]{2,10}[ -]{0,1}[а-яА-Яa-zA-Z]{0,10}" placeholder=<fmt:message key="label.author" /> name="author" required oninvalid="this.setCustomValidity('incorrect enter!')"  oninput="setCustomValidity('')">
                            <input type="text"maxlength="4" pattern="[0-9]{4}" placeholder=<fmt:message key="label.year" /> name="year" required oninvalid="this.setCustomValidity('incorrect enter!')"  oninput="setCustomValidity('')">
                            <input type="text"maxlength="1" pattern="[0-9]{1}" placeholder=<fmt:message key="label.number" /> name="number" required oninvalid="this.setCustomValidity('incorrect enter!')"  oninput="setCustomValidity('')">
                            <textarea type="text" maxlength="1400" placeholder=<fmt:message key="label.description" /> name="description" required oninvalid="this.setCustomValidity('incorrect enter!')"  oninput="setCustomValidity('')"></textarea>
                            <button type="submit" id="log"><fmt:message key="addbook" /></button>
                        </div>
                    </form>
                </div>

   </c:if>
      <c:if test="${pagetype.equals('addbookres')}">
         <div class="frameform">
            <h2>${resultaddbook}</h2>
         </div>
      </c:if>
<!--Finish ADDBOOK-->

<!--START USER-->
   <c:if test="${pagetype.equals('user')}">
      <div class="infobook">
       <table class="content">
                  <tr>

                  <c:if test="${!reader.isConfirm()}">
                     <form method="post"  action="http://localhost:8080/library/confirmreader?id=${reader.id}">
                        <th>
                           <div id="conf">
                              <button type="submit"> <fmt:message key="confirm" /></button>
                           </div>
                        </th>
                     </form>
                  </c:if>
                  <c:if test="${reader.role!='LIBRARIAN'}">
                     <form method="post"   action="http://localhost:8080/library/deleteuser?id=${reader.id}">
                        <th>
                           <div id="del">
                               <button type="submit"> <fmt:message key="delete" /></button>
                           </div>
                        </th>
                     </form>
                  </c:if>

                  </tr>
               </table>
         <h2>${reader.name}  ${reader.sureName}</h2>
         <h3>phone: ${reader.phone} </h3>
         <h3>адрес: ${reader.address} </h3>
       <c:if test="${listusersbook==null||listusersbook.size()==0}">
          <div class="info">
             <h3><fmt:message key="book.nobooks" /></h3>
          </div>
       </c:if>
       <c:if test="${listusersbook!=null&&listusersbook.size()!=0}">
       </div>
       <div class="infobook">
       <h2><fmt:message key="books.taked" />:</h2>
         <table class="content">
            <tr>
               <th>
                  <fmt:message key="label.tittle" />
               </th>
               <th>
                  <fmt:message key="label.author" />
               </th>
               <th>
                  <fmt:message key="datereceipt" />
               </th>
            </tr>
            <c:forEach items="${listusersbook}" var="operation">
               <tr>
                  <td>
                     <c:out value="${operation.book.name}" />
                  </td>
                  <td>${operation.book.author}</td>
                  <td>${operation.dateGet}</td>
               </tr>
            </c:forEach>
         </table>
      </c:if>
     </div>
   </c:if>
<!--FINISH USER-->

<!--START CONFIRMREADER-->
    <c:if test="${pagetype.equals('confirmreader')}">
        <div class="info">
            <h3>${info}</h3>
        </div>
    </c:if>
<!--FINISH CONFIRMREADER-->

<!--Block NONCONFIRMREADER-->
   <c:if test="${pagetype.equals('nonconfirmreaders')}">
          <table class="content">
             <tr>
                <th><fmt:message key="label.name" /></th>
                <th><fmt:message key="label.surename" /></th>
                <th><fmt:message key="label.phone" /></th>
                <th><fmt:message key="address" /></th>
             </tr>
             <c:forEach items="${nonconfirmreaders}" var="reader">
                 <a href="http://localhost:8080/library/books">
                     <tr>
                         <td>${reader.name}</td>
                         <td><a href="http://localhost:8080/library/user?id=${reader.id}" >${reader.sureName}</a></td>
                         <td>${reader.phone}</td>
                         <td>${reader.address}</td>
                     </tr>
                 </a>
             </c:forEach>
          </table>
    </c:if>
<!--Finish NONCONFIRMREADER-->

<!--Block CONFIRMREADERS-->
    <c:if test="${pagetype.equals('confirmreaders')}">
        <div id="inline">
            <table class="content">
                <form method="post" action="http://localhost:8080/library/readers">
                    <td><input type="text" placeholder=<fmt:message key="label.name" /> name="namereader"></td>
                    <td><input type="text" placeholder=<fmt:message key="label.surename" /> name="surename"></td>
                    <td><button type="submit"><fmt:message key="label.searchreaders" /></button></td>
                </form>
                <c:if test="${user.role=='LIBRARIAN'}">

                    <form action="http://localhost:8080/library/nonconfirmreaders">
                        <td>
                            <button type="submit"><fmt:message key="readers.unconfirmed" /></button>
                        </td>
                    </form>
                </c:if>


            </table>
        </div>

        <table class="content">
            <tr>
                <th><fmt:message key="label.name" /></th>
                <th><fmt:message key="label.surename" /></th>
                <th><fmt:message key="label.phone" /></th>
                <th><fmt:message key="address" /></th>
            </tr>
            <c:forEach items="${confirmreaders}" var="reader">
                <a href="http://localhost:8080/library/books">
                    <tr>
                        <td>${reader.name}</td>
                        <td><a href="http://localhost:8080/library/user?id=${reader.id}">${reader.sureName}</a></td>
                        <td>${reader.phone}</td>
                        <td>${reader.address}</td>
                    </tr>
                </a>
            </c:forEach>
        </table>
    </c:if>
    <!--Finish CONFIRMREADERS-->

</body>


</html>
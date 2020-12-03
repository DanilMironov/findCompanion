<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Event card</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
  </head>

  <body>

      Title:<c:out value="${eventForm.title}"/><br>
      Description:<c:out value="${eventForm.description}"/><br>
      Datetime:<c:out value="${eventForm.datetime}"/><br>
      Address:<c:out value="${eventForm.address}"/><br>
      Username:<c:out value="${eventForm.username}"/><br>


      <button type="button" onclick="javascript:Unsubscribe()">Unsubscribe from event</button>

      <h4><a href="event${Id}/chat">To chat</a></h4>
      <h4><a href="${contextPath}/menu">To menu</a></h4>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  </body>

  <script type="text/javascript">
        Unsubscribe = function() {
          var http = new XMLHttpRequest();
          var url = document.URL
          var params = "unsubscribe"
          http.open('POST', url, false)
          http.send(params)
        }
  </script>

</html>

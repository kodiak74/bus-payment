<!-- [START base] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
  <head>
    <title> <i class="glyphicon glyphicon-usd"></i>Bus Payment Processing System</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  </head>
  <body>
    <div class="navbar navbar-default">
      <div class="container">
        <div class="navbar-header">
          <div class="navbar-brand">Bus Payment Processing System  s</div>
        </div>
        <ul class="nav navbar-nav">
          <li><a href="/">Files</a></li>
          <li><a href="/results">Processing Results</a></li>
        </ul>
        
      </div>
    </div>
    <c:import url="/${page}.jsp" />
  </body>
</html>
<!-- [END base]-->
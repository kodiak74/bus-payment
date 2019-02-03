<!-- [START base] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
  <head>
    <title> Bus Payment Processing System</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
     <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/knockout/3.4.2/knockout-min.js"></script>
    
  </head>
  <body>
  
  <script>
	
	BAlert = function() {}
	BAlert.warning = function(target,message) {
	            $(target).html('<div class="alert alert-warning"><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>')
	        }
	BAlert.error = function(target,message) {
        $(target).html('<div class="alert alert-danger"><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>')
    }
	BAlert.info = function(target,message) {
        $(target).html('<div class="alert alert-primary"><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>')
    }
	    

	 
	
	</script>    
  
  
    <div class="navbar navbar-default">
      <div class="container">
        <div class="navbar-header">
          <div class="navbar-brand"><i class="glyphicon glyphicon-credit-card"></i> Bus Payment Processing System</div>
        </div>
        <ul class="nav navbar-nav">
          <li><a href="/"><i class="glyphicon glyphicon-inbox"></i> Files</a></li>
          <li><a href="/bp/results"><i class="glyphicon glyphicon-list-alt"></i> Processing Results</a></li>
        </ul>
        
      </div>
    </div>
    <div id="alertPlaceholder"></div>
    
    <c:import url="/${page}.jsp" />
	
	
   
  </body>
</html>
<!-- [END base]-->
<!-- [START list] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
  
  
  <h3>Processing results</h3>
  
  <p class="muted">
  This contains the results of processing uploaded data files. Click on an entry below to view results, or <a href="/">select a data file</a> to process.
  </p>
  
  <br/>
  
  <c:choose>
  <c:when test="${empty results}">
  <p class="text-danger">No results found</p>
  </c:when>
  <c:otherwise>
  
	  <table class="table table-striped">
	  <thead>
	  	<tr>
	  		<th>Data File</th>
	  		<th>Processing date</th>
	  		<th>Actions</th>
	  	</tr>
	  
	  </thead>
	  <tbody>
	  
	  <c:forEach items="${results}" var="result">
		  <!-- <c:out value="${file}"></c:out> -->
		  <tr>
		  	<td><i class="glyphicon glyphicon-file"></i> ${result.file}</td>
		  	<td>${result.created}</td>
		  	<td> <button class="btn btn-default"><i class="glyphicon glyphicon-eye-open"></i> View</button></td>
		  </tr>
		  
	  </c:forEach>
	  </tbody>
	  </table>
   
  </c:otherwise>
  </c:choose>
</div>
<!-- [END list] -->
<!-- [START list] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
  
  <a href="/bp/data/add" class="btn btn-success btn-sm pull-right">
    <i class="glyphicon glyphicon-plus"></i>
    Add file
  </a>
  <h3>Uploaded data files</h3>
  
  <br/>
  
  <c:choose>
  <c:when test="${empty datafiles}">
  <p class="text-danger">No files found</p>
  </c:when>
  <c:otherwise>
  
	  <table class="table table-striped">
	  <thead>
	  	<tr>
	  		<th>File</th>
	  		<th>Size (B)</th>
	  		<th>Created</th>
	  		<th>Actions</th>
	  	</tr>
	  
	  </thead>
	  <tbody>
	  
	  <c:forEach items="${datafiles}" var="file">
		  <!-- <c:out value="${file}"></c:out> -->
		  <tr>
		  	<td><i class="glyphicon glyphicon-file"></i> ${file.name}</td>
		  	<td>${file.size}</td>
		  	<td>${file.created}</td>
		  	<td><button class="btn btn-danger"><i class="glyphicon glyphicon-trash"></i> Delete </button>&nbsp;<button class="btn btn-primary"><i class="glyphicon glyphicon-cog"></i> Process</button></td>
		  </tr>
		  
	  </c:forEach>
	  </tbody>
	  </table>
   
  </c:otherwise>
  </c:choose>
</div>
<!-- [END list] -->
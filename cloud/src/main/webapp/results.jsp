<!-- [START list] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
  
  
  <h3>Trip Processing results</h3>
  
  <p class="muted">
  This contains the results of processing uploaded data files. 
  </p>
  
  <br/>
  
  <c:choose>
  <c:when test="${empty batchID}">
  <p class="text-success">Showing all processing batches.</p>
  </c:when>
  <c:otherwise>
  <div><span class="text-warning">Showing batch: ${batchID} </span> <a href="/bp/results" class="btn btn-default"><i class="glyphicon glyphicon-remove"></i> Clear Filter</a></div>
  </c:otherwise>
  </c:choose>
  <hr/>
  
  <c:choose>
  <c:when test="${empty results}">
  <p class="text-danger">No results found</p>
  </c:when>
  <c:otherwise>
  
	  <table class="table table-striped">
	  <thead>
	  	<tr>
	  		<th>Batch</th>
	  		<th>Started</th>
	  		<th>Finished</th>
	  		<th>Duration</th>
	  		<th>FromStop</th>
	  		<th>ToStop</th>
	  		<th>Charge </th>
	  		<th>Company</th>
	  		<th>Bus</th>
	  		<th>PAN</th>
	  		<th>Status</th>
	  	</tr>
	  
	  </thead>
	  <tbody class="small">
	  
	  <c:forEach items="${results}" var="result">
		  <!-- <c:out value="${file}"></c:out> -->
		  <tr>
		  	<td><a href="/bp/results?batch=${result.batchID}">${result.batchID}</a></td>
		  	<td>${result.started}</td>
		  	<td>${result.finished}</td>
		  	<td>${result.duration}</td>
		  	<td>${result.fromStop}</td>
		  	<td>${result.toStop}</td>
		  	<td>${result.chargeAmount}</td>
		  	<td>${result.companyID}</td>
		  	<td>${result.busID}</td>
		  	<td>${result.pan}</td>
		  	<td>${result.status}</td>
		   
		  	 
		  </tr>
		  
	  </c:forEach>
	  </tbody>
	  </table>
   
  </c:otherwise>
  </c:choose>
</div>
<!-- [END list] -->
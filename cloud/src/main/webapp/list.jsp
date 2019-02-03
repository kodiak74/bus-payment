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
		  	<td><button class="btn btn-danger" data-bind="click: function(data, event) { removeFile('${file.name}') }"><i class="glyphicon glyphicon-trash"></i> Delete </button>&nbsp;
		  	    <button class="btn btn-primary"  data-bind="click: function(data, event) { processFile('${file.name}') }"><i class="glyphicon glyphicon-cog"></i> Process</button></td>
		  </tr>
		  
	  </c:forEach>
	  </tbody>
	  </table>
   
  </c:otherwise>
  </c:choose>
</div>
<!-- [END list] -->


<div id="resultsModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h5 class="modal-title">Processing results</h5>
        
      </div>
      <div class="modal-body">
        <h4>Processing summary</h4>
        <div class="container">
        	<div class="row"><div class="col-md-1">Datafile:</div><div class="col-md-4" data-bind="text: datafile"></div></div>
        	<div class="row"><div class="col-md-1">Message:</div><div class="col-md-4" data-bind="text: message"></div></div>
        	<div class="row"><div class="col-md-1">Trips:</div><div class="col-md-4" data-bind="text: trips"></div></div>
        	 
        </div>
        
        
      </div>
      <div class="modal-footer">
        
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>



<script>



 

function AppViewModel() {
	var self = this;
	self.datafile = ko.observable();
	self.message = ko.observable();
	self.trips = ko.observable();
	
	
	self.processFile = function(fname){
		$.getJSON(  "/bp/api/process", {'file':fname})
		.done(function(data) {
			self.datafile(data.datafile);
			self.message(data.message);
			self.trips(data.recordCount);
			$('#resultsModal').modal("show");
			
		})
		.fail(function(jqxhr, textStatus, error ) {
			var err = "Error: " + jqxhr.status + " - " + jqxhr.statusText;
			self.message(err);
		});
	}
	
	self.removeFile = function(fname){
		console.log(fname);
		$.getJSON(  "/bp/api/delete", {'file':fname})
		.done(function(data) {
			BSAlert.info("#alertPalceholder", "File deleted");
		})
		.fail(function(jqxhr, textStatus, error ) {
			var err = "Error: " + jqxhr.status + " - " + jqxhr.statusText;
			BSAlert.error("#alertPalceholder", err);
		});
	}
	
}

// Activates knockout.js
ko.applyBindings(new AppViewModel());


</script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container">
  <h3>
    Upload Data File
  </h3>

  <form method="POST" action="${destination}" enctype="multipart/form-data">

    
    <div class="form-group  ">
      <label for="image">Data file</label>
      <input type="file" name="file" id="file" class="form-control" />
    </div>
 

    <button type="submit" class="btn btn-success">Save</button>
    <a class="btn btn-default" href="/">Cancel</a>
  </form>
</div>
<!-- [END form] -->
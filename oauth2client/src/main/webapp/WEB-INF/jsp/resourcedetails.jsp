<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
  <title>Resource Details PAGE</title>
  </head>
  
  <body>
    <h2> THIS IS Resource details PAGE </h2>
    
    <table>
    	<c:forEach var="resourcedetail" items="${resourcedetails}">
	    	<tr><td></td>    <td></td>  </tr>
	    	<c:forEach var="resource" items="${resourcedetail}">
		    	<tr><td>"${resource.key}"</td>    <td>"${resource.value}"</td>  </tr>
	    	</c:forEach>    
    	</c:forEach>    
    
    </table>
  </body>
</html>

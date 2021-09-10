<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
  <title>User Details PAGE</title>
  </head>
  
  <body>
    <h2> THIS IS User details PAGE </h2>
    
    <table>
    	<tr><td>principal name</td>    <td>${client.getPrincipalName()}</td>  </tr>
    	<tr><td>Access Token value</td>    <td>${client.getAccessToken().getTokenValue()}</td>  </tr>
    	<tr><td>Access Token scopes</td>    <td>${client.getAccessToken().getTokenValue()}</td>  </tr>
    	<tr><td>Access Token value</td>    <td>${client.getAccessToken().getScopes()}</td>  </tr>
    	
    	<c:forEach var="userAttribute" items="${userAttributes}">
	    	<tr><td>"${userAttribute.key}"</td>    <td>"${userAttribute.value}"</td>  </tr>
    	</c:forEach>    
    
    </table>
  </body>
</html>

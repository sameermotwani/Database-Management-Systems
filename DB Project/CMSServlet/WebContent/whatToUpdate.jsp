  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UPDATE</title>
</head>
<body>
Length:${sessionScope.length }
<form action="ActualUpdateDriver" method="post">

<table border="1">
<tr>
<th>Driver ID</th>
<th>First Name</th>
<th>Last Name</th>
</tr>

	<c:forEach var="i" begin="0" end="${sessionScope.length}" step="1">
	<tr>
	 <td>
                  ${sessionScope.List[i].id}
     </td>
     <td>
                  ${sessionScope.List[i].fName}
     </td>
     <td>
                  ${sessionScope.List[i].lName}
     </td>
     </tr>
	</c:forEach>




</table>

ENTER THE ID IN THE LIST YOU WANT TO UPDATE:<input type="text" name="DRIVER ID">
<p><input type="submit" value="Update" name="Update"></p>

</form>


</body>
</html>
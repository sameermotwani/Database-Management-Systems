  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ride details</title>
</head>
<body bgcolor="#EFFBF5">
Length:${sessionScope.length }
<form action="ActualUpdateDriver" method="post">

<table border="1">
<tr>
<th>Ride ID</th>
<th>Start time</th>
<th>End Time</th>
</tr>

	<c:forEach var="i" begin="0" end="${sessionScope.length}" step="1">
	<tr>
	 <td>
                  ${sessionScope.List[i].rid}
     </td>
      <td>
                  ${sessionScope.List[i].start}
     </td>
      <td>
                  ${sessionScope.List[i].end}
     </td>
     </tr>
	</c:forEach>




</table>

</form>


</body>
</html>
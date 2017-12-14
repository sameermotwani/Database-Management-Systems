<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Driver</title>
</head>
<body bgcolor="#EFFBF5">
<html>

<table border="1">
<tr>
<th>First Name</th>
<th>Last Name</th>
<th>SSN</th>
<th>NUID</th>
<th>contact_phone</th>
<th>home_phone</th>
<th>streetName</th>
<th>city</th>
<th>State</th>
<th>apartment_no</th>
</tr>

<tr>
<th>${sessionScope.fName}</th>
<th>${sessionScope.lName}</th>
<th>${sessionScope.ssn}</th>
<th>${sessionScope.nuid}</th>
<th>${sessionScope.contact_phone}</th>
<th>${sessionScope.home_phone}</th>
<th>${sessionScope.streetName}</th>
<th>${sessionScope.city}</th>
<th>${sessionScope.State}</th>
<th>${sessionScope.apartment_no}</th>
</tr>

</table>

<br>
<br>

<table border="1" bordercolor="#3104B4">
<tr>
<td>
<form action="UpdateFinal" method="post">
  First Name:
  <input type="text" name="First Name">
  <br><br>
   Last Name:
  <input type="text" name="Last Name">
  <br><br>
  SSN:
  <input type="text" name="SSN">
  <br><br>
  NUID:
  <input type="text" name="NUID">
  <br><br>
  Contact Phone:
  <input type="text" name="Contact Phone">
  <br><br>
  Home Phone:
  <input type="text" name="Home Phone">
  <br><br>

<table border="1" bordercolor="#3104B4">
<tr>
<td>
  Address:
   <br><br>

  Street:
  <input type="text" name=Street>
   <br><br>
   City:
  <input type="text" name="City">
   <br><br>
   State:
  <input type="text" name="State">
   <br><br>
   Apartment Number:
  <input type="text" name="Apartment Number">
  <br><br>
  </td>
</tr>
</table>
 <input type="Submit" value="Update">
</form>
</td>
</tr>
</table>
</html>

</body>
</html>
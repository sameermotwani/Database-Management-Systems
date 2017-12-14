<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NUPD</title>
</head>
<body>
<div style='position:absolute;z-index:0;left:0;top:0;width:100%;height:100%'>
  <img src='NU_RedEye.jpg' style='width:100%;height:100%' alt='[]' />
</div>
<style type="text/css">
html, body{width: 100%; height: 100%; padding: 0; margin: 0}
div{position: absolute; padding: 1em; border: 1px solid #000}
#nw{font-style: normal; color: RED; top: 0; left: 0; right: 50%; bottom: 50%}
#ne{font-style: normal; color: RED; top: 0; left: 50%; right: 0; bottom: 50%}
#sw{font-style: normal; color: RED; top: 50%; left: 0; right: 50%; bottom: 0}
#se{font-style: normal; color: RED; top: 50%; left: 50%; right: 0; bottom: 0}
</style>

<div id="nw">DRIVER 
<hr>
&nbsp;
<br>

<form action="addNew" method="post">
<a href="/CMSServlet/addNew.jsp"><font color="RED">Add new account</font></a>
</form>

<br><br><br>
<form action="updateDriver" method="post">
<a href="/CMSServlet/gettingDriverDetails.jsp">UpdateExisting</a>
</form>
<br><br><br>
<form action="monitorDriver" method="post">
<a href="/CMSServlet/monitorDriverDetails.jsp"> Monitor driver details</a>
</form>
</div>
<div id="ne">STUDENT
<hr>
&nbsp;
<br>
<form action="fetchDefaulters" method="post">
<a href="/CMSServlet/getAllDefaulters.jsp">Fetch defaulters</a>
</form>
<br><br><br>
<form action="clearDefaulters" method="post">
<a href="/CMSServlet/getAllDefaultersClear.jsp">Clear defaulters</a>
</div>
<div id="sw">VEHICLE
<hr>
&nbsp;
<br>
<form action="addNewVehicle" method="post">
<a href="/CMSServlet/addNewVehicle.jsp">Add new vehicle</a>
</form>
<br><br><br>
<form action="monitorVehicle" method="post">
<a href="/CMSServlet/monitorVehDetails.jsp"> Monitor vehicle details</a>
</form>
</div>
<div id="se">RIDE 
<hr>
&nbsp;
<br>
<form action="monitorRide" method="post">
<a href="/CMSServlet/monitorRideDetails.jsp">Start and End time of the ride</a>
</form>

</div>

</body>
</html>
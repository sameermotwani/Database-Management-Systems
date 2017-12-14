<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="JavaScript" src="ts_picker.js">

//Script by Denis Gritcyuk: tspicker@yahoo.com
//Submitted to JavaScript Kit (http://javascriptkit.com)
//Visit http://javascriptkit.com for this script

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>select date range</title>
</head>
<body bgcolor="#EFFBF5">

<form name="monitorDriver" action="monitorRide" method="post">
Please select the date: 
<br>
<input type="Text" name="timestamp" value="">
<a href="javascript:show_calendar('document.monitorDriver.timestamp', document.monitorDriver.timestamp.value);"><img src="cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>

<br>
        	
			<input type="submit" name="GET" value="submit"/>
</form>
</body>
</html>
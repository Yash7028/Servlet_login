<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action ="regForm" method="post">
Name : <input  type = "text" name = "name1" /><br/><br/>
Email : <input  type = "text" name = "email1" /><br/><br/>
Password : <input type="password" name = "pass1"><br/><br/>
Gender : <input type="radio" name="gender1" value="male" > Male <input type="radio" name="gender1" value="female"/\> Female<br/> <br/>
City : <select name="city1">
           <option>Select city</option>
           <option> Delhi </option>
           <option> Mumbai </option>
           <option> Pune </option>
           <option> Banglore </option>
           <option> Nashik </option>
       </select> <br/> <br/>
       <input type="submit" value ="Register" />
 </form>
</body>
</html>
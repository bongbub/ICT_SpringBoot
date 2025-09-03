<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Product</title>
</head>
<body>


	<div align="center">
		<h1> Create New Product </h1>
		
		<form action="/save" method="post">
			<table border="1" cellpadding="10">
<!--사용안함 				<tr>
					<th> Product ID : </th>		id= max+1로 insert 구현할 것이기 때문
					<td> <input type="text" name="id"></td>
				</tr> -->
				
				<tr>
					<th>Product Name : </th>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<th>Product Brand : </th>
					<td><input type="text" name="brand"></td>
				</tr>
				<tr>
					<th>Product MadeIn : </th>
					<td><input type="text" name="madein"></td>
				</tr>
				<tr>
					<th>Product Price : </th>
					<td><input type="text" name="price"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
</head>
<body>


	<div align="center">
		<h1> Detail Product </h1>
		
		<form action="/update" method="post">
			<table border="1" cellpadding="10">
 				<tr>
					<th> Product ID : </th>		
					<td><input type="hidden" value="${dto.id}" name="id">${dto.id}</td>
				</tr>
				<tr>
					<th>Product Name : </th>
					<td><input type="text" name="name" value="${dto.name}"></td>
				</tr>
				<tr>
					<th>Product Brand : </th>
					<td><input type="text" name="brand" value="${dto.brand}"></td>
				</tr>
				<tr>
					<th>Product MadeIn : </th>
					<td><input type="text" name="madein" value="${dto.madein}"></td>
				</tr>
				<tr>
					<th>Product Price : </th>
					<td><input type="text" name="price" value="${dto.price}"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
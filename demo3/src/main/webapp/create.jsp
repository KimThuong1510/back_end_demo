<%--
  Created by IntelliJ IDEA.
  User: THUONG
  Date: 9/5/2024
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style>
        body{
            margin-top: 100px;
        }
        table{
            margin: auto;
        }
    </style>
</head>
<body>
<h1 style="text-align: center; color: #F08080">THÊM MỚI HỌC VIÊN</h1>
<form action="?action=create" method="post">
    <div style="text-align: center; margin-right: 550px; margin-left: 550px">
        <fieldset id="name" style="text-align: center; background-color: #F5F5DC">
            <legend><h3 style="color: #F08080" >TÊN SINH VIÊN</h3></legend>
            <input style="width: 250px" type="text" id ="yourname" name="name" required>
        </fieldset>
        <fieldset id="Size" style="text-align: center;background-color: #F5F5DC" >
            <legend><h3 style="color: #F08080">GIỚI TÍNH</h3></legend>
            <input type= "radio" value="1" name="gender">Nam
            <input style="margin-left: 30px" type="radio" value="0" name="gender">Nữ
        </fieldset>
        <fieldset id="Point" style="text-align: center;background-color: #F5F5DC">
            <legend><h3 style="color: #F08080">ĐIỂM HỌC PHẦN</h3></legend>
            <input style="width: 250px" type="number" name="point" required>
        </fieldset>

        <fieldset id="id" style="text-align: center;background-color: #F5F5DC">
            <legend><h3 style="color: #F08080 ">MÃ LỚP</h3></legend>
            <input style="width: 250px" type="text" name="class_id" required>
        </fieldset>

        <h3 style="text-align: center;"><button style="width: 100px; background-color: #F5F5DC">Thêm mới</button></h3>
    </div>
</form>
</body>
</html>


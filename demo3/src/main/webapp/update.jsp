<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: THUONG--%>
<%--  Date: 9/4/2024--%>
<%--  Time: 11:48 PM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
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
<h1 style="text-align: center; color: #F08080">CHỈNH SỬA THÔNG TIN</h1>
<form action="?action=edit" method="post">
    <div style="text-align: center; margin-right: auto; margin-left: auto; max-width: 550px;">
        <fieldset id="idHS" style="text-align: center; background-color: #F5F5DC">
            <legend><h3 style="color: #F08080">ID SINH VIÊN</h3></legend>
            <input value="${st.id}" type="text" name="id" readonly>
        </fieldset>
        <fieldset id="name" style="text-align: center; background-color: #F5F5DC">
            <legend><h3 style="color: #F08080">TÊN SINH VIÊN</h3></legend>
            <input value="${st.name}" type="text" name="name" required>
        </fieldset>
        <fieldset id="Size" style="text-align: center; background-color: #F5F5DC">
            <legend><h3 style="color: #F08080">GIỚI TÍNH</h3></legend>
            <input type="radio" name="gender" value="1" ${st.gender == 1 ? "checked" : ""}>Nam
            <input type="radio" name="gender" value="0" ${st.gender == 0 ? "checked" : ""}>Nữ
        </fieldset>
        <fieldset id="Point" style="text-align: center; background-color: #F5F5DC">
            <legend><h3 style="color: #F08080">ĐIỂM HỌC PHẦN</h3></legend>
            <input value="${st.point}" type="number" name="point" required>
        </fieldset>
        <fieldset id="idLop" style="text-align: center; background-color: #F5F5DC">
            <legend><h3 style="color: #F08080">MÃ LỚP</h3></legend>
            <input value="${st.clazz.classInt}" type="text" name="class_id" required>
        </fieldset>
        <h3 style="text-align: center;">
            <button type="submit" style="width: 100px; background-color: #F5F5DC">Cập nhật</button>
        </h3>
    </div>
</form>
</body>
</html>



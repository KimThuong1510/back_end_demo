<%--
  Created by IntelliJ IDEA.
  User: THUONG
  Date: 9/4/2024
  Time: 11:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title >Danh sách lớp</title>
    <style>
        table{
            margin: auto;
            width: 80%;
        }

        tbody tr:nth-child(odd) {
            background-color: 	#FFE4E1;
        }

        .header-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-right: 250px;
        }

        .logo-container {
            flex-basis: 30%;
            max-width: 200px;
            margin-left: 40px;

        }

        .title-container {
            flex-basis: 70%;
            font-size: 30px;
            font-weight: bold;
            margin-left: 100px;
            text-align: center;
        }

    </style>
</head>
<body>
<div class="header-container">
    <div class ="title-container">
        <h1 style="color:rgba(160, 176, 192, 0.968);", size = 500px; style="font-family: Arial, Helvetica, sans-serif;">TRƯỜNG ĐẠI HỌC KINH TẾ ĐÀ NẴNG</h1>
    </div>
    <div class = "logo-container">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUY1CXmJNeisjadNcFrvwNL6UAFU3p9QrHiLUhTaxnbw&s " alt="DUE Logo">
    </div>
</div>
<h1 style="text-align: center; color: #F08080">DANH SÁCH SINH VIÊN KHÓA 50K</h1>
<a href="?action=create" style="margin-left: 150px;"><button style="background-color: #FFE4E1">Thêm mới sinh viên</button></a>
<br>

    <table border="1px" >
        <thead class="header" style="text-align: center; background-color: 	#FFC0CB" >
        <tr>
            <td>ID</td>
            <td>TÊN</td>
            <td>GIỚI TÍNH</td>
            <td>ĐIỂM</td>
            <td>XẾP LOẠI</td>
            <td>LỚP</td>
            <td>UPDATE</td>
            <td>DELETE</td>
        </tr>
        </thead>

        <tbody style="text-align: center; padding: 100px" >
        <c:forEach items="${studentList}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>
                    <c:if test="${s.gender == 1}">
                        Nam
                    </c:if>
                    <c:if test="${s.gender == 0}">
                        Nữ
                    </c:if>
                </td>
                <td>${s.point}</td>
                <td>
                    <c:choose>
                        <c:when test="${s.point > 8.9}">
                            Loại giỏi
                        </c:when>
                        <c:when test="${s.point > 7.9}">
                            Loại Khá
                        </c:when>
                        <c:when test="${s.point > 6.9}">
                            Loại Trung Bình
                        </c:when>
                        <c:when test="${s.point <6.9}">
                            Loại Yếu
                        </c:when>
                    </c:choose>
                </td>
                <td>${s.clazz.class_name}</td>
                <td>
                    <a href="?action=edit&sid=${s.id}"><button style="background-color: #F5F5DC">Chỉnh sửa</button></a>
                </td>
                <td>
                    <a href="#" onclick="showMess(${s.id})"><button style="background-color:#F5F5DC">Xóa</button></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

<script>
    function showMess(id){
        let option = confirm("Are you sure ??? ");
        if (option === true){
            window.location.href = "?action=delete&sid=" + id;
        }
    }
</script>
</body>
</html>

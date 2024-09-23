<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh Sửa Thông Tin</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            margin-top: 100px;
        }
        .form-container {
            max-width: 600px;
            margin: auto;
        }
        .custom-fieldset {
            background-color: #F5F5DC;
        }
        .custom-title {
            color: #F08080;
        }
        .text-center {
            color: #2d5a5a;
        }
    </style>
</head>
<body>

<div class="container form-container">
    <h1 class="text-center custom-title">CHỈNH SỬA THÔNG TIN</h1>
    <br>
    <form action="student-servlet?action=edit" method="post">
        <fieldset class="custom-fieldset p-4 mb-4">
            <legend><h3 class="custom-title">ID SINH VIÊN</h3></legend>
            <input value="${st.id}" type="text" name="id" readonly class="form-control">
        </fieldset>

        <fieldset class="custom-fieldset p-4 mb-4">
            <legend><h3 class="custom-title">TÊN SINH VIÊN</h3></legend>
            <input style="width: 100%;" value="${st.name}" type="text" name="name" class="form-control" required>
        </fieldset>

        <fieldset class="custom-fieldset p-4 mb-4">
            <legend><h3 class="custom-title">EMAIL</h3></legend>
            <input style="width: 100%;" value="${st.email}" type="email" name="email" class="form-control" required>
        </fieldset>

        <fieldset class="custom-fieldset p-4 mb-4">
            <legend><h3 class="custom-title">GIỚI TÍNH</h3></legend>
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" name="gender" value="1" ${st.gender == 1 ? "checked" : ""} required>
                <label class="form-check-label">Nam</label>
            </div>
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" name="gender" value="0" ${st.gender == 0 ? "checked" : ""} required>
                <label class="form-check-label">Nữ</label>
            </div>
        </fieldset>

        <fieldset class="custom-fieldset p-4 mb-4">
            <legend><h3 class="custom-title">ĐIỂM KỲ 1</h3></legend>
            <input type="number" value="${st.point}" name="point" step="0.01" class="form-control" placeholder="Nhập điểm đầu vào" required>
        </fieldset>

        <fieldset class="custom-fieldset p-4 mb-4">
            <legend><h3 class="custom-title">TÊN LỚP</h3></legend>
            <select class="form-control" name="class_id" id="class_id" required>
                <c:forEach var="clazz" items="${listU}">
                    <option value="${clazz.class_id}" ${clazz.class_id == st.clazz.class_id ? 'selected' : ''}>${clazz.class_name}</option>
                </c:forEach>
            </select>
        </fieldset>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">Cập nhật</button>
        </div>
    </form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
    <% if (request.getAttribute("errorMessage") != null) { %>
    let emailErrorModal = new bootstrap.Modal(document.getElementById('emailErrorModal'));
    emailErrorModal.show();
    <% } %>
</script>
</body>
</html>
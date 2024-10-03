<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Mới Học Viên</title>
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
            padding: 20px;
        }
        .custom-title {
            color: #F08080;
        }
        .text-center{
            color: #2d5a5a;
        }
    </style>
</head>
<body>

<div class="container form-container">
    <h1 class="text-center custom-title">THÊM MỚI HỌC VIÊN</h1>
    <br>
    <form action="?action=create" method="post">
        <fieldset class="custom-fieldset mb-4">
            <legend><h3 class="custom-title">TÊN SINH VIÊN</h3></legend>
            <input type="text" id="yourname" name="name" class="form-control"  placeholder="Nhập tên sinh viên" value="${st.name}"required>
        </fieldset>

        <fieldset class="custom-fieldset mb-4">
            <legend><h3 class="custom-title">EMAIL</h3></legend>
            <input type="email" id="youremail" name="email" class="form-control" placeholder="Nhập email sinh viên" value="${st.email}" required>
        </fieldset>

        <fieldset class="custom-fieldset mb-4">
            <legend><h3 class="custom-title">GIỚI TÍNH</h3></legend>
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" name="gender" value="1" id="male" ${st.gender == '1' ? 'checked' : ''} required>
                <label class="form-check-label" for="male">Nam</label>
            </div>
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" name="gender" value="0" id="female" ${st.gender == '0' ? 'checked' : ''} required>
                <label class="form-check-label" for="female">Nữ</label>
            </div>
        </fieldset>

        <fieldset class="custom-fieldset mb-4">
            <legend><h3 class="custom-title">ĐIỂM KỲ 1</h3></legend>
            <input type="number" name="point" step="0.01" class="form-control" placeholder="Nhập điểm học kỳ" value="${st.point}" required>
        </fieldset>

        <fieldset class="custom-fieldset mb-4">
            <legend><h3 class="custom-title">TÊN LỚP</h3></legend>
            <select name="class_id" required class="form-control">
                <option value="" disabled selected>Chọn lớp</option>
                <c:forEach items="${listC}" var="c">

                    <option value="${c.class_id}" ${c.class_id == st.clazz.class_id ? 'selected' : ''}>${c.class_name}</option>
                </c:forEach>

            </select>
        </fieldset>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">Thêm mới</button>
        </div>
    </form>
</div>

<%-- Modal for email validation error --%>
<div class="modal fade" id="emailErrorModal" tabindex="-1" aria-labelledby="emailErrorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="emailErrorModalLabel">Lỗi</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>
            </div>
            <div class="modal-body">
                ${errorMessage}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
    <% if (request.getAttribute("errorMessage") != null) { %>
    let emailErrorModal = new bootstrap.Modal(document.getElementById('emailErrorModal'));
    emailErrorModal.show();
    <% } %>
</script>
</body>
</html>
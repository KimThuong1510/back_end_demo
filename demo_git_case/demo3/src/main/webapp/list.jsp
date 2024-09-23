<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách học viên</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        .custom-thead {
            background-color: #F08080;
            color: #F5F5DC;
        }
        .d-flex{
            margin-left: auto;
            width: 250px;
        }
        table td, table th {
            text-align: center;
        }
        table tbody tr:hover {
            background-color: #cf9191;
        }
        .btn-custom {
            transition: background-color 0.3s, transform 0.2s;
            background-color: #ff8570;
            color: #dfd3e1;
            font-size: 15px;
        }
        .btn-danger {
            background-color: #ff8570;
            color: #dfd3e1;
            font-size: 15px;
        }
        .btn-custom:hover, .btn-danger:hover, .btn-success:hover {
            background-color: #dfd3e1;
            transform: scale(1.05);
        }
        .btn-success {
            background-color: #da6872;
        }
        .text-left a {
            transition: color 0.3s, text-decoration 0.2s;
        }
        .text-left a:hover {
            color: #f6a984;
            text-decoration: underline;
        }
    </style>

</head>
<body>
<div class="container mt-4" >
    <div class="row mb-4 align-items-center">
        <div class="col-md-8">
            <h1 style="color:rgba(160, 176, 192, 0.968); font-family: Arial, Helvetica, sans-serif;">TRƯỜNG ĐẠI HỌC KINH TẾ ĐÀ NẴNG</h1>
        </div>
        <div class="col-md-4 text-center">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUY1CXmJNeisjadNcFrvwNL6UAFU3p9QrHiLUhTaxnbw&s" alt="DUE Logo">
        </div>
    </div>
    <h1 class="text-center text-danger">DANH SÁCH KẾT QUẢ THI KỲ 1</h1>

    <form class="d-flex" action="student-servlet" method="get">
        <div class="input-group">
            <input type="text" class="form-control" name="search" placeholder="Tìm kiếm">
            <button type="submit" class="btn btn-secondary"><i class="bi-search"></i></button>
        </div>
    </form>
    <br>
    <a href="?action=create" class="btn btn-success mb-4">Thêm mới sinh viên</a>
    <table  id="mainTable" class="table table-striped table-bordered">
        <thead>
        <tr style=" background-color: #F08080;color: #F5F5DC;">
            <th>STT</th>
            <th>TÊN SINH VIÊN</th>
            <th>EMAIL</th>
            <th>GIỚI TÍNH</th>
            <th>ĐIỂM</th>
            <th>XẾP LOẠI</th>
            <th>LỚP</th>
            <th>CHỈNH SỬA</th>
            <th>XÓA</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${studentList}" var="s" varStatus="c" >
            <tr>
                <td>${c.index + 1}</td>
                <td>${s.name}</td>
                <td>${s.email}</td>
                <td><c:choose>
                    <c:when test="${s.gender == 1}">Nam</c:when>
                    <c:otherwise>Nữ</c:otherwise>
                </c:choose></td>
                <td>${s.point}</td>
                <td>
                    <c:choose>
                        <c:when test="${s.point > 8.9}">Loại Giỏi</c:when>
                        <c:when test="${s.point > 7.9}">Loại Khá</c:when>
                        <c:when test="${s.point > 6.9}">Loại Trung Bình</c:when>
                        <c:otherwise>Loại Yếu</c:otherwise>
                    </c:choose>
                </td>
                <td>${s.clazz.class_name}</td>
                <td>
                    <a href="?action=edit&sid=${s.id}" class="btn btn-custom btn-sm">Chỉnh sửa</a>
                </td>
                <td>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" data-id="${s.id}" data-name="${s.name}">Xóa</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn muốn xóa học viên <span id="studentName"></span> không?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <a id="confirmDelete" href="#" class="btn btn-danger">Xóa</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>

<script>
    $(document).ready(function(){
        $('#mainTable').DataTable({
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 5,
            "columnDefs": [
                {"orderable": false, "targets": 7}
            ]
        });


        $('#deleteModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var id = button.data('id');
            var studentName = button.data('name');
            var modal = $(this);
            var deleteUrl = "?action=delete&sid=" + id;
            modal.find('#studentName').text(studentName);
            modal.find('#confirmDelete').attr('href', deleteUrl);
        });
    });

</script>
</body>
</html>

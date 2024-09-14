<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Automatic Slideshow</title>
    <style>
        .slideshow-container {
            position: relative;
            max-width: 1000px;
            margin: auto;
        }

        .mySlides {
            display: none;
        }

        .mySlides img {
            width: 100%;
            height: auto;
        }
    </style>
</head>
<body>

<div class="slideshow-container">
    <div class="mySlides">
        <img src="https://xdcs.cdnchinhphu.vn/thumb_w/640/446259493575335936/2023/7/23/dhktdn5-16901005273691386582160.png " style="width: 1000px; height: 500px;">
    </div>
    <div class="mySlides">
        <img src="https://scontent-hkg4-2.xx.fbcdn.net/v/t39.30808-6/456187658_887551446739792_7944119534803996706_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=f727a1&_nc_ohc=PUOxRMUah-sQ7kNvgFjg0Eb&_nc_ht=scontent-hkg4-2.xx&_nc_gid=AOdlitvv_zktx_f9-Xu-QFC&oh=00_AYC1erDnzA02810dPTUIKplJktI7tDiZlrCOWs-0LCzIaQ&oe=66E8E4A4" style="width: 1000px ; height: 500px;">
    </div>
    <div class="mySlides">
        <img src="https://scontent-hkg1-2.xx.fbcdn.net/v/t39.30808-6/307092550_2685997954867557_3568308505682357177_n.png?stp=dst-jpg&_nc_cat=102&ccb=1-7&_nc_sid=86c6b0&_nc_ohc=I2M0cGiGAvUQ7kNvgHOsXAn&_nc_ht=scontent-hkg1-2.xx&oh=00_AYA9yAY9i83wlF61Jk8jXXAvE9yBVZVlFDnHiyYnlepHNQ&oe=66E8F972" style="width: 1000px ; height: 500px;">
    </div>
</div>

<script>
    var slideIndex = 0;
    showSlides();

    function showSlides() {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slideIndex++;
        if (slideIndex > slides.length) {slideIndex = 1}
        slides[slideIndex-1].style.display = "block";
        setTimeout(showSlides, 2000); // Chuyển đổi sau mỗi 2 giây
    }
</script>
<br>
<div style="text-align: center; font-size: 50px">
    <a href="student-servlet">DANH SÁCH SINH VIÊN NHẬP HỌC 2024</a>
</div>

</body>
</html>

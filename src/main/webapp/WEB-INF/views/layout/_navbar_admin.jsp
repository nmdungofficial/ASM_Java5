<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="/static/css/main.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary p-0">

    <div class="navbar-collapse collapse d-flex justify-content-center">
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="/admin">Quản lý loại sản phẩm</a>
            <a class="nav-item nav-link" href="/admin/product">Quản lý sản phẩm</a>
            <a class="nav-item nav-link" href="/admin/account">Quản lý tài khoản</a>
        </div>
    </div>

    <ul class="navbar-nav ml-auto" style="margin-right:30px">
        <li class="nav-item no-arrow">
            <a class="nav-link dropdown-toggle p-0" data-bs-toggle="dropdown" href="#">
                <img alt="" class="rounded-circle" style="width:60px"
                     src="/static/images/user.svg"/>
            </a>
            <div class="dropdown-menu dropdown-menu-end" >
                <a class="dropdown-item" href="/logout">
                    <c:choose>
                        <c:when test="${not empty sessionScope.account}">
                            <p>Đăng Xuất</p>
                        </c:when>
                        <c:otherwise>
                            <p>Đăng Nhập</p>
                        </c:otherwise>
                    </c:choose>
                </a>
            </div>
        </li>
    </ul>
</nav>
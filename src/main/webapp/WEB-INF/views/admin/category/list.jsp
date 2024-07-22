<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="admin">
    <label class="form-label">Tìm Kiếm</label>
    <input type="text" name="keyword" value="${param.keyword}" class="form-control" placeholder="Từ khóa"><br>
    <button type="submit" class="btn btn-secondary">Tìm</button>
</form>
<div class="container" style="margin-top: 100px">
    <table class="table table-hover">
        <div class="alert alert-secondary" style="display: ${note==null?"none":"block"}">${note}</div>
        <thead>
        <h2>Danh Mục Điện Thoại</h2>
        <a href="/admin/category/create" class="btn btn-success">Thêm</a>
        <tr>
            <th>ID</th>
            <th>Danh Mục</th>
            <th>Thao Tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page}" var="category">
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td>
                    <a href="admin/category/update/${category.id}" class="btn btn-secondary">Sửa</a>
                    <a href="admin/category/delete/${category.id}" onclick="return confirm('Bạn có chắc xóa không?')" class="btn btn-danger">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

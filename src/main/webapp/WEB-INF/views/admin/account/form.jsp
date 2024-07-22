<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form:form method="post" cssStyle="margin-top: 100px" cssClass="container" modelAttribute="account">
    <h1>Thêm Mới</h1>
    <a href="/admin/account" class="btn btn-primary">Danh Sách</a>
    <div class="mt-3 mb-3">
        <label class="form-label">Tên Người Dùng:</label>
        <form:input path="username" cssClass="form-control"/>
        <form:errors cssStyle="color: indianred" path="username"/>
    </div>
    <div class="mt-3 mb-3">
        <label class="form-label">Họ Tên:</label>
        <form:input path="fullname" cssClass="form-control"/>
        <form:errors cssStyle="color: indianred" path="fullname"/>
    </div>
    <div class="mt-3 mb-3">
        <label class="form-label">Mật Khẩu:</label>
        <form:input path="password" cssClass="form-control"/>
        <form:errors cssStyle="color: indianred" path="password"/>
    </div>
    <div class="mt-3 mb-3">
        <label class="form-label">E-Mail:</label>
        <form:input path="email" cssClass="form-control"/>
        <form:errors cssStyle="color: indianred" path="email"/>
    </div>
    <div class="mt-3 mb-3">
        <label class="form-label">Trạng Thái Hoạt Động:</label>
        <form:checkbox path="activated"  cssClass="form-check-input"/>
    </div>
    <div class="mt-3 mb-3">
        <label class="form-label">Quản Lý:</label>
        <form:checkbox path="admin" cssClass="form-check-input"/>
    </div>

    <form:button onclick="return confirm('Bạn có chắc lưu không?')" class="btn btn-outline-success">Lưu</form:button>
    <div class="alert alert-warning mt-3" style="display: ${error==null?"none":"block"}">${error}</div>

</form:form>

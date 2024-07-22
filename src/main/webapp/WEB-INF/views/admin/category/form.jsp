<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form:form method="post" cssStyle="margin-top: 100px" cssClass="container" modelAttribute="category">
    <h1>${labelCategory}</h1>
    <a href="/admin" class="btn btn-primary">Danh Sách</a>
    <div class="mt-3 mb-3" ${labelCategory.contains("Sửa")?"hidden":""}>
        <label class="form-label">ID:</label>
        <form:input path="id" cssClass="form-control"/>
        <form:errors cssStyle="color: indianred" path="id"/>
    </div>
    <div class="mt-3 mb-3" >
        <label class="form-label">Tên Danh Mục:</label>
        <form:input path="name" cssClass="form-control"/>
        <form:errors cssStyle="color: indianred" path="name"/>
    </div>

    <form:button onclick="return confirm('Bạn có chắc lưu không?')" class="btn btn-outline-success">Lưu</form:button>
    <div class="alert alert-secondary mt-3" style="display: ${error==null?"none":"block"}">${error}</div>

</form:form>

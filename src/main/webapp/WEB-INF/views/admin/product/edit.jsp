<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form:form method="post" cssStyle="margin-top: 100px" cssClass="container" modelAttribute="product">
    <h1>Chỉnh sửa sản phẩm</h1>
    <a href="/admin/product" class="btn btn-primary">Danh Sách</a>
    <div class="mt-3 mb-3">
        <label class="form-label">Loại:</label>
        <form:select path="category" cssClass="form-control">
            <form:option value="" label="-Lựa Chọn-" id=""/>
            <form:options items="${listCategory}" itemLabel="name" itemValue="id"/>
        </form:select>
        <form:errors cssStyle="color: #cd5c5c" path="category"/>
    </div>
    <div class="mt-3 mb-3">
        <label class="form-label">Tên:</label>
        <form:input path="name" cssClass="form-control"/>
        <form:errors cssStyle="color: indianred" path="name"/>
    </div>
    <div class="mt-3 mb-3">
        <label class="form-label">Đơn Giá:</label>
        <form:input type="number" path="price" cssClass="form-control"/>
        <form:errors cssStyle="color: indianred" path="price"/>
    </div>
    <div class="mt-3 mb-3">
        <label class="form-label">Còn Hàng:</label>
        <form:checkbox path="available" cssClass="form-check-input"/>
    </div>
    <div class="mt-3 mb-3">
        <label class="form-label">Thay Đổi Hình Ảnh:</label>
        <form:input  type="file" path="image"   title="Chọn Ảnh" class="form-control"/>
        <form:errors cssStyle="color: indianred" path="image"/>
    </div>

    <form:button onclick="return confirm('Bạn có chắc lưu không?')" class="btn btn-outline-success">Lưu</form:button>
    <div class="alert alert-secondary mt-3" style="display: ${error==null?"none":"block"}">
            ${error}</div>

</form:form>

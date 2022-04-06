<%@include file="/common/taglib.jsp" %>
<c:url var="NewsURL" value="/admin-news-list"/>
<c:url var="APIurl" value="/api-admin-new"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chỉnh sửa bài viết</title>
</head>
<!-- /.main-content -->

<body>

<div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang chủ</a>
                    </li>
                    <li class="active">Chỉnh sửa bài viết</li>

                </ul><!-- /.breadcrumb -->

            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <form id="formSubmit">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                    <div class="col-sm-9">
                                        <select class="form-control" id="categoryCode" name="categoryCode">
                                            <c:if test="${empty NewsModel.categoryCode}">
                                                <option value="">Chọn loại bài viết</option>
                                                <c:forEach var="item" items="${categoryList}">
                                                    <option value="${item.code}">${item.name}</option>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${not empty NewsModel.categoryCode}">
                                                <c:forEach var="item" items="${categoryList}">
                                                    <option value="${item.code}" <c:if test="${item.code == NewsModel.categoryCode}">selected="selected"</c:if>>
                                                            ${item.name}
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="title" name="title" value="${NewsModel.title}"/>
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="thumbnail" name="thumbnail" value="${NewsModel.thumbnail}"/>
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${NewsModel.shortDescription}"/>
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                    <div class="col-sm-9">
                                        <textarea rows="" cols="" id="content" name="content" style="width: 820px;height: 175px">${NewsModel.content}</textarea>
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <c:if test="${not empty NewsModel.id}">
                                            <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật bài viết" id="btnAddOrUpdateNews"/>
                                        </c:if>
                                        <c:if test="${empty NewsModel.id}">
                                            <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm bài viết" id="btnAddOrUpdateNews"/>
                                        </c:if>
                                    </div>
                                </div>
                                <input type="hidden" value="${NewsModel.id}" id="id" name="id"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>

<script>

    var editor ='';

    $(document).ready(function () {
        editor = CKEDITOR.replace('content');
    });
    $('#btnAddOrUpdateNews').click(function (e){
        e.preventDefault();
        var data = {};
        var formData = $("#formSubmit").serializeArray();
        $.each(formData, function (i, field) {
            data[field.name] = field.value;
        });
        data['content'] = editor.getData();
        if ($('#id').val()==''){
            addNews(data);
        } else {
            updateNews(data);
        }
    });
    function addNews(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            datatype: 'json',
            success: function (result) {
                alert('Cập nhật bài viết thành công');
                window.location.href = '${NewsURL}?page=1&maxItemInPage=5&sortBy=title&sortType=asc&type=list';
            },
            error: function (result) {
                alert('Cập nhật bài viết thất bại');
            }
        });
    }
    function updateNews(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: 'application/json',
            datatype: 'json',
            success: function (result) {
                alert('Cập nhật bài viết thành công');
                window.location.href = '${NewsURL}?page=1&maxItemInPage=5&sortBy=title&sortType=asc&type=list';
            },
            error: function (result) {
                alert('Cập nhật bài viết thất bại');
            }
        });
    }
</script>
</body>
</html>
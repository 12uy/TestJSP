
<%@include file="/common/taglib.jsp"%>
<c:url var="NewsURL" value="/admin-news-list"/>
<c:url var="APIurl" value="/api-admin-new"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách bài viết</title>
    </head>
    <!-- /.main-content -->

    <body>

    <div class="main-content">
        <form action="<c:url value="/admin-news-list"/> " id="formSubmit" method="get">
            <div class="main-content-inner">
                <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="ace-icon fa fa-home home-icon"></i>
                            <a href="#">Trang chủ</a>
                        </li>
                        <li class="active">Danh sách bài viết</li>

                    </ul><!-- /.breadcrumb -->

                </div>
                <div class="page-content">
                    <div class="row" >
                        <div class="col-xs-12">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="widget-box table-filter">
                                        <div class="table-btn-controls">
                                            <div class="pull-right tableTools-container">
                                                <div class="dt-buttons btn-overlap btn-group">
                                                    <c:url var="addNews" value="/admin-news-list">
                                                        <c:param name="type" value="edit"/>
                                                    </c:url>
                                                    <a flag="info"
                                                       class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
                                                       title='Thêm bài viết' href='<c:url value="${addNews}"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                                    </a>
                                                    <button id="btnDelete" type="button"
                                                            class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th><input class="form-check-input" type="checkbox" value="" id="checkboxAll"></th>
                                                <th>Tên bài viết</th>
                                                <th>Mô tả ngắn</th>
                                                <th>Thao tác</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%--                                            NewsModel là newsModel hay là giá trị của Constant trong controller (NewsController)--%>
                                            <c:forEach var="item" items="${NewsModel.listResult}" >
                                                <tr>
                                                    <td><input class="form-check-input" type="checkbox" value="${item.id}" id="checkboxItem${item.id}"></td>
                                                    <td>${item.title}</td>
                                                    <td>${item.shortDescription}</td>
                                                    <td>
                                                        <c:url var="editurl" value="/admin-news-list">
                                                                <c:param name="id" value="${item.id}"/>
                                                                <c:param name="type" value="edit"/>
                                                        </c:url>
                                                        <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                           title="Cập nhật bài viết" href='${editurl}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <ul class="pagination" id="pagination"></ul>
                                        <input type="hidden" value="" name="page" id="page"/>
                                        <input type="hidden" value="" name="maxItemInPage" id="maxItemInPage"/>
                                        <input type="hidden" value="" name="sortBy" id="sortBy"/>
                                        <input type="hidden" value="" name="sortType" id="sortType"/>
                                        <input type="hidden" value="" name="type" id="type"/>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <script type="text/javascript">
        var currentPage = ${NewsModel.page};
        var totalPage = ${NewsModel.totalPage};
        var limit = 5;

        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPage,
                visiblePages: 10,
                startPage: currentPage,
                onPageClick: function (event, page) {
                    if (page != currentPage) {
                        $('#maxItemInPage').val(limit);
                        $('#page').val(page);
                        $('#sortBy').val('title');
                        $('#sortType').val('asc');
                        $('#type').val('list');
                        $('#formSubmit').submit();
                    }
                }
            }).on('page', function (event, page) {
                console.info(page + ' (from event listening)');
            });
        });
        //chọn tất cả checkbox
        $("#checkboxAll").click(function () {
            $(".form-check-input").prop('checked', $(this).prop('checked'));
        });

        $( "#btnDelete" ).click(function() {
            var data = {};
            ids = $("tbody input:checkbox:checked").map(function () {
                return $(this).val();
            }).get();
            data['ids'] = ids;
            // console.log(data)
            deleteNews(data);
        });
        function deleteNews(data) {
            $.ajax({
                url: '${APIurl}',
                type: 'DELETE',
                data: JSON.stringify(data),
                contentType: 'application/json',
                datatype: 'json',
                success: function (result) {
                    alert('Xoá bài viết thành công');
                    window.location.href = '${NewsURL}?page=1&maxItemInPage=5&sortBy=title&sortType=asc&type=list';
                },
                error: function (result) {
                    alert('Xoá bài viết thất bại');
                }
            });
        }
    </script>
    </body>

	</html>
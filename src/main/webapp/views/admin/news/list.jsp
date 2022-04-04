
<%@include file="/common/taglib.jsp"%>

<c:url var ="NewURL" value="/admin-news-list"/>
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
                    </ul><!-- /.breadcrumb -->
                </div>
                <div class="page-content">
                    <div class="row" >
                        <div class="col-xs-12">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>Tên bài viết</th>
                                                <th>Mô tả ngắn</th>
                                                <th>Thao tác</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%--                                            NewsModel là newsModel hay là giá trị của Constant trong controller (NewsController)--%>
                                            <c:forEach var="item" items="${NewsModel.listResult}" >
                                                <tr>
                                                    <td>${item.title}</td>
                                                    <td>${item.shortDescription}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <ul class="pagination" id="pagination"></ul>
                                        <input type="hidden" value="" name="page" id="page"/>
                                        <input type="hidden" value="" name="maxItemInPage" id="maxItemInPage"/>
                                        <input type="hidden" value="" name="sortBy" id="sortBy"/>
                                        <input type="hidden" value="" name="sortType" id="sortType"/>

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
                        $('#formSubmit').submit();
                    }
                }
            }).on('page', function (event, page) {
                console.info(page + ' (from event listening)');
            });
        });
    </script>
    </body>

	</html>
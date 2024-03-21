<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<%--    <meta charset="utf-8">--%>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>
    <title>当前借阅</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>

<body class="hold-transition skin-red sidebar-mini">
<!-- .box-body -->
<div class="box-header with-border">
    <h3 class="box-title">当前借阅</h3>
</div>
<div class="box-body">
    <!--工具栏 数据搜索 -->
    <div class="box-tools pull-right">
        <div class="has-feedback">
            <form action="${pageContext.request.contextPath}/book/searchBorrowed" method="post">
                图书名称：<input name="name" value="${search.name}">&nbsp&nbsp&nbsp&nbsp
                图书作者：<input name="author" value="${search.author}">&nbsp&nbsp&nbsp&nbsp
                出版社：<input name="press" value="${search.press}">&nbsp&nbsp&nbsp&nbsp
                <input class="btn btn-default" type="submit" value="查询">
            </form>
        </div>
    </div>
    <!--工具栏 数据搜索 /-->
    <!--数据列表-->
    <div class="table-box">
        <!-- 数据表格 -->
        <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
            <thead>
            <tr>
                <th class="sorting_asc">图书名称</th>
                <th class="sorting">图书作者</th>
                <th class="sorting">出版社</th>
                <th class="sorting">标准ISBN</th>
                <th class="sorting">书籍状态</th>
                <th class="sorting">借阅人</th>
                <th class="sorting">借阅时间</th>
                <th class="sorting">应归还时间</th>
                <th class="text-center">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.rows}" var="book">
                <tr>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.press}</td>
                    <td>${book.isbn}</td>
                    <td>
                        <c:if test="${book.status ==1}">借阅中</c:if>
                        <c:if test="${book.status ==2}">归还中</c:if>
                    </td>
                    <td>${book.borrower}</td>
                    <td>${book.borrowTime}</td>
                    <td>${book.returnTime}</td>
                    <td class="text-center">
                        <c:if test="${book.status ==1}">
                            <button type="button" class="btn bg-olive btn-xs" onclick="returnBook(${book.id})">归还
                            </button>
                        </c:if>
                        <c:if test="${book.status ==2}">
                            <button type="button" class="btn bg-olive btn-xs" disabled="true">归还中</button>
                            <c:if test="${USER_SESSION.role =='ADMIN'}">
                                <button type="button" class="btn bg-olive btn-xs" onclick="returnConfirm(${book.id})">
                                    归还确认
                                </button>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!-- 数据表格 /-->
        <%--分页插件--%>
        <div id="pagination" class="pagination"></div>
    </div>
    <!-- 数据表格 /-->
</div>
<!-- /.box-body -->
</body>
<script>
	/*分页插件展示的总页数*/
    pageargs.total = Math.ceil(${pageResult.total}/pageargs.pagesize);
	/*分页插件当前的页码*/
    pageargs.cur = ${pageNum}
	/*分页插件页码变化时将跳转到的服务器端的路径*/
	pageargs.gourl = "${gourl}"
	/*保存搜索框中的搜索条件，页码变化时携带之前的搜索条件*/
    bookVO.name = "${search.name}"
    bookVO.author = "${search.author}"
    bookVO.press = "${search.press}"
	/*分页效果*/
    pagination(pageargs);
</script>
</html>
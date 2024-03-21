<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>
    <title>新书推荐</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<!--数据展示头部-->
<div class="box-header with-border">
    <h3 class="box-title">新书推荐</h3>
</div>
<!--数据展示头部-->
<!--数据展示内容区-->
<div class="box-body">
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
            <th class="sorting">预计归还时间</th>
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageResult.rows}" var="book">
            <tr>
                <td> ${book.name}</td>
                <td>${book.author}</td>
                <td>${book.press}</td>
                <td>${book.isbn}</td>
                <td>
                    <c:if test="${book.status ==0}">可借阅</c:if>
                    <c:if test="${book.status ==1}">借阅中</c:if>
                    <c:if test="${book.status ==2}">归还中</c:if>
                </td>
                <td>${book.borrower}</td>
                <td>${book.borrowTime}</td>
                <td>${book.returnTime}</td>
                <td class="text-center">
                    <c:if test="${book.status ==0}">
                        <button type="button" class="btn bg-olive btn-xs" data-toggle="modal" data-target="#borrowModal"
                                onclick="findBookById(${book.id},'borrow')"> 借阅
                        </button>
                    </c:if>
                    <c:if test="${book.status ==1 ||book.status ==2}">
                        <button type="button" class="btn bg-olive btn-xs" disabled="true">借阅</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 数据表格 /-->
</div>
<!-- 数据展示内容区/ -->
<%--引入存放模态窗口的页面--%>
<jsp:include page="/admin/book_modal.jsp"></jsp:include>
</body>
</html>

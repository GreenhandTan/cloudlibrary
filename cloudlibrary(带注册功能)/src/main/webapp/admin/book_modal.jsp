<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 图书借阅信息的模态窗口，默认是隐藏的 -->
<div class="modal fade" id="borrowModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">图书信息</h3>
            </div>
            <div class="modal-body">
                <form id="borrowBook">
                    <table class="table table-bordered table-striped" width="800px">
                        <%--图书的id，不展示在页面--%>
                        <span><input type="hidden" id="bid" name="id"></span>
                        <tr>
                            <td>图书名称</td>
                            <td><input class="form-control" readonly name="name" id="bname"></td>
                            <td>标准ISBN</td>
                            <td><input class="form-control" readonly name="isbn" id="bisbn"></td>
                        </tr>
                        <tr>
                            <td>出版社</td>
                            <td><input class="form-control" readonly name="press" id="bpress"></td>
                            <td>作者</td>
                            <td><input class="form-control" readonly name="author" id="bauthor"></td>
                        </tr>
                        <tr>
                            <td>书籍页数</td>
                            <td><input class="form-control" readonly name="pagination" id="bpagination"></td>
                            <td>归还时间<br/><span style="color: red">*</span></td>
                            <%--时间控件中的内容改变时，调用js文件中的cg()方法--%>
                            <td><input class="form-control" type="date" name="returnTime" id="time" onchange="cg()">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <%--点击保存按钮时，隐藏模态窗口并调用js文件中的borrow()方法--%>
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" onclick="borrow()"
                        disabled="true" id="savemsg">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

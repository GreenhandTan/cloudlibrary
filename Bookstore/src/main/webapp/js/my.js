//���Ĵ�����ʱ���ǩ�����ݸı�ʱִ��
function cg() {
    $("#savemsg").attr("disabled", false);
    var rt = $("#time").val().split("-");
    var ny = new Date().getFullYear();
    var nm = new Date().getMonth() + 1;
    var nd = new Date().getDate();
    if (rt[0] < ny) {
        alert("���ڲ������ڽ���")
        $("#savemsg").attr("disabled", true);
    } else if (rt[0] == ny) {
        if (rt[1] < nm) {
            alert("���ڲ������ڽ���")
            $("#savemsg").attr("disabled", true);
        } else if (rt[1] == nm) {
            if (rt[2] < nd) {
                alert("���ڲ������ڽ���")
                $("#savemsg").attr("disabled", true);
            } else {
                $("#savemsg").attr("disabled", false);
            }
        }
    }
}
//�������ͼ��ʱִ��
function borrow() {
    var url =getProjectPath()+ "/book/borrowBook";
    $.post(url, $("#borrowBook").serialize(), function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href = getProjectPath()+"/book/search";
        }
    })
}

//������Ӻͱ༭����������������
function resetFrom() {
    $("#aoe").attr("disabled",true)
    var $vals=$("#addOrEditBook input");
    $vals.each(function(){
        $(this).attr("style","").val("")
    });
}
//������Ӻͱ༭��������������ʽ
function resetStyle() {
    $("#aoe").attr("disabled",false)
    var $vals=$("#addOrEditBook input");
    $vals.each(function(){
        $(this).attr("style","")
    });
}
//��ѯid��Ӧ��ͼ����Ϣ������ͼ����Ϣ���Ե��༭����ĵĴ�����
function findBookById(id,doname) {
    resetStyle()
    var url = getProjectPath()+"/book/findById?id=" + id;
    $.get(url, function (response) {
        //����Ǳ༭ͼ�飬����ȡ��ͼ����Ϣ���Ե��༭�Ĵ�����
        if(doname=='edit'){
            $("#ebid").val(response.data.id);
            $("#ebname").val(response.data.name);
            $("#ebisbn").val(response.data.isbn);
            $("#ebpress").val(response.data.press);
            $("#ebauthor").val(response.data.author);
            $("#ebpagination").val(response.data.pagination);
            $("#ebprice").val(response.data.price);
            $("#ebstatus").val(response.data.status);
        }
        //����ǽ���ͼ�飬����ȡ��ͼ����Ϣ���Ե����ĵĴ�����
        if(doname=='borrow'){
            $("#savemsg").attr("disabled",true)
            $("#time").val("");
            $("#bid").val(response.data.id);
            $("#bname").val(response.data.name);
            $("#bisbn").val(response.data.isbn);
            $("#bpress").val(response.data.press);
            $("#bauthor").val(response.data.author);
            $("#bpagination").val(response.data.pagination);
        }
    })
}
//�����ӻ�༭�Ĵ��ڵ�ȷ����ťʱ���ύͼ����Ϣ
function addOrEdit() {
    //��ȡ����ͼ��id������
    var ebid = $("#ebid").val();
    //���������ͼ��id�����ݣ�˵������Ϊ�༭����
    if (ebid > 0) {
        var url = getProjectPath()+"/book/editBook";
        $.post(url, $("#addOrEditBook").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath()+"/book/search";
            }
        })
    }
    //�������û��ͼ��id��˵������Ϊ��Ӳ���
    else {
        var url = getProjectPath()+"/book/addBook";
        $.post(url, $("#addOrEditBook").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath()+"/book/search";
            }
        })
    }
}
//�黹ͼ��ʱִ��
function returnBook(bid) {
    var r = confirm("ȷ���黹ͼ��?");
    if (r) {
        var url = getProjectPath()+"/book/returnBook?id=" + bid;
        $.get(url, function (response) {
            alert(response.message)
            //����ɹ�ʱ��ˢ�µ�ǰ���ĵ��б�����
            if (response.success == true) {
                window.location.href = getProjectPath()+"/book/searchBorrowed";
            }
        })
    }
}
//ȷ��ͼ���Ѿ��黹
function returnConfirm(bid) {
    var r = confirm("ȷ��ͼ���ѹ黹?");
    if (r) {
        var url = getProjectPath()+"/book/returnConfirm?id=" + bid;
        $.get(url, function (response) {
            alert(response.message)
            //����ȷ�ϳɹ�ʱ��ˢ�µ�ǰ���ĵ��б�����
            if (response.success == true) {
                window.location.href = getProjectPath()+"/book/searchBorrowed";
            }
        })
    }
}
//���ͼ����Ϣ�Ĵ����У�ͼ����Ϣ��д�Ƿ�����
function checkval(){
    var $inputs=$("#addOrEditTab input")
    var count=0;
    $inputs.each(function () {
        if($(this).val()==''||$(this).val()=="����Ϊ�գ�"){
            count+=1;
        }
    })
    //���ȫ���������д���������ȷ�ϰ�ť�Ľ���״̬
    if(count==0){
        $("#aoe").attr("disabled",false)
    }
}
//ҳ�������ɺ󣬸�ͼ��ģ̬���ڵ�������ʧȥ����ͻ�ȡ�����¼�
$(function() {
    var $inputs=$("#addOrEditBook input")
    var eisbn="";
    $inputs.each(function () {
        //��������ʧȥ�����¼�
        $(this).blur(function () {
            if($(this).val()==''){
                $("#aoe").attr("disabled",true)
                $(this).attr("style","color:red").val("����Ϊ�գ�")
            }
            else if($(this).attr("name")=="isbn"&&eisbn!==$(this).val()){
                if($(this).val().length!=13){
                    $("#aoe").attr("disabled",true)
                    alert("����Ϊ13λ���ı�׼ISBN�����������룡")
                    $(this).val("")
                }
            }else{
                checkval()
            }
        }).focus(function () {
            if($(this).val()=='����Ϊ�գ�'){
                $(this).attr("style","").val("")
            }else{
                $(this).attr("style","")
            }
            if($(this).attr("name")=="isbn"){
                eisbn=$(this).val();
            }
        })
    })
});
//��ȡ��ǰ��Ŀ������
function getProjectPath() {
    //��ȡ������ַ֮���Ŀ¼���磺 cloudlibrary/admin/books.jsp
    var pathName = window.document.location.pathname;
    //��ȡ��"/"����Ŀ�����磺/cloudlibrary
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return  projectName;
}

/**
 * ����չʾҳ���ҳ����Ĳ���
 * cur ��ǰҳ
 * total ��ҳ��
 * len   ��ʾ����ҳ��
 * pagesize 1ҳ��ʾ����������
 * gourl ҳ��仯ʱ ��ת��·��
 * targetId ��ҳ������õ�id
 */
var pageargs = {
    cur: 1,
    total: 0,
    len: 5,
    pagesize:10,
    gourl:"",
    targetId: 'pagination',
    callback: function (total) {
        var oPages = document.getElementsByClassName('page-index');
        for (var i = 0; i < oPages.length; i++) {
            oPages[i].onclick = function () {
                changePage(this.getAttribute('data-index'), pageargs.pagesize);
            }
        }
        var goPage = document.getElementById('go-search');
        goPage.onclick = function () {
            var index = document.getElementById('yeshu').value;
            if (!index || (+index > total) || (+index < 1)) {
                return;
            }
            changePage(index, pageargs.pagesize);
        }
    }
}
/**
 *ͼ���ѯ���Ĳ�ѯ����
 * name ͼ������
 * author ͼ������
 * press ͼ�������
 */
var bookVO={
    name:'',
    author:'',
    press:''
}
/**
 *���ļ�¼��ѯ���Ĳ�ѯ����
 * name ͼ������
 * borrower ������
 */
var recordVO={
    bookname:'',
    borrower:''
}
//����չʾҳ���ҳ�����ҳ�뷢�ͱ仯ʱִ��
function changePage(pageNo,pageSize) {
    pageargs.cur=pageNo;
    pageargs.pagesize=pageSize;
    document.write("<form action="+pageargs.gourl +" method=post name=form1 style='display:none'>");
    document.write("<input type=hidden name='pageNum' value="+pageargs.cur+" >");
    document.write("<input type=hidden name='pageSize' value="+pageargs.pagesize+" >");
    //�����ת����ͼ����Ϣ��ѯ��������ӣ��ύͼ���ѯ���еĲ���
    if(pageargs.gourl.indexOf("book")>=0){
        document.write("<input type=hidden name='name' value="+bookVO.name+" >");
        document.write("<input type=hidden name='author' value="+bookVO.author+" >");
        document.write("<input type=hidden name='press' value="+bookVO.press+" >");
    }
    //�����ת����ͼ���¼��ѯ��������ӣ��ύͼ���¼��ѯ���еĲ���
    if(pageargs.gourl.indexOf("record")>=0){
        document.write("<input type=hidden name='bookname' value="+recordVO.bookname+" >");
        document.write("<input type=hidden name='borrower' value="+recordVO.borrower+" >");
    }
    document.write("</form>");
    document.form1.submit();
    pagination(pageargs);
}


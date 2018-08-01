<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ERP | 个人设置</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>


    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_account"/>
    </jsp:include>

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                账号管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">个人账号</h3>
                </div>
                <div class="box-body">
                    <form method="post" class="saveForm">
                        <div class="form-group">
                            <label>账号</label>
                            <input type="text" class="form-control" name="employeeName">
                        </div>
                        <div class="form-group">
                            <label>手机号码(用于登录)</label>
                            <input type="text" class="form-control" name="employeeTel">
                        </div>
                        <div class="form-group">
                            <label>旧密码</label>
                            <input type="password" class="form-control" name="password">
                        </div>
                        <div class="form-group">
                            <label>新密码</label>
                            <input type="password" class="form-control" name="password">
                        </div>

                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary pull-right" id="saveBtn">保存</button>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script>
    $(function () {
        var message = "${message}";
        if(message){
            layer.msg(message);
        }

        $("#saveBtn").click(function () {
            $(".saveForm").submit();
        });
    });
</script>
</body>
</html>
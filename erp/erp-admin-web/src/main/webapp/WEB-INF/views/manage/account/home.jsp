<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ERP | 账号管理</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_employee"/>
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
            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" name="nameMobile" placeholder="账号或手机号码" class="form-control" value="${param.employeeTel}">
                        <select name="roleId" class="form-control">
                            <option value="">所有账号</option>
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}" ${param.roleId == role.id ? 'selected' : ''}>${role.roleName}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <div class="box-tools">
                        <a href="/manage/account/add" class="btn btn-success btn-sm">
                            <i class="fa fa-plus"></i> 新增账号
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>账号</th>
                            <th>手机号码</th>
                            <th>角色</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${employeeList}" var="employee">
                            <tr>
                                <td>${employee.employeeName}</td>
                                <td>${employee.employeeTel}</td>
                                <td>
                                    <c:forEach items="${employee.roleList}" var="role">
                                        ${role.roleName}
                                    </c:forEach>
                                </td>
                                <td>
                                        ${employee.state == 1 ? "正常" : "禁用"}
                                </td>
                                <td>
                                    <fmt:formatDate value="${employee.createTime}"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${employee.state == 1}">
                                            <a class="btn btn-warning btn-xs" rel="${employee.id}" href="/manage/account/${employee.id}/error" title="禁用"><i class="fa fa-lock"></i></a>
                                        </c:when>

                                        <c:otherwise>
                                            <a class="btn btn-warning btn-xs" rel="${employee.id}" href="/manage/account/${employee.id}/success" title="正常"><i class="fa fa-unlock"></i></a>
                                        </c:otherwise>
                                    </c:choose>
                                    <a class="btn btn-primary btn-xs" href="/manage/account/${employee.id}/edit"><i class="fa fa-edit"></i></a>
                                    <a class="btn btn-danger btn-xs delLink" rel="${employee.id}" href="javascript:;" title="删除"><i class="fa fa-trash"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script src="/static/plugins/layer/layer.js"></script>

<script>
    $(function () {
        $('.tree').treegrid();

        var message = "${message}";
        if(message){
            layer.msg(message);
        }
        //删除
        $(".delLink").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该角色？",function (index) {
                layer.close(index);
                $.get("/manage/account/"+id+"/del").done(function (result) {
                    if(result.state == 'success') {
                        layer.msg("删除成功", {icon:2, time:1000},function () {
                            history.go(0);
                        });
                    } else {
                        layer.msg(result.message);
                    }
                }).error(function () {
                    layer.msg("服务器忙");
                });
            })
        });
    });

</script>
</body>
</html>
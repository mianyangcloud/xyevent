<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">

    	<title>媒体云</title>

	    <!-- Bootstrap Core CSS -->
	    <link href="${base}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	    <!-- MetisMenu CSS -->
	    <link href="${base}/static/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

	    <link href="${base}/static/dist/css/sb-admin-2.css" rel="stylesheet">

	    <link href="${base}/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    
	    <link href="${base}/static/vendor/bootstrap-table/css/bootstrap-table.css" rel="stylesheet">
	    
	    <link href="${base}/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    
	    <style>
	    	.groupTitle {
	    		margin-top: -10px;
	    	}
	    </style>
	    
	</head>

	<body>
    	<div id="wrapper">
        	<!-- Navigation -->
        	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	            <#include "header.ftl"/>
				<#include "/menu/event.ftl"/>  
        	</nav>
        	<div id="page-wrapper">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h1 class="page-header">用户</h1>
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
            	<div class="row">
	                <div class="col-lg-12">
	                	<div id="toolbar">
           					 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addUserModal" onclick="addUser()">添加用户</button>
           				</div>
           				<table id="table"
				               data-toggle="table"
				               data-toolbar="#toolbar"
				               data-pagination="true"
				               data-search="true"
				               data-cache="false"
				               data-show-refresh="true"
				               data-show-columns="true"
				               data-page-number="1"
				               data-query-params-type=""
				               data-page-size="10"
				               data-page-list="[10,20,30]"
				               data-side-pagination="server"
				               data-url="${base}/api/user/list">
				            <thead>
				            <tr>
				                <th data-field="realname">姓名</th>
				                <th data-field="mobilenum">电话</th>
				                <th data-field="email">邮箱</th>
				                <th data-field="statusStr">状态</th>
				                 <th data-field="remark">备注</th>
				                <th data-field="createTime">注册时间</th>
				                <th data-formatter="operateFormatter">操作</th>
				            </tr>
				            </thead>
				        </table>
       				 </div>
	                <!-- /.col-lg-12 -->
	            </div>
	            <!-- /.row -->
        	</div>
        	<!-- /#page-wrapper -->
	    </div>
	    <!-- /#wrapper -->
		
	    <!-- jQuery -->
	    <script src="${base}/static/vendor/jquery/jquery.min.js"></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <script src="${base}/static/vendor/bootstrap/js/bootstrap.min.js"></script>
	
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="${base}/static/vendor/metisMenu/metisMenu.min.js"></script>
	
	    <script src="${base}/static/vendor/bootstrap-table/js/bootstrap-table.js"></script>
	    <script src="${base}/static/vendor/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
	    <script src="${base}/static/vendor/bootstrap-table/js/bootstrap-table-mobile.min.js"></script>
	
	    <!-- Custom Theme JavaScript -->
	    <script src="${base}/static/dist/js/sb-admin-2.js"></script>
	
	    <div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">编辑用户</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			<form action="${base}/user/edit" id="userForm" method="post">
                      				<input type="hidden" name="id" id="userId" />
	                       			<input type="hidden" name="eventId" value="" />
	                       			<div class="form-group">
	                                	<label>姓名：</label>
	                                   	<input class="form-control" placeholder="姓名" id="realname" name="realname" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="姓名未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>电话：</label>
		                        		<span class="alertSpan" id="alertMobile2" value="" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="手机已经存在，请尝试其他的手机" ></span>
	                                   	<input class="form-control" placeholder="电话" id="mobilenum" name="mobilenum" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="电话未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>邮箱：</label>
	                                	<span class="alertSpan" id="alertEmail1" value="" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="邮箱格式不正确，请填写正确格式的邮箱。例如：biz@ehui.net" ></span>
		                        		<span class="alertSpan" id="alertEmail2" value="" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="邮箱已经存在，请尝试其他的邮箱" ></span>
	                                   	<input class="form-control" placeholder="邮箱" id="email" name="email" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="邮箱未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>备注：</label>
	                                   	<input class="form-control" placeholder="备注" id="remark" name="remark" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="备注未填写">
	                                </div>
                                </form>
                      		</div>
                    	</div>
                   	</div>
                    <div class="modal-footer">
                    	<button type="button" id="saveBtn" onclick="submit()" class="btn btn btn-primary">保存</button>
                        <button type="button" name="cld" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        
        <div class="modal fade" id="delUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">禁用用户</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			您确定禁用所选的用户吗？
                      		</div>
                    	</div>
                   	</div>
                    <div class="modal-footer">
                    	<button type="button" id="delBtn" class="btn btn btn-primary">禁用</button>
                        <button type="button" name="cld" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
	</body>
	 
	<script type="text/javascript">
		var $table = $('#table');
		
		$("[data-toggle=popover]").focus(function () {
			if($(this).val()!='') {
				$(this).popover('hide');
			}else {
				$(this).popover('show');
			} 
		});
	    
	    function addUser() {
	    	$("#userId").val("");
	    	$("#realname").val("");
	    	$("#mobilenum").val("");
	    	$("#remark").val("");
	    }
	    
	    function editUser(id, realname, mobilenum, email, remark) {
	    	$("#userId").val(id);
	    	$("#realname").val(realname);
	    	$("#mobilenum").val(mobilenum);
	    	$("#email").val(email);
	    	$("#remark").val(remark);
	    }
	    
	    function delUser(id) {
	    	$("#delBtn").click(function(){
	    		window.location.href='${base}/user/delete?id='+id;
	    	});
	    }
	    
	    function submit() {
	    	var realname = $("#realname").val();
	    	var email = $("#email").val();
	    	var isemail = /^\w+([-\.]\w+)*@\w+([\.-]\w+)*\.\w{2,4}$/;
	    	var mobilenum = $("#mobilenum").val();
	    	var flag = true;
	    	if(realname == "") {
	    		flag = false;
	    		$("#realname").focus();
	    	}
	    	if(email == "") {
	    		flag = false;
	    		$("#email").focus();
	    	}
	    	if(!isemail.test(email)) {
	    		flag = false;
	    		$(".alertSpan").hide();
				$("#alertEmail1").css("display","block");
				$("#alertEmail1").focus();
	    	}
	    	$.ajax({
		    	type: "GET",
		        url: "${base}/api/user/findByMobilenum",
		        data: {'eventId':${event.id},'mobilenum':mobilenum},
		        success: function(data){
		        	var msg = eval(data);
		           	if(msg.code=='1' && msg.data != null) {
		           		if(msg.data.id == $("#userId").val() && flag) {
		           			$("#userForm").submit();
		           		}
			    		flag = false;
			    		$(".alertSpan").hide();
						$("#alertMobile2").focus();
		          	}else if(msg.code=='1' && msg.data == null) {
		           		if(flag) {
	    					$("#userForm").submit();
	    				}
		          	}else {
		          		alert("EXCEPTION");
		          		return;
		          	}
		   		}
		   	});
		   	return;
	    }
    	
    	function operateFormatter(value, row, index) {
    		
    		return [
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" data-target="#addUserModal" onclick="editUser(\''+row.id+'\',\''+row.realname+'\',\''+row.mobilenum+'\',\''+row.email+'\',\''+row.remark+'\')">',
    	                '编辑',
    	            '</a>',
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" onclick="openCheckin(\''+row.id+'\')">',
    	                '打卡信息',
    	            '</a>',
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" onclick="openPicture(\''+row.id+'\')">',
    	                '图片',
    	            '</a>',
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" onclick="openVideo(\''+row.id+'\')">',
    	                '视频',
    	            '</a>',
    	            '<a href="#" data-toggle="modal" data-target="#delUserModal" onclick="delUser(\''+row.id+'\')">',
    	                '禁用',
    	            '</a>'
    	        ].join('');
    	}
    	
    	function openCheckin(id) {
    		window.location.href = "${base}/checkin/list?userId="+id
    	}
    	
    	function openPicture(id) {
    		window.location.href = "${base}/picture/listByUser?userId="+id
    	}
    	
    	function openVideo(id) {
    		window.location.href = "${base}/video/listByUser?userId="+id
    	}
    	
    	function isRealNum(val){
    		if(val === "" || val ==null){
        		return false;
    		}
    		if(!isNaN(val)){
        		return true;
    		}else{
        		return false;
    		}
		}           
	    
	</script>
</html>

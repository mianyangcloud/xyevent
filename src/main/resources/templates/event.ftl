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
	    
	    <link href="${base}/static/dist/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css">
	    
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
				<#include "/menu/menu.ftl"/>  
        	</nav>
        	<div id="page-wrapper">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h1 class="page-header">活动</h1>
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
            	<div class="row">
	                <div class="col-lg-12">
	                	<div id="toolbar">
           					 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addEventModal" onclick="addEvent()">添加活动</button>
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
				               data-url="${base}/api/event/list">
				            <thead>
				            <tr>
				                <th data-field="title">活动名称</th>
				                <th data-field="btime">活动时间</th>
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
	    
	    <script src="${base}/static/dist/js/jquery.datetimepicker.full.js" type="text/javascript" charset="utf-8"></script>
	
	    <!-- Custom Theme JavaScript -->
	    <script src="${base}/static/dist/js/sb-admin-2.js"></script>
	
	    <div class="modal fade" id="addEventModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">编辑活动</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			<form action="${base}/event/edit" id="eventForm" method="post">
                      				<input type="hidden" name="id" id="eventId" />
	                       			<input type="hidden" name="eventId" value="" />
	                       			<div class="form-group">
	                                	<label>活动名称：</label>
	                                   	<input class="form-control" placeholder="活动名称" id="title" name="title" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="活动名称未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>活动开始时间：</label>
	                                   	<input class="form-control datetimepicker" placeholder="活动开始时间" id="beginTime" name="bTime" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="活动开始时间未填写">
	                                </div>
                                </form>
                      		</div>
                    	</div>
                   	</div>
                    <div class="modal-footer">
                    	<button type="button" id="saveBtn" onclick="submit()" class="btn btn btn-primary" >保存</button>
                        <button type="button" name="cld" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        
        <div class="modal fade" id="delEventModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">删除活动</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			删除后将无法恢复，您确定删除所选的活动吗？
                      		</div>
                    	</div>
                   	</div>
                    <div class="modal-footer">
                    	<button type="button" id="delBtn" class="btn btn btn-primary" >删除</button>
                        <button type="button" name="cld" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
	</body>
	 
	<script type="text/javascript">
		
		var myDate = new Date();
	    var nowDate = myDate.getFullYear()+"\\"+(myDate.getMonth()+1)+"\\"+myDate.getDate();
		
		$("#beginTime").datetimepicker({
			format:'Y-m-d H:i',
			startDate:nowDate
		});
		
		var $table = $('#table');
		
		$("[data-toggle=popover]").focus(function () {
			if($(this).val()!='') {
				$(this).popover('hide');
			}else {
				$(this).popover('show');
			} 
		});
	    
	    function addEvent() {
	    	$("#eventId").val("");
	    	$("#title").val("");
	    	$("#beginTime").val("");
	    }
	    
	    function editEvent(id, title, beginTime) {
	    	$("#eventId").val(id);
	    	$("#title").val(title);
	    	$("#beginTime").val(beginTime);
	    }
	    
	    function delEvent(id) {
	    	$("#delBtn").click(function(){
	    		window.location.href='${base}/event/delete?id='+id;
	    	});
	    }
	    
	    function submit() {
	    	var title = $("#title").val();
	    	var beginTime = $("#beginTime").val();
	    	var flag = true;
	    	if(title=="") {
	    		flag = false;
	    		$("#title").focus();
	    	}
	    	if(beginTime=="") {
	    		flag = false;
	    		$("#beginTime").focus();
	    	}
	    	if(flag) {
	    		$("#eventForm").submit();
	    	}
	    }
    	
    	function operateFormatter(value, row, index) {
    		
    		return [
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" data-target="#addEventModal" onclick="editEvent(\''+row.id+'\',\''+row.title+'\',\''+row.btime+'\')">',
    	                '编辑',
    	            '</a>',
    	            '<a href="${base}/event/management?eventId='+row.id+'" style="margin-right: 10px;">',
    	                '管理',
    	            '</a>'
    	            //'<a href="#" data-toggle="modal" data-target="#delEventModal" onclick="delEvent(\''+row.id+'\')">',
    	            //    '删除',
    	            //'</a>'
    	        ].join('');
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

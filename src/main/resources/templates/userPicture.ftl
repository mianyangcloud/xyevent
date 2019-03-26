<#assign base=request.contextPath />
<#assign picture_domain="https://pic.ehuami.cn/" />
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
	    
	    <link href="${base}/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

	    <link href="${base}/static/dist/css/sb-admin-2.css" rel="stylesheet">
	    
	    <link href="${base}/static/vendor/bootstrap-table/css/bootstrap-table.css" rel="stylesheet">
	    
	    <!--引入CSS-->
		<link href="${base}/static/vendor/webuploader/css/webuploader.css" rel="stylesheet" type="text/css">
		
		<link href="${base}/static/dist/css/pictureUpload.css" rel="stylesheet">
	    
	    <style type="text/css">
	    	.upload_div {
	    		text-align: center;
    			border: 1px solid #e5e5e5;
    			border-radius: 5px;
    			padding: 80px 33px;
	    	}
	    	
	    	.upload_div_1 {
	    		text-align: center;
    			border: 1px solid #e5e5e5;
    			border-radius: 5px;
	    	}
	    	
	    	.upload_div p {
	    		font-size:12px;
	    		text-align: left;
	    		color:#aaa;
	    	}
	    	
	    	.groupList>ul>.active>a, .groupList>ul>.active>a:hover, .groupList>ul>.active>a:focus {
	    		color:#262626;
	    		background-color: #eeeeee;
	    	}
	    	
	    	.error {
	    		margin:0px
	    	}
	    	
	    	.groupList {
	    		border: 1px solid #ccc;
			    border-radius: 4px;
			    padding: 6px 12px;
			    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
			    list-style-type:none;
	    	}
	    	
	    	#gpSelect .col-lg-4 {
	    		padding-left:0px
	    	}
	    	
	    	.groupList[disabled] {
	    		cursor: not-allowed;
    			background-color: #eee;
    			opacity: 1;
	    	}
	    	
	    	.groupList[disabled] a {
	    		cursor: not-allowed;
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
	                    <h1 class="page-header">图片</h1>
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
            	<!-- /.row -->
            	<div class="row">
	                <div class="col-lg-12">
           				<table id="table"
				               data-toggle="table"
				               data-toolbar="#toolbar"
				               data-pagination="true"
				               data-show-refresh="true"
				               data-show-columns="true"
				               data-cache="false"
				               data-click-to-select="true"
				               data-page-number="1"
				               data-query-params-type=""
				               data-page-size="10"
				               data-page-list="[10,20,30]"
				               data-side-pagination="server"
				               data-url="${base}/api/picture/listByUserId?userId=${userId}">
				            <thead>
				            <tr>
				                <th data-field="state" data-checkbox="true"></th>
				                <th data-field="url" data-formatter="picFormatter">图片</th>
				                <th data-field="createTime">创建时间</th>
				            </tr>
				            </thead>
				        </table>
				        <div id="filePicker" style="display: none"></div>
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
	    
	    <!--引入JS-->
		<script src="${base}/static/vendor/webuploader/js/webuploader.js" type="text/javascript"></script>
		
		<!--引入JS-->
		<script src="${base}/static/dist/js/pictureUpload.js?v=1.0" type="text/javascript"></script>
        
        <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog" style="width:800px">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" id="cld_img_1" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">编辑图片信息</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                    		<input type="hidden" id="upload_groupId" value=""/>
                    		<input type="hidden" id="upload_eventId" value=""/>
                    		<div class="row upload_div" id="selectImage">
                    			<button type="button" class="btn btn-primary" onclick="uploadClick()" >选择图片</button>
                    		</div>
                    		<div class="row" id="gpSelect">
                    			<div class="col-lg-4">
	                    			<div class="form-group">
	                                	<label>图片分组：</label>
	                            		<li class="dropdown groupList">
	                            			<a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:#333;text-decoration:none;width: 100%;display: block;">
	                            				<span id="upload_group_title">未分组</span> <i class="fa fa-caret-down"></i>
	                            			</a>
	                            			<ul class="dropdown-menu" id="upload_group_list">
	                            				<li>
	                            					<a href="javascript:void(0)" style="color:#262626" name="" >未分组</a>
	                            				</li>
	                            			</ul>
	                            		</li>
	                                </div>
                                </div>
                    		</div>
                    		<div class="row upload_div" id="uploader">
                    			<div class="queueList">
                      				<div id="dndArea" class="placeholder">
				                    </div>
                      			</div>
                      			<div class="statusBar" style="display:none;">
                      				<div class="progress progress-striped active" style="width: 50%;margin: 22px 5px 0px 20px;float:left;">
										<div class="progress-bar progress-bar-success" role="progressbar" id="upLine" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
											
										</div>
									</div>
				                    <div class="info"></div>
				                    <div class="btns">
				                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
				                    </div>
				                </div>
                      		</div>
                    	</div>
                   	</div>
                    <div class="modal-footer">
                    	<button type="button" id="add_btn" class="btn btn-default" onclick="rAdd()" style="display: none">继续添加</button>
                    	<button type="button" id="startUpload" onclick="startUpload()" class="btn btn btn-primary" >开始上传</button>
                        <button type="button" name="cld" id="cld_img_2" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">编辑图片信息</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                    		<form action="${base}/picture/edit" id="editForm" method="post">
                    			<input type="hidden" name="pictureId" />
	                       		<input type="hidden" name="groupId" />
	                      		<div class="row">
	                       			<div class="form-group">
	                                	<label>图片分组：</label>
	                            		<li class="dropdown groupList">
	                            			<a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:#333;text-decoration:none;width: 100%;display: block;">
	                            				<span id="edit_group_title">未分组</span> <i class="fa fa-caret-down"></i>
	                            			</a>
	                            			<ul class="dropdown-menu" id="edit_group_list">
	                            				<li>
	                            					<a href="javascript:void(0)" style="color:#262626" name="" >未分组</a>
	                            				</li>
	                            			</ul>
	                            		</li>
	                                </div>
	                      		</div>
                      		</form>
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
        <div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">删除图片</h4>
                   	</div>
                   	<form action="${base}/picture/delete" id="delForm" method="post">
                   		<input type="hidden" name="id" />
	                   	<div class="modal-body">
	                    	<div class="container-fluid">
	                      		<div class="row">
	                      			删除后将无法恢复，您确定删除所选的图片吗？
	                      		</div>
	                    	</div>
	                   	</div>
                   	</form>
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
		var $table = $('#table'),
			$deleteButton = $('#btn_delete');
			$editButton = $('#btn_edit');
		
		$table.bootstrapTable({
			onCheck: function() {
        		checkboxLeg = $("input[name='btSelectItem']:checked").length;
		        if(checkboxLeg>0) {
	        		$deleteButton.removeAttr('disabled');
	        		$editButton.removeAttr('disabled');
	        	}else {
	        		$deleteButton.attr('disabled','disabled');
	        		$editButton.attr('disabled','disabled');
	        	}
        	},
        	onUncheck: function() {
        		checkboxLeg = $("input[name='btSelectItem']:checked").length;
		        if(checkboxLeg>0) {
	        		$deleteButton.removeAttr('disabled');
	        		$editButton.removeAttr('disabled');
	        	}else {
	        		$deleteButton.attr('disabled','disabled');
	        		$editButton.attr('disabled','disabled');
	        	}
        	},
        	onCheckAll: function() {
        		$deleteButton.removeAttr('disabled');
        		$editButton.removeAttr('disabled');
        	},
        	onUncheckAll: function() {
        		$deleteButton.attr('disabled','disabled');
        		$editButton.attr('disabled','disabled');
        	}
		});
	    
	    $table.bootstrapTable({});
	    
		$("#search_group_list").find("a").each(function(index,e) {
			var str = $(this).attr("data-stopPropagation");
			if(str=="true"){
				$("ul.dropdown-menu").on("click", "[data-stopPropagation]", function(e) {  
       				e.stopPropagation();  
   				});  
			}else {
				$(this).click(function(){
   					$("#search_group_title").text($(this).text());
   					var groupId = $(this).attr("name");
   					if(index==0) {
   						groupId = "";
   					}
   					$("#search_group_list").prev().attr("name",groupId);
   					$table.bootstrapTable('refresh',{url:'${base}/api/picture/list?type=&groupId='+groupId});
				});
			}
		});
		
		
		$("#delBtn").click(function(){
	    	$("#delForm").submit();
	    });
    	
    	function uploadClick() {
    		$("#filePicker").find("label").click();
    	}
    	
    	$(".groupList > ul").metisMenu();
    	
    	function openUpload() {
    		$("#selectImage").show();
    		$("#uploader").hide();
    		$("#gpSelect").hide();
    		$("#add_btn").hide();
    		$("#upload_group_list").css("display","");
    		$(".groupList").attr("disabled",false);
    		$("#startUpload").addClass("disabled");
    		$("#upload_groupId").val();
    	}
    	
    	function startUpload() {
    		$("#upload_group_list").css("display","none");
    		$("#upload_group_list").parent().attr("disabled","disabled");
    		$(".uploadBtn").removeClass("disabled");
			$(".uploadBtn").click();
		}
		
		function rAdd() {
			$("#filePicker2").find("label").click();
		}
    	
    	function bytesToSize(bytes) {
    		if (bytes === 0) return '0 B';
    		var k = 1000, // or 1024
        	sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
        	i = Math.floor(Math.log(bytes) / Math.log(k)); 
   			return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
		}
		
		function operateFormatter(value, row, index) {
    		
    		return [
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" data-target="#editModal" onclick="editPicture(\''+row.id+'\',\''+row.groupId+'\',\''+row.groupTitle+'\')">',
    	                '编辑',
    	            '</a>',
    	            '<a href="#" data-toggle="modal" data-target="#delModal" onclick="delPicture(\''+row.id+'\')">',
    	                '删除',
    	            '</a>'
    	        ].join('');
    	}
    	
    	function picFormatter(value, row, index) {
    		var prefix = '${picture_domain}';
    		return '<img src=\"'+prefix+row.url+'?x-oss-process=style/140_80\" width="140px" height="80px" />';
    	}
    	
    	function editPicture(id, groupId, groupTitle) {
    		
    		$("#editForm [name='pictureId']").val(id);
    		$("#editForm [name='groupId']").val(groupId);
    		if(groupTitle=="undefined") {
    			$("#edit_group_title").text("未分组");
    		}else {
    			$("#edit_group_title").text(groupTitle);
    		}
    		$(".groupList").attr("disabled",false);
    	}
    	
    	function delPicture(id) {
	    	$("#delForm [name='id']").val(id);
	    }
		
		$("#upload_group_list").find("a").each(function(index,e) {
			var str = $(this).attr("data-stopPropagation");
			if(str=="true"){
				$("ul.dropdown-menu").on("click", "[data-stopPropagation]", function(e) {  
       				e.stopPropagation();  
   				});  
			}else {
				$(this).click(function(){
   					$("#upload_groupId").val($(this).attr("name"));
   					$("#upload_group_title").text($(this).text());
				});
			}
		});
		
		$("#edit_group_list").find("a").each(function(index,e) {
			var str = $(this).attr("data-stopPropagation");
			if(str=="true"){
				$("ul.dropdown-menu").on("click", "[data-stopPropagation]", function(e) {  
       				e.stopPropagation();  
   				});  
			}else {
				$(this).click(function(){
   					$("#editForm [name='groupId']").val($(this).attr("name"));
   					$("#edit_group_title").text($(this).text());
				});
			}
		});
		
		function submit() {
			$("#editForm").submit();
		}
		
		function batch_delete() {
			var picture = $table.bootstrapTable('getSelections');
			var picStr = [];
			for(var i=0;i<picture.length;i++) {
		    	picStr.push(picture[i].id);
		    }
		    $("#delForm [name='id']").val(picStr.join());
		}
		
		function batch_edit() {
			$("#editForm [name='groupId']").val();
			var picture = $table.bootstrapTable('getSelections');
			var picStr = [];
			for(var i=0;i<picture.length;i++) {
		    	picStr.push(picture[i].id);
		    }
		    $("#editForm [name='pictureId']").val(picStr.join());
		    $("#edit_group_title").text("未分组");
		}
	    
	</script>
</html>

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

	    <link href="${base}/static/dist/css/sb-admin-2.css" rel="stylesheet">

	    <link href="${base}/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    
	    <link href="${base}/static/vendor/bootstrap-table/css/bootstrap-table.css" rel="stylesheet">
	    
	    <link href="${base}/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    
	    <script src="${base}/static/dist/js/wangEditor.min.js"></script>
	    
	    <style>
	    	.groupTitle {
	    		margin-top: -10px;
	    	}
	    	
	    	.groupList>ul>.active>a, .groupList>ul>.active>a:hover, .groupList>ul>.active>a:focus {
	    		color:#262626;
	    		background-color: #eeeeee;
	    	}
	    	
	    	.groupList {
	    		border: 1px solid #ccc;
			    border-radius: 4px;
			    padding: 6px 12px;
			    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
			    list-style-type:none;
	    	}
	    	
	    	.ck-balloon-panel_toolbar_west {
	    		z-index: 10000;
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
	                    <h1 class="page-header">新闻媒体</h1>
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
            	<!-- /.row -->
            	<div class="row">
	                <div class="col-lg-12">
	                	<div id="toolbar">
           					 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal" onclick="add()">添加新闻</button>
           				</div>
           				<table id="table"
				               data-toggle="table"
				               data-toolbar="#toolbar"
				               data-pagination="true"
				               data-search="true"
				               data-cache="false"
				               data-show-refresh="true"
				               data-show-columns="true"
				               data-query-params-type=""
				               data-page-number="1"
				               data-page-size="10"
				               data-page-list="[10,20,30]"
				               data-side-pagination="server"
				               data-url="${base}/api/news/list">
				            <thead>
				            <tr>
				                <th data-field="title">新闻标题</th>
				                <th data-field="imageUrl" data-formatter="picFormatter">新闻头图</th>
				                <th data-field="introduce">简介</th>
				                <th data-field="httpUrl">跳转链接</th>
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
	
	    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog" style="width:800px">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">编辑新闻</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			<form action="${base}/news/edit" id="editForm" method="post" enctype="multipart/form-data">
                      				<input type="hidden" name="id" id="newsId" />
	                       			<input type="hidden" name="content" id="content"/>
	                       			<input type="file" id="upload_headimage" accept="image/*" name="file" style="display:none" />
	                       			<div class="form-group" style="text-align: center;">
				                        <img src="" style="width: 300px;height: 150px" id="headimage">
				                        <p style="margin: 10px 0px">
				                        	<button type="button" name="button" onclick="uploadImage()" class="btn btn-primary btn-sm">上传头图</button>
				                        </p>
				                    </div>
				                    <div class="form-group">
	                                	<label>新闻标题：</label>
	                                   	<input class="form-control" placeholder="新闻标题" id="title" name="title" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="新闻标题未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>新闻简介：</label>
	                                   	<input class="form-control" placeholder="新闻简介" id="introduce" name="introduce" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="新闻简介未填写">
	                                </div>
	                       			<div class="form-group">
	                                	<label>跳转链接地址：</label>
	                                   	<input class="form-control" placeholder="跳转链接地址" name="httpUrl" id="httpUrl" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="跳转链接地址未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>排序级别：</label>
	                                   	<input class="form-control" placeholder="排序级别" id="level" name="level" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="只能输入数字，按数字从小到大排序">
	                                </div>
	                                <div class="form-group">
	                                	<label>新闻详情：</label>
	                                   	<div id="editor">
        									<p></p>
    									</div>
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
        
        <div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">删除新闻</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			删除后将无法恢复，您确定删除所选的新闻吗？
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
        var E = window.wangEditor
        var editor = new E('#editor')
        editor.customConfig.uploadImgServer = '${base}/api/common/picture/upload';
        editor.customConfig.uploadFileName = 'file';
        editor.customConfig.uploadImgTimeout = 60000;
        editor.customConfig.uploadImgHooks = {
    		customInsert: function (insertImg, result, editor) {
        		var url = '${picture_domain}' + result.data
        		insertImg(url)
    		}
    	}
        editor.create()
    </script>
	<script type="text/javascript">
		var $table = $('#table');
		$(".groupList > ul").metisMenu();
		$("[data-toggle=popover]").focus(function () {
			if($(this).val()!='') {
				if($(this).attr("id")=="level") {
					var level = $("#level").val();
					if(level!=""&&!isRealNum(level)) {
						$(this).popover('show');
					}else {
						$(this).popover('hide');
					}
				}else {
					$(this).popover('hide');
				}
			}else {
				$(this).popover('show');
			} 
		});
		
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
   					$table.bootstrapTable('refresh',{url:'${base}/api/news/list'});
				});
			}
		});
		
		
		
		$("#eGroupList").find("a").each(function(index,e) {
			var str = $(this).attr("data-stopPropagation");
			if(str=="true"){
				$("ul.dropdown-menu").on("click", "[data-stopPropagation]", function(e) {  
       				e.stopPropagation();  
   				});  
			}else {
				$(this).click(function(){
   					$("#editForm [name='groupId']").val($(this).attr("name"));
   					$("#eGTitle").text($(this).text());
				});
			}
		});
	    
	    function add() {
	    	$("#newsId").val("");
	    	$("#title").val("");
	    	$("#introduce").val("");
	    	$("#httpUrl").val("");
	    	$("#level").val();
	    	$("#content").val("");
	    	$("#headimage").attr("src","");
	    	editor.txt.html("");
	    }
	    
	    function edit(index) {
	    	var data = $table.bootstrapTable('getData');
	    	$("#newsId").val(data[index].id);
	    	$("#title").val(data[index].title);
	    	$("#introduce").val(data[index].introduce);
	    	$("#httpUrl").val(data[index].httpUrl);
	    	$("#level").val(data[index].level);
	    	$("#headimage").attr("src",'${picture_domain}'+data[index].imageUrl);
	    	editor.txt.html(data[index].content);
	    }
	    
	    function del(id) {
	    	$("#delBtn").click(function(){
	    		window.location.href='${base}/news/delete?id='+id;
	    	});
	    }
	    
	    function submit() {
	    	var title = $("#title").val();
	    	var content = editor.txt.html();
	    	$("#content").val(content);
	    	var flag = true;
	    	if(title=="") {
	    		flag = false;
	    		$("#title").focus();
	    	}
	    	if(flag) {
	    		$("#editForm").submit();
	    	}
	    }
    	
    	function operateFormatter(value, row, index) {
    		return [
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" data-target="#addModal" onclick="edit(\''+index+'\')">',
    	                '编辑',
    	            '</a>',
    	            '<a href="#" data-toggle="modal" data-target="#delModal" onclick="del(\''+row.id+'\')">',
    	                '删除',
    	            '</a>'
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
		
		function uploadImage() {
			$("#upload_headimage").click(); //隐藏了input:file样式后，点击头像就可以本地上传
		   	$("#upload_headimage").on("change",function(){
				var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
			   	if(objUrl) {
			   		$("#headimage").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
			    }
		    });
	    }  
	    
	    function uploadBanner() {
			$("#upload_banner").click(); //隐藏了input:file样式后，点击头像就可以本地上传
		   	$("#upload_banner").on("change",function(){
				var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
			   	if(objUrl) {
			   		$("#banner").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
			    }
		    });
	    }   
		
		function getObjectURL(file) {
	    	var url = null ;
	    	if (window.createObjectURL!=undefined) { // basic
	    		url = window.createObjectURL(file) ;
	    	} else if (window.URL!=undefined) { // mozilla(firefox)
	    		url = window.URL.createObjectURL(file) ;
	    	} else if (window.webkitURL!=undefined) { // webkit or chrome
	    		url = window.webkitURL.createObjectURL(file) ;
	    	}
	    	return url ;
    	}
    	
    	function picFormatter(value, row, index) {
    		var prefix = '${picture_domain}';
    		return '<img src=\"'+prefix+row.imageUrl+'?x-oss-process=style/140_80\" width="140px" height="80px" />';
    	}       
	    
	</script>
</html>

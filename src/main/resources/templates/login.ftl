<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>华米云</title>
	
	    <!-- Bootstrap Core CSS -->
	    <link href="${base}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	    <!-- MetisMenu CSS -->
	    <link href="${base}/static/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="${base}/static/dist/css/sb-admin-2.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="${base}/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
	    <style type="text/css">
	    	.alertSpan {
			    display: none;
			    width: 100%;
			    outline-style: none;
			}
	    </style>
	
	</head>

	<body>
	
	    <div class="container">
	        <div class="row">
	            <div class="col-md-4 col-md-offset-4">
	                <div class="login-panel panel panel-default">
	                    <div class="panel-heading">
	                        <h3 class="panel-title">华米云系统</h3>
	                    </div>
	                    <div class="panel-body">
                            <fieldset>
                                <div class="form-group">
                                	<span class="alertSpan" id="alertPD" value="" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="账号或密码错误，请重新输入" ></span>
                                    <input class="form-control" placeholder="账号" id="passport" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="请输入账号">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="密码" id="password" type="password" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="请输入密码">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input id="reb" type="checkbox" >记住用户名
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <a href="javascript:void(0)" onclick="checkEuser()" class="btn btn-lg btn-success btn-block">登陆</a>
                            </fieldset>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	
	    <!-- jQuery -->
	    <script src="${base}/static/vendor/jquery/jquery.min.js"></script>
	
	     <!-- Bootstrap Core JavaScript -->
	    <script src="${base}/static/vendor/bootstrap/js/bootstrap.min.js"></script>
	
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="${base}/static/vendor/metisMenu/metisMenu.min.js"></script>
	
	   <!-- Custom Theme JavaScript -->
	    <script src="${base}/static/dist/js/sb-admin-2.js"></script>
	    
	    <script type="text/javascript">
	    
	    	$("[data-toggle=popover]").focus(function () {
				if($(this).val()!='') {
					$(this).popover('hide');
				}else {
					$(this).popover('show');
				} 
			});
			
			if(getCookie("ehuamiPD")!="") {
				$("#passport").val(getCookie("ehuamiPD"));
				$("#reb").click();
			}
			
	    	function checkEuser() {
				var passport = $("#passport").val();
				passport = $.trim(passport);
				var password = $("#password").val();
				
				if(passport==''){
		            $("#passport").focus();
					return;
				}
				if(password=='') {
					$("#password").focus();
					return;
				}
				$.ajax({
		
		             type: "post",
		
		             url: "${base}/api/account/login",
		
		             data: {'mobilenum':passport, 'password':password},
		
		             dataType: "json",
		
		             success: function(data){
		             	var obj = eval(data);
		             	if(obj.code==1) {
		             		if($("#reb").is(':checked')) {
		             			setCookie("ehuamiPD", passport, 24 * 60 * 60 * 7); //一周后失效
		             		}
		             		window.location.href='event/list';
		             	}else {
		             		$(".alertSpan").hide();
							$("#alertPD").css("display","block");
							$("#alertPD").focus();
		             	}
		             }
		         });
			}
			
			function setCookie(name, value, expires, path, domain, secure) {
				var expSecs = expires * 1000;
				var expDate = new Date();
				expDate.setTime(expDate.getTime() + expSecs);
				var expString = ((expires == "-1") ? "" : (";expires=" + expDate.toGMTString()))
				var pathString = ((path == null) ? "" : (";path=" + path))
				var domainString = ((domain == null) ? "" : (";domain=" + domain))
				var secureString = ((secure == true) ? ";secure" : "")
				document.cookie = name + "=" + encodeURI(value) + expString + pathString + domainString + secureString;
			}
			
			function getCookie(name) {
				var result = null;
				var myCookie = document.cookie + ";";
				var searchName = name + "=";
				var startOfCookie = myCookie.indexOf(searchName);
				var endOfCookie;
				if (startOfCookie != -1) {
					startOfCookie += searchName.length;
					endOfCookie = myCookie.indexOf(";", startOfCookie);
					if (endOfCookie == -1) {
						endOfCookie = mycookie.indexOf("&", startOfCookie);
					}
					result = decodeURI(myCookie.substring(startOfCookie, endOfCookie));
				}
				if (result == null) result = "";
				return result;
			}
			
	    </script>
	
	</body>
</html>
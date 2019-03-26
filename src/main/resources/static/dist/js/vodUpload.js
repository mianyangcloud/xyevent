	var uploader;
	var accessKeyId;
    var accessKeySecret;
    var secretToken;
    var expireTime;
    var uploadAuth;
    var uploadAddress;
    var endpoint;
    var bucket;
    var objectPre;
    
    function initUpload() {
        uploader = new VODUpload({
            // 文件上传失败
            'onUploadFailed': function (uploadInfo, code, message) {
                log("onUploadFailed: file:" + uploadInfo.file.name + ",code:" + code + ", message:" + message);
            },
            // 文件上传完成
            'onUploadSucceed': function (uploadInfo) {
            	var videoId = $("#videoId").val();
            	$.ajax({  
        			type:'POST',
        			url:'http://localhost:8092/xyevent/api/video/getVideoInfo',  
        			//url:'https://www.ehuami.cn/mediaCloud/video/getVideoInfo',  
        			dataType:'json',
        			data:{"videoId":videoId},  
        			success:function(data){//返回json结果  
            			var jsonStr = eval(data);
            			clearUpload();
        			}  
    			}); 
            	$("#upLine").parent().hide();
            	$("#upLineText").css("margin-left","0px");
            	$("#upLineText").text("上传成功");
            	$("#upLineText").parent().next().html("");
            	$table.bootstrapTable('refresh');
                log("onUploadSucceed: " + uploadInfo.file.name + ", endpoint:" + uploadInfo.endpoint + ", bucket:" + uploadInfo.bucket + ", object:" + uploadInfo.object);
            },
            // 文件上传进度
            'onUploadProgress': function (uploadInfo, totalSize, uploadedSize) {
            	var num = $("#upLineText").text();
            	num = num.replace("%","");
            	if(num<=Math.ceil(uploadedSize * 100 / totalSize)) {
            		if(Math.ceil(uploadedSize * 100 / totalSize)==100) {
            			$("#upLine").css("width","99%");
            		}else {
            			$("#upLine").css("width",Math.ceil(uploadedSize * 100 / totalSize) + "%");
            		}
            		if(Math.ceil(uploadedSize * 100 / totalSize)==100) {
            			$("#upLineText").text("99%");
            		}else {
            			$("#upLineText").text(Math.ceil(uploadedSize * 100 / totalSize) + "%");
            		}
            	}
                log("onUploadProgress:file:" + uploadInfo.file.name + ", fileSize:" + totalSize + ", percent:" + Math.ceil(uploadedSize * 100 / totalSize) + "%");
            },
            // STS临时账号会过期，过期时触发函数
            'onUploadTokenExpired': function () {
                log("onUploadTokenExpired");
                if (isVodMode()) {
                    // 实现时，从新获取UploadAuth
                    // uploader.resumeUploadWithAuth(uploadAuth);
                } else if (isSTSMode()) {
                    // 实现时，从新获取STS临时账号用于恢复上传
                    // uploader.resumeUploadWithToken(accessKeyId, accessKeySecret, secretToken, expireTime);
                }
            },
            // 开始上传
            'onUploadstarted': function (uploadInfo) {
                if (isVodMode()) {
                    uploader.setUploadAuthAndAddress(uploadInfo, uploadAuth, uploadAddress);
                }
                log("onUploadStarted:" + uploadInfo.file.name + ", endpoint:" + uploadInfo.endpoint + ", bucket:" + uploadInfo.bucket + ", object:" + uploadInfo.object);
            }
        });

        if (isVodMode()) {
            // 点播上传。每次上传都是独立的鉴权，所以初始化时，不需要设置鉴权
            uploader.init();
        } else if (isSTSMode()) {
            // OSS直接上传:STS方式，安全但是较为复杂，建议生产环境下使用。
            // 临时账号过期时，在onUploadTokenExpired事件中，用resumeWithToken更新临时账号，上传会续传。
            uploader.init(accessKeyId, accessKeySecret, secretToken, expireTime);
        } else {
            // OSS直接上传:AK方式，简单但是不够安全，建议测试环境下使用。
            uploader.init(accessKeyId, accessKeySecret);
        }
    };

    var textarea;

    function start() {
        log("start upload.");
        uploader.startUpload();
    }

    function stop() {
        log("stop upload.");
        uploader.stopUpload();
    }

    function resumeWithToken() {
        log("resume upload with token.");
        
        if (isVodMode()) {
            uploader.resumeUploadWithAuth(uploadAuth);
        } else if (isSTSMode()) {
            uploader.resumeUploadWithToken(accessKeyId, accessKeySecret, secretToken, expireTime);
        }
    }

    function clearList() {
        log("clean upload list.");
        uploader.cleanList();
    }

    function getList() {
        log("get upload list.");
        var list = uploader.listFiles();
        for (var i=0; i<list.length; i++) {
            log("file:" + list[i].file.name + ", status:" + list[i].state + ", endpoint:" + list[i].endpoint + ", bucket:" + list[i].bucket + ", object:" + list[i].object);
        }
    }

    function deleteFile() {
        if (document.getElementById("deleteIndex").value) {
            var index = document.getElementById("deleteIndex").value
            log("delete file index:" + index);
            uploader.deleteFile(index);
        }
    }

    function cancelFile() {
        if (document.getElementById("cancelIndex").value) {
            var index = document.getElementById("cancelIndex").value
            log("cancel file index:" + index);
            uploader.cancelFile(index);
        }
    }

    function resumeFile() {
        if (document.getElementById("resumeIndex").value) {
            var index = document.getElementById("resumeIndex").value
            log("resume file index:" + index);
            uploader.resumeFile(index);
        }
    }

    function clearLog() {
        textarea.options.length = 0;
    }

    function log(value) {
    	console.log(value);
//        if (!value) {
//            return;
//        }
//
//        var len = textarea.options.length;
//        if (len > 0 && textarea.options[len-1].value.substring(0, 40) == value.substring(0, 40)) {
//            textarea.remove(len-1);
//        } else if (len > 25) {
//            textarea.remove(0);
//        }
//
//        var option=document.createElement("option");
//        option.value=value,option.innerHTML=value;
//        textarea.appendChild(option);
    }

    function isVodMode() {
        return (uploadAuth && uploadAuth.length > 0);
    }

    function isSTSMode() {
//        var secretToken = document.getElementById("secretToken").value;
//        var expireTime = document.getElementById("expireTime").value;
//        if (!isVodMode()) {
//            if (secretToken && secretToken.length > 0 && expireTime && expireTime.length > 0) {
//                return true;
//            }
//        }
        return false;
    }
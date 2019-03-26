
jQuery(function() {
    var $ = jQuery,    // just in case. Make sure it's not an other libaray.

        $wrap = $('#uploader'),

        // 图片容器
        $queue = $('<ul class="filelist"></ul>')
            .appendTo( $wrap.find('.queueList') ),

        // 状态栏，包括进度和控制按钮
        $statusBar = $wrap.find('.statusBar'),

        // 文件总体选择信息。
        $info = $statusBar.find('.info'),
        
        $cld_1 = $("#cld_img_1"),
        
        $cld_2 = $("#cld_img_2"),

        // 上传按钮
        $upload = $wrap.find('.uploadBtn'),
        $upload_copy = $("#startUpload"); 
    	
    	//继续添加
    	$add_btn = $("#add_btn"); 

        // 没选择文件之前的内容。
        $placeHolder = $wrap.find('.placeholder'),

        // 总体进度条
        $progress = $statusBar.find('.progress').hide(),
        
        //失败文件列表
        failFile = [];
        
        //上传成功数量
        successNum = 0;
        
        //上传失败数量
        errorNum = 0;

        // 添加的文件数量
        fileCount = 0,

        // 添加的文件总大小
        fileSize = 0,
        
        // 优化retina, 在retina下这个值是2
        ratio = window.devicePixelRatio || 1,

        // 缩略图大小
        thumbnailWidth = 110 * ratio,
        thumbnailHeight = 110 * ratio,

        // 可能有pedding, ready, uploading, confirm, done.
        state = 'pedding',

        // 所有文件的进度信息，key为file id
        percentages = {},

        supportTransition = (function(){
            var s = document.createElement('p').style,
                r = 'transition' in s ||
                      'WebkitTransition' in s ||
                      'MozTransition' in s ||
                      'msTransition' in s ||
                      'OTransition' in s;
            s = null;
            return r;
        })(),

        // WebUploader实例
        uploader;

    if ( !WebUploader.Uploader.support() ) {
        alert( 'Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
        throw new Error( 'WebUploader does not support the browser you are using.' );
    }

    // 实例化
    uploader = WebUploader.create({
        pick: {
            id: '#filePicker',
            innerHTML: '选择文件'
        },
        dnd: '#uploader .queueList',
        paste: document.body,

        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        },

        // swf文件路径
        swf: '../../vendor/webuploader/js/Uploader.swf',

        disableGlobalDnd: true,

        chunked: false,
        //server: 'https://www.ehuami.cn/xyevent/api/picture/upload',
        server: 'http://localhost:8092/api/picture/upload', 
        compress: false,
    });

    // 添加“添加文件”的按钮，
    uploader.addButton({
        id: '#filePicker2',
        label: '继续添加'
    });

    // 当有文件添加进来时执行，负责view的创建
    function addFile( file ) {
    	$upload.hide();
    	$("#selectImage").hide();
		$("#uploader").show();
		$("#gpSelect").show();
    	$("#filePicker2").hide();
    	$("#uploader").removeClass("upload_div");
    	$("#uploader").addClass("upload_div_1");
    	$("#startUpload").removeClass("disabled");
    	
        var $li = $( '<li id="' + file.id + '">' +
                '<p class="title">' + file.name + '</p>' +
                '<p class="imgWrap"></p>'+
                '<p class="progress" style="background-color:transparent;border:none;box-shadow:none"><span></span></p>' +
                '</li>' ),

            $btns = $('<div class="file-panel">' +
                '<i class="fa fa-trash fa-fw" style="color: white;font-size: 20px;line-height: 30px;float: right;margin-right:  5px;cursor: pointer;"></i></div>').appendTo( $li ),
            $prgress = $li.find('p.progress span'),
            $wrap = $li.find( 'p.imgWrap' ),
            $info = $('<p class="error"></p>'),

            showError = function( code ) {
                switch( code ) {
                    case 'exceed_size':
                        text = '文件大小超出';
                        errorNum = errorNum + 1;
                        failFile.push(file);
                        break;

                    case 'interrupt':
                        text = '上传暂停';
                        break;

                    default:
                        text = '上传失败，请重试';
                    	errorNum = errorNum + 1;
                    	failFile.push(file);
                        break;
                }

                $info.text( text ).appendTo( $li );
            };

        if ( file.getStatus() === 'invalid' ) {
            showError( file.statusText );
        } else {
            // @todo lazyload
            $wrap.text( '预览中' );
            uploader.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $wrap.text( '不能预览' );
                    return;
                }

                var img = $('<img src="'+src+'">');
                $wrap.empty().append( img );
            }, thumbnailWidth, thumbnailHeight );

            percentages[ file.id ] = [ file.size, 0 ];
            file.rotation = 0;
        }

        file.on('statuschange', function( cur, prev ) {
            if ( prev === 'progress' ) {
                $prgress.hide().width(0);
            } else if ( prev === 'queued' ) {
                $li.off( 'mouseenter mouseleave' );
                $btns.remove();
            }

            // 成功
            if ( cur === 'error' || cur === 'invalid' ) {
                console.log( file.statusText );
                showError( file.statusText );
                percentages[ file.id ][ 1 ] = 1;
            } else if ( cur === 'interrupt' ) {
                showError( 'interrupt' );
            } else if ( cur === 'queued' ) {
                percentages[ file.id ][ 1 ] = 0;
            } else if ( cur === 'progress' ) {
                $info.remove();
                $prgress.css('display', 'block');
            } else if ( cur === 'complete' ) {
                //$li.append( '<span class="success"></span>' );
            }

            $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
        });

        $li.on( 'mouseenter', function() {
            $btns.stop().animate({height: 30});
        });

        $li.on( 'mouseleave', function() {
            $btns.stop().animate({height: 0});
        });

        $btns.on( 'click', 'i', function() {
            var index = $(this).index(),
                deg;

            switch ( index ) {
                case 0:
                    uploader.removeFile( file );
                    return;

                case 1:
                    file.rotation += 90;
                    break;

                case 2:
                    file.rotation -= 90;
                    break;
            }

            if ( supportTransition ) {
                deg = 'rotate(' + file.rotation + 'deg)';
                $wrap.css({
                    '-webkit-transform': deg,
                    '-mos-transform': deg,
                    '-o-transform': deg,
                    'transform': deg
                });
            } else {
                $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                // use jquery animate to rotation
                // $({
                //     rotation: rotation
                // }).animate({
                //     rotation: file.rotation
                // }, {
                //     easing: 'linear',
                //     step: function( now ) {
                //         now = now * Math.PI / 180;

                //         var cos = Math.cos( now ),
                //             sin = Math.sin( now );

                //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                //     }
                // });
            }


        });

        $li.appendTo( $queue );
    }

    // 负责view的销毁
    function removeFile( file ) {
        var $li = $('#'+file.id);

        delete percentages[ file.id ];
        updateTotalProgress();
        $li.off().find('.file-panel').off().end().remove();
    }

    function updateTotalProgress() {
        var loaded = 0,
            total = 0,
            spans = $progress.children(),
            percent;

        $.each( percentages, function( k, v ) {
            total += v[ 0 ];
            loaded += v[ 0 ] * v[ 1 ];
        } );

        percent = total ? loaded / total : 0;
        $("#upLine").text(Math.round( percent * 100 ) + '%');
        $("#upLine").css( 'width', Math.round( percent * 100 ) + '%' );
       // spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
       // spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
        updateStatus();
    }

    function updateStatus() {
        var text = '', stats;
        if ( state === 'ready' ) {
            text = '选中' + fileCount + '张图片，共' +
                    WebUploader.formatSize( fileSize ) + '。';
        } else if ( state === 'confirm' ) {
            stats = uploader.getStats();
            if ( stats.uploadFailNum || errorNum) {
            	//var upFailNum = stats.uploadFailNum;
            	//errorNum = parseInt(errorNum)+parseInt(upFailNum);
                text = '已成功上传' +successNum+ '张照片，'+
                errorNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
            }

        } else {
            stats = uploader.getStats();
            text = '共' + fileCount + '张（' +
                    WebUploader.formatSize( fileSize )  +
                    '），已上传' + successNum + '张';

            if ( stats.uploadFailNum || errorNum) {
                text += '，失败' + errorNum + '张';
            }
        }

        $info.html( text );
    }

    function setState( val ) {
        var file, stats;

        if ( val === state ) {
            return;
        }

        $upload.removeClass( 'state-' + state );
        $upload.addClass( 'state-' + val );
        state = val;

        switch ( state ) {
            case 'pedding':
                $placeHolder.removeClass( 'element-invisible' );
                $queue.parent().removeClass('filled');
                $queue.hide();
                $statusBar.addClass( 'element-invisible' );
                uploader.refresh();
                break;

            case 'ready':
                $placeHolder.addClass( 'element-invisible' );
                $( '#filePicker2' ).removeClass( 'element-invisible');
                $add_btn.show();
                $queue.parent().addClass('filled');
                $queue.show();
                $statusBar.removeClass('element-invisible');
                uploader.refresh();
                break;

            case 'uploading':
                $( '#filePicker2' ).addClass( 'element-invisible' );
                $add_btn.hide();
                $progress.show();
                $upload.text( '暂停上传' );
                $upload_copy.text('暂停上传' );
                
                $upload.removeClass("disabled");
                $upload_copy.removeClass("disabled");
                break;

            case 'paused':
                $progress.show();
                $upload.text( '继续上传' );
                $upload_copy.text('继续上传' );
                $upload.removeClass("disabled");
                $upload_copy.removeClass("disabled");
                break;

            case 'confirm':
                $progress.hide();
                $upload.text( '开始上传' ).addClass( 'disabled' );
                $upload_copy.text('开始上传' ).addClass( 'disabled' );

                stats = uploader.getStats();
                if ( stats.successNum && !stats.uploadFailNum  && !errorNum ) {
                    setState( 'finish' );
                    return;
                }
                break;
            case 'finish':
                stats = uploader.getStats();
                if ( stats.successNum ) {
                    //alert( '上传成功' );
                } else {
                    // 没有成功的图片，重设
                    state = 'done';
                    location.reload();
                }
                break;
        }

        updateStatus();
    }
    
    uploader.onUploadBeforeSend = function( file, data ) {
    	data.groupId = $("#upload_groupId").val();
    	data.eventId = $("#upload_eventId").val();
    };
    
    uploader.onUploadSuccess = function( file, response ) {
    	if(response.code=='1') {
    		successNum = successNum + 1;
    		var $lis = $('#'+file.id);
    		$lis.find('.error').remove();
    		$lis.append( '<span class="success"></span>' );
    	}else {
    		var $lis = $('#'+file.id);
            var text = '上传失败，请重试';
            $infos = $('<p class="error"></p>'),
        	$infos.text( text ).appendTo( $lis );
            errorNum = errorNum + 1;
            failFile.push(file);
    	}
    };

    uploader.onUploadProgress = function( file, percentage ) {
        var $li = $('#'+file.id),
            $percent = $li.find('.progress span');

        $percent.css( 'width', percentage * 100 + '%' );
        percentages[ file.id ][ 1 ] = percentage;
        updateTotalProgress();
    };

    uploader.onFileQueued = function( file ) {
        fileCount++;
        fileSize += file.size;

        if ( fileCount === 1 ) {
            $placeHolder.addClass( 'element-invisible' );
            $statusBar.show();
        }

        addFile( file );
        setState( 'ready' );
        updateTotalProgress();
    };

    uploader.onFileDequeued = function( file ) {
        fileCount--;
        fileSize -= file.size;

        if ( !fileCount ) {
            setState( 'pedding' );
        }

        removeFile( file );
        updateTotalProgress();

    };
    
    uploader.onUploadError = function( file,reason ) {
    	
    }

    uploader.on( 'all', function(  type, arg1, arg2  ) {
        var stats;
        switch( type ) {
            case 'uploadFinished':
                setState( 'confirm' );
                break;

            case 'startUpload':
                setState( 'uploading' );
                break;

            case 'stopUpload':
                setState( 'paused' );
                break;
            

        }
    });

    uploader.onError = function( code ) {
        alert( '错误: ' + code );
    };

    $upload.on('click', function() {
        if ( $(this).hasClass( 'disabled' ) ) {
            return false;
        }

        if ( state === 'ready' ) {
            uploader.upload();
        } else if ( state === 'paused' ) {
            uploader.upload();
        } else if ( state === 'uploading' ) {
            uploader.stop();
        }
    });

    $info.on( 'click', '.retry', function() {
    	var newFailFile = failFile;
    	failFile = [];
    	for(var f=0;f<newFailFile.length;f++) {
    		errorNum = errorNum - 1;
    		uploader.retry(newFailFile[f]);
    	}
    } );

    $info.on( 'click', '.ignore', function() {
    	initUpload();
    } );
    
    $cld_1.click(function(){
    	initUpload();
    });
    
    $cld_2.click(function(){
    	initUpload();
    });
    
    function initUpload() {
    	$("#uploadModal").modal('hide')
    	var upfile = uploader.getFiles();
    	for(var i=0;i<upfile.length;i++) {
    		removeFile(upfile[i]);
    		uploader.removeFile(upfile[i], true );
    		//alert(stats.uploadFailNum);
    	}
        failFile = [];
        successNum = 0;
        errorNum = 0;
        fileCount = 0;
        fileSize = 0;
        $table.bootstrapTable('refresh');
    }

    $upload.addClass( 'state-' + state );
    updateTotalProgress();
});
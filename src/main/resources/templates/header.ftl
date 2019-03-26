<#assign base=request.contextPath />
<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
   		<span class="sr-only">Toggle navigation</span>
    	<span class="icon-bar"></span>
   		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
	</button>
    <a class="navbar-brand" href="${base}/event/list">华米云</a>
</div>
            <!-- /.navbar-header -->

<ul class="nav navbar-top-links navbar-right">
	<!-- /.dropdown -->
    <li class="dropdown">
    	<a class="dropdown-toggle" data-toggle="dropdown" href="#">
        	<i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-user">
        	<li>
        		<a href="${base}/logout"><i class="fa fa-sign-out fa-fw"></i> 注销</a>
        	</li>
        </ul>
	</li>
</ul>
<!-- /.navbar-top-links -->
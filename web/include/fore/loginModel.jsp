
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">删除分类</h4>
      </div>
      <div class="modal-body">
				<strong style="font-size: 18px;">账户登录</strong>
				<div class="input">
					<span class="glyphicon glyphicon-user"></span>
					<input type="text" id="usr" placeholder="手机/会员名/邮箱" />
				</div>
				<div class="input">
					<span class="glyphicon glyphicon-lock"></span>
					<input type="password" id="pwd" placeholder="密码" />
				</div>
				<div style="height: 30px;">
					<div class="forgetPWD">
						<a href="#1">忘记登录密码</a>
					</div>
					<div class="freeRegist">
						<a href="#1">免费注册</a>
					</div>
					
				</div>
				<div id="loginTips" class="loginTips">
					用户名或者密码错误
				</div>
				<button id="login"class="login-btn">登录</button>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消删除</button>
        <button type="button" class="btn btn-primary">
        	<a id="deleteConfirm" href="#5" style="color: white;">继续删除</a>
        </button>
      </div>
    </div>
  </div>
</div>

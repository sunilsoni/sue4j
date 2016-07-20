<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="sue4j 一个基于spring boot + Mybatis的高性能web构建">
    <meta name="author" content="Mr.Su">
    <title>Sue4j 登录页</title>
    <link href="/css/common/bootstrap.min.css" rel="stylesheet">
    <link href="/css/login.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <form class="form-signin" id="mainform" method="post" action="/user/login">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="username" class="sr-only">username</label>
        <input type="text" id="username" name="username"  class="form-control" placeholder="Username">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password">
        <#--  注释掉验证码  -->
        <#--<img class="validateCode" src="/validateCode" />
        <input type="text" name="randomcode" id="valicode" class="form-control" placeholder="valicode">
        --><div class="checkbox">
            <label>
                <input type="checkbox" name="rememberMe" value="true"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" id="submitButton" type="button">Sign in</button>
    </form>
</div> <!-- /container -->

<!-- Small modal -->
<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalTitle">title</h4>
            </div>
            <div class="modal-body" id="modalContent">content
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
    <script type="text/javascript" src="/js/common/jquery.min.js"></script>
    <script type="text/javascript" src="/js/common/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/common/sha256.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>
    <script type="text/javascript">
        $(function () {
            Login.Init();
        })
    </script>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户列表</title>
    <script type="text/javascript" src="http://lib.sinaapp.com/js/jquery/2.0.3/jquery-2.0.3.min.js"></script>

    <script type="text/javascript">
        // 首先,需要创建一个WebSocket连接
        var ws;
        var WS_URL = window.location.host + "/websocket"
        // 如果页面是https,那么必须走wss协议, 否则走ws协议
        if (location.protocol == 'http:') {
            ws = new WebSocket("ws://" + WS_URL);
        } else {
            ws = new WebSocket("wss://" + WS_URL);
        }
        // 连接成功后,会触发onopen回调
        ws.onopen = function (event) {
            console.log("websocket onopen ...");
            // 加入home房间
            ws.send(JSON.stringify({room: 'home', "action": "join"}));
            // 发送消息到指定房间
            ws.send(JSON.stringify({room: 'home', "action": "msg2room", "msg": "大家好!!"}));
        };
        // 收到服务器发来的信息时触发的回调
        ws.onmessage = function (event) {
            console.log("websocket onmessage", event.data);
            var re = JSON.parse(event.data);
            if (re.action == "notify") {
                // 弹个浏览器通知
            } else if (re.action == "msg") {
                // 插入到聊天记录中
            }
        };

        // 定时发个空消息,避免服务器断开连接
        function ws_ping() {
            if (ws) {
                ws.send("{}"); // TODO 断线重连.
            }
        }
        setInterval("ws_ping()", 25000); // 25秒一次就可以了
    </script>

    <script type="text/javascript">
        var pageNumber = 1;
        var pageSize = 10;
        var base = '<%=request.getAttribute("base")%>';
        function user_reload() {
            $.ajax({
                url: base + "/user/queryUserByName.action",
                data: $("#user_query_form").serialize(),
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    $("#user_count").html("共" + data.pager.recordCount + "个用户, 总计" + data.pager.pageCount + "页");
                    var list_html = "";
                    console.log(data.list);
                    for (var i = 0; i < data.list.length; i++) {
                        var user = data.list[i];
                        console.log(user);
                        var tmp = "\n<p>" + user.id + " " + user.name
                            + " <button onclick='user_update(" + user.id + ");'>修改</button> "
                            + " <button onclick='user_delete(" + user.id + ");'>删除</button> "
                            + "</p>";
                        list_html += tmp;
                    }
                    $("#user_list").html(list_html);
                }
            });
        }
        $(function () {
            user_reload();
            $("#user_query_btn").click(function () {
                user_reload();
            });
            $("#user_add_btn").click(function () {
                $.ajax({
                    url: base + "/user/add.action",
                    data: $("#user_add_form").serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data.ok) {
                            user_reload();
                            alert("添加成功");
                        } else {
                            alert(data.msg);
                        }
                    }
                });
            });
        });
        function user_update(userId) {
            var passwd = prompt("请输入新的密码");
            if (passwd) {
                $.ajax({
                    url: base + "/user/update.action",
                    data: {"id": userId, "password": passwd},
                    dataType: "json",
                    success: function (data) {
                        if (data.ok) {
                            user_reload();
                            alert("修改成功");
                        } else {
                            alert(data.msg);
                        }
                    }
                });
            }
        };
        function user_delete(userId) {
            var s = prompt("请输入y确认删除");
            if (s == "y") {
                $.ajax({
                    url: base + "/user/delete.action",
                    data: {"id": userId},
                    dataType: "json",
                    success: function (data) {
                        if (data.ok) {
                            user_reload();
                            alert("删除成功");
                        } else {
                            alert(data.msg);
                        }
                    }
                });
            }
        };
    </script>
</head>
<body>
<div>
    <form action="#" id="user_query_form">
        条件<input type="text" name="name">
        页数<input type="text" name="pageNumber" value="1">
        每页<input type="text" name="pageSize" value="10">
    </form>
    <button id="user_query_btn">查询</button>
    <p>---------------------------------------------------------------</p>
    <p id="user_count"></p>
    <div id="user_list">

    </div>
</div>
<div>
    <p>---------------------------------------------------------------</p>
</div>
<div id="user_add">
    <form action="#" id="user_add_form">
        用户名<input name="name">
        密码<input name="password">
    </form>
    <button id="user_add_btn">新增</button>
</div>
</body>
</html>
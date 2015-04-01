try {
    include("common.js");
    println("_____FILENAME______");
    var request = fetcher.request;

    //封装获取方式
    request.url = "https://www.baidu.com";
    //返回Dom文档
    var dom = fetch.dom(request, "gb2312");

    request.url = "http://www.csdn.com";
    //返回String文档内容
    var text = fetch.text(request, "gb2312");
    //获取xml类型页面的Dom 未遇到过该类页面，只是原样封装了下
    //var res = fetch.xml(request, "gb2312");

    print("-----返回地址重定向地址与页面text-----start")
    request.url = "https://sh.ac.10086.cn/loginex?iscb=1&act=6&t=" + new Date().getTime();
    var res = fetch.location(request, "utf-8");
    println("locationurl:" + res.locationurl)
    println("locationtext:" + res.restext)
    print("-----返回地址重定向地址与页面text-----end")


    var obj = new Object();
    obj.name = "test";
    obj.age = "12";
    obj.tes = "";


    //原始打印信息
    println("原始打印信息:println:" + JSON.stringify(obj))

    //打印信息 在println的基础上稍微封装了下   支持String 和 各种对象；
    //使用本方式打印信息，可以配置调试模式，进行打印控制
    print("print:test12321");
    print(obj);
    // 打印信息 不支持下面这种方式
    //print("obj:"+obj);

    //将简单对象转换为url查询
    print("将简单对象转换为url查询")
    print(unObjToUrl.topost(obj));
    //将字符串转换成对象
    print("将字符串转换成对象")
    var testobj = unEscapeJson.str("name=1&pass=123");
    print(testobj);

    //将url的查询内容部分转换成对象
    print("将url的查询内容部分转换成对象:http://127.0.0.1/index?name=xxxx&pass=23123")
    var testobj = unEscapeJson.url("http://127.0.0.1/index?name=xxxx&pass=23123");
    print(testobj);

    //将Tailor的postData转换成对象
    print("将Tailor的postData转换成对象")
    var request = fetcher.request;
    request.postData = "action=login&page=2";
    var testobj = unEscapeJson.post(request);
    print(testobj);


    //以下只在没有jst引入情况下适用
    //server.locationUrl("http://www.baidu.com","我是弹出信息，locationUrl");
    //server.locationHistoryBack("我是弹出信息，locationHistoryBack");
    //server.setHtmlResult("<div>我了个去哦，setHtmlResult</div>");
    //server.setJsonResult(obj);
    //server.setTextResult("我了个去，setTextResult");
} catch (e) {
    var szLocation = fetcher.request.url;
    var szMessage = e.name + ": " + e.message + "\n at (" + e.fileName + ":" + e.lineNumber + ")";
    log.error(szLocation, szMessage);
    println(szMessage);
}

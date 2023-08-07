# To run the test

Parameters:
* browser ==> chrome [default], firefox, remote-chrome, remote-firefox
* devtools ==> true, false [default]


    ./gradlew run ==> devtools=false, browser=chrome

    browser=firefox devtools=true ./gradlew run ==> run with dev tools enabled using firefox

    browser=remote-chrome devtools=false ./gradlew run ==> run with dev tools disabled using chrome in docker container

# Prerequisites for running the tests using RemoteWebDriver

Use the provided docker containers having selenium-hub, firefox and chrome, 

OR, 

Start your own docker container first, before running the tests

### Starting the docker container

    ./dockerContainers.sh up

### Stopping the docker container

    ./dockerContainers.sh down

## Error seen when enabling DevTools with FirefoxDriver:

Command:

    browser=firefox  devtools=true ./gradlew run

```
Driver info: driver.version: unknown
org.openqa.selenium.WebDriverException: {"id":4,"error":{"message":"Log.clear","data":"RemoteAgentError@chrome://remote/content/cdp/Error.sys.mjs:20:5\nUnknownMethodError@chrome://remote/content/cdp/Error.sys.mjs:103:7\nexecute@chrome://remote/content/cdp/domains/DomainCache.sys.mjs:92:13\nreceiveMessage@chrome://remote/content/cdp/sessions/ContentProcessSession.sys.mjs:79:45\n"},"sessionId":"b6e29c45-5ce1-4d51-8dd2-3c41080e167c"}
Build info: version: '4.11.0', revision: '040bc5406b'
System info: os.name: 'Mac OS X', os.arch: 'x86_64', os.version: '11.5', java.version: '11.0.11'
Driver info: driver.version: unknown
        at org.openqa.selenium.devtools.Connection.handle(Connection.java:260)
        at org.openqa.selenium.devtools.Connection.access$200(Connection.java:57)
        at org.openqa.selenium.devtools.Connection$Listener.lambda$onText$0(Connection.java:224)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
        at java.base/java.lang.Thread.run(Thread.java:829)

Aug 07, 2023 1:19:42 PM org.openqa.selenium.devtools.Connection$Listener lambda$onText$0
WARNING: Unable to process: {"method":"Network.requestWillBeSent","params":{"requestId":"159300337008659","loaderId":"159300337008659","documentURL":"http://google.com/","request":{"url":"http://google.com/","method":"GET","headers":{"host":"google.com","user-agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/116.0","accept":"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8","accept-language":"en-US,en;q=0.5","accept-encoding":"gzip, deflate","connection":"keep-alive"},"hasPostData":false,"isLinkPreload":false},"timestamp":1691394582.404,"type":"Document","frameId":"9"},"sessionId":"b6e29c45-5ce1-4d51-8dd2-3c41080e167c"}
org.openqa.selenium.json.JsonException: Unable to create instance of class org.openqa.selenium.devtools.v115.network.model.RequestWillBeSent
Build info: version: '4.11.0', revision: '040bc5406b'
System info: os.name: 'Mac OS X', os.arch: 'x86_64', os.version: '11.5', java.version: '11.0.11'
Driver info: driver.version: unknown
        at org.openqa.selenium.json.StaticInitializerCoercer.lambda$apply$0(StaticInitializerCoercer.java:61)
        at org.openqa.selenium.json.JsonTypeCoercer.lambda$buildCoercer$6(JsonTypeCoercer.java:163)
        at org.openqa.selenium.json.JsonTypeCoercer.coerce(JsonTypeCoercer.java:145)
        at org.openqa.selenium.json.JsonInput.read(JsonInput.java:305)
        at org.openqa.selenium.devtools.v115.network.Network.lambda$requestWillBeSent$41(Network.java:528)
        at org.openqa.selenium.devtools.Connection.lambda$handle$4(Connection.java:297)
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:177)
        at java.base/java.util.stream.ReferencePipeline$11$1.accept(ReferencePipeline.java:442)
        at java.base/java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1603)
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
        at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
        at org.openqa.selenium.devtools.Connection.handle(Connection.java:287)
        at org.openqa.selenium.devtools.Connection.access$200(Connection.java:57)
        at org.openqa.selenium.devtools.Connection$Listener.lambda$onText$0(Connection.java:224)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
        at java.base/java.lang.Thread.run(Thread.java:829)
Caused by: java.lang.reflect.InvocationTargetException
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.openqa.selenium.json.StaticInitializerCoercer.lambda$apply$0(StaticInitializerCoercer.java:59)
        ... 21 more
Caused by: org.openqa.selenium.json.JsonException: Unable to create instance of class org.openqa.selenium.devtools.v115.network.model.Request
Build info: version: '4.11.0', revision: '040bc5406b'
System info: os.name: 'Mac OS X', os.arch: 'x86_64', os.version: '11.5', java.version: '11.0.11'
Driver info: driver.version: unknown
        at org.openqa.selenium.json.StaticInitializerCoercer.lambda$apply$0(StaticInitializerCoercer.java:61)
        at org.openqa.selenium.json.JsonTypeCoercer.lambda$buildCoercer$6(JsonTypeCoercer.java:163)
        at org.openqa.selenium.json.JsonTypeCoercer.coerce(JsonTypeCoercer.java:145)
        at org.openqa.selenium.json.JsonInput.read(JsonInput.java:305)
        at org.openqa.selenium.devtools.v115.network.model.RequestWillBeSent.fromJson(RequestWillBeSent.java:163)
        ... 26 more
Caused by: java.lang.reflect.InvocationTargetException
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.openqa.selenium.json.StaticInitializerCoercer.lambda$apply$0(StaticInitializerCoercer.java:59)
        ... 30 more
Caused by: java.lang.NullPointerException: initialPriority is required
        at java.base/java.util.Objects.requireNonNull(Objects.java:246)
        at org.openqa.selenium.devtools.v115.network.model.Request.<init>(Request.java:80)
        at org.openqa.selenium.devtools.v115.network.model.Request.fromJson(Request.java:246)
        ... 35 more
```

## Error seen when enabling DevTools for RemoteWebDriver with Firefox:

Command:

    ./dockerContainers.sh start
    browser=remote-firefox  devtools=true ./gradlew run 

```

```
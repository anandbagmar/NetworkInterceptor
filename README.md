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

## Error seen when enabling DevTools for RemoteWebDriver:

```Aug 04, 2023 5:13:21 PM org.openqa.selenium.remote.http.netty.NettyWebSocket lambda$new$0
WARNING: connection timed out: /192.168.0.3:4444
java.net.ConnectException: connection timed out: /192.168.0.3:4444
        at org.asynchttpclient.netty.channel.NettyConnectListener.onFailure(NettyConnectListener.java:179)
        at org.asynchttpclient.netty.channel.NettyChannelConnector$1.onFailure(NettyChannelConnector.java:108)
        at org.asynchttpclient.netty.SimpleChannelFutureListener.operationComplete(SimpleChannelFutureListener.java:28)
        at org.asynchttpclient.netty.SimpleChannelFutureListener.operationComplete(SimpleChannelFutureListener.java:20)
        at io.netty.util.concurrent.DefaultPromise.notifyListener0(DefaultPromise.java:590)
        at io.netty.util.concurrent.DefaultPromise.notifyListeners0(DefaultPromise.java:583)
        at io.netty.util.concurrent.DefaultPromise.notifyListenersNow(DefaultPromise.java:559)
        at io.netty.util.concurrent.DefaultPromise.notifyListeners(DefaultPromise.java:492)
        at io.netty.util.concurrent.DefaultPromise.setValue0(DefaultPromise.java:636)
        at io.netty.util.concurrent.DefaultPromise.setFailure0(DefaultPromise.java:629)
        at io.netty.util.concurrent.DefaultPromise.tryFailure(DefaultPromise.java:118)
        at io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1.run(AbstractNioChannel.java:262)
        at io.netty.util.concurrent.PromiseTask.runTask(PromiseTask.java:98)
        at io.netty.util.concurrent.ScheduledFutureTask.run(ScheduledFutureTask.java:153)
        at io.netty.util.concurrent.AbstractEventExecutor.runTask(AbstractEventExecutor.java:174)
        at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:167)
        at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:470)
        at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:569)
        at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:829)
Caused by: io.netty.channel.ConnectTimeoutException: connection timed out: /192.168.0.3:4444
        at io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1.run(AbstractNioChannel.java:261)
        ... 10 more

Exception in thread "main" org.openqa.selenium.remote.http.ConnectionFailedException: Unable to establish websocket connection to http://192.168.0.3:4444/session/76e2ea399128c44ebbb5321cf63d73e2/se/cdp
Build info: version: '4.11.0', revision: '040bc5406b'
System info: os.name: 'Mac OS X', os.arch: 'x86_64', os.version: '11.5', java.version: '11.0.11'
Driver info: driver.version: unknown
        at org.openqa.selenium.remote.http.netty.NettyWebSocket.<init>(NettyWebSocket.java:116)
        at org.openqa.selenium.remote.http.netty.NettyWebSocket.lambda$create$3(NettyWebSocket.java:147)
        at org.openqa.selenium.remote.http.netty.NettyClient.openSocket(NettyClient.java:104)
        at org.openqa.selenium.devtools.Connection.<init>(Connection.java:83)
        at org.openqa.selenium.devtools.SeleniumCdpConnection.<init>(SeleniumCdpConnection.java:36)
        at org.openqa.selenium.devtools.SeleniumCdpConnection.lambda$create$3(SeleniumCdpConnection.java:103)
        at java.base/java.util.Optional.map(Optional.java:265)
        at org.openqa.selenium.devtools.SeleniumCdpConnection.create(SeleniumCdpConnection.java:103)
        at org.openqa.selenium.devtools.SeleniumCdpConnection.create(SeleniumCdpConnection.java:49)
        at org.openqa.selenium.devtools.DevToolsProvider.getImplementation(DevToolsProvider.java:49)
        at org.openqa.selenium.devtools.DevToolsProvider.getImplementation(DevToolsProvider.java:29)
        at org.openqa.selenium.remote.Augmenter.augment(Augmenter.java:191)
        at org.openqa.selenium.remote.Augmenter.augment(Augmenter.java:162)
        at test.NetworkInterceptorCheck.main(NetworkInterceptorCheck.java:47)
Aug 04, 2023 5:13:21 PM org.openqa.selenium.remote.http.WebSocket$Listener onError
WARNING: connection timed out: /192.168.0.3:4444
java.net.ConnectException: connection timed out: /192.168.0.3:4444
        at org.asynchttpclient.netty.channel.NettyConnectListener.onFailure(NettyConnectListener.java:179)
        at org.asynchttpclient.netty.channel.NettyChannelConnector$1.onFailure(NettyChannelConnector.java:108)
        at org.asynchttpclient.netty.SimpleChannelFutureListener.operationComplete(SimpleChannelFutureListener.java:28)
        at org.asynchttpclient.netty.SimpleChannelFutureListener.operationComplete(SimpleChannelFutureListener.java:20)
        at io.netty.util.concurrent.DefaultPromise.notifyListener0(DefaultPromise.java:590)
        at io.netty.util.concurrent.DefaultPromise.notifyListeners0(DefaultPromise.java:583)
        at io.netty.util.concurrent.DefaultPromise.notifyListenersNow(DefaultPromise.java:559)
        at io.netty.util.concurrent.DefaultPromise.notifyListeners(DefaultPromise.java:492)
        at io.netty.util.concurrent.DefaultPromise.setValue0(DefaultPromise.java:636)
        at io.netty.util.concurrent.DefaultPromise.setFailure0(DefaultPromise.java:629)
        at io.netty.util.concurrent.DefaultPromise.tryFailure(DefaultPromise.java:118)
        at io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1.run(AbstractNioChannel.java:262)
        at io.netty.util.concurrent.PromiseTask.runTask(PromiseTask.java:98)
        at io.netty.util.concurrent.ScheduledFutureTask.run(ScheduledFutureTask.java:153)
        at io.netty.util.concurrent.AbstractEventExecutor.runTask(AbstractEventExecutor.java:174)
        at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:167)
        at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:470)
        at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:569)
        at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:829)
Caused by: io.netty.channel.ConnectTimeoutException: connection timed out: /192.168.0.3:4444
        at io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1.run(AbstractNioChannel.java:261)
        ... 10 more

```
source:https://www.jianshu.com/p/2accc2840a1b

RPC（Remote Procedure Call Protocol），远程过程调用协议，通过网络从远程计算机上请求调用某种服务。

只要是通过网络从而调用远端的某种服务，那么这就是RPC，而不一定就是要http请求，tcp请求，udp请求，soap报文请求都可以，何况这几种请求是有关联的，因此当说自己的分布式系统通信是通过RPC实现的则可以继续问是什么协议/方式实现的RPC

1.解决问题

a.解决分布式系统中，服务之间的调用问题。

b.远程调用时，要能够像本地调用一样方便，让调用者感知不到远程调用的逻辑。



RMI(Remote Method Invocation)，远程方法调用




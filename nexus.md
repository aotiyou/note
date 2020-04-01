1.maven-central（代理资源库）:

> 这是maven的中心仓库，nexus只是一个代理，最后会转发到maven-central库

2.maven-public（1+2=3组资源库）

> 这是一个仓库组，访问这个仓库组的地址其实会访问组内所有仓库

3.1maven-releases（托管资源库）：

> 这是nexus提供的一个默认的存放release版本的jar包的仓库

3.2maven-snapshots（托管资源库）：

> 这是nexus提供的一个默认的存放snapshot版本的jar包的仓库
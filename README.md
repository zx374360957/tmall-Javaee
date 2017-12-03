# 简易天猫商城服务
本项目基于 jsp+servlet+mysql
***
###运行环境
由于本项目是 java project，不能直接在 eclipse 上运行, 需要配置本地 tomcat, 并修改 tomcat 安装目录下 conf/server.xml
在
```
<Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
```
下面添加
```
<Context path="/tmall" docBase="{项目目录}\\web" reloadable="false" />
```
启动 tomcat 后， 在浏览器中输入 ``localhost:8080/tmall/admin ``来访问后台界面
***
### 版本 0.0.2
后台功能完善

### 版本 0.0.1
已完成大部分后台界面

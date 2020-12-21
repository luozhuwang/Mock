为了解决第三方系统的依赖以及本地搭建服务，前台和后台的依赖，提高开发和测试效率，通过使用Mock数据接口，可在只开发了UI的情况下，无须后台服务可以进行产品的演示。
灵活配置URL请求、参数、响应、是否延迟，跟据不同模块来区分，使用过滤器对系统URL和mock-URL进行区分，统计时间段接口请求数量以及时间段接口请求占比。
项目使用主流SSM框架整合thymeleaf模板引擎，前端使用html结合BootStrap、jQuery、echarts操作和动态展示页面，页面简单整洁。
采用Maven对工程进行管理和打包，使用Tomcat部署在Linux机器


访问Mock后台登陆页面
http://127.0.0.1:8080/Mock/system/login
自定义Mock请求地址：
http://127.0.0.1:8080/Mock/test-path/1

初始化SQL参考：目录中mock.sql文件，初始化admin/a123456
功能模块参考：Mock管理平台.doc# Mock

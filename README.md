# 【Mock】
Mock管理平台

访问Mock后台登陆页面
http://127.0.0.1:8080/Mock/system/login

自定义Mock请求地址：
http://127.0.0.1:8080/Mock/test-path/1

初始化SQL参考：目录中mock.sql文件，初始化admin/a123456


文档介绍
为了解决第三方系统的依赖以及本地搭建服务，前台和后台的依赖，提高开发和测试效率，通过使用Mock数据接口，可在只开发了UI的情况下，无须后台服务可以进行产品的演示。灵活配置URL请求、参数、响应、是否延迟，跟据不同模块来区分，使用过滤器对系统URL和mock-URL进行区分，统计时间段接口请求数量以及时间段接口请求占比。
项目使用主流SSM框架整合thymeleaf模板引擎，前端使用html结合BootStrap、jQuery、echarts操作和动态展示页面，页面简单整洁。采用Maven对工程进行管理和打包，使用Tomcat部署在Linux机器

一、	注册/登陆
1.1注册
1.【登陆名】唯一，不允许重复注册
2.登陆名为登陆帐号

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/1.png)

1.2登陆
用户锁定状态不允许登陆，需要管理员恢复

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/2.png)

二、首页
2.1	登陆成功后显示规则，跟据日期范围统计【接口请求统计】和【每日请求统计】数据，默认显示最近一周

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/3.png)
2.2	右上角显示当前”用户名称”，可以修改当前用户”个人信息”和”修改密码”

三、Mock配置
3.1 mock列表
3.1.1	mock列表页面
通过接口名称和模块名称查询对应的数据

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/4.png)

3.1.2	新增mock
Mock列表中页面，点击”新增”按钮，出现此界面
模块默认”默认模块”，如果需要”支付模块”， 点击”添加模块”会跳转到【4.2模块管理】进行添加，URL路径必须以”/”开始

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/5.png)

3.1.3	编辑mock
名称和URL路径不允许修改，以免影响统计，其它同新增功能

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/6.png)

3.1.4	响应列表
显示URL对应所有的响应数据

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/7.png)

3.1.4.1 新增响应
URL--响应列表中页面，点击”新增”按钮，出现此界面：
请求头Header支持Key-value形式
Body参数formData支持Key-value形式，json默认为文本形式
【body】选项为【formData】会默认添加【header】Content-Type为application/x-www-form-urlencoded
【body】选项为【json】会默认添加【header】Content-Type为application/json
是否延迟：勾选后可设置时间，模拟请求延迟场景

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/8.png)

3.1.4.2	编辑响应
响应名称不允许修改，其它字段同【新增响应】

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/9.png)

3.1.4.3	删除响应
数据库软删除
3.1.5	日志列表
跳转到【mock日志列表】并且过滤”命中URL”相同的数据

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/10.png)

3.1.6	删除mock
数据库软删除

3.2 mock请求日志
3.2.1	mock日志列表页面
通过请求URL、请求IP、请求方式、请求时间查询对应的数据
请求URL：是请求过程使用的地址
命中URL：mock配置的地址

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/11.png)

3.2.2	查看详情
请求参数为form-data，每行显示一个参数和值，请求参数为json，显示json数据

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/12.png)

四、基础管理
4.1用户管理
4.1.1用户列表页面
1.管理员提供编辑、删除、重置和用户状态开启和停用功能
2.通过登陆名称、手机号码、用户状态、创建时间查询对应的数据
3.新增用户可以通过页面注册

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/13.png)
![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/14.png)

4.1.2编辑用户
登陆名称不允许编辑，其它字段均可修改

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/15.png)

4.1.3删除用户
数据库软删除
4.1.4重置密码
密码重置后新密码为【123456】

4.2模块管理
4.2.1模块列表页面
1.通过模块名称查询对应的数据
2. mock所属模块，故不提供删除，以免数据混乱

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/16.png)

4.2.2新增模块
模块列表中页面，点击”新增”按钮，出现此界面，模块名称为必填项

![image](https://github.com/luozhuwang/Mock/blob/master/src/main/webapp/static/images/17.png)

4.2.3编辑模块
同新增功能

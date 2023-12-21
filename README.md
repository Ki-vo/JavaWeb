# JavaWeb

姓名：许俊聪

学号：202130441993

部署地址：http://8.134.116.81:5173

测试账号：root

测试口令：1234

## 前端工程

vue-project					根目录

​	|--- src					源代码

​		|--- api				数据交互模块

​			|--- product.js	商品数据交互

​			|--- user.js		用户数据交互

​		|--- assets			静态资源

​		|--- router			路由模块

​		|--- stores			数据共享模块

​			|--- index.js		统一管理

​			|--- user.js		用户信息暂存

​			|--- shopcart.js	购物车信息暂存

​		|--- utils				工具模块

​			|--- jwt.js		请求和响应工具

​		|--- views			视图模块

​			|--- components	复用组件

​				|--- DeregisterConfirm.vue	注销验证界面

​				|--- PageContainer.vue	页面包裹容器

​				|--- ProductEdit.vue		商品编辑界面

​			|--- CartView.vue	购物车界面

​			|--- DetailView.vue	商品详细界面

​			|--- HomeView.vue	主页界面

​			|--- LoginView.vue	登录/注册界面

​			|--- SellerView.vue	商品管理界面

​		|--- App.vue

​		|--- main.js



## 后端工程

springboot

​	|--- src.main.java.com.project	源代码根目录

​		|--- config

​			|--- WebConfig.java	

​		|--- controller

​			|--- DeregisterController.java	注销功能

​			|--- LoginController.java	登录功能

​			|--- ProductController.java	商品管理功能

​			|--- RegisterController.java	注册功能

​			|--- UserController.java	用户管理功能

​		|--- exception

​			|--- GlobalExceptionHandler.java	报错统一管理

​		|--- interceptor

​			|--- LoginCheckInterceptor.java	登录校验拦截器

​		|--- mapper

​			|--- ProductMapper.java	商品Mapper接口

​			|--- UserMapper.java	用户Mapper接口

​		|--- pojo

​			|--- User.java	用户实体类

​			|--- Product.java	商品实体类

​			|--- Result.java	返回结果实体类

​		|--- service

​			|--- impl	Service接口实现类

​				|--- ProductServiceImpl.java

​				|--- UserServiceImpl.java

​			|--- ProductService.java	商品Service接口

​			|--- UserService.java	用户Service接口

​		|--- utils

​			|---JWTUtils.java	JWT令牌生成工具

​		|--- SpringbootApplication.java	启动后端服务
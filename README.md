# JavaWeb

* 姓名：许俊聪

* 学号：202130441993

* 部署地址：http://8.134.116.81:5173/

* 顾客角色
  * 测试账号：user
  * 测试口令：1234

* 销售人员角色
  * 测试账号：10000
  * 测试口令：1234

* 管理员角色
  * 测试账号：10000
  * 测试口令：1234

## 前端工程

vue-project	根目录

* src	源代码
  * api	数据交互模块
    * product.js	商品数据交互
    * user.js		顾客数据交互
    * sales.js		销售人员数据交互
    * admin.js		管理员数据交互
    * order.js		订单数据交互
    * password.js	密码数据交互
    * log.js			日志数据交互 
  * assets			静态资源
  *  router			路由模块
  * stores			数据共享模块
    * index.js		统一管理
    * user.js		用户信息暂存
    * shopcart.js	购物车信息暂存
  * utils				工具模块
    *  jwt.js		请求和响应工具
  *  views			视图模块
    * components	复用组件
      * DeregisterConfirm.vue	注销验证界面
      * PageContainer.vue	页面包裹容器
      * ProductEdit.vue		商品编辑界面
    * AdminView.vue	管理员管理界面
    * CartView.vue	购物车界面
    * DetailView.vue	商品详细界面
    * HomeView.vue	主页界面
    * LoginView.vue	顾客登录/注册界面
    * ManagerLoginView.vue	销售人员/管理员登录界面
    * NavBarView.vue	导航栏界面
    * SalesView.vue	销售人员管理界面
  * App.vue
  * main.js

## 后端工程

* springboot
  * src.main.java.com.project	源代码根目录
    * anno
      * MyLog.java	日志代理类
    * aop
      * LogAspect.java	操作日志管理
    * config
      * WebConfig.java	访问路径过滤
    * controller
      * LoginController.java	顾客登录
      * RegisterController.java	顾客注册
      * DeregisterController.java	顾客注销
      * SalesLoginController.java	销售人员登录
      * AdminLoginController.java	管理员登录
      * PasswordController.java	密码管理
      * BrowseLogController.java	浏览日志管理
      * OperateLogController.java	操作日志管理
      * SalesController.java	销售人员管理
      * OrderController.java	订单管理
      * ProductController.java	商品管理
      * ImageController.java	后端图片URL提供服务
    * exception
      * GlobalExceptionHandler.java	报错统一管理
    * interceptor
      * LoginCheckInterceptor.java	登录校验拦截器
    * mapper
      * ProductMapper.java	商品Mapper接口
      * OrderMapper.java	订单Mapper接口
      * UserMapper.java	顾客Mapper接口
      * SalesMapper.java	销售人员Mapper接口
      * AdminMapper.java	管理员Mapper接口
      * BrowseLogMapper.java	浏览日志Mapper接口
      * OperateLogMapper.java	操作日志Mapper接口
    * pojo
      * User.java	用户实体类
      * Salesman.java	销售人员实体类
      * Admin.java	管理员实体类
      * Product.java	商品实体类
      * Order.java	订单实体类
      * BrowseLog.java	浏览日志实体类
      * OperateLog.java	操作日志实体类
      * Result.java	返回结果实体类
    * service
      * impl	Service接口实现类
        * AdminServiceImpl.java
        * BrowseLogServiceImpl.java
        * EmailServiceImpl.java
        * FileServiceImpl.java
        * OperateLogServiceImpl.java
        * OrderServiceImpl.java
        * ProductServiceImpl.java
        * SalesServiceImpl.java
        * UserServiceImpl.java
      * UserService.java	顾客服务
      * SalesService.java	销售人员服务
      *  AdminService.java	管理员服务
      * BrowseLogService.java	浏览日志服务
      * OperateLogService.java	操作日志服务
      * ProductService.java	商品服务
      * OrderService.java	订单服务
      * EmailService.java	电子邮件服务
      * FileService.java	文件交互服务
    * utils
      * JWTUtils.java	JWT令牌生成工具
      * IpUtils.java	IP地址获取工具
    * SpringbootApplication.java	启动后端服务
# 后端开发脚手架项目
旨在为java语言开发一套通用的，可以快速上手进行业务开发的脚手架，从而使业务开发者免于
考虑一些基础设施的建设，如权限，登陆登出等
### 已使用到的技术
- [x] springcloud 
- [x] gateway(web flutter)
- [x] mybatis-plus
- [x] Dubbo
- [x] swagger
- [x] oauth2.0 + jwt + springSecurtiy
- [x] redis
- [x] mysql

### 后续会补充进去的其他技术
- [ ] 性能监控埋点 prometheus
- [ ] 分布式任务调度 考虑xxl-job
- [ ] 微服务链路追踪 skyWalking/zipkin
- [ ] gateWay限流熔断 目前使用的是gateway自带的RateLimiter 后期考虑sentiel
- [ ] oauth增加其他验证方式 短信/授权码/客户端
- [ ] MQ组件 RacketMQ/RabbitMQ/kafka
- [ ] mysql分库分表组件
- [ ] ....
### 目前框架已经具备的能力
- 单点登陆
- 权限控制
- rpc调用
- 接口限流
- swagger文档 访问地址：http://127.0.0.1/doc.html#/home
### 项目使用简介
如需新增模块,可以参考service-one/service-two  
如采用，希望能为项目点亮一个star
#### 欢迎有相同兴趣的开发者贡献代码
#### [个人博客](https://www.yuque.com/wangzewen-jlbvo/ehmcng)

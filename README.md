# shiro-learn

学习shiro笔记

参看慕课视频:[Shiro安全框架入门](https://www.imooc.com/learn/977)

数据脚本在`resources`目录下的`1.sql`文件中(用户名:test,密码:1234567)

使用技术:
```
spring
springMVC
jdbcTemplate
shiro
redis
```

包含内容:
1. shiro自定义realm
2. shiro加密
3. shiro继承spring
4. shiro注解配置授权
5. shiro自定义过滤器
6. shiro会话管理
7. shiro缓存管理
8. shiro自动登录


重写`PathMatchingFilterChainResolver`和`ShiroFilterFactoryBean`使支持restful风格的url,具体参看:
[shiro自定义过滤器对restful风格实现拦截的问题](https://segmentfault.com/q/1010000014665639)
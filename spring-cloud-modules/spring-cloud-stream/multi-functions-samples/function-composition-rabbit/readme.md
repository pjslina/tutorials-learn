使用这种方法配置，健康检查可以正常工作，但是如果使用binders配置，健康检查会报错，原因未知。
```yaml
spring:
  rabbitmq:
    host: 192.168.16.101
    port: 5672
    username: root
    password: 123456
```

启动成功后，会新建uppercase-in和uppercaseecho-out-0两个exchange，然后新建一个临时队列
uppercase-in.anonymous.DAfk5Rx-Ss2rzmOLqDIGvw

在uppercase-in这个exchange中发布消息(fdf)，控制台会输出如下：
Uppercase: FDF
Echo: FDF
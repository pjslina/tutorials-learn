会生成一个partitioned.destination的exchange,并且会生成4个queue:partitioned.destination.myGroup-0,partitioned.destination.myGroup-1,partitioned.destination.myGroup-2,partitioned.destination.myGroup-3

然后需要启动4个consumer的微服务才行，同时指定--spring.cloud.stream.bindings.listen-in-0.consumer.instanceIndex=1，对应到group的数字，否则消息会堆积在queue中，不会被消费掉
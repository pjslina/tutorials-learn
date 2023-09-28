rabbitmq:
会生成一个持久化的Exchange: dataOut，同时会生成一个临时的队列，绑定到dataOut上，这个队列的名字是随机的（dataOut.anonymous.z68tx4HeRmWdTFF6KPtRIw），这个队列的消息会被转发到kafka的topic: dataOut

kafka：
会生成一个topic: dataIn，我本地单机会有一个partition0
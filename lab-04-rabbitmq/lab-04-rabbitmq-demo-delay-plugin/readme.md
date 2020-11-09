http://www.iocoder.cn/Fight/Spring-Boot-RabbitMQ-deferred-message-implementation-full-version/?self

利用 rabbitmq_delayed_message_exchange 插件实现rabbitmq 延时队列
插件怎么实现的原理是什么？和自己实现的原理一样？


rabbit mq 通过ttl + 死信队列实现 队列中如果过期时间不一样，存在时序问题

rabbitmq-plugins enable rabbitmq_delayed_message_exchange

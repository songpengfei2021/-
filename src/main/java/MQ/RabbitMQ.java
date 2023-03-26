package MQ;

import java.util.concurrent.ExecutionException;

public class RabbitMQ {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
// 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

// 创建连接
        Connection connection = factory.newConnection();

// 创建通道
        Channel channel = connection.createChannel();

// 创建队列
        channel.queueDeclare("queue1", false, false, false, null);

// 发送消息
        byte[] messageBodyBytes = "Hello, RabbitMQ!".getBytes();
        channel.basicPublish("", "queue1", null, messageBodyBytes);

// 接收消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received message: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume("queue1", false, consumer);
    }
}

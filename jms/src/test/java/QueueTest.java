import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.junit.Test;

import javax.jms.*;

/**
 * @author guojiabang
 * @date 2018/8/7 0007
 */
public class QueueTest {

    /**
     * 消息生产者
     * @throws Exception
     */
    @Test
    public void messageProducer() throws JMSException{


        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            // 1.创建连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // 2.创建连接 并开启
            connection = connectionFactory.createConnection();
            connection.start();

            // 3.创建Session(param1:是否开启手动提交事务；接收者签收的模式：AUTO_ACKNOWLEDGE（自动签收）;CLIENT_ACKNOWLEDGE(手动签收))
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 手动签收
            //Session session = connection.createSession(true,Session.CLIENT_ACKNOWLEDGE);

            // 4.创建消息目的地
            Destination destination = session.createQueue("Hello-Queue");

            /*  消息发布者  4. 创建Topic对象
            Topic topic = session.createTopic("weixin-Topic");*/

            // 5.创建生产者
            producer = session.createProducer(destination);

            // DeliveryMode.PERSISTENT 持久传输，MQ服务重启后，未消费的消息还会存在
            // DeliveryMode.NON_PERSISTENT 非持久传输，MQ服务重启后，未消费的消息将会消失
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            // 6.发送信息
            TextMessage textMessage = session.createTextMessage("Hello-MQ2");
            producer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // 7.释放资源
            producer.close();
            session.close();
            connection.close();
        }



    }

    /**
     * 消息消费者
     */
    @Test
    public void consumerMessage()throws Exception{

        // 1.创建工厂
        ActiveMQConnectionFactory  connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // 自定义重试参数
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //设置重试次数
        redeliveryPolicy.setMaximumRedeliveries(3);
        //设置初次重试延迟时间，单位毫秒
        redeliveryPolicy.setInitialRedeliveryDelay(5000);
        //设置每次重试延迟时间，单位毫秒
        redeliveryPolicy.setRedeliveryDelay(5000);

        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);


        // 2.创建并开启连接
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // 3.创建Session(param1:是否开启手动提交事务；接收者签收的模式：AUTO_ACKNOWLEDGE（自动签收）;CLIENT_ACKNOWLEDGE(手动签收))
        final Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        // 手动签收
        // Session session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);

        // 4.创建目的地对象
        Destination destination = session.createQueue("Hello-Queue");

        /*  消息订阅者  4. 创建目的地对象
        Topic topic = session.createTopic("weixin-Topic");*/

        // 5.创建消费者
        MessageConsumer consumer = session.createConsumer(destination);

        // 6.获取信息
        consumer.setMessageListener(new MessageListener(){
            public void onMessage(Message message) {

                try {
                    // 消费者获得消息
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println(textMessage.getText());
                    if("Hello-MQ2".equals(textMessage.getText())){
                        throw new JMSException("error");
                    }

                    // 手动签收
                    textMessage.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();

                   /* //自动签收模式下，不catch异常
                    throw new RuntimeException(e);*/

                    try {

                        // 调用rollback
                        //session.rollback();

                        session.recover();

                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        System.in.read();

        // 7.释放资源
        consumer.close();
        session.close();
        connection.close();


    }

}

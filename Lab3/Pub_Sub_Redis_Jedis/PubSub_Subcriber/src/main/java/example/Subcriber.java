package example;

import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.BasicConfigurator;

public class Subcriber {
    public static void main(String[] args) throws Exception {
        //thiết lập môi trường cho JMS
        BasicConfigurator.configure();
        //thiết lập môi trường cho JJNDI
        Properties settings = new Properties();
        settings.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
        //tạo context
        Context ctx = new InitialContext(settings);
        //lookup JMS connection factory
        Object obj = ctx.lookup("TopicConnectionFactory");
        ConnectionFactory factory = (ConnectionFactory) obj;
        //tạo connection
        Connection con = factory.createConnection("admin", "admin");
        //nối đến MOM
        con.start();
        //tạo session
        Session session = con.createSession(
                /*transaction*/false,
                /*ACK*/Session.CLIENT_ACKNOWLEDGE
        );
        //tạo consumer
        Destination destination = (Destination) ctx.lookup("dynamicTopics/thanthidet");
        System.out.println("Đang lắng nghe...");
        MessageConsumer receiver = session.createConsumer(destination);
        //receiver.receive();//blocked method
        //Cho receiver lắng nghe trên queue, chừng có message thì notify
        receiver.setMessageListener(new MessageListener() {
            @Override
        //có message đến queue, phương thức này được thực thi
            public void onMessage(Message msg) {//msg là message nhận được
                try {
                    if (msg instanceof TextMessage) {
                        TextMessage tm = (TextMessage) msg;
                        String txt = tm.getText();
                        System.out.println("XML= " + txt);
                        msg.acknowledge();//gửi tín hiệu ack
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

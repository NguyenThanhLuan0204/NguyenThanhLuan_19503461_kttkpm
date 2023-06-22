package NetWork.ChatThread;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.BasicConfigurator;
import data.Person;
import helper.XMLConvert;

public class client extends JFrame {
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private MessageConsumer consumer;
    private JTextArea chatArea;
    private JTextField messageField;

    public client() {
       

        setTitle("Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        messageField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });

        //JPanel bottomPanel = new JPanel(new BorderLayout());
        //bottomPanel.add(messageField, BorderLayout.CENTER);
        //bottomPanel.add(sendButton, BorderLayout.EAST);
        //add(bottomPanel, BorderLayout.SOUTH);
       
        setSize(400, 300);
        setVisible(true);
        
        try {
			initializeMessaging();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    private void initializeMessaging() throws Exception {
    	BasicConfigurator.configure();
		// thiết lập môi trường cho JJNDI
		Properties settings = new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		// tạo context
		Context ctx = new InitialContext(settings);
		// lookup JMS connection factory
		Object obj = ctx.lookup("ConnectionFactory");
		ConnectionFactory factory = (ConnectionFactory) obj;
		// lookup destination
		Destination destination = (Destination) ctx.lookup("dynamicQueues/ChatQueue");
		// tạo connection
		Connection con = factory.createConnection("admin", "admin");
		// nối đến MOM
		con.start();
		// tạo session
		Session session = con.createSession(/* transaction */false, /* ACK */Session.CLIENT_ACKNOWLEDGE);
//        //create producer
//        MessageProducer producer = session.createProducer(destination);
//        try {
//            String name = messageField.getText();
//            Person p = new Person(1001,name, new Date());
//            String xml = new XMLConvert<Person>(p).object2XML(p);
//            String txt = messageField.getText().trim();
//            Message msg = session.createTextMessage(txt);
//            producer.send(msg);
//            messageField.setText("");
//            System.out.println(name);
//        } catch (Exception e) {            session.close();
//            con.close();
//            System.out.println("Finished...");
//        }
        // tạo consumer
		MessageConsumer receiver = session.createConsumer(destination);
        receiver.setMessageListener(new MessageListener() {
			// có message đến queue, phương thức này được thực thi
			public void onMessage(Message msg) {// msg là message nhận được
				try {
					if (msg instanceof TextMessage) {
						TextMessage tm = (TextMessage) msg;
						String txt = tm.getText();
						chatArea.append("\nContent: " + txt);
						msg.acknowledge();// gửi tín hiệu ack
					} else if (msg instanceof ObjectMessage) {
						ObjectMessage om = (ObjectMessage) msg;
						System.out.println(om);
					}
//others message type....
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

  

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new client();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

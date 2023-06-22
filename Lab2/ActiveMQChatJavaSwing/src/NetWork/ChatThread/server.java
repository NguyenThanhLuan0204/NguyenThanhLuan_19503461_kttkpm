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

public class server extends JFrame  implements ActionListener {
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private MessageConsumer consumer;
    private JTextArea chatArea;
    private JTextField messageField;

    public server() {
       

        setTitle("Server");
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

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    
        setSize(400, 300);
        setVisible(true);
        
        sendButton.addActionListener(this);
        messageField.addActionListener(this);
    }

    private void initializeMessaging() throws Exception {
    	BasicConfigurator.configure();
		//config environment for JNDI
		Properties settings = new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		//create context
		Context ctx = new InitialContext(settings);
		//lookup JMS connection factory
		ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
		//lookup destination. (If not exist-->ActiveMQ create once)
		Destination destination = (Destination) ctx.lookup("dynamicQueues/ChatQueue");
		//get connection using credential
		Connection con = factory.createConnection("admin", "admin");
		//connect to MOM
		con.start();
		//create session
		Session session = con.createSession(/* transaction */false, /* ACK */Session.AUTO_ACKNOWLEDGE);
		//create producer
		MessageProducer producer = session.createProducer(destination);
		try {
			String name = messageField.getText();
			Person p = new Person(1001,name, new Date());
			String xml = new XMLConvert<Person>(p).object2XML(p);
			String txt = messageField.getText().trim();
			Message msg = session.createTextMessage(txt);
			producer.send(msg);
			messageField.setText("");
//			chatArea.setText(messageField.getText() + "\n" + name);
            TextMessage tm = (TextMessage) msg;
            String txtReceive = tm.getText();
            chatArea.append("\nContent: " + txtReceive);
            msg.acknowledge();
			System.out.println(name);

		} finally {
			session.close();
			con.close();
			System.out.println("Finished...");
		}
    }

    public void actionPerformed(ActionEvent e) {
		try {
			initializeMessaging();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new server();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

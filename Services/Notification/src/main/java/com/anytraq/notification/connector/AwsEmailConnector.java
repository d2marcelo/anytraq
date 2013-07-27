package com.anytraq.notification.connector;

import static com.brtracker.shared.utils.SystemConfiguration.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.simpleemail.AWSJavaMailTransport;
import com.anytraq.notification.service.NotificationEntity;
import com.anytraq.notification.service.NotificationServiceException;
import com.anytraq.notification.service.NotificationServiceException.Reason;
import com.anytraq.notification.service.NotificationTemplateEntity;
import com.brtracker.shared.utils.SystemConfiguration;

@Component("awsEmailConnector")
public class AwsEmailConnector implements NotificationConnector, InitializingBean {

	@Autowired
	@Qualifier("systemConfiguration")
	private SystemConfiguration systemConfiguration;
	
	private Properties props = new Properties();
	private Session session;
	
	@Override
	public void sendNotification(NotificationEntity n, String formattedMessage, NotificationTemplateEntity template) {
		
		String defaultSubject = systemConfiguration.getConfigElement(NOTIFICATION, NOTIFICATION_DEFAULT_SUBJECT);
		
		String from = template.getSender();
		String to = n.getRecipient();
		String body = formattedMessage;
		String senderName = template.getSenderName();
		String mimeType = template.getMimeType();
		String subject = template.getSubject();
		if (StringUtils.isEmpty(subject)) {
			subject = defaultSubject;
		}
		
		System.out.println("Sending email message " + formattedMessage + " delivered to " + n.getRecipient());
		
        Transport transport = null;
        
        try {
        	
            // Create a new Message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, senderName));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
			msg.setContent(body, mimeType);
			msg.saveChanges();

            transport = new AWSJavaMailTransport(session, null);
            transport.connect();
            transport.sendMessage(msg, null);

        } catch (AddressException e) {
        	throw new NotificationServiceException("Caught an AddressException, which means one or more of your "
                    + "addresses are improperly formatted.", e, Reason.AWS_DELIVERY_ERROR);
        } catch (MessagingException e) {
        	throw new NotificationServiceException("Caught a MessagingException, which means that there was a "
                    + "problem sending your message to Amazon's E-mail Service check the "
                    + "stack trace for more information.", e, getReason(e));
        } catch (UnsupportedEncodingException e) {
         	throw new NotificationServiceException("Caught an UnsupportedEncodingException, which means the from"
                    + " email address is improperly formatted.", e, Reason.AWS_DELIVERY_ERROR);
        } finally {
        	if (transport != null) {
        		try {
        			transport.close();
        		} catch (Exception e) {
        		}
        	}
        }
	}

	private Reason getReason(MessagingException e) {
		Reason r = Reason.UNK;
		if (e.getNextException() != null) {
			String detailedMessage = e.getNextException().getMessage();
			if (detailedMessage != null) {
				if (detailedMessage.contains("address is not verified")) {
					r = Reason.INVALID_RECIPIENT;
				}
			}
		}
		return r;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		initialize();
	}

	private void initialize() throws IOException {
		
		// TODO: move out credentials from property file to a keystore
		InputStream credentialsStream = AwsEmailConnector.class
			.getClassLoader().getResourceAsStream("AwsCredentials.properties");
		
        PropertiesCredentials credentials = new PropertiesCredentials(credentialsStream);
        
		props.setProperty("mail.transport.protocol", "aws");
        props.setProperty("mail.aws.user", credentials.getAWSAccessKeyId());
        props.setProperty("mail.aws.password", credentials.getAWSSecretKey());
        
        session = Session.getInstance(props);
	}

}

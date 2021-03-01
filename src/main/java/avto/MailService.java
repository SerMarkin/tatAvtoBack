package avto;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailService {

	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public void sendEmail(Session session, String subject, String body){
		try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("radel.kirgizov.96@bk.ru"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("kadil.12353@gmail.com"));
            // message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse("kadil1.2353@gmail.com"));
            // message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse("kadil12.353@gmail.com"));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
}
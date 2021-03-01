package avto;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private MailService mailService;

	@Value("${email.password}")
	private String passwordEmail;

    private void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping("/sendEmail")
    public void sendEmail(@RequestParam("body") String body) {
		final String fromEmail = "radel.kirgizov.96@bk.ru"; //requires valid gmail id
		final String password = this.passwordEmail;
	    Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.mail.ru"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //

		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
        mailService.sendEmail(session, "Новый заказ !", body);
    }

}

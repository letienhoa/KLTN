package carbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService2 {

	private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private String port;
	@Value("${spring.mail.username}")
	private String email;
	@Value("${spring.mail.password}")
	private String password;

	@Autowired
	ThymeleafService thymeleafService;

	public void sendMailBookingTicket(String emailReceived, String code, String tenTuyen, int gioChay, String slotMails,
			Double giaVe, String ngay) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});

		Message message = new MimeMessage(session);
		try {
			message.setRecipients(Message.RecipientType.TO,
					new InternetAddress[] { new InternetAddress(emailReceived) });

			message.setFrom(new InternetAddress(email));
			message.setSubject("MÃ XÁC THỰC ĐẶT VÉ");
			message.setContent(thymeleafService.getContent(code, tenTuyen, gioChay, slotMails, giaVe, ngay),
					CONTENT_TYPE_TEXT_HTML);
			// message.setContent(thymeleafService.getContent2(),CONTENT_TYPE_TEXT_HTML);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendMailRefundTicket(String emailReceived, String tenTuyen, String gioChay, String ngay,
			String moneyRefundmailString) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});

		Message message = new MimeMessage(session);
		try {
			message.setRecipients(Message.RecipientType.TO,
					new InternetAddress[] { new InternetAddress(emailReceived) });

			message.setFrom(new InternetAddress(email));
			message.setSubject("HỦY VÉ");
			message.setContent(thymeleafService.getContentForRefund(tenTuyen, gioChay, ngay, moneyRefundmailString),
					CONTENT_TYPE_TEXT_HTML);
			// message.setContent(thymeleafService.getContent2(),CONTENT_TYPE_TEXT_HTML);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendMailCancelTuyenXe(String emailReceived, String tenTuyen, String gioChay, String ngay,
			String moneyRefundmailString) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});
		Message message = new MimeMessage(session);
		try {
			message.setRecipients(Message.RecipientType.TO,
					new InternetAddress[] { new InternetAddress(emailReceived) });

			message.setFrom(new InternetAddress(email));
			message.setSubject("HỦY VÉ");
			message.setContent(thymeleafService.getContentCancelTuyenXe(tenTuyen, gioChay, ngay, moneyRefundmailString),
					CONTENT_TYPE_TEXT_HTML);
			// message.setContent(thymeleafService.getContent2(),CONTENT_TYPE_TEXT_HTML);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

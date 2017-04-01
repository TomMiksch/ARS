package edu.uiowa.ars;

import java.io.File;
import java.security.MessageDigest;
import java.util.Properties;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.bind.DatatypeConverter;

public final class SystemSupport {

	public static String md5(final String str) {
		String result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] hash = digest.digest(str.getBytes("UTF-8"));
			return bytesToHex(hash);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	private static String bytesToHex(final byte[] hash) {
		return DatatypeConverter.printHexBinary(hash);
	}

	public static void sendEmail(final String address, final String subject, final String emailContent,
			final File attachment, final String attachmentName) {
		final String username = "iowaair.no.reply@gmail.com";
		final String password = "h78_Ne$wK?x20!ws";

		final Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		final Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("iowaair.no.reply@gmail.com", "Iowa Air"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
			message.setSubject(subject);
			final Multipart multipart = new MimeMultipart();

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(emailContent, "text/html; charset=utf-8");
			multipart.addBodyPart(messageBodyPart);

			if (attachment != null) {
				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachment);
				messageBodyPart.setDataHandler(new javax.activation.DataHandler(source));
				messageBodyPart.setFileName(attachmentName);
				multipart.addBodyPart(messageBodyPart);
			}

			// Send the message.
			message.setContent(multipart);
			Transport.send(message);
		} catch (final Exception e) {
			System.out.println("Error occured. Please try again later.");
		}
	}
}

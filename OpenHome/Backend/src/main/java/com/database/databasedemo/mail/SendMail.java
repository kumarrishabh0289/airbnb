package com.database.databasedemo.mail;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class SendMail {
    public String sendEmail(String subject, String receiver, String body) throws javax.mail.MessagingException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("open.home.us@gmail.com", "openhome123");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(receiver, false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        msg.setSubject(subject);
        msg.setContent("Open Home Notification", "text/html");
        msg.setSentDate(new Date());
        msg.setText(body);

        Transport.send(msg);


        return "Email sent successfully";
    }
}

package dolphin.demo.service;

import dolphin.demo.model.Deposit;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    public EmailService(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }


    void sendEmailToBankEmployee(Deposit deposit) {
        String host = "smtp.gmail.com";
        int port = 465;
        String senderEmail = "bankdeposittest@gmail.com";
        String senderPassword = "Skywalker21";
        String recipientEmail = "524dolphin524@gmail.com";
        String subject = "New Deposit Request";
        String body = "New deposit request " + deposit;

        EmailService emailSender = new EmailService(host, port, senderEmail, senderPassword);
        emailSender.sendEmail(recipientEmail, subject, body);
    }

    void sendEmailToClient(Deposit deposit) {
        EmailService emailSender = new EmailService("smtp.gmail.com", 465, "bankdeposittest@gmail.com",
                "Skywalker21");
        String to = "524dolphin524@gmail.com";
        String subject = "Deposit Update";
        String body = "Your deposit " + deposit.getId() + " has been " +
                deposit.getStatus().toString();
        emailSender.sendEmail(to, subject, body);
    }

    public void sendEmail(String to, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
    }
}


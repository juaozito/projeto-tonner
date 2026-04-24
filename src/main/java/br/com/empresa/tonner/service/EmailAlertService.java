package br.com.empresa.tonner.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailAlertService implements AlertService {
    @Override
    public void sendAlert(String subject, String message) {
        // Configurações do seu servidor de e-mail (exemplo com Gmail)
        String host = "smtp.gmail.com";
        String from = "jlucas@positivo.com.br";
        String password = "sua-senha-de-app"; // IMPORTANTE: Use "Senha de App", não sua senha pessoal!

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("destino@email.com"));
            msg.setSubject(subject);
            msg.setText(message);

            Transport.send(msg);
            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
package camcontrols.comunication;

import camcontrols.dependencies.MotionCameraInterface;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
 */
public class Mailer
{

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    /**
     *
     * http://www.codejava.net/java-ee/javamail/send-e-mail-with-attachment-in-java
     * http://crunchify.com/java-mailapi-example-send-an-email-via-gmail-smtp/
     *
     * @param emailLogin
     * @param emailPassword
     * @param imagePath
     * @param targetEmail
     * @throws AddressException
     * @throws MessagingException
     */
    private static void generateAndSendEmail(String emailLogin, String emailPassword, String targetEmail, String imagePath) throws AddressException, MessagingException
    {

        //Create mail server	
        System.out.println("Setting up mail server properties ...");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        System.out.println("Mail Server Properties have been setup successfully ...");

        //Generate mail	
        System.out.println("Getting mail session ...");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(targetEmail));
        generateMailMessage.setSubject("Movement detected!");
        generateMailMessage.setSentDate(new Date());

        String emailBody = "There was movement spotted!";

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(emailBody, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachPart = new MimeBodyPart();

        if (imagePath != null)
        {
            try
            {
                attachPart.attachFile(imagePath);
            } catch (IOException e)
            {
                System.err.println("Attachment file for mail not found ...");
            }
        }
        multipart.addBodyPart(attachPart);

        generateMailMessage.setContent(multipart);
        System.out.println("Mail Session has been created successfully ...");

        //Send mail	
        System.out.println("Sending mail ...");
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", emailLogin, emailPassword);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    /**
     *
     * @param MotionCamera
     */
    public void sendCamImage(MotionCameraInterface MotionCamera)
    {
        try
        {
            generateAndSendEmail(MotionCamera.getEmailLogin(), MotionCamera.getEmailPassword(), MotionCamera.getAlerEmail(), MotionCamera.getImageFolderPath());
        } catch (MessagingException e)
        {
            System.err.println("Unable to send message ...");
        }
    }

}

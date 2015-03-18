package camcontrols.comunication;

import java.io.IOException;
import java.io.OutputStream;
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
 * @author Dominik from
 * http://crunchify.com/java-mailapi-example-send-an-email-via-gmail-smtp/
 * http://www.codejava.net/java-ee/javamail/send-e-mail-with-attachment-in-java
 */
public class Mailer
{

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void generateAndSendEmail(String imagePath) throws AddressException, MessagingException
    {

//TODO(Dominik):remove messages
//Create mail server	
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        System.out.println("Mail Server Properties have been setup successfully..");

//create mail	
        //TODO(Dominik): maybe add peg files as attachment
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("target mail"));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
        generateMailMessage.setSubject("Movement detected");
        generateMailMessage.setSentDate(new Date());

        String emailBody = "There was movement spotted!";
        //generateMailMessage.setContent(emailBody, "text/html");

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(emailBody, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachPart = new MimeBodyPart();

        //TODO(Dominik):fix
        if (imagePath != null)
        {
            try
            {
                attachPart.attachFile(imagePath);
            } catch (IOException ex)
            {
                System.err.println("Missing attachment file for e-mail");
                ex.printStackTrace();
            }
        }
        multipart.addBodyPart(attachPart);

        generateMailMessage.setContent(multipart);
        System.out.println("Mail Session has been created successfully..");

//send mail	
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "login", "password");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    //TODO(Dominik):remove main
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException
    {
        //TODO(Dominik):you have to set application settings in google security settings
        generateAndSendEmail(null);
        System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");

    }

}

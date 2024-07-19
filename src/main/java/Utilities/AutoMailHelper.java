package Utilities;



import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class AutoMailHelper {

    public static void sendAutoMail(String timeStamp) throws IOException {

        Properties properties = PropertyFileReader.readPropertiesFile(System.getProperty("user.dir")+"/src/main/resources/Properties/MailDetails.properties");

        String from = properties.getProperty("from");
        String password = properties.getProperty("password");
        String recipient = properties.getProperty("to");

        //send credentials
        send(from, password, recipient, timeStamp);

    }

    public static void send(String from, String password, String recipient, String timeStamp){
        Properties props = System.getProperties();
        String host = "pop.rediffmail.com";

        props.put("mail.pop3.host", host);
        props.put("mail.pop3.port", "587");
        props.put("mail.pop3.starttls.enable", "true");

        // Create session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set sender and recipient email addresses
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            // Set email subject and content
            message.setSubject("Test Email from Rediffmail");
            message.setText("Hello,\n\nThis is a test email sent from Rediffmail using JavaMail API.");

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

       /* //create session and mimeMessage object
        Session session = Session.getDefaultInstance(prop);
        MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[recipient.length];

            // To get the array of addresses
            for( int i = 0; i < recipient.length; i++ ) {
                toAddress[i] = new InternetAddress(recipient[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject("Bimabharosa API Test Execution Report");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hii team, /n" +
                    "Please find the attached test execution report of Bima Bharosa Portal /n" +
                    "Thanks & Regards, /n" +
                    "Abhishek Gupta.");


            multipart.addBodyPart(messageBodyPart);

            File attachReport = new File(System.getProperty("user.dir")+"/Excel_Report/Report" + timeStamp + ".xlsx");

            MimeBodyPart attachBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachReport.getAbsolutePath());
            attachBodyPart.setDataHandler(new DataHandler(source));
            attachBodyPart.setFileName("Report"+timeStamp+".xlsx");
            multipart.addBodyPart(attachBodyPart);

            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }*/
    }


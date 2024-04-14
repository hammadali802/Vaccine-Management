package hbv.example.emailandpdfgenerator;


import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MailSender{

        private String from = "coronaapp65@gmail.com";
        private String password = "hhne fbjj oqon gljp";
        private String host = "smtp.gmail.com";

        Properties properties = new Properties();

        {
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.starttls.enable", "true");
        }

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        public void sendMailRegister(String recipientEmail, String lastName) {

            Thread emailThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(from));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
                        message.setSubject("Einrichtung des Kontos für die Buchung von Terminen für die Covid-19-Impfung.");
                        message.setText("Hallo Frau/Herr " + lastName + "," + "\n" + "Ihr Konto wurde erfolgreich erstellt. Klicken Sie " +
                                "auf den folgenden Link um Ihnen enloggen zu können: " + "\n"
                                + "https://informatik.hs-bremerhaven.de/docker-swe3-2022team08-java/");
                        Transport.send(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            emailThread.start();
        }


    public void sendPdfEmail(String recipientEmail, String firstName, String lastName, ByteArrayOutputStream pdfData, int appointmentId) {
        Thread emailThread = new Thread(() -> {
            try {

                String accessUrl = "https://informatik.hs-bremerhaven.de/docker-hamali-java/appointmentData?id=" + appointmentId;
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
                message.setSubject("Terminbestätigung für " + lastName);

                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent("Sehr geehrte/r " + "<b>" + firstName + " " + lastName + "</b>"
                        + ",<br/><br/>anbei erhalten Sie die Terminbestätigung für Ihren Impftermin. "
                        + "Bitte bringen Sie das Dokument zum Impfzentrum mit.<br/><br/>Mit freundlichen Grüßen<br/>"
                        + "Ihr Impfzentrum", "text/html");

                MimeBodyPart pdfAttachment = new MimeBodyPart();
                pdfAttachment.setFileName(lastName + "_appointment.pdf");
                pdfAttachment.setDataHandler(new DataHandler(new ByteArrayDataSource(pdfData.toByteArray(), "application/pdf")));

                MimeBodyPart qrCodeAttachment = new MimeBodyPart();
                qrCodeAttachment.setFileName(lastName + "_qr.png");
                qrCodeAttachment.setDataHandler(new DataHandler(new ByteArrayDataSource(generateQRCodeBytes(accessUrl), "image/png")));

                // Create the multipart message and add body parts
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(pdfAttachment);
                multipart.addBodyPart(qrCodeAttachment);

                message.setContent(multipart);

                // Send the email
                Transport.send(message);

                System.out.println("Email sent successfully.");
            } catch (MessagingException | IOException | WriterException e) {
                e.printStackTrace();
            }
        });
        emailThread.start();
    }

    private byte[] generateQRCodeBytes(String data) throws WriterException, IOException {
        int size = 250;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, size, size);
        BufferedImage qrImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        ImageIO.write(qrImage, "png", outputStream);
        return outputStream.toByteArray();
    }

//public void sendPdfEmail(String recipientEmail, String firstName, String lastName, ByteArrayOutputStream pdfData, int appointmentId) throws MessagingException, WriterException {
//    String accessUrl = "http://localhost:8080/demo_war_exploded/centers" + appointmentId;
//    BitMatrix bitMatrix = new MultiFormatWriter().encode(accessUrl, BarcodeFormat.QR_CODE, 400, 400);
//
//    BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
//
//    MimeMessage message = new MimeMessage(session);
//
//
//    message.setFrom(new InternetAddress(from));
//    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
//    message.setSubject("Appointment Confirmation for " + firstName + " " + lastName);
//
//
//    Multipart multipart = new MimeMultipart();
//    message.setContent(multipart);
//
//    MimeBodyPart pdfAttachment = new MimeBodyPart();
//    pdfAttachment.setDataHandler(new DataHandler(new ByteArrayDataSource(pdfData, "application/pdf")));
//    pdfAttachment.setFileName("appointment_confirmation.pdf");
//    multipart.addBodyPart(pdfAttachment);
//
//    if (qrCodeImage != null) {
//        try {
//            ByteArrayOutputStream qrCodeByteArray = new ByteArrayOutputStream();
//            ImageIO.write(qrCodeImage, "png", qrCodeByteArray);
//
//            MimeBodyPart qrCodeImagePart = new MimeBodyPart();
//            qrCodeImagePart.setHeader("Content-ID", "<qrCodeImage>");
//            qrCodeImagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(qrCodeByteArray, "image/png")));
//            multipart.addBodyPart(qrCodeImagePart);
//
//
//            String htmlContent = "... (Existing email body content) ..."
//                    + "<img src=\"cid:qrCodeImage\" alt=\"QR Code\">";
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setContent(htmlContent, "text/html");
//            multipart.addBodyPart(messageBodyPart);
//        } catch (IOException e) {
//            System.err.println("Error embedding QR code: " + e.getMessage());
//        }
//    } else {
//
//    }
//
//
//    Transport.send(message);
//}

        static ByteArrayOutputStream outputStream;

        {
            try {
                outputStream = PdfGenerator.sendMailConfirmationWithPdf("test","passed","bremerjhaven","testing","12.12.12","12:12");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

                public static void main (String[]args){
                    MailSender mailSender = new MailSender();
                    mailSender.sendPdfEmail("hamadtarar802@gmail.com", "hamad", "ali", outputStream, 1);
                }


            }


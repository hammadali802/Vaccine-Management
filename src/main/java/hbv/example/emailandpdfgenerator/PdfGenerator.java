package hbv.example.emailandpdfgenerator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;

public class PdfGenerator {


    //    public static void main(String[] args) throws IOException {
//        sendMailConfirmationWithPdf("hammad","ali","bremerhaven","pfizer","12.23.23","12:12");
//        System.out.println("successfully created pdf file");
//    }
    public static ByteArrayOutputStream sendMailConfirmationWithPdf( String firstName, String lastName, String impfOrt,
                                                                     String impfung, String date, String time) throws IOException {


        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);

            Paragraph title = new Paragraph("Terminbestätigung für " + firstName + " " + lastName, titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            document.add(new Paragraph("Name: " + lastName));
            document.add(new Paragraph("Vorname: " + firstName));
            document.add(new Paragraph("Impszentrum: " + impfOrt));
            document.add(new Paragraph("Impfstoff: " + impfung));
            document.add(new Paragraph("Datum: " + date));
            document.add(new Paragraph("Uhrzeit: " + time));

//            Paragraph appointmentHeader = new Paragraph("Termine:", titleFont);
//            appointmentHeader.setSpacingAfter(20);
//            document.add(appointmentHeader);
//
//            document.add(new Paragraph("Erster Termin: "));
//            document.add(new Paragraph("Zweiter Termin: "));
//            document.add(new Paragraph("Impfstoff: "));

            Paragraph confirmation = new Paragraph("Vielen Dank für Ihre Terminbuchung \n Bitte bringen Sie Ihren Personalausweis  mit.");
            confirmation.setAlignment(Element.ALIGN_CENTER);
            confirmation.setSpacingBefore(20);
            document.add(confirmation);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return outputStream;
    }


//        byte[] pdfBytes = baos.toByteArray();
//        String fileName = "appointment_confirmation_" + firstName + "_" + lastName + ".pdf";  // Customize filename
//        final Path filePath = Paths.get("/Users/madi/Desktop", fileName);  // Update path
//        try (OutputStream out = Files.newOutputStream(filePath, StandardOpenOption.CREATE_NEW)) {
//            out.write(pdfBytes);
//            System.out.println("PDF saved successfully: " + filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new IOException("Failed to save PDF file: " + e.getMessage());  // Re-throw with clear message
//        } finally {
//            document.close();
//        }

}
package edu.uiowa.ars;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.InputStream;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import edu.uiowa.ars.model.Flight;
import edu.uiowa.ars.model.User;

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
            System.err.println("Error occured. Please try again later.");
        }
    }

    public static InputStream writePdf(final Flight flight, final User user) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PdfWriter writer = PdfWriter.getInstance(document, out);
        document.setPageSize(new Rectangle(700, 250));
        document.setMargins(0, 0, 15, 0);
        document.open();
        // Meta data.
        {
            document.addTitle("Iowa Air Booking");
            document.addSubject("Tickets");
            document.addAuthor("Iowa Air");
            document.addCreator("Iowa Air");
        }
        // Top of the ticket.
        {
            final Font headerLabelFont = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL, BaseColor.GRAY);
            final Font headerValueFont = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
            final PdfPTable table = new PdfPTable(3);

            // Flight id.
            {
                final PdfPTable flightId = new PdfPTable(1);
                final PdfPCell header = new PdfPCell(new Phrase("FLIGHT", headerLabelFont));
                header.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                header.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                header.setBorder(Rectangle.NO_BORDER);
                flightId.addCell(header);

                final PdfPCell value = new PdfPCell(new Phrase(String.valueOf(flight.getId()), headerValueFont));
                value.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                value.setBorder(Rectangle.NO_BORDER);
                flightId.addCell(value);

                final PdfPCell cell = new PdfPCell(flightId);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            // Destination.
            {
                final PdfPTable destination = new PdfPTable(1);
                final PdfPCell header = new PdfPCell(new Phrase("DESTINATION", headerLabelFont));
                header.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                header.setBorder(Rectangle.NO_BORDER);
                destination.addCell(header);

                final PdfPCell value = new PdfPCell(
                        new Phrase(flight.getOrigin() + " -> " + flight.getDestination(), headerValueFont));
                value.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                value.setBorder(Rectangle.NO_BORDER);
                destination.addCell(value);

                final PdfPCell cell = new PdfPCell(destination);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            // Passenger Info.
            {
                final PdfPTable passengerInfo = new PdfPTable(1);
                final PdfPCell header = new PdfPCell(new Phrase("PASSENGER", headerLabelFont));
                header.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                header.setBorder(Rectangle.NO_BORDER);
                passengerInfo.addCell(header);

                final PdfPCell value = new PdfPCell(
                        new Phrase((user.getLastName() + " / " + user.getFirstName()).toUpperCase(), headerValueFont));
                value.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                value.setBorder(Rectangle.NO_BORDER);
                passengerInfo.addCell(value);

                final PdfPCell cell = new PdfPCell(passengerInfo);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            document.add(table);
        }
        // Spacing.
        {
            final PdfPTable table = new PdfPTable(1);
            {
                final PdfPCell cell = new PdfPCell();
                cell.setFixedHeight(20);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            document.add(table);
        }
        // Passenger Information section.
        {
            final Font headerLabelFont = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL, BaseColor.GRAY);
            final Font headerValueFont = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
            final PdfPTable table = new PdfPTable(1);
            {
                final PdfPTable passengerInfo = new PdfPTable(1);
                final PdfPCell header = new PdfPCell(new Phrase("PASSENGER", headerLabelFont));
                header.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                header.setBorder(Rectangle.NO_BORDER);
                passengerInfo.addCell(header);

                final PdfPCell value = new PdfPCell(
                        new Phrase((user.getLastName() + " / " + user.getFirstName()).toUpperCase(), headerValueFont));
                value.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                value.setBorder(Rectangle.NO_BORDER);
                passengerInfo.addCell(value);

                final PdfPCell cell = new PdfPCell(passengerInfo);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            document.add(table);
        }
        // Spacing.
        {
            final PdfPTable table = new PdfPTable(1);
            {
                final PdfPCell cell = new PdfPCell();
                cell.setFixedHeight(20);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            document.add(table);
        }
        // Flight Information Section
        {
            final Font headerLabelFont = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL, BaseColor.GRAY);
            final Font headerValueFont = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
            final PdfPTable table = new PdfPTable(4);

            // Seat.
            {
                final PdfPTable flightId = new PdfPTable(1);
                final PdfPCell header = new PdfPCell(new Phrase("SEAT", headerLabelFont));
                header.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                header.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                header.setBorder(Rectangle.NO_BORDER);
                flightId.addCell(header);

                final PdfPCell value = new PdfPCell(new Phrase("19B", headerValueFont));
                value.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                value.setBorder(Rectangle.NO_BORDER);
                flightId.addCell(value);

                final PdfPCell cell = new PdfPCell(flightId);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            // Gate.
            {
                final PdfPTable flightId = new PdfPTable(1);
                final PdfPCell header = new PdfPCell(new Phrase("GATE", headerLabelFont));
                header.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                header.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                header.setBorder(Rectangle.NO_BORDER);
                flightId.addCell(header);

                final PdfPCell value = new PdfPCell(new Phrase("A6", headerValueFont));
                value.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                value.setBorder(Rectangle.NO_BORDER);
                flightId.addCell(value);

                final PdfPCell cell = new PdfPCell(flightId);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            // Departure Time.
            {
                final PdfPTable destination = new PdfPTable(1);
                final PdfPCell header = new PdfPCell(new Phrase("DEPARTURE", headerLabelFont));
                header.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                header.setBorder(Rectangle.NO_BORDER);
                destination.addCell(header);

                final PdfPCell value = new PdfPCell(new Phrase(flight.getStartTime(), headerValueFont));
                value.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                value.setBorder(Rectangle.NO_BORDER);
                destination.addCell(value);

                final PdfPCell cell = new PdfPCell(destination);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            // Estimated Arrival Time.
            {
                final PdfPTable destination = new PdfPTable(1);
                final PdfPCell header = new PdfPCell(new Phrase("ESTIMATED ARRIVAL", headerLabelFont));
                header.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                header.setBorder(Rectangle.NO_BORDER);
                destination.addCell(header);

                final PdfPCell value = new PdfPCell(new Phrase(flight.getEndTime(), headerValueFont));
                value.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                value.setBorder(Rectangle.NO_BORDER);
                destination.addCell(value);

                final PdfPCell cell = new PdfPCell(destination);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            document.add(table);
        }
        // Spacing.
        {
            final PdfPTable table = new PdfPTable(1);
            {
                final PdfPCell cell = new PdfPCell();
                cell.setFixedHeight(20);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            document.add(table);
        }
        // Bottom section
        {
            final Font headerValueFont = new Font(Font.FontFamily.COURIER, 10, Font.ITALIC);
            final Font logo = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.ITALIC);
            final PdfPTable table = new PdfPTable(2);

            // Barcode
            {
                final String code = "AZH-4792-IHYX-11402";
                final PdfContentByte cb = writer.getDirectContent();
                final Barcode128 code128 = new Barcode128();
                code128.setFont(null);
                code128.setCode(code);
                code128.setCodeType(Barcode128.CODE128);
                final Image code128Image = code128.createImageWithBarcode(cb, null, null);
                final PdfPCell cell = new PdfPCell();
                cell.addElement(new Phrase("ID: " + code, headerValueFont));
                cell.addElement(code128Image);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }
            // Iowa Air logo.
            {
                final PdfPCell logoCell = new PdfPCell();
                logoCell.setBorder(Rectangle.NO_BORDER);
                try (final InputStream in = SystemSupport.class.getResourceAsStream("/logo.jpeg")) {
                    final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    int nRead;
                    byte[] data = new byte[16384];
                    while ((nRead = in.read(data, 0, data.length)) != -1) {
                        buffer.write(data, 0, nRead);
                    }
                    buffer.flush();
                    Image image = Image.getInstance(buffer.toByteArray());
                    image.scalePercent(10);
                    final Paragraph paragraph = new Paragraph();
                    paragraph.add(new Chunk(image, 0, 0, true));
                    paragraph.add(new Phrase("    Iowa Air", logo));
                    paragraph.setAlignment(PdfPCell.ALIGN_CENTER);
                    logoCell.addElement(paragraph);
                }
                table.addCell(logoCell);
            }
            document.add(table);
        }
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}

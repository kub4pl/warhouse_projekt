package com.example.warehouse.service;

import com.example.warehouse.model.Part;
import com.example.warehouse.repository.PartRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Primary
public class QueueingApplicationService implements EmailService {

    @Autowired
    private PartRepository partRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Value("${spring.mail.username}")

    private String email;

    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException, IOException {

        List<Part> dataFromDatabase = getDataFromDatabase();
        String csvData = generateCsv(dataFromDatabase);

        byte[] attachmentData = csvData.getBytes(StandardCharsets.UTF_8);


        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(email);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        // Dodanie załącznika
        helper.addAttachment("example.csv", new ByteArrayResource(attachmentData));

        emailSender.send(message);

    }

    public static byte[] convertFileToByteArray(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] fileBytes = new byte[(int) file.length()];
        fis.read(fileBytes);
        fis.close();
        return fileBytes;
    }

    private List<Part> getDataFromDatabase() {
        return partRepository.findAll();
        // Pobierz dane z bazy danych, np. za pomocą repozytorium
        // Przykład: return myEntityRepository.findAll();
    }

    private String generateCsv(List<Part> data) throws IOException {
        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer);

        // Konwertuj listę obiektów na tablicę pól CSV
        for (Part part : data) {
            csvWriter.writeNext(new String[]{part.getPartDescription(), part.getModelPart(), part.getSupplier()});
        }

        csvWriter.close();
        return writer.toString();
    }
}
//    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException, IOException {
//        File file = new File("C:\\Users\\HP\\Desktop\\warhouse_projekt\\warehouse\\test.txt");
//
//        byte[] attachmentData = convertFileToByteArray(file);
//        String attachmentName = "example.txt";
//        MimeMessage message = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setFrom(email);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(text);
//
//        // Dodanie załącznika
//        helper.addAttachment(attachmentName, new ByteArrayResource(attachmentData));
//
//        emailSender.send(message);
//    }


//    public void sendSimpleMessage(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(email);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);
//    }

//    private final PartRepository partRepository;
//    private final TransactionRepository transactionRepository;

//    MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject("Testowa wiadomość z załącznikiem");
//    MimeBodyPart attachmentPart = new MimeBodyPart();
//            attachmentPart.attachFile(attachmentFilePath);
//
//    MimeBodyPart textPart = new MimeBodyPart();
//            textPart.setText("Witaj, to jest testowa wiadomość z załącznikiem.");
//
//    Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(textPart);
//            multipart.addBodyPart(attachmentPart);
//
//            message.setContent(multipart);
//
//            Transport.send(message);
//    public void queuing() {
//
//        List<Part> partList = partRepository.findAll()

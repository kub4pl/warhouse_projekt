package com.example.warehouse.service;

import com.example.warehouse.model.User;
import com.example.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String email;
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

//    @Autowired
//    private UserRepository userRepository;
//
//    @Transactional
//    public void registerUser(User user) {
//
//        userRepository.save(user);
//        String activationCode = generateActivationCode();
//
//        user.setActivationCode(activationCode);
//        userRepository.save(user);
//
//        sendActivationEmail(user.getEmail(), activationCode);
//    }
//    private String generateActivationCode() {
//        return "ABC123";
//    }
//
//    private void sendActivationEmail(String email, String activationCode) {


}




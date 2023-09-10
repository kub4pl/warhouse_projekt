package com.example.warehouse.service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException, IOException;
}

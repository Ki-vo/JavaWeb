package com.project.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body) throws Exception;
}

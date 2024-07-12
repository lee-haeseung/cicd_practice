package com.umc.domain.user.service;

import com.umc.domain.user.dto.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class MailSendService {
    @Autowired
    private JavaMailSender mailSender;
    private Integer authNumber;

    @Value("${spring.mail.username}")
    private String mailAddr;

    public void makeRandomNumber() {
        Random random = new Random();
        String randomNumber = "";
        for (int i = 0; i < 6; i++) {
            randomNumber += Integer.toString(random.nextInt(10));
        }

        authNumber = Integer.parseInt(randomNumber);
    }

    public String joinEmail(EmailRequestDTO emailRequestDTO) {
        String email = emailRequestDTO.getEmail();

        makeRandomNumber();

        String setFrom = mailAddr;
        String toMail = email;
        String title = "[UMC test code] 회원 가입 인증 메일입니다.";
        String content =
                "반가우이" +
                        "<br><br>" +
                        "인증 번호는 " + authNumber + "입니다." +
                        "<br>";
        mailSend(setFrom, toMail, title, content);
        return Integer.toString(authNumber);
    }

    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

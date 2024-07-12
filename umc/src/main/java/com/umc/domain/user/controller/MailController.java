package com.umc.domain.user.controller;

import com.umc.domain.user.dto.EmailRequestDTO;
import com.umc.domain.user.service.MailSendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/mail")
@RestController
public class MailController {

    private final MailSendService mailSendService;

    @PostMapping()
    public String mailSend(@RequestBody @Valid EmailRequestDTO emailRequestDTO) {
        return mailSendService.joinEmail(emailRequestDTO);
    }

//    @PostMapping("/checking")
//    public String mailCheck(@RequestBody @Valid )

}

package com.example.gvdw.services;

import com.example.gvdw.exceptions.SpringRedditException;
import com.example.gvdw.models.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

  private final JavaMailSender mailSender;
  private final MailContentBuilderService mailContentBuilderService;

  public void sendMail(NotificationEmail notificationEmail){
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
      mimeMessageHelper.setFrom("springreddit@emailll.com");
      mimeMessageHelper.setTo(notificationEmail.getRecipient());
      mimeMessageHelper.setSubject(notificationEmail.getSubject());
      mimeMessageHelper.setText(mailContentBuilderService.build(notificationEmail.getBody()));
    };
    try {
      mailSender.send(messagePreparator);
        log.info("Activation email sent!");
    }catch (MailException e) {
      throw new SpringRedditException("Exception occurred when sending mail to");
    }
  }
}

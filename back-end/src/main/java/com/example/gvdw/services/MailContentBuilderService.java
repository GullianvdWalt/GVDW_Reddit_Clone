package com.example.gvdw.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilderService {

  private final TemplateEngine templateEngine;

  // Take email message
  public String build(String message) {
    Context context = new Context();
    context.setVariable("message", message);
    return templateEngine.process("mailTemplate", context);
  }

}

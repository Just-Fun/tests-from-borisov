package com.conference.spring.test.webassistant.service;

import com.conference.spring.test.webassistant.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

import static com.conference.spring.test.webassistant.controller.AnswerSender.sendAnswer;

/**
 * @author tolkv
 * @version 26/03/2017
 */
@Service
@RequiredArgsConstructor
public class NotificationService {
  private final AnswerCacheService answerCacheService;
  private Deque<SseEmitter> emitters = new ConcurrentLinkedDeque<>();
  private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);


  public boolean addEmitter(SseEmitter sseEmitter) {
    return emitters.add(sseEmitter);
  }

  public boolean removeEmitter(SseEmitter sseEmitter) {
    return emitters.remove(sseEmitter);
  }

  public void notify(Answer answer) {
    setDateIfNotExist(answer);
    if (answer.getQuestionId() != null) {
      answerCacheService.put(answer.getQuestionId(), answer);
    }
    emitters.forEach(sseEmitter -> sendAnswer(answer).to(sseEmitter));
  }

  private void setDateIfNotExist(Answer answer) {
    if (answer.getAnswerDate() == null) {
      answer.setAnswerDate(ZonedDateTime.now().format(formatter));
    }
  }
}

package com.conference.spring.test.yegor256;

import com.conference.spring.test.yegor256.service.Question;
import com.conference.spring.test.yegor256.service.SlowRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tolkv
 * @version 25/03/2017
 */
@RestController
@RequiredArgsConstructor
public class QuestionController {
  private final SlowRecommendationService slowRecommendationService;

  @RequestMapping(path = "/question", method = RequestMethod.POST)
  public ResponseEntity handleQuestion(@RequestBody Question question) {
    slowRecommendationService.addQuestion(question);
    return ResponseEntity.ok().build();
  }
}

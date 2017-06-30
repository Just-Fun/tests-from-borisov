package com.conference.spring.test.webassistant.service.resolvers.question;

import com.conference.spring.test.webassistant.domain.Question;
import com.conference.spring.test.webassistant.domain.QuestionType;
import com.conference.spring.test.webassistant.service.resolvers.words.WordsFrequencyResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tolkv
 * @version 23/02/2017
 */
@Service
@RequiredArgsConstructor
public class TextBasedQuestionTypeResolver implements QuestionTypeResolver {
  private final List<WordsFrequencyResolver> wordsFrequencyResolvers;

  @Override
  public QuestionType resolveType(Question question) {
    int maxMatches=0;
    int winnerPosition = -1;
    for (int i = 0; i < wordsFrequencyResolvers.size(); i++) {
      WordsFrequencyResolver wordsFrequencyResolver = wordsFrequencyResolvers.get(i);
      int match = wordsFrequencyResolver.match(question);
      if (match > maxMatches) {
        maxMatches = match;
        winnerPosition=i;
      }
    }
    if (maxMatches > 0) {
      return wordsFrequencyResolvers.get(winnerPosition).getQuestionType();
    }
    return QuestionType.OTHER;
  }
}

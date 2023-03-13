package br.com.glafoxyquiz.domain.service;

import br.com.glafoxyquiz.domain.exception.EntityNotFoundException;
import br.com.glafoxyquiz.domain.model.Question;
import br.com.glafoxyquiz.domain.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterQuestionService {

    public static final String MSG_ENTITY_NOT_FOUND = "Question with id=%d not found!";
    @Autowired
    private QuestionRepository questionRepository;
    
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public void exclude(Long questionId) {
        try {
            questionRepository.deleteById(questionId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(MSG_ENTITY_NOT_FOUND, questionId));
        }
    }
    
    public Question findOrFail(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_ENTITY_NOT_FOUND, questionId)));
    }
}

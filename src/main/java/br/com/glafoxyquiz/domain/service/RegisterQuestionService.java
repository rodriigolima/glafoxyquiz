package br.com.glafoxyquiz.domain.service;

import br.com.glafoxyquiz.domain.exception.EntityNotFoundException;
import br.com.glafoxyquiz.domain.model.Question;
import br.com.glafoxyquiz.domain.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterQuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public void exclude(Long questionId) {
        try {
            questionRepository.deleteById(questionId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Question with id=%d not found!", questionId));
        }
    }
}

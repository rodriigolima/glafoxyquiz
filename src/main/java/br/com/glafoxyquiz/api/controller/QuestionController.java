package br.com.glafoxyquiz.api.controller;

import br.com.glafoxyquiz.domain.exception.EntityNotFoundException;
import br.com.glafoxyquiz.domain.model.Question;
import br.com.glafoxyquiz.domain.repository.QuestionRepository;
import br.com.glafoxyquiz.domain.service.RegisterQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/questions")
public class QuestionController {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private RegisterQuestionService registerQuestionService;
    
    @GetMapping
    public List<Question> list() {
        return questionRepository.findAll();
    }
    
    @GetMapping("/by_description")
    public List<Question> findByDescription(String description) {
        return questionRepository.findTodasByDescriptionContaining(description);
    }
    
    @PostMapping
    public Question create(@RequestBody Question question) {
        return registerQuestionService.save(question);
    }
    
    @PutMapping("/{questionId}")
    public ResponseEntity<?> update(@PathVariable Long questionId, @RequestBody Question question) {
        try {
            Question questionCurrent = questionRepository.findById(questionId).orElse(null);
            
            if (questionCurrent != null) {
                BeanUtils.copyProperties(question, questionCurrent, "id");
                
                questionCurrent = registerQuestionService.save(questionCurrent);
                return ResponseEntity.ok(questionCurrent);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long questionId) {
        registerQuestionService.exclude(questionId);
    }
    
    
}

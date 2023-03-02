package br.com.glafoxyquiz.domain.repository;

import br.com.glafoxyquiz.domain.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findTodasByDescriptionContaining(String description);
}

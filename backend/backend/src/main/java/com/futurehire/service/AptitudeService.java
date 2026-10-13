package com.futurehire.service;

import com.futurehire.entity.AptitudeQuestion;
import com.futurehire.entity.AptitudeQuiz;
import com.futurehire.entity.QuizResult;
import com.futurehire.entity.User;
import com.futurehire.exception.ResourceNotFoundException;
import com.futurehire.repository.AptitudeQuestionRepository;
import com.futurehire.repository.AptitudeQuizRepository;
import com.futurehire.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AptitudeService {

    @Autowired
    private AptitudeQuizRepository quizRepository;

    @Autowired
    private AptitudeQuestionRepository questionRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    public List<AptitudeQuiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public AptitudeQuiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
    }

    public List<Map<String, Object>> getQuizQuestionsForStudent(Long quizId) {
        AptitudeQuiz quiz = getQuizById(quizId);
        return quiz.getQuestions().stream().map(q -> {
            Map<String, Object> dto = new HashMap<>();
            dto.put("id", q.getId());
            dto.put("questionText", q.getQuestionText());
            dto.put("options", Arrays.asList(q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD()));
            return dto;
        }).collect(Collectors.toList());
    }

    public QuizResult submitQuizAnswers(User student, Long quizId, Map<Long, Integer> studentAnswers) {
        AptitudeQuiz quiz = getQuizById(quizId);
        List<AptitudeQuestion> questions = quiz.getQuestions();

        int correctCount = 0;
        for (AptitudeQuestion q : questions) {
            Integer studentAnswerIndex = studentAnswers.get(q.getId());
            if (studentAnswerIndex != null && studentAnswerIndex == q.getCorrectOptionIndex()) {
                correctCount++;
            }
        }

        int total = questions.isEmpty() ? 1 : questions.size();
        double pct = Math.round((correctCount * 100.0 / total) * 10.0) / 10.0;

        QuizResult result = new QuizResult(student, quiz, correctCount, total, pct);
        return quizResultRepository.save(result);
    }

    public List<QuizResult> getStudentResults(User student) {
        return quizResultRepository.findByStudentId(student.getId());
    }

    public AptitudeQuiz createQuiz(AptitudeQuiz quiz) {
        return quizRepository.save(quiz);
    }

    public AptitudeQuestion addQuestionToQuiz(Long quizId, AptitudeQuestion question) {
        AptitudeQuiz quiz = getQuizById(quizId);
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}

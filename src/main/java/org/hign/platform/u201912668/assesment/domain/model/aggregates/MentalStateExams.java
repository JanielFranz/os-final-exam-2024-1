package org.hign.platform.u201912668.assesment.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hign.platform.u201912668.assesment.domain.model.commands.CreateMentalStateExamCommand;
import org.hign.platform.u201912668.assesment.domain.model.valueobjects.ExaminerNationalProviderIdentifier;
import org.hign.platform.u201912668.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;


@Getter
@NoArgsConstructor
@Entity
public class MentalStateExams extends AuditableAbstractAggregateRoot<MentalStateExams> {
    @NotNull
    private Long patientId;

    @Embedded
    private ExaminerNationalProviderIdentifier examinerNationalProviderIdentifier;

    @NotNull
    private LocalDate examDate;

    @NotNull
    private Integer orientationScore;

    @NotNull
    private Integer registrationScore;

    @NotNull
    private Integer attentionAndCalculationScore;

    @NotNull
    private Integer recallScore;

    @NotNull
    private Integer languageScore;

    public MentalStateExams (CreateMentalStateExamCommand command) {
        this.patientId = command.patientId();
        this.examinerNationalProviderIdentifier = new ExaminerNationalProviderIdentifier(command.examinerNationalProviderIdentifier());

        validateIsPreviousDate(command.examDate());
        this.examDate = command.examDate();

        validateScores(command.orientationScore(), command.registrationScore(), command.attentionAndCalculationScore(),
                command.recallScore(),command.languageScore());
        this.orientationScore = command.orientationScore();
        this.registrationScore = command.registrationScore();
        this.attentionAndCalculationScore = command.attentionAndCalculationScore();
        this.recallScore = command.recallScore();
        this.languageScore = command.languageScore();
    }

    public void validateIsPreviousDate(LocalDate examDate) {
        if(examDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Exam date cannot be a future date");
        }
    }

    public void validateScores(Integer orientationScore, Integer registrationScore, Integer attentionAndCalculationScore,
                               Integer recallScore, Integer languageScore) {
        validateScore(orientationScore, 0, 10, "Orientation score must be between 0 and 10");
        validateScore(registrationScore, 0, 3, "Registration score must be between 0 and 3");
        validateScore(attentionAndCalculationScore, 0, 5, "Attention and calculation score must be between 0 and 5");
        validateScore(recallScore, 0, 3, "Recall score must be between 0 and 3");
        validateScore(languageScore, 0, 9, "Language score must be between 0 and 9");
    }

    private void validateScore(Integer score, Integer min, Integer max, String errorMessage) {
        if(score == null || score < min || score > max) {
            throw new IllegalArgumentException(errorMessage);
        }
    }


}

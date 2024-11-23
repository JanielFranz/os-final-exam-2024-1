package org.hign.platform.u201912668.personnel.application.internal.commandservices;

import org.hign.platform.u201912668.personnel.domain.model.aggregates.Examiner;
import org.hign.platform.u201912668.personnel.domain.model.commands.CreateExaminerCommand;
import org.hign.platform.u201912668.personnel.domain.model.valueobjects.NationalProvideIdentifier;
import org.hign.platform.u201912668.personnel.domain.services.ExaminerCommandService;
import org.hign.platform.u201912668.personnel.infrastructure.persistence.jpa.repositories.ExaminerRepository;
import org.springframework.stereotype.Service;

@Service
public class ExaminerCommandServiceImpl implements ExaminerCommandService {
    private final ExaminerRepository examinerRepository;

    public ExaminerCommandServiceImpl(ExaminerRepository examinerRepository) {
        this.examinerRepository = examinerRepository;
    }

    @Override
    public Long handle(CreateExaminerCommand command) {
        var nationalProvideIdentifier = new NationalProvideIdentifier(command.nationalProvideIdentifier());

        if(examinerRepository.existsByNationalProvideIdentifier(nationalProvideIdentifier))
            throw new IllegalArgumentException("Examiner with the same national provide identifier already exists");

        var examiner = new Examiner(command);

        try{
            examinerRepository.save(examiner);
            return examiner.getId();
        }catch(Exception e) {
            throw new IllegalArgumentException("Failed to save examiner: %s".formatted(e.getMessage()));
        }
    }
}

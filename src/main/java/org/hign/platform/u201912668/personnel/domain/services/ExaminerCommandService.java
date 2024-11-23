package org.hign.platform.u201912668.personnel.domain.services;

import org.hign.platform.u201912668.personnel.domain.model.aggregates.Examiner;
import org.hign.platform.u201912668.personnel.domain.model.commands.CreateExaminerCommand;
import org.springframework.scheduling.config.Task;

public interface ExaminerCommandService {
    Long handle (CreateExaminerCommand command);
}

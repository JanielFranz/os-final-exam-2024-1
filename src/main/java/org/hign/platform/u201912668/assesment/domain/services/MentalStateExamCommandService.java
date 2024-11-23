package org.hign.platform.u201912668.assesment.domain.services;

import org.hign.platform.u201912668.assesment.domain.model.commands.CreateMentalStateExamCommand;

public interface MentalStateExamCommandService {
    Long handle (CreateMentalStateExamCommand command);
}

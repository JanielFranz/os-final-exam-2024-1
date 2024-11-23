package org.hign.platform.u201912668.assesment.domain.model.commands;

import java.time.LocalDate;


public record CreateMentalStateExamCommand(
        Long patientId,
        String examinerNationalProviderIdentifier,
        LocalDate examDate,
        Integer orientationScore,
        Integer registrationScore,
        Integer attentionAndCalculationScore,
        Integer recallScore,
        Integer languageScore
) { }

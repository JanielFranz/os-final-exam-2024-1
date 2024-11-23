package org.hign.platform.u201912668.assesment.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.Date;

public record MentalStateExamResource(
        Long mentalStateExamId,
        Long patientId,
        String examinerNationalProviderIdentifier,
        LocalDate examDate,
        Integer orientationScore,
        Integer registrationScore,
        Integer attentionAndCalculationScore,
        Integer recallScore,
        Integer languageScore
) {
}

package org.hign.platform.u201912668.assesment.interfaces.rest.transform;

import org.hign.platform.u201912668.assesment.domain.model.aggregates.MentalStateExams;
import org.hign.platform.u201912668.assesment.interfaces.rest.resources.MentalStateExamResource;

public class MentalStateExamResourceFromEntityAssembler {
    public static MentalStateExamResource toResourceFromEntity (MentalStateExams entity) {
        return new MentalStateExamResource(entity.getId(), entity.getPatientId()
        , entity.getExaminerNationalProviderIdentifier().examinerNationalProviderIdentifier(),
                entity.getExamDate(), entity.getOrientationScore(),
                entity.getRegistrationScore()
                ,entity.getAttentionAndCalculationScore(),
                entity.getRecallScore(), entity.getLanguageScore());

    }
}

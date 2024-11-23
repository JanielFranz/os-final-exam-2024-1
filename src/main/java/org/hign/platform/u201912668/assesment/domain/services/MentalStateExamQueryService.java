package org.hign.platform.u201912668.assesment.domain.services;

import org.hign.platform.u201912668.assesment.domain.model.aggregates.MentalStateExams;
import org.hign.platform.u201912668.assesment.domain.model.queries.GetMentalStateExamByIdQuery;

import java.util.Optional;

public interface MentalStateExamQueryService {
    Optional<MentalStateExams> handle(GetMentalStateExamByIdQuery query);
}

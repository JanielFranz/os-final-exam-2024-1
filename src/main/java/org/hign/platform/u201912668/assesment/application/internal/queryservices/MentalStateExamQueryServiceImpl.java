package org.hign.platform.u201912668.assesment.application.internal.queryservices;

import org.hign.platform.u201912668.assesment.domain.model.aggregates.MentalStateExams;
import org.hign.platform.u201912668.assesment.domain.model.queries.GetMentalStateExamByIdQuery;
import org.hign.platform.u201912668.assesment.domain.services.MentalStateExamQueryService;
import org.hign.platform.u201912668.assesment.infrastructure.MentalStateExamRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MentalStateExamQueryServiceImpl implements MentalStateExamQueryService {
    private final MentalStateExamRepository mentalStateExamRepository;

    public MentalStateExamQueryServiceImpl(MentalStateExamRepository mentalStateExamRepository){
        this.mentalStateExamRepository = mentalStateExamRepository;
    }

    @Override
    public Optional<MentalStateExams> handle(GetMentalStateExamByIdQuery query) {
        return mentalStateExamRepository.findById(query.mentalStateExamId());
    }
}

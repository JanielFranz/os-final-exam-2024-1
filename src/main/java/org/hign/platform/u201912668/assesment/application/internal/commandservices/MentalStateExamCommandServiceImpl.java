package org.hign.platform.u201912668.assesment.application.internal.commandservices;

import org.hign.platform.u201912668.assesment.application.internal.outboundservices.acl.ExternalPersonnelService;
import org.hign.platform.u201912668.assesment.domain.model.aggregates.MentalStateExams;
import org.hign.platform.u201912668.assesment.domain.model.commands.CreateMentalStateExamCommand;
import org.hign.platform.u201912668.assesment.domain.services.MentalStateExamCommandService;
import org.hign.platform.u201912668.assesment.infrastructure.MentalStateExamRepository;
import org.springframework.stereotype.Service;

@Service
public class MentalStateExamCommandServiceImpl implements MentalStateExamCommandService {

    private final ExternalPersonnelService externalPersonnelService;
    private final MentalStateExamRepository mentalStateExamRepository;

    public MentalStateExamCommandServiceImpl(ExternalPersonnelService externalPersonnelService, MentalStateExamRepository mentalStateExamRepository) {
        this.externalPersonnelService = externalPersonnelService;
        this.mentalStateExamRepository = mentalStateExamRepository;
    }


    @Override
    public Long handle(CreateMentalStateExamCommand command) {
        var examinerNationalIdentifier = command.examinerNationalProviderIdentifier();
        if(!externalPersonnelService.isPersonnelExistsByNationalIdentifier(examinerNationalIdentifier)) {
            throw new IllegalArgumentException("Examiner not found");
        }

        var mentalStateExam = new MentalStateExams(command);
        try {
            mentalStateExamRepository.save(mentalStateExam);
            return mentalStateExam.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to save mental state exam");
        }
    }
}

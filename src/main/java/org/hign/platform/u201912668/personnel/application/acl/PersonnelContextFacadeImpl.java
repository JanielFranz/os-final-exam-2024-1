package org.hign.platform.u201912668.personnel.application.acl;

import org.hign.platform.u201912668.personnel.domain.model.valueobjects.NationalProvideIdentifier;
import org.hign.platform.u201912668.personnel.infrastructure.persistence.jpa.repositories.ExaminerRepository;
import org.hign.platform.u201912668.personnel.interfaces.acl.PersonnelContextFacade;
import org.springframework.stereotype.Service;

@Service
public class PersonnelContextFacadeImpl implements PersonnelContextFacade {

    private final ExaminerRepository examinerRepository;

    public PersonnelContextFacadeImpl(ExaminerRepository examinerRepository) {
        this.examinerRepository = examinerRepository;
    }

    @Override
    public boolean isPersonnelExist(String nationalProviderIdentifier) {
        var ProviderIdentifier = new NationalProvideIdentifier(nationalProviderIdentifier);
        return examinerRepository.existsByNationalProvideIdentifier(ProviderIdentifier);
    }
}

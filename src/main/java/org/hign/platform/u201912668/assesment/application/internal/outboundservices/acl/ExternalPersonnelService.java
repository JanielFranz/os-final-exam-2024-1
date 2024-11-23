package org.hign.platform.u201912668.assesment.application.internal.outboundservices.acl;

import org.hign.platform.u201912668.personnel.interfaces.acl.PersonnelContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalPersonnelService {
    private final PersonnelContextFacade personnelContextFacade;

    public ExternalPersonnelService(PersonnelContextFacade personnelContextFacade) {
        this.personnelContextFacade = personnelContextFacade;
    }


    public boolean isPersonnelExistsByNationalIdentifier(String nationalIdentifier) {
        return personnelContextFacade.isPersonnelExist(nationalIdentifier);
    }
}

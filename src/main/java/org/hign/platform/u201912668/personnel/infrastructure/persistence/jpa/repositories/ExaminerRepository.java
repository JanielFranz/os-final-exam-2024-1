package org.hign.platform.u201912668.personnel.infrastructure.persistence.jpa.repositories;

import org.hign.platform.u201912668.personnel.domain.model.aggregates.Examiner;
import org.hign.platform.u201912668.personnel.domain.model.valueobjects.NationalProvideIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminerRepository extends JpaRepository<Examiner, Long> {

    boolean existsByNationalProvideIdentifier(NationalProvideIdentifier nationalProvideIdentifier);

}

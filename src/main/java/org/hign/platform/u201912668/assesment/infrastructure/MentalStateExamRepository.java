package org.hign.platform.u201912668.assesment.infrastructure;

import org.hign.platform.u201912668.assesment.domain.model.aggregates.MentalStateExams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentalStateExamRepository extends JpaRepository<MentalStateExams, Long> {

}

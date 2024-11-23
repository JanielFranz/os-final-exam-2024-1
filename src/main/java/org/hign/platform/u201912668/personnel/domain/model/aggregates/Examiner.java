package org.hign.platform.u201912668.personnel.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hign.platform.u201912668.personnel.domain.model.commands.CreateExaminerCommand;
import org.hign.platform.u201912668.personnel.domain.model.valueobjects.NationalProvideIdentifier;
import org.hign.platform.u201912668.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
@NoArgsConstructor
public class Examiner extends AuditableAbstractAggregateRoot<Examiner> {
    private String firstName;
    private String lastName;

    @Embedded
    private NationalProvideIdentifier nationalProvideIdentifier;

    public Examiner(CreateExaminerCommand command) {
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.nationalProvideIdentifier = new NationalProvideIdentifier(command.nationalProvideIdentifier());
    }

}

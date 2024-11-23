package org.hign.platform.u201912668.assesment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ExaminerNationalProviderIdentifier(String examinerNationalProviderIdentifier) {
    public ExaminerNationalProviderIdentifier {
        if (examinerNationalProviderIdentifier == null) {
            throw new IllegalArgumentException("Examiner National Provider Identifier is required");
        }
        if (examinerNationalProviderIdentifier.isBlank()) {
            throw new IllegalArgumentException("Examiner National Provider Identifier is required");
        }
    }
}

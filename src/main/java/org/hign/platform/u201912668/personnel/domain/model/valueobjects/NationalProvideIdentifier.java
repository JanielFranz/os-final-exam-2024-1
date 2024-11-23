package org.hign.platform.u201912668.personnel.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record NationalProvideIdentifier(String identifier) {
    private static UUID uuidIdentifier;

    public NationalProvideIdentifier {
        if (identifier == null) {
            throw new IllegalArgumentException("identifier must not be null");
        }
        try{
            this.uuidIdentifier = UUID.fromString(identifier);
        } catch(Exception e) {
            throw new IllegalArgumentException("identifier must be a valid UUID");
        }
    }

    public UUID getUuidIdentifier() {
        return uuidIdentifier;
    }

    public String getStringIdentifier() {
        return identifier;
    }


}

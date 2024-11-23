package org.hign.platform.u201912668.personnel.domain.model.commands;

public record CreateExaminerCommand(String firstName, String lastName, String nationalProvideIdentifier) {
    public CreateExaminerCommand{
        if(firstName == null || firstName.isBlank()){
            throw new IllegalArgumentException("First name is required");
        }
        if(lastName == null || lastName.isBlank()){
            throw new IllegalArgumentException("Last name is required");
        }
        if(nationalProvideIdentifier == null || nationalProvideIdentifier.isBlank()){
            throw new IllegalArgumentException("National provide identifier is required");
        }
    }
}

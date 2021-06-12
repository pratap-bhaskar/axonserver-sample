package com.walmart.cqrs.demoapp.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateInvestigationCommand {
    @TargetAggregateIdentifier
    private String investigationId;
    private String description;
    private String status;
    private Integer caseCount;

    public CreateInvestigationCommand(String investigationId, String description,
    String status, int caseCount){
         this.investigationId = investigationId;
         this.description = description;
         this.status = status;
         this.caseCount = caseCount;
    }
}

package com.walmart.cqrs.demoapp.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateInvestigationCommand {
    @TargetAggregateIdentifier
    private String investigationId;
    private String description;
    private String status;
    private Integer caseCount;
}

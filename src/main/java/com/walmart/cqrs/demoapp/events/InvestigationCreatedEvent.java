package com.walmart.cqrs.demoapp.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestigationCreatedEvent {
    private String investigationId;
    private String description;
    private String status;
    private Integer caseCount;
}

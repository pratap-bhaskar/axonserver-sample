package com.walmart.cqrs.demoapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestigationModel {
    private String investigationId;
    private String description;
    private String status;
    private Integer caseCount;
}

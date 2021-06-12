package com.walmart.cqrs.demoapp.aggregates;

import java.util.HashMap;

import com.walmart.cqrs.demoapp.commands.CreateInvestigationCommand;
import com.walmart.cqrs.demoapp.commands.UpdateInvestigationCommand;
import com.walmart.cqrs.demoapp.events.InvestigationCreatedEvent;
import com.walmart.cqrs.demoapp.events.InvestigationUpdatedEvent;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class InvestigationAggregate {
    @AggregateIdentifier
    private String investigationId;
    private String description;
    private String status;
    private Integer caseCount;

    @CommandHandler
    public InvestigationAggregate(CreateInvestigationCommand command) {
        apply(new InvestigationCreatedEvent(command.getInvestigationId(), command.getDescription(),
                command.getStatus(), 0));
    }

    @EventSourcingHandler
    public void on(InvestigationCreatedEvent event){
        // this is important to store the event aggregate
        this.investigationId = event.getInvestigationId();
        this.description = event.getDescription();
        this.status = event.getStatus();
        this.caseCount = event.getCaseCount();
    }

    @CommandHandler
    public void handle(UpdateInvestigationCommand command){
        this.investigationId = command.getInvestigationId();
        this.description = command.getDescription();
        this.status = command.getStatus();
        this.caseCount = command.getCaseCount();

        apply(new InvestigationUpdatedEvent(
            command.getInvestigationId(), command.getDescription(),
            command.getStatus(), this.caseCount));
    }

    @EventSourcingHandler
    public void on(InvestigationUpdatedEvent event){
        this.caseCount = event.getCaseCount();
    }

    protected InvestigationAggregate(){
        //Required by Axon
    }
}

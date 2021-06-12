package com.walmart.cqrs.demoapp.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.walmart.cqrs.demoapp.commands.CreateInvestigationCommand;
import com.walmart.cqrs.demoapp.commands.UpdateInvestigationCommand;
import com.walmart.cqrs.demoapp.models.InvestigationModel;
import com.walmart.cqrs.demoapp.queries.FindAllInvestigationsQuery;

@RestController
public class InvestigationController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public InvestigationController(CommandGateway commandGateway, QueryGateway queryGateway){
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/investigation")
    public CompletableFuture<Void> addInvestigation(@RequestBody InvestigationModel model) {
        String investigationId = UUID.randomUUID().toString();
        return commandGateway.send(new CreateInvestigationCommand(investigationId, model.getDescription(),
                model.getStatus(), model.getCaseCount()));
    }

    @PatchMapping("/investigation/{investigationId}")
    public CompletableFuture<Void> updateInvestigation(@RequestParam String investigationId,
            @RequestBody InvestigationModel model) {

        return commandGateway.send(new UpdateInvestigationCommand(investigationId, model.getDescription(),
                model.getStatus(), model.getCaseCount()));
    }

    @GetMapping("/investigations")
    public CompletableFuture<List<InvestigationModel>> getInvestigations(){
        return queryGateway.query(new FindAllInvestigationsQuery(), ResponseTypes.multipleInstancesOf(InvestigationModel.class));
    }

}

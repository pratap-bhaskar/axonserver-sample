package com.walmart.cqrs.demoapp.projectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.walmart.cqrs.demoapp.events.InvestigationCreatedEvent;
import com.walmart.cqrs.demoapp.events.InvestigationUpdatedEvent;
import com.walmart.cqrs.demoapp.models.InvestigationModel;
import com.walmart.cqrs.demoapp.queries.FindAllInvestigationsQuery;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("investigations")
public class InvestigationsProjector {
    // Storing the state in-memory could be placed anywhere
    private final Map<String, InvestigationModel> investigations = new HashMap<>();

    @EventHandler
    public void on(InvestigationCreatedEvent event) {
        investigations.put(event.getInvestigationId(), new InvestigationModel(event.getInvestigationId(),
                event.getDescription(), event.getStatus(), event.getCaseCount()));
    }

    @EventHandler
    public void on(InvestigationUpdatedEvent event){
        investigations.computeIfPresent(event.getInvestigationId(), (investigationId, investigation)->{
            investigation.setCaseCount(event.getCaseCount());
            return investigation;
        });
    }

    @QueryHandler
    public List<InvestigationModel> handle(FindAllInvestigationsQuery query){
        return new ArrayList<>(investigations.values());
    }
}

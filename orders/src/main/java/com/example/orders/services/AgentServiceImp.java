package com.example.orders.services;

import com.example.orders.models.Agent;
import com.example.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentServices")
public class AgentServiceImp implements AgentService{
    @Autowired
    AgentRepository agentrepos;

    @Override
    public Agent save(Agent agent) {
        return agentrepos.save(agent);
    }

    @Override
    public Agent findAgentById(long id) {
        return agentrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent Id " + id + " Not Found!"));
    }
}

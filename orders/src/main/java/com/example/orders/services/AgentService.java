package com.example.orders.services;

import com.example.orders.models.Agent;

public interface AgentService {

    Agent findAgentById(long id);

    Agent save(Agent agent);
}

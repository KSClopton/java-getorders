package com.example.orders.controllers;

import com.example.orders.models.Agent;
import com.example.orders.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentService agentServices;

    // http://localhost:2019/agents/agent/id - returns the agent and their customers with the given agent id
    @GetMapping(value = "agent/{agentid}", produces = "application/json")
    private ResponseEntity<?> findAgentById(@PathVariable long agentid){
        Agent myAgent = agentServices.findAgentById(agentid);
        return new ResponseEntity<>(myAgent, HttpStatus.OK);
    }
}

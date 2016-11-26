package com.scht.admin.service;

import com.scht.admin.entity.AgentMoney;

/**
 * Created by vanjoger on 2016/11/26.
 */
public interface AgentMoneyService {
    AgentMoney findByAgentId(String agentId);
}

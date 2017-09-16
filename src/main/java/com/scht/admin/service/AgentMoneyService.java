package com.scht.admin.service;

import com.scht.admin.entity.AgentMoney;

/**
 * Created by wxh on 2016/11/26.
 */
public interface AgentMoneyService {
    AgentMoney findByAgentId(String agentId);
}

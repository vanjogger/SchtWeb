package com.scht.admin.service.impl;

import com.scht.admin.dao.AgentMoneyDao;
import com.scht.admin.entity.AgentMoney;
import com.scht.admin.service.AgentMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Service
public class AgentMoneyServiceImpl implements AgentMoneyService{

    @Autowired
    AgentMoneyDao agentMoneyDao;

    @Override
    public AgentMoney findByAgentId(String agentId) {
        return this.agentMoneyDao.findByAgentId(agentId);
    }
}

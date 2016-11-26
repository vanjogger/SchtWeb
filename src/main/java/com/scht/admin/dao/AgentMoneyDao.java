package com.scht.admin.dao;

import com.scht.admin.entity.AgentMoney;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Repository
public interface AgentMoneyDao {
    AgentMoney findByAgentId(@Param("agentId")String agentId);
}

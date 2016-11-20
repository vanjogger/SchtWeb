package com.scht.admin.dao;

import com.scht.admin.entity.SystemLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/12/31.
 */
@Repository
public interface LogDao {
    List<SystemLog> listLog(@Param("operateName") String operateName, @Param("startMills") long startMills, @Param("endMills") long endMills, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    Integer countLog(@Param("operateName") String operateName, @Param("startMills") long startMills, @Param("endMills") long endMills);

    void saveLog(SystemLog log);
}

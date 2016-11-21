package com.scht.admin.service;

import com.scht.admin.entity.AdvertPlace;
import com.scht.common.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public interface AdvertPlaceService {

    PageInfo list(String code,PageInfo pageInfo);

    int save(AdvertPlace data);

    int update(AdvertPlace data);

    AdvertPlace find(String id);

    AdvertPlace findByCode(String code);

    int delete(String id);
}

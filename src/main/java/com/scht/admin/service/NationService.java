package com.scht.admin.service;

import com.scht.admin.entity.Nation;

import java.util.List;

/**
 * Created by wxh on 2016/12/10.
 */
public interface NationService {

    List<Nation> listByParentId(String lx,String id);

}

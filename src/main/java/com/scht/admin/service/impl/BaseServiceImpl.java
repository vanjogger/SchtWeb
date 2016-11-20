package com.scht.admin.service.impl;

import java.util.List;
import java.util.Map;

import com.scht.admin.dao.BaseMyBatisDao;
import com.scht.admin.service.BaseService;
import com.scht.common.PageInfo;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

	@Autowired
	private BaseMyBatisDao baseMyBatisDao;

	@Override
	public <T> List<T> searchByPage(Class<?> entity, Map<String, Object> searchParams) {

		return baseMyBatisDao.searchByPage(entity, searchParams);
	}

	@Override
	public <T> T findById(Class<?> entity, Long id) {

		return baseMyBatisDao.findById(entity, id);
	}

	@Override
	public <T> T findById(Class<?> entity, Integer id) {

		return baseMyBatisDao.findById(entity, id);
	}

	@Override
	public <T> void insert(Class<?> mapper, T entity) {

		baseMyBatisDao.insert(mapper, entity);
	}

	@Override
	public <T> void update(Class<?> mapper, T entity) {

		baseMyBatisDao.update(mapper, entity);
	}

	@Override
	public <T> void delete(Class<?> entity, String[] ids) {

		baseMyBatisDao.delete(entity, ids);
	}

	@Override
	public <T> void saveBatch(Class<?> entity, List<T> addList) {

		baseMyBatisDao.saveBatch(entity, addList);
	}

	@Override
	public <T> void updateBatch(Class<?> entity, List<T> editList) {

		baseMyBatisDao.updateBatch(entity, editList);
	}

	@Override
	public int count4Page(Class<?> entity, Map<String, Object> searchParams) {

		return baseMyBatisDao.count4Page(entity, searchParams);
	}

	@Override
	public <T> PageInfo<T> listByPage(Class<?> entity, Map<String, Object> params) {
		PageInfo<T> page = new PageInfo<T>();
		// 设置页码
		String currentPage = params.get("currentPage").toString();
		if (!NumberUtils.isNumber(currentPage)) {
			currentPage = "1";
		}
		page.setPageIndex(Integer.parseInt(currentPage));
		//page.setCurrentPage(currentPage);
		// 查询总条数
		//page.setTotalRows(baseMyBatisDao.count4Page(entity, params));
		page.setTotal(baseMyBatisDao.count4Page(entity,params));
		// 查询list
		params.put("start", page.getStart());
		params.put("limit", page.getLimit());
		List<T> list = baseMyBatisDao.searchByPage(entity, params);
		page.setResult(list);
		return page;
	}

	@Override
	public <T> List<T> findAll(Class<?> entity) {
		return baseMyBatisDao.findAll(entity);
	}

	@Override
	public <T> T findById(Class<?> entity, String id) {
		return baseMyBatisDao.findById(entity, id);
	}

}

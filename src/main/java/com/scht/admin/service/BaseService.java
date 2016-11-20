package com.scht.admin.service;

import com.scht.common.PageInfo;

import java.util.List;
import java.util.Map;


public interface BaseService {
	/**
	 * 按条件分页查询记录
	 * 
	 * @param <T>
	 * @param searchParams
	 *            ：查询条件
	 * @return List<T> ：查询结果集
	 */
	public <T> List<T> searchByPage(Class<?> entity, Map<String, Object> searchParams);

	/**
	 * 计算每个分页查询时总数
	 * 
	 * @param entity
	 *            对应Mapper接口
	 * @param searchParams
	 *            查询条件
	 * @return
	 */
	public int count4Page(Class<?> entity, Map<String, Object> searchParams);

	/**
	 * 通过 id获取一条记录
	 * 
	 * @param id
	 *            ：记录主键
	 * @return T ：记录对象实体
	 */
	public <T> T findById(Class<?> entity, Long id);

	/**
	 * 通过 id获取一条记录
	 * 
	 * @param id
	 *            ：记录主键
	 * @return T ：记录对象实体
	 */
	public <T> T findById(Class<?> entity, Integer id);
	
	/**
	 * 通过 id获取一条记录
	 * 
	 * @param id
	 *            ：记录主键
	 * @return T ：记录对象实体
	 */
	public <T> T findById(Class<?> entity, String id);

	/**
	 * 保存一条记录
	 * 
	 * @param <T>
	 *            ：记录对象实体
	 */
	public <T> void insert(Class<?> mapper, T entity);

	/**
	 * 修改记录
	 * 
	 * @param <T>
	 *            ：记录对象实体
	 */
	public <T> void update(Class<?> mapper, T entity);

	/**
	 * 通过id数组批量删除记录
	 * 
	 * @param <T>
	 * @param ids
	 *            ：记录主键id数组
	 */
	public <T> void delete(Class<?> entity, String[] ids);

	/**
	 * 批量保存记录
	 * 
	 * @param <T>
	 * @param addList
	 */
	public <T> void saveBatch(Class<?> entity, List<T> addList);

	/**
	 * 批量修改记录
	 * 
	 * @param <T>
	 * @param editList
	 */
	public <T> void updateBatch(Class<?> entity, List<T> editList);

	/**
	 * 封装分页信息
	 * @description TODO <br/>
	 * @date 2015-6-30 下午3:55:27 <br/>
	 * @param entity
	 * @param params
	 * @return
	 */
	public <T> PageInfo<T> listByPage(Class<?> entity, Map<String, Object> params);
	
	public <T> List<T> findAll(Class<?> entity);
}

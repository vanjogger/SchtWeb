package com.scht.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ClassUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("baseMyBatisDao")
public class BaseMyBatisDao {
	@Autowired
	protected SqlSession sqlSession;

	/**
	 * 按条件分页查询记录
	 * 
	 * @param <T>
	 * @param searchParams
	 *            ：查询条件
	 * @return List<T> ：查询结果集
	 */
	public <T> List<T> searchByPage(Class<?> entity,
			Map<String, Object> searchParams) {
		return sqlSession.selectList(this.getMethodPath(entity, "searchByPage"), searchParams);
	}

	/**
	 * 通过 id获取一条记录
	 * 
	 * @param id
	 *            ：记录主键
	 * @return T ：记录对象实体
	 */
	public <T> T findById(Class<?> entity, Long id) {
		return sqlSession.selectOne(this.getMethodPath(entity, "findById"), id);
	}
	
	/**
	 * 通过 id获取一条记录
	 * 
	 * @param id
	 *            ：记录主键
	 * @return T ：记录对象实体
	 */
	public <T> T findById(Class<?> entity, String id) {
		return sqlSession.selectOne(this.getMethodPath(entity, "findById"), id);
	}

	/**
	 * 通过 id获取一条记录
	 * 
	 * @param id
	 *            ：记录主键
	 * @return T ：记录对象实体
	 */
	public <T> T findById(Class<?> entity, Integer id) {
		return sqlSession.selectOne(this.getMethodPath(entity, "findById"), id);
	}

	/**
	 * 保存一条记录
	 * 
	 * @param <T>
	 * @param T
	 *            ：记录对象实体
	 */
	public <T> int insert(Class<?> mapper, T entity) {
		return sqlSession.insert(this.getMethodPath(mapper, "insert"), entity);
	}

	/**
	 * 修改记录
	 * 
	 * @param <T>
	 * @param T
	 *            ：记录对象实体
	 */
	public <T> int update(Class<?> mapper, T entity) {
		return sqlSession.update(this.getMethodPath(mapper, "update"), entity);
	}

	/**
	 * 通过id数组批量删除记录
	 * 
	 * @param ids
	 *            ：记录主键id数组
	 */
	public <T> void delete(Class<?> entity, String[] ids) {
		sqlSession.delete(this.getMethodPath(entity, "delete"), ids);
	}

	/**
	 * 批量保存记录
	 * 
	 * @param <T>
	 * @param addList
	 */
	public <T> void saveBatch(Class<?> entity, List<T> addList) {
		sqlSession.insert(this.getMethodPath(entity, "insertBatch"), addList);
	}

	/**
	 * 批量修改记录
	 * 
	 * @param <T>
	 * @param editList
	 */
	public <T> void updateBatch(Class<?> entity, List<T> editList) {
		sqlSession.update(this.getMethodPath(entity, "updateBatch"), editList);
	}

	public int count4Page(Class<?> entity, Map<String, Object> searchParams) {
		return sqlSession.selectOne(this.getMethodPath(entity, "count4Page"),searchParams);
	}
	
	public <T> List<T> findAll(Class<?> entity) {
		return sqlSession.selectList(this.getMethodPath(entity, "findAll"));
	}

	/**
	 * 拼接里面的方法名称
	 * 
	 * @param <T>
	 * 
	 * @description TODO <br/>
	 * @date 2015-4-10 下午4:37:55 <br/>
	 * @param methodType
	 * @return
	 */
	private <T> String getMethodPath(Class<?> entity, String methodType) {
		return entity.getName() + "." + methodType;
	}
	
	public static void main(String[] args) {
		//System.out.println(StringUtils.uncapitalize("Abcd"));
		System.out.println(BaseMyBatisDao.class.getResource("").getPath());
		System.out.println(ClassUtils.getPackageName(BaseMyBatisDao.class));
	}
}

package com.glsx.biz.access.container.dao.base;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseDAO {

	@Resource
	protected HibernateBaseDAO hibernateBaseDAO;
	
	@Resource
	protected JdbcTemplate jdbcTemplate;

	public Integer save(Object object) {

		return (Integer) hibernateBaseDAO.save(object);
	}

	public void update(Object object) {

		hibernateBaseDAO.update(object);
	}

	public void delete(Object object) {

		hibernateBaseDAO.delete(object);
	}

	public Object load(Class<?> entityClass, Serializable id) {

		if (id != null) {
			return hibernateBaseDAO.load(entityClass, id);
		} else {
			return null;
		}
	}

	public Object get(String className, String key, Integer value) {

		String hql = " from " + className + " where " + key + "=:objId";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objId", value);
		return this.hibernateBaseDAO.findOne(hql, param);
	}

	public HibernateBaseDAO getHibernateBaseDAO() {

		return hibernateBaseDAO;
	}

}

package com.glsx.biz.access.common.entity.enums.usertype;

import com.glsx.biz.access.common.entity.enums.base.EnumsID;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 枚举模板（接口实现）
 * @param <E>
 */
public class IntegerEnumUserType<E extends Enum<E>> implements UserType {

	private Class<E> clazz = null;

	protected IntegerEnumUserType(Class<E> c) {
		this.clazz = c;
	}

	private static final int[] SQL_TYPES = { Types.INTEGER };

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Class<E> returnedClass() {
		return clazz;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y)
			return true;
		if (null == x || null == y)
			return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		final int id = resultSet.getInt(names[0]);
		if (!resultSet.wasNull()) {
			try {
				return clazz.getMethod("findById", new Class[] { Integer.class }).invoke(null, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (null == value) {
			preparedStatement.setNull(index, SQL_TYPES[0]);
		} else {
			EnumsID eID = (EnumsID) value;
			preparedStatement.setInt(index, eID.getId());
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}

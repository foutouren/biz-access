package com.glsx.biz.access.common.entity.base;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 实体类 - 基类
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -6718838800112233445L;

	public static final String CREATE_TIME_PROPERTY_NAME = "createTime";// "创建日期"属性名称
	public static final String UPDATE_TIME_PROPERTY_NAME = "updateTime";// "修改日期"属性名称
	
	@XmlTransient
	protected Date createTime;// 创建日期
	
	@XmlTransient
	protected Date updateTime;// 修改日期

	@XmlTransient
	protected Integer lastOperatorId;// 操作者ID
	
	@XmlTransient
	protected String lastOperatorName;// 操作者名称
	
	@XmlTransient
	protected Integer operatorType;// 操作者类型
	
	@XmlTransient
	protected int version;

	@Column(name = "create_time", updatable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "last_operator_id")
	public Integer getLastOperatorId() {
		return lastOperatorId;
	}

	public void setLastOperatorId(Integer lastOperatorId) {
		this.lastOperatorId = lastOperatorId;
	}

	@Column(name = "last_operator_name")
	public String getLastOperatorName() {
		return lastOperatorName;
	}

	public void setLastOperatorName(String lastOperatorName) {
		this.lastOperatorName = lastOperatorName;
	}

	@Column(name = "operator_type")
	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	@Version
	@Column(name = "ver", length = 11)
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}	

}
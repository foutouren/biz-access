package com.glsx.biz.access.container.dao.access;

import com.glsx.biz.access.container.dao.base.BaseDAO;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateDao extends BaseDAO {

//	public void save(Merchant merchant) {
//
//		this.hibernateBaseDAO.save(merchant);
//	}
//
//	public void update(Merchant merchant) {
//
//		this.hibernateBaseDAO.update(merchant);
//	}
//
//	public Merchant get(Integer id) {
//
//		return (Merchant) this.hibernateBaseDAO.load(Merchant.class, id);
//	}
//
//	public void delete(Merchant merchant) {
//
//		this.hibernateBaseDAO.delete(merchant);
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Merchant> getMerchants(MerchantVo merchant) {
//
//		List<Merchant> merchants = new ArrayList<Merchant>();
//		StringBuilder sb = new StringBuilder();
//		sb.append("from Merchant where 1=1");
//		if (merchant != null) {
//			if (merchant.getProvinceId() != null) {
//				sb.append(" and provinceId=" + merchant.getProvinceId());
//			}
//			if (merchant.getCityId() != null) {
//				sb.append(" and cityId=" + merchant.getCityId());
//			}
//			if (merchant.getMerchantType() != null) {
//				sb.append(" and merchantType=" + merchant.getMerchantType());
//			}
//			if (merchant.getLabelId() != null) {
//				sb.append(" and labelId=" + merchant.getLabelId());
//			}
//			if (merchant.getMerchantStatus() != null) {
//				sb.append(" and merchantStatus=" + merchant.getMerchantStatus());
//			}
//			if (merchant.getRoleId() != null) {
//				sb.append(" and roleId=" + merchant.getRoleId());
//			}
//			if (merchant.getMerchantName() != null) {
//				sb.append(" and merchantName like '%" + merchant.getMerchantName() + "%'");
//			}
//			if (merchant.getAuditBeginTime() != null && merchant.getAuditEndTime() != null) {
//				sb.append(" and auditTime between '" + merchant.getAuditBeginTime() + "' and '" + merchant.getAuditEndTime()
//						+ "'");
//			}
//			merchants = hibernateBaseDAO.find(sb.toString(), null);
//		}
//
//		return merchants;
//	}
//
//	public void deleteAll(List<Merchant> merchants) {
//
//		for (Merchant m : merchants) {
//			delete(m);
//		}
//	}
//
//	public void updateAll(List<Merchant> merchants) {
//
//		for (Merchant m : merchants) {
//			update(m);
//		}
//	}
//
//	public int countMerchantByName(String merchantName) {
//		Finder f = Finder.create("from Merchant where merchantName = :mercharntName");
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("mercharntName", merchantName);
//		return hibernateBaseDAO.countQueryResult(f, param);
//
//	}
//
//	public int countMerchantById(Integer merchantId) {
//
//		return hibernateBaseDAO.countByProperty(Merchant.class, "merchantId", merchantId);
//	}
//
//	public Integer getId(final String type, final int head) throws Exception {
//
//		// String procedure = "{call pro_generate_glsxid(?,?,?,?)}";
//
//		Object[] inputs = new Object[2];
//		inputs[0] = type;
//		inputs[1] = Integer.valueOf(head);
//
//		int[] inputTypes = new int[2];
//		inputTypes[0] = 12;
//		inputTypes[1] = 4;
//
//		int[] outputTypes = new int[2];
//		outputTypes[0] = -5;
//		outputTypes[1] = 4;
//		Session se = hibernateBaseDAO.getSession();
//		return se.doReturningWork(new ReturningWork<Integer>() {
//
//			@Override
//			public Integer execute(Connection con) throws SQLException {
//
//				int result = 0;
//				CallableStatement cstmt = con
//						.prepareCall("{call pro_generate_glsxid(?,?,?,?)}");
//				cstmt.setString(1, type);
//				cstmt.setInt(2, head);
//				cstmt.registerOutParameter("i_idaccount", Types.INTEGER);
//				cstmt.registerOutParameter("i_result", Types.INTEGER);
//				cstmt.executeUpdate();
//				result = cstmt.getInt("i_result");
//				int userId = cstmt.getInt("i_idaccount");
//				if (result == 1) {
//					return userId;
//				}
//				return null;
//			}
//		});
//	}
//
//	public String getAreaByMerchant(Integer merchantId) {
//
//		StringBuffer hql = new StringBuffer();
//		Map<String, Object> param = new HashMap<String, Object>();
//		hql.append("select concat(p.province,'-',c.city) as area");
//		hql.append(" from Merchant m ");
//		hql.append(",City c");
//		hql.append(",Province p");
//		hql.append(" where m.provinceId=p.pid and m.cityId=c.cid and m.merchantId=:merchantId");
//		param.put("merchantId", merchantId);
//		return (String) this.hibernateBaseDAO.findOne(hql.toString(), param);
//	}
//
//	public Merchant getBindMerchant(Integer merchantId) {
//
//		StringBuffer hql = new StringBuffer();
//		Map<String, Object> param = new HashMap<String, Object>();
//		hql.append("select new com.glsx.biz.common.user.entity.Merchant(m.merchantId,m.merchantName,m.contact,m.tel,m.mobile,m.fax,m.email,m.address,m.gpsLon,m.gpsLat)");
//		hql.append(" from Merchant m where m.merchantId=:merchantId");
//		param.put("merchantId", merchantId);
//		return (Merchant) hibernateBaseDAO.findOne(hql.toString(), param);
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Merchant> getMerchantsByCity(String cityName, Integer merchantId) {
//
//		StringBuffer hql = new StringBuffer();
//		hql.append("select new com.glsx.biz.common.user.entity.Merchant(m.merchantId,m.merchantName,m.contact,m.tel,m.mobile,m.fax,m.email,m.address,m.gpsLon,m.gpsLat)");
//		hql.append(" from Merchant m,City c where c.city like '%");
//		hql.append(cityName);
//		hql.append("%'");
//		hql.append(" and c.cityCode=m.cityId");
//		if (merchantId != null) {
//			hql.append(" and m.merchantId!=");
//			hql.append(merchantId);
//		}
//		hql.append(" order by m.createTime desc");
//		return hibernateBaseDAO.find(hql.toString(), null);
//	}
//
//	public Pagination find(int targetPage, int pageSize) {
//
//		String hql = " from Merchant ";
//		return hibernateBaseDAO.getMapListByHql(hql, targetPage, pageSize, null);
//	}
//
//	public Pagination findChild(Integer parentId, int targetPage, int pageSize) {
//
//		String hql = " from Merchant where parentId = :parentId";
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("parentId", parentId);
//		return hibernateBaseDAO.getMapListByHql(hql, targetPage, pageSize, param);
//	}
//
//	public Pagination findChild(Integer parentId, Integer typeId, int targetPage, int pageSize) {
//
//		String hql = " from Merchant where parentId = :parentId and merchantType = :merchantType";
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("parentId", parentId);
//		param.put("merchantType", typeId);
//		return hibernateBaseDAO.getMapListByHql(hql, targetPage, pageSize, param);
//	}
//
//	public Pagination find(HashMap<String, Object> condition, int targetPage, int pageSize) {
//
//		StringBuffer hql = new StringBuffer(" from Merchant where merchantStatus != -9 ");
//		if (condition != null) {
//			if (condition.get("merchantName") != null && !"".equals(condition.get("merchantName"))) {
//				hql.append(" and merchantName like '%");
//				hql.append(condition.get("merchantName"));
//				hql.append("%'");
//			}
//			if (condition.get("contact") != null && !"".equals(condition.get("contact"))) {
//				hql.append(" and contact like '%");
//				hql.append(condition.get("contact"));
//				hql.append("%'");
//			}
//			if (condition.get("mobile") != null && !"".equals(condition.get("mobile"))) {
//				hql.append(" and mobile like '%");
//				hql.append(condition.get("mobile"));
//				hql.append("%'");
//			}
//			if (condition.get("provinceId") != null && !"".equals(condition.get("provinceId"))) {
//				hql.append(" and provinceId = " + condition.get("provinceId"));
//			}
//			if (condition.get("cityId") != null && !"".equals(condition.get("cityId"))) {
//				hql.append(" and cityId =" + condition.get("cityId"));
//			}
//			if (condition.get("merchantId") != null && !"".equals(condition.get("merchantId"))) {
//				hql.append(" and merchantId like '%");
//				hql.append(condition.get("merchantId"));
//				hql.append("%'");
//			}
//			if (condition.get("merchantSource") != null && !"".equals(condition.get("merchantSource"))) {
//				hql.append(" and merchantSource = " + condition.get("merchantSource"));
//			}
//			if (condition.get("parentId") != null && !"".equals(condition.get("parentId"))) {
//				hql.append(" and parentId = " + condition.get("parentId"));
//			}
//			if (condition.get("merchantType") != null && !"".equals(condition.get("merchantType"))) {
//				hql.append(" and merchantType = " + condition.get("merchantType"));
//			}
//		}
//		hql.append(" order by createTime desc ");
//		return hibernateBaseDAO.getMapListByHql(hql.toString(), targetPage, pageSize, null);
//	}
//
//	public Merchant getByMerchantName(String merchantName) {
//
//		String hql = new String("from Merchant where merchantName =:merchantName ");
//		HashMap<String, Object> param = new HashMap<String, Object>();
//		param.put("merchantName", merchantName);
//		return (Merchant) hibernateBaseDAO.findOne(hql, param);
//
//	}
}

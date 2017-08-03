package sist.spring.dao;

import java.io.Closeable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import sist.spring.bean.MasterBean;
import sist.spring.bean.OrderBean;
import sist.spring.bean.StoreBean;

public class OrderDao extends SqlSessionDaoSupport{
	private SqlSessionFactoryBean sqlSessionFactory;

	

	public int getTotalRow(HashMap<String, Object> map) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			try {
				if (map.get("Q").equals("null")) {
					map.put("Q", null);
					map.put("D", null);
					return sqlSession.selectOne("getTotalRow", map);
				} else {
					return sqlSession.selectOne("getTotalRow", map);
				}

			} catch (Exception e) {
				// TODO: handle exception
				return sqlSession.selectOne("getTotalRow", map);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} 
	}

	public int getTotalRowCom(HashMap<String, Object> map) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();

			try {
				if (map.get("Q2").equals("null")) {
					map.put("Q2", null);
					map.put("D2", null);
					return sqlSession.selectOne("getTotalRowCom", map);
				} else {
					return sqlSession.selectOne("getTotalRowCom", map);
				}

			} catch (Exception e) {
				// TODO: handle exception
				return sqlSession.selectOne("getTotalRowCom", map);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} 
	}

	public List<OrderBean> selectOrder(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		System.out.println(map.get("query"));
		System.out.println(map.get("data"));
		System.out.println(map.get("start"));
		System.out.println(map.get("end"));

		try {
			sqlSession = this.getSqlSession();
			try {
				if (map.get("query").equals("null")) {
					map.put("query", null);
					map.put("data", null);
					return sqlSession.selectList("selectOrder", map);
				} else {
					return sqlSession.selectList("selectOrder", map);
				}

			} catch (Exception e) {
				// TODO: handle exception
				return sqlSession.selectList("selectOrder", map);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public List<OrderBean> selectOrderCom(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			try {
				if (map.get("query2").equals("null")) {
					map.put("query2", null);
					map.put("data2", null);
					return sqlSession.selectList("selectOrderCom", map);

				} else {
					return sqlSession.selectList("selectOrderCom", map);
				}

			} catch (Exception e) {
				// TODO: handle exception
				return sqlSession.selectList("selectOrderCom", map);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public void updateOrder(HashMap<String, Object> map) {
		SqlSession session = this.getSqlSession();
		try {
			session.update("updateOrder", map);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public MasterBean selectMaster(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("selectMaster", map);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public StoreBean selectStore(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("selectStore", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public void updateStore(HashMap<String, Object> map) {
		SqlSession session = this.getSqlSession();
		try {
			session.update("updateStore", map);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} 
	}

	public void updateMaster(HashMap<String, Object> map) {
		SqlSession session = this.getSqlSession();
		try {
			session.update("updateMaster", map);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
	}

	public void dropMaster(MasterBean bean) {
		SqlSession session = this.getSqlSession();
		try {
			session.insert("dropMaster", bean);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
	}

	public void updateMemberPang(HashMap<String, Object> map) {
		SqlSession session = this.getSqlSession();
		try {
			session.update("updateMemberPang", map);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
	}

}

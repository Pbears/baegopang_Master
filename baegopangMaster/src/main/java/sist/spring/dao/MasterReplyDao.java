package sist.spring.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import sist.spring.bean.MasterReplyBean;

import org.apache.ibatis.session.SqlSession;
import java.util.List;

/*public class MasterReplyDao {
	private SqlSessionFactory sqlSessionFactory;

	public MasterReplyDao() {
		sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
	}

	public void insertReply(MasterReplyBean bean) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			sqlSession.insert("insertReply", bean);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public Integer selectRepSeq() throws Exception {
		return this.getSqlSession().selectOne("selectRepSeq");
	}

	public List<MasterReplyBean> selectReply(String storeName) {
		return this.getSqlSession().selectList("selectReply", storeName);
	}
=======*/

import java.io.Closeable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MasterReplyDao extends SqlSessionDaoSupport{

	private SqlSessionFactoryBean sqlSessionFactory;


	public List<MasterReplyBean> selectReply(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("selectReply", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public int getRTotalRows(String storename) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("getRTotalRows", storename);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int checkReply(HashMap<String, Object> ckmap) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("checkReply", ckmap);
		} catch (Exception e) {
			/* e.printStackTrace(); */
			return -1;
		} 
	}
	public int getReplyTotal(String storename) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("getReplyTotal", storename);
		} catch (Exception e) {
			/* e.printStackTrace(); */
			return -1;
		} 
	}
	public void updateReply(HashMap<String, Object> map) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		try {
			sqlSession.update("updateReply", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
	}

	public void insertReply(HashMap<String, Object> map) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		try {
			sqlSession.insert("insertReply", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
	}

	public String selectOneRep(HashMap<String, Object> map) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		try {
			return sqlSession.selectOne("selectOneRep", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public void getPoint(String id) {
		SqlSession sqlSession = this.getSqlSession();
		try {
			sqlSession.update("getPoint", id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
	}

}

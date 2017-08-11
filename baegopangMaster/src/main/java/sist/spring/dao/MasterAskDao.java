package sist.spring.dao;

import java.io.Closeable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import sist.spring.bean.MasteraskadminBean;

public class MasterAskDao extends SqlSessionDaoSupport{
	private SqlSessionFactoryBean sqlSessionFactory;

	public void insertAsk(MasteraskadminBean bean) {
		SqlSession sqlSession = this.getSqlSession();
		try {
			sqlSession.insert("insertAsk", bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public int getTotalRows(String masterid) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("getTotalRows", masterid);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} 
	}

	public List<MasteraskadminBean> selectAsk(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("selectAsk", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public MasteraskadminBean selQuestionOne(String title) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("selQuestionOne", title);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
}

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

	private void closeSqlSession(Closeable c) {
		try {
			if (c != null)
				c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertAsk(MasteraskadminBean bean) {
		SqlSession sqlSession = this.getSqlSession();
		try {
			sqlSession.insert("insertAsk", bean);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
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
		} finally {
			closeSqlSession(sqlSession);
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
		} finally {
			closeSqlSession(sqlSession);
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
		} finally {
			closeSqlSession(sqlSession);
		}

	}
}

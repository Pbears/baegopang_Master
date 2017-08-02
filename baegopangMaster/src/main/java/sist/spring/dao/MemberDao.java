package sist.spring.dao;

import java.io.Closeable;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import sist.spring.bean.MasterBean;

public class MemberDao extends SqlSessionDaoSupport{

	private SqlSessionFactoryBean sqlSessionFactory;

	public MasterBean memberCheck(String id) {
		System.out.println(id);
		return this.getSqlSession().selectOne("memberCheck");
	}

	private void closeSqlSession(Closeable c) {
		try {
			if (c != null)
				c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void masterInsert(MasterBean bean) {
		SqlSession session = this.getSqlSession();
		try {
			session.insert("masterInsert", bean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	public MasterBean selectMasterOne(String id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("selectMasterOne", id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeSqlSession(sqlSession);
		}

	}

}
package sist.spring.dao;

import java.io.Closeable;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import sist.spring.bean.MasterBean;

public class MemberDao extends SqlSessionDaoSupport{

	private SqlSessionFactoryBean sqlSessionFactory;

	public MasterBean memberCheck(String id) {
		return this.getSqlSession().selectOne("memberCheck");
	}


	public void masterInsert(MasterBean bean) {
		SqlSession session = this.getSqlSession();
		try {
			session.insert("masterInsert", bean);
		} catch (Exception e) {
			e.printStackTrace();
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
		} 

	}

}
package sist.spring.dao;

import java.io.Closeable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import sist.spring.bean.PointBean;

public class PointDao extends SqlSessionDaoSupport{
	private SqlSessionFactoryBean sqlSessionFactory;

	
	public List<PointBean> brandPoint(String storename) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("brandPoint", storename);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public List<PointBean> guPoint(String storename) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("guPoint", storename);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public PointBean mybrandPoint(String storename) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("mybrandPoint", storename);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public PointBean myguPoint(String storename) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("myguPoint", storename);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
}

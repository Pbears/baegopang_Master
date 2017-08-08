package sist.spring.dao;

import java.io.Closeable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import sist.spring.bean.ZipcodeBean;

public class ZipCodeDao extends SqlSessionDaoSupport {
	private SqlSessionFactoryBean sqlSessionFactory;

	public List<ZipcodeBean> searchAddress(String dong) {
		SqlSession sqlSession = null;
		System.out.println(dong);
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("searchAddress", dong);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
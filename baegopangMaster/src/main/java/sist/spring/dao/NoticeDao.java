package sist.spring.dao;

import java.io.Closeable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import sist.spring.bean.NoticeBean;

public class NoticeDao extends SqlSessionDaoSupport{
	private SqlSessionFactoryBean sqlSessionFactory;


	public List<NoticeBean> noticeSel() {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("noticeSel");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public NoticeBean selNoticeOne(String title) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("selNoticeOne", title);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public int getNTotalRows() throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("getNTotalRows");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public List<NoticeBean> selectPage(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("selectPage", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

package sist.spring.dao;

import java.io.Closeable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sun.prism.RTTexture;

import sist.spring.bean.MenuBean;

public class MenuManageDao extends SqlSessionDaoSupport{
	private SqlSessionFactoryBean sqlSessionFactory;

	private void closeSqlSession(Closeable c) {
		try {
			if (c != null)
				c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMenu(String menuname) {
		SqlSession sqlSession = this.getSqlSession();
		try {
			sqlSession.insert("deleteMenu", menuname);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}

	public List<MenuBean> selectMenu(String id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("selectMenu", id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeSqlSession(sqlSession);
		}
	}

	public List<MenuBean> selectMenuOne(String picture) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectOne("selectMenuOne", picture);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeSqlSession(sqlSession);
		}

	}

	public List<MenuBean> myStoreMenu(String storename) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("myStoreMenu", storename);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeSqlSession(sqlSession);
		}
	}

	public void insertMenu(HashMap<String, String> map) {
		SqlSession sqlSession = this.getSqlSession();
		try {
			sqlSession.insert("insertMenu", map);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
}

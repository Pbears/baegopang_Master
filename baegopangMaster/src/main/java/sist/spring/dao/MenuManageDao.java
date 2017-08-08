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


	public void deleteMenu(String menuname) {
		SqlSession sqlSession = this.getSqlSession();
		try {
			sqlSession.insert("deleteMenu", menuname);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
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
		} 
	}
}

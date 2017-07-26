package sist.spring.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import sist.spring.bean.AdminBean;

public class AdminDao extends SqlSessionDaoSupport{
	private SqlSessionFactoryBean sqlSessionFactory;
	
	public List<AdminBean> selectAllAdmin(){
		return this.getSqlSession().selectList("selectAllAdmin");
	}
}

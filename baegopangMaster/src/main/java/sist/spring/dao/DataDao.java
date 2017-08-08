package sist.spring.dao;

import java.io.Closeable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import sist.spring.bean.AgeDataBean;
import sist.spring.bean.BrandDataBean;
import sist.spring.bean.DaySalesDataBean;
import sist.spring.bean.GenderDataBean;
import sist.spring.bean.MonthSalesDataBean;
import sist.spring.bean.WeekDayDataBean;
import sist.spring.bean.WeekdaySalesDataBean;

public class DataDao extends SqlSessionDaoSupport{
	
	private SqlSessionFactoryBean sqlSessionFactory;

	public void insertOrderData(HashMap<String, Object> map) {
		SqlSession session = this.getSqlSession();
		try {
			session.insert("insertOrderData", map);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} 
	}

	public List<GenderDataBean> selectGenderData(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("selectGenderData", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<WeekDayDataBean> selectWeekDayData(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("selectWeekDayData", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<AgeDataBean> selectAgeData(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			System.out.println(map.get("storename"));
			return sqlSession.selectList("selectAgeData", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public List<BrandDataBean> selectBrandData(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("selectBrandData", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public List<DaySalesDataBean> selectDaySalesData(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("selectDaySalesData", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public List<WeekdaySalesDataBean> selectWeekDaySalesData(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("selectWeekDaySalesData", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public List<MonthSalesDataBean> selectMonthSalesData(HashMap<String, Object> map) {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.getSqlSession();
			return sqlSession.selectList("selectMonthSalesData", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

}

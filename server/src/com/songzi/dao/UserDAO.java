package com.songzi.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.songzi.model.User;

/**
 * A data access object (DAO) providing persistence and search support for
 * User entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.songzi.model.User
 * @author MyEclipse Persistence Tools
 */
public class UserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String TELPONE = "telpone";
	public static final String LATLNG_LATELY = "latlngLately";
	private Session session;
	private Transaction tx;

	public void save(User transientInstance) {
		session = getSession();
		tx = session.beginTransaction();
		try {
			session.save(transientInstance);
			tx.commit();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
	public void update(User transientInstance) {
		session = getSession();
		tx = session.beginTransaction();
		try {
			session.update(transientInstance);
			tx.commit();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getSession().get(
					"com.songzi.model.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		session = getSession();
		tx = session.beginTransaction();
		try {
			List results = getSession()
					.createCriteria("com.songzi.model.User")
					.add(Example.create(instance)).list();
			tx.commit();
			return results;
		} catch (RuntimeException re) {
			re.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public User loginCheck(String username,String password){
		session = getSession();
		tx = session.beginTransaction();
		try {
			String queryString = "from User as model where model.username= ? and model.password=?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, username);
			query.setParameter(1, password);
			List<User> users = query.list();
			if(users.size()==0){
				return null;
			}else{
				return users.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	public User findByUsername(String username) {
		session = getSession();
		tx = session.beginTransaction();
		try {
			String queryString = "from User as model where model.username= ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, username);
			List<User> users = query.list();
			tx.commit();
			if(users.size()==0){
				return null;
			}else{
				return users.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByTelpone(Object telpone) {
		return findByProperty(TELPONE, telpone);
	}

	public List findByLatlngLately(Object latlngLately) {
		return findByProperty(LATLNG_LATELY, latlngLately);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
/**
 * 
 */
package com.collegediary.platform.hbm;

import java.io.Serializable;

import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author gaurav.khullar
 *
 */
public class CDEntityManager extends HibernateDaoSupport{
	public void clear() {
		getSession().clear();
	}

	public void close() {
		getSession().close();
	}

	public boolean contains(Object entity) {
		return getSession().contains(entity);
	}
	
	public Object find(Class entityClass, Serializable primaryKey) {
		return getSession().get(entityClass, primaryKey);
	}

	public void flush() {
		getSession().flush();		
	}

	public FlushMode getFlushMode() {
		return getSession().getFlushMode();
	}

	public Transaction getTransaction() {
		return getSession().getTransaction();
	}

	public boolean isOpen() {
		return getSession().isConnected();
	}

	public void lock(Object object, LockMode lockMode) {
		getSession().lock(object, lockMode);
	}

	public Object merge(Object object) {
			return getSession().merge(object);
	}

	public void persist(Object object) {
		getSession().persist(object);
	}
	
	public void saveOrUpdate(final Object object) {
		getSession().saveOrUpdate(object);
	}

	public void refresh(Object object) {
		getSession().refresh(object);		
	}

	public void remove(Object object) {
		getSession().delete(object);	
	}
	
	public void evict(Object object){
		getSession().evict(object);
		getSessionFactory().evict(object.getClass());
	}

	public void setFlushMode(FlushMode flushMode) {
		getSession().setFlushMode(flushMode);		
	}
}

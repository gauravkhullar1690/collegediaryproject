/**
 * 
 */
package com.collegediary.platform.hbm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.LockMode;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gaurav.khullar
 * 
 */
@Transactional(propagation = Propagation.REQUIRED)
public class PersistenceService implements IPersistenceService {
	private CDEntityManager entityManager;

	public CDEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(CDEntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Object save(Object obj) {
		return entityManager.merge(obj);
	}

	public void persist(Object obj) {
		entityManager.persist(obj);
	}

	public void saveOrUpdate(final Object obj) {
		entityManager.saveOrUpdate(obj);
	}

	public List save(List objs) {
		List updatedObjs = new ArrayList();
		if (null != objs) {
			Iterator iterator = objs.iterator();
			while (iterator.hasNext()) {
				Object updatedObj = entityManager.merge(iterator.next());
				updatedObjs.add(updatedObj);
			}
		}
		objs.clear();
		objs.addAll(updatedObjs);
		return objs;
	}

	public Object find(Class entityClass, Serializable primaryKey) {
		return entityManager.find(entityClass, primaryKey);
	}

	public void remove(Object obj) {
		entityManager.remove(obj);
	}

	public void evict(Object object) {
		entityManager.evict(object);
	}

	public void remove(List objs) {
		if (null != objs) {
			Iterator iterator = objs.iterator();
			while (iterator.hasNext()) {
				entityManager.remove(iterator.next());
			}
		}
	}

	public void lock(Object object) {
		entityManager.lock(object, LockMode.NONE);
	}

	public void flush() {
		entityManager.flush();
	}

	public void clear() {
		entityManager.clear();
	}
}

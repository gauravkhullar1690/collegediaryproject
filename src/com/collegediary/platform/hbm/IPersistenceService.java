/**
 * 
 */
package com.collegediary.platform.hbm;

import java.util.List;

/**
 * @author gaurav.khullar
 *
 */
public interface IPersistenceService {
	/**
	 * This API is used to save the Object
	 * @param obj
	 * @return
	 */
	 public Object save(Object obj);
	 public void lock(Object object);
	 public void flush();
	 public void clear();
	 public void remove(Object obj);
	 public void remove(List objs);    
	 public void saveOrUpdate(final Object obj);
	 public void persist(Object obj);
}

package com.gem.sistema.web.semaphore;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



// TODO: Auto-generated Javadoc
/**
 * The Class Semaphores.
 */
@ManagedBean(eager=true)
@ApplicationScoped()
public class Semaphores{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Semaphores.class);
	
	/** The semaphore. */
	private Map<PolienUK, LockSemaphore> semaphore = new ConcurrentHashMap<PolienUK, LockSemaphore>();
	
	/**
	 * Instantiates a new semaphores.
	 */
	public Semaphores() {
		super();
		
	}
	
	
	/**
	 * Checks if is allowed to use.
	 *
	 * @param id the id
	 * @param user the user
	 * @return true, if is allowed to use
	 */
	public boolean isAllowedToUse(PolienUK id, String user){
		LOGGER.info("isAllowedToUse, id= {},  user={}",id,user);
		if(id == null || user == null){
			throw new RuntimeException("Los parametros no pueden ser nulos");
		}		
		LockSemaphore lockSemaphore = semaphore.get(id);
		if (lockSemaphore == null){	
			return true;
		}else{
			if(lockSemaphore.getUser().equals(user)){
				return true;
			}else{
				//no se permite usar porque esta bloqueado por alguien.
				return false;
			}						
		}		
	}
	
	
	/**
	 * Lock.
	 *
	 * @param id the id
	 * @param idSession the id session
	 * @param user the user
	 * @return true, if successful
	 */
	public synchronized boolean lock(PolienUK id, String idSession, String user){
		LOGGER.info("lock, id= {}, idSession= {}, user={}",id,idSession,user);
		//showMap();
		if(id == null || idSession == null || user == null){
			throw new RuntimeException("Los parametros no pueden ser nulos");
		}
		
		LockSemaphore lockSemaphore = semaphore.get(id);
		if (lockSemaphore == null){				
			lockSemaphore = new LockSemaphore(id, idSession, user);
			semaphore.put(id, lockSemaphore);
			return true;
		}else{
			if(lockSemaphore.getUser().equals(user)){
				lockSemaphore.setIdSession(idSession);
				semaphore.put(id, lockSemaphore);
				return true;
			}else{
				//no se permite usar porque esta bloqueado por alguien.
				return false;
			}						
		}		
	}
	
	/**
	 * Unlock.
	 *
	 * @param id the id
	 */
	public synchronized void unlock(PolienUK id){	
		LOGGER.info("unlock, id= {}",id);
		if(id == null){
			throw new RuntimeException("Los parametros no pueden ser nulos");
		}
		LockSemaphore lockSemaphore = semaphore.get(id);
		
		if (lockSemaphore != null){	
			semaphore.remove(id);		
		}	
		//showMap();
	}	
	
	/**
	 * Show map.
	 */
	private void showMap(){	
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("showMap, timestamp ={}",new Date());
			final Iterator<Entry<PolienUK, LockSemaphore>> it = semaphore.entrySet().iterator();
			Entry<PolienUK, LockSemaphore> entry = null;
			PolienUK k = null;
			LockSemaphore v = null;
			while (it.hasNext()) {
				entry = it.next();
				k = entry.getKey(); // clave
				v = entry.getValue(); // valor
				LOGGER.info("showMap, clave={},valor={}; ",k, v);
			}	
		}
	}
	
	/**
	 * Clean session.
	 *
	 * @param idSession the id session
	 */
	public void cleanSession(String idSession){	
			LOGGER.info("cleanSession, timestamp ={}, idSession={}",new Date(), idSession);
			if(idSession == null){
				throw new RuntimeException("Los parametros no pueden ser nulos");
			}
			//showMap();
			final Iterator<Entry<PolienUK, LockSemaphore>> it = semaphore.entrySet().iterator();
			Entry<PolienUK, LockSemaphore> entry = null;
			PolienUK k = null;
			LockSemaphore v = null;
			while (it.hasNext()) {
				entry = it.next();
				k = entry.getKey(); // clave
				v = entry.getValue(); // valor
				//LOGGER.info("cleanSession clave={},valor={}; ",k, v);
				if(idSession.equals(v.getIdSession())){
					LOGGER.info("cleanSession remove, timestamp ={}, idSession={}, LockSemaphore={}",new Date(), idSession, v);
					semaphore.remove(k);	
				}
			}	
			//showMap();
	}
		
}

package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm2911BS;
import com.gem.sistema.business.domain.Pm2911;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2911ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm2911Service")
public class Pm2911ServiceImpl implements Pm2911Service{
	
	/** The pm 2911 BS. */
	@Autowired
	private Pm2911BS pm2911BS;
	
	
	
	/**
	 * Gets the pm 2911 BS.
	 *
	 * @return the pm 2911 BS
	 */
	public Pm2911BS getPm2911BS() {
		return pm2911BS;
	}

	/**
	 * Sets the pm 2911 BS.
	 *
	 * @param pm2911BS the new pm 2911 BS
	 */
	public void setPm2911BS(Pm2911BS pm2911BS) {
		this.pm2911BS = pm2911BS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2911Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2911> save(Integer index, List<Pm2911> listPm2911) {
		return this.pm2911BS.save(index, listPm2911);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2911Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2911> delete(Integer index, List<Pm2911> listPm2911) {
		return this.pm2911BS.delete(index, listPm2911);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2911Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2911> clean(Integer index, List<Pm2911> listPm2911) {
		return this.pm2911BS.clean(index, listPm2911);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2911Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2911> cancel(Integer index, List<Pm2911> listPm2911) {
		return this.pm2911BS.cancel(index, listPm2911);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2911Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm2911> orderBySemestreAsc(Integer idSector) {
		return this.pm2911BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2911Service#add()
	 */
	@Override
	public Pm2911 add() {
		return this.pm2911BS.add();
	}

}

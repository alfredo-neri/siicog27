package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm2311BS;
import com.gem.sistema.business.domain.Pm2311;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2311ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm2311Service")
public class Pm2311ServiceImpl implements Pm2311Service{
	
	/** The pm 2311 BS. */
	@Autowired
	private Pm2311BS pm2311BS;
	
	
	
	/**
	 * Gets the pm 2311 BS.
	 *
	 * @return the pm 2311 BS
	 */
	public Pm2311BS getPm2311BS() {
		return pm2311BS;
	}

	/**
	 * Sets the pm 2311 BS.
	 *
	 * @param pm2311BS the new pm 2311 BS
	 */
	public void setPm2311BS(Pm2311BS pm2311BS) {
		this.pm2311BS = pm2311BS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2311Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2311> save(Integer index, List<Pm2311> listPm2311) {
		return this.pm2311BS.save(index, listPm2311);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2311Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2311> delete(Integer index, List<Pm2311> listPm2311) {
		return this.pm2311BS.delete(index, listPm2311);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2311Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2311> clean(Integer index, List<Pm2311> listPm2311) {
		return this.pm2311BS.clean(index, listPm2311);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2311Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2311> cancel(Integer index, List<Pm2311> listPm2311) {
		return this.pm2311BS.cancel(index, listPm2311);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2311Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm2311> orderBySemestreAsc(Integer idSector) {
		return this.pm2311BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2311Service#add()
	 */
	@Override
	public Pm2311 add() {
		return this.pm2311BS.add();
	}

}

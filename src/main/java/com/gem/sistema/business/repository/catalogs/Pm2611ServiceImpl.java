package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm2611BS;
import com.gem.sistema.business.domain.Pm2611;
import com.gem.sistema.business.service.catalogos.Pm2611Service;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2611ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm2611Service")
public class Pm2611ServiceImpl implements Pm2611Service {
	
	/** The pm 2611 BS. */
	@Autowired
	private Pm2611BS pm2611BS;

	/**
	 * Gets the pm 2611 BS.
	 *
	 * @return the pm 2611 BS
	 */
	public Pm2611BS getPm2611BS() {
		return pm2611BS;
	}

	/**
	 * Sets the pm 2611 BS.
	 *
	 * @param pm2611bs the new pm 2611 BS
	 */
	public void setPm2611BS(Pm2611BS pm2611bs) {
		pm2611BS = pm2611bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2611Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2611> save(Integer index, List<Pm2611> listPm2611) {
		return this.pm2611BS.save(index, listPm2611);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2611Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2611> delete(Integer index, List<Pm2611> listPm2611) {
		return this.pm2611BS.delete(index, listPm2611);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2611Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2611> clean(Integer index, List<Pm2611> listPm2611) {		
		return this.pm2611BS.clean(index, listPm2611);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2611Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2611> cancel(Integer index, List<Pm2611> listPm2611) {
		return this.pm2611BS.cancel(index, listPm2611);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2611Service#orderBySemestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm2611> orderBySemestreAsc(Integer idSector) {
		return this.pm2611BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2611Service#add()
	 */
	@Override
	public Pm2611 add() {
		return this.pm2611BS.add();
	}

}

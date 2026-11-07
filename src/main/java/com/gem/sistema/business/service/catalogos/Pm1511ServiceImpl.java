package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm1511BS;
import com.gem.sistema.business.domain.Pm1511;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm1511ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm1511Service")
public class Pm1511ServiceImpl implements Pm1511Service{
	
	/** The pm 1511 BS. */
	@Autowired
	private Pm1511BS pm1511BS;
	
	
	
	/**
	 * Gets the pm 1511 BS.
	 *
	 * @return the pm 1511 BS
	 */
	public Pm1511BS getPm1511BS() {
		return pm1511BS;
	}

	/**
	 * Sets the pm 1511 BS.
	 *
	 * @param pm1511BS the new pm 1511 BS
	 */
	public void setPm1511BS(Pm1511BS pm1511BS) {
		this.pm1511BS = pm1511BS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm1511Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm1511> save(Integer index, List<Pm1511> listPm1511) {
		return this.pm1511BS.save(index, listPm1511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm1511Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm1511> delete(Integer index, List<Pm1511> listPm1511) {
		return this.pm1511BS.delete(index, listPm1511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm1511Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm1511> clean(Integer index, List<Pm1511> listPm1511) {
		return this.pm1511BS.clean(index, listPm1511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm1511Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm1511> cancel(Integer index, List<Pm1511> listPm1511) {
		return this.pm1511BS.cancel(index, listPm1511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm1511Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm1511> orderBySemestreAsc(Integer idSector) {
		return this.pm1511BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm1511Service#add()
	 */
	@Override
	public Pm1511 add() {
		return this.pm1511BS.add();
	}

}

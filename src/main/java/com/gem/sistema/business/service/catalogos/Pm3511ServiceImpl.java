package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm3511BS;
import com.gem.sistema.business.domain.Pm3511;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3511ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm3511Service")
public class Pm3511ServiceImpl implements Pm3511Service{
	
	/** The pm 3511 BS. */
	@Autowired
	private Pm3511BS pm3511BS;
	
	
	
	/**
	 * Gets the pm 3511 BS.
	 *
	 * @return the pm 3511 BS
	 */
	public Pm3511BS getPm3511BS() {
		return pm3511BS;
	}

	/**
	 * Sets the pm 3511 BS.
	 *
	 * @param pm3511BS the new pm 3511 BS
	 */
	public void setPm3511BS(Pm3511BS pm3511BS) {
		this.pm3511BS = pm3511BS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3511Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3511> save(Integer index, List<Pm3511> listPm3511) {
		return this.pm3511BS.save(index, listPm3511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3511Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3511> delete(Integer index, List<Pm3511> listPm3511) {
		return this.pm3511BS.delete(index, listPm3511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3511Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm3511 clean() {
		return this.pm3511BS.clean();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3511Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3511> cancel(Integer index, List<Pm3511> listPm3511) {
		return this.pm3511BS.cancel(index, listPm3511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3511Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm3511> orderBySemestreAsc(Integer idSector) {
		return this.pm3511BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3511Service#add()
	 */
	@Override
	public Pm3511 add() {
		return this.pm3511BS.add();
	}

}

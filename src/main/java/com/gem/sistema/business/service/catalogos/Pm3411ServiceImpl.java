package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm3411BS;
import com.gem.sistema.business.domain.Pm3411;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3411ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm3411Service")
public class Pm3411ServiceImpl implements Pm3411Service{
	
	/** The pm 3411 BS. */
	@Autowired
	private Pm3411BS pm3411BS;
	
	
	
	/**
	 * Gets the pm 3411 BS.
	 *
	 * @return the pm 3411 BS
	 */
	public Pm3411BS getPm3411BS() {
		return pm3411BS;
	}

	/**
	 * Sets the pm 3411 BS.
	 *
	 * @param pm3411BS the new pm 3411 BS
	 */
	public void setPm3411BS(Pm3411BS pm3411BS) {
		this.pm3411BS = pm3411BS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3411Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3411> save(Integer index, List<Pm3411> listPm3411) {
		return this.pm3411BS.save(index, listPm3411);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3411Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3411> delete(Integer index, List<Pm3411> listPm3411) {
		return this.pm3411BS.delete(index, listPm3411);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3411Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3411> clean(Integer index, List<Pm3411> listPm3411) {
		return this.pm3411BS.clean(index, listPm3411);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3411Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3411> cancel(Integer index, List<Pm3411> listPm3411) {
		return this.pm3411BS.cancel(index, listPm3411);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3411Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm3411> orderBySemestreAsc(Integer idSector) {
		return this.pm3411BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3411Service#add()
	 */
	@Override
	public Pm3411 add() {
		return this.pm3411BS.add();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3411Service#getAcumulado(java.lang.Integer)
	 */
	@Override
	public List<Pm3411> getAcumulado(Integer idSector) {
		return this.pm3411BS.getAcumulado(idSector);
	}

}

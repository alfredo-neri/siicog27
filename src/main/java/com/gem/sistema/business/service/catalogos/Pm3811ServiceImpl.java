package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm3811BS;
import com.gem.sistema.business.domain.Pm3811;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3811ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm3811Service")
public class Pm3811ServiceImpl implements Pm3811Service{
	
	/** The pm 3811 BS. */
	@Autowired
	private Pm3811BS pm3811BS;
	
	
	
	/**
	 * Gets the pm 3811 BS.
	 *
	 * @return the pm 3811 BS
	 */
	public Pm3811BS getPm3811BS() {
		return pm3811BS;
	}

	/**
	 * Sets the pm 3811 BS.
	 *
	 * @param pm3811BS the new pm 3811 BS
	 */
	public void setPm3811BS(Pm3811BS pm3811BS) {
		this.pm3811BS = pm3811BS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3811Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3811> save(Integer index, List<Pm3811> listPm3811) {
		return this.pm3811BS.save(index, listPm3811);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3811Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3811> delete(Integer index, List<Pm3811> listPm3811) {
		return this.pm3811BS.delete(index, listPm3811);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3811Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm3811 clean() {
		return this.pm3811BS.clean();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3811Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3811> cancel(Integer index, List<Pm3811> listPm3811) {
		return this.pm3811BS.cancel(index, listPm3811);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3811Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm3811> orderBySemestreAsc(Integer idSector) {
		return this.pm3811BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3811Service#add()
	 */
	@Override
	public Pm3811 add() {
		return this.pm3811BS.add();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3811Service#validateTxt(com.gem.sistema.business.domain.Pm3811)
	 */
	@Override
	public boolean validateTxt(Pm3811 PM3811) {
		// TODO Auto-generated method stub
		return this.pm3811BS.validateTxt(PM3811);
	}

}

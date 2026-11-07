package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm0811BS;
import com.gem.sistema.business.domain.Pm0811;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0811ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm0811Service")
public class Pm0811ServiceImpl implements Pm0811Service{
	
	/** The pm 0811 BS. */
	@Autowired
	private Pm0811BS pm0811BS;
	
	
	
	/**
	 * Gets the pm 0811 BS.
	 *
	 * @return the pm 0811 BS
	 */
	public Pm0811BS getPm0811BS() {
		return pm0811BS;
	}

	/**
	 * Sets the pm 0811 BS.
	 *
	 * @param pm0811BS the new pm 0811 BS
	 */
	public void setPm0811BS(Pm0811BS pm0811BS) {
		this.pm0811BS = pm0811BS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0811Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0811> save(Integer index, List<Pm0811> listPm0811) {
		return this.pm0811BS.save(index, listPm0811);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0811Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0811> delete(Integer index, List<Pm0811> listPm0811) {
		return this.pm0811BS.delete(index, listPm0811);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0811Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0811> clean(Integer index, List<Pm0811> listPm0811) {
		return this.pm0811BS.clean(index, listPm0811);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0811Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0811> cancel(Integer index, List<Pm0811> listPm0811) {
		return this.pm0811BS.cancel(index, listPm0811);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0811Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm0811> orderBySemestreAsc(Integer idSector) {
		return this.pm0811BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0811Service#add()
	 */
	@Override
	public Pm0811 add() {
		return this.pm0811BS.add();
	}

}

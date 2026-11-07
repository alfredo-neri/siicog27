package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm5811BS;
import com.gem.sistema.business.domain.Pm5811;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5811ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm5811Service")
public class Pm5811ServiceImpl implements Pm5811Service{
	
	/** The pm 5811 BS. */
	@Autowired
	private Pm5811BS pm5811BS;
	
	
	
	/**
	 * Gets the pm 5811 BS.
	 *
	 * @return the pm 5811 BS
	 */
	public Pm5811BS getPm5811BS() {
		return pm5811BS;
	}

	/**
	 * Sets the pm 5811 BS.
	 *
	 * @param pm5811BS the new pm 5811 BS
	 */
	public void setPm5811BS(Pm5811BS pm5811BS) {
		this.pm5811BS = pm5811BS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5811Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm5811> save(Integer index, List<Pm5811> listPm5811) {
		return this.pm5811BS.save(index, listPm5811);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5811Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm5811> delete(Integer index, List<Pm5811> listPm5811) {
		return this.pm5811BS.delete(index, listPm5811);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5811Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm5811 clean() {
		return this.pm5811BS.clean();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5811Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm5811> cancel(Integer index, List<Pm5811> listPm5811) {
		return this.pm5811BS.cancel(index, listPm5811);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5811Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm5811> orderBySemestreAsc(Integer idSector) {
		return this.pm5811BS.orderBySemestralAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5811Service#add()
	 */
	@Override
	public Pm5811 add() {
		return this.pm5811BS.add();
	}

}

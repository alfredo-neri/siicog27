package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm5511BS;
import com.gem.sistema.business.domain.Pm5511;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5511ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm5511Service")
public class Pm5511ServiceImpl implements Pm5511Service{
	
	/** The pm 5511 BS. */
	@Autowired
	private Pm5511BS pm5511BS;

	/**
	 * Gets the pm 5511 BS.
	 *
	 * @return the pm 5511 BS
	 */
	public Pm5511BS getPm5511BS() {
		return pm5511BS;
	}

	/**
	 * Sets the pm 5511 BS.
	 *
	 * @param pm5511bs the new pm 5511 BS
	 */
	public void setPm5511BS(Pm5511BS pm5511bs) {
		pm5511BS = pm5511bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5511Service#findAllOrderByMensual()
	 */
	@Override
	public List<Pm5511> findAllOrderByMensual() {
		return this.pm5511BS.findAllOrderByMensual();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5511Service#orderByAsc()
	 */
	@Override
	public Sort orderByAsc() {
		return this.pm5511BS.orderByAsc();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5511Service#beginVariables()
	 */
	@Override
	public Pm5511 beginVariables() {
		return this.pm5511BS.beginVariables();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5511Service#save(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5511> save(List<Pm5511> listPm511, Integer index) {
		return this.pm5511BS.save(listPm511, index);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5511Service#delete(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5511> delete(List<Pm5511> listPm511, Integer index) {
		return this.pm5511BS.delete(listPm511, index);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5511Service#clean(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5511> clean(List<Pm5511> listPm511, Integer index) {
		return this.pm5511BS.clean(listPm511, index);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5511Service#cancel(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5511> cancel() {
		return this.pm5511BS.cancel();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5511Service#add()
	 */
	@Override
	public Pm5511 add() {
		return pm5511BS.beginVariables();
	}

}

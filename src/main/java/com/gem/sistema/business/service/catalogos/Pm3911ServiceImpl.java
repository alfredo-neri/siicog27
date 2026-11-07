package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm3911BS;
import com.gem.sistema.business.domain.Pm3911;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3911ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm3911Service")
public class Pm3911ServiceImpl implements Pm3911Service{
	
	/** The pm 3911 BS. */
	@Autowired
	private Pm3911BS pm3911BS;
	
	
	
	/**
	 * Gets the pm 3911 BS.
	 *
	 * @return the pm 3911 BS
	 */
	public Pm3911BS getPm3911BS() {
		return pm3911BS;
	}

	/**
	 * Sets the pm 3911 BS.
	 *
	 * @param pm3911BS the new pm 3911 BS
	 */
	public void setPm3911BS(Pm3911BS pm3911BS) {
		this.pm3911BS = pm3911BS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3911Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3911> save(Integer index, List<Pm3911> listPm3911) {
		return this.pm3911BS.save(index, listPm3911);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3911Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3911> delete(Integer index, List<Pm3911> listPm3911) {
		return this.pm3911BS.delete(index, listPm3911);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3911Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3911> clean(Integer index, List<Pm3911> listPm3911) {
		return this.pm3911BS.clean(index, listPm3911);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3911Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3911> cancel(Integer index, List<Pm3911> listPm3911) {
		return this.pm3911BS.cancel(index, listPm3911);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3911Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm3911> orderBySemestreAsc(Integer idSector) {
		return this.pm3911BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3911Service#add()
	 */
	@Override
	public Pm3911 add() {
		return this.pm3911BS.add();
	}

}

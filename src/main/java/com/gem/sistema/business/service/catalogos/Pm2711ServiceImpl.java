package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm2711BS;
import com.gem.sistema.business.domain.Pm2711;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2711ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm2711Service")
public class Pm2711ServiceImpl implements Pm2711Service{
	
	/** The pm 2711 BS. */
	@Autowired
	private Pm2711BS pm2711BS;
	
	
	
	/**
	 * Gets the pm 2711 BS.
	 *
	 * @return the pm 2711 BS
	 */
	public Pm2711BS getPm2711BS() {
		return pm2711BS;
	}

	/**
	 * Sets the pm 2711 BS.
	 *
	 * @param pm2711BS the new pm 2711 BS
	 */
	public void setPm2711BS(Pm2711BS pm2711BS) {
		this.pm2711BS = pm2711BS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2711Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2711> save(Integer index, List<Pm2711> listPm2711) {
		return this.pm2711BS.save(index, listPm2711);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2711Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2711> delete(Integer index, List<Pm2711> listPm2711) {
		return this.pm2711BS.delete(index, listPm2711);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2711Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2711> clean(Integer index, List<Pm2711> listPm2711) {
		return this.pm2711BS.clean(index, listPm2711);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2711Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2711> cancel(Integer index, List<Pm2711> listPm2711) {
		return this.pm2711BS.cancel(index, listPm2711);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2711Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm2711> orderBySemestreAsc(Integer idSector) {
		return this.pm2711BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2711Service#add()
	 */
	@Override
	public Pm2711 add() {
		return this.pm2711BS.add();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2711Service#acumulado(java.lang.Integer)
	 */
	@Override
	public List<Pm2711> acumulado(Integer idSector) {
		// TODO Auto-generated method stub
		return this.pm2711BS.acumulado(idSector);
	}

}

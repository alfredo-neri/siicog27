package com.gem.sistema.business.repository.catalogs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.TcCuentasAdicionales;
import com.gem.sistema.business.predicates.TcCuentasAdicionalesPredicates;
import com.gem.sistema.business.service.catalogos.CuentaAdicionalService;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaAdicionalServiceImpl.
 *
 * @author Gerardo CUERO
 */
@Service(value = "cuentaAdicionalService")
public class CuentaAdicionalServiceImpl implements CuentaAdicionalService {

	/** The tc cuentas adicionales repository. */
	@Autowired
	@Qualifier("tcCuentasAdicionalesRepository")
	private TcCuentasAdicionalesRepository tcCuentasAdicionalesRepository;

	/** The Constant ACC_4XXX. */
	private static final String ACC_4XXX = FrontProperty.getPropertyValue("catalog.account.presup.type52");

	/**
	 * Gets the tc cuentas adicionales repository.
	 *
	 * @return the tc cuentas adicionales repository
	 */
	public TcCuentasAdicionalesRepository getTcCuentasAdicionalesRepository() {
		return tcCuentasAdicionalesRepository;
	}

	/**
	 * Sets the tc cuentas adicionales repository.
	 *
	 * @param tcCuentasAdicionalesRepository the new tc cuentas adicionales repository
	 */
	public void setTcCuentasAdicionalesRepository(TcCuentasAdicionalesRepository tcCuentasAdicionalesRepository) {
		this.tcCuentasAdicionalesRepository = tcCuentasAdicionalesRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.CuentaAdicionalService#
	 * findByCuenta(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<TcCuentasAdicionales> findByCuenta(String cuenta, String adicional, Integer idSector) {
		List<TcCuentasAdicionales> listAdicionales = (List<TcCuentasAdicionales>) tcCuentasAdicionalesRepository
				.findAll(TcCuentasAdicionalesPredicates.findByCuenta(cuenta, adicional, idSector),
						CuentaAdicionalServiceImpl.BY_ALL);
		return listAdicionales;
	}

	/** The Constant BY_ALL. */
	public static final Sort BY_ALL = new Sort(Sort.Direction.ASC, "invertir");
	
	
	/**
	 * Fill or cut left string.
	 *
	 * @param cad the cad
	 * @param size the size
	 * @return the string
	 */
	private String fillOrCutLeftString(String cad, int size){
		if(null!=cad){
			if(cad.length() > size){
				return StringUtils.substring(cad, cad.length() - size);
			} else {
				return StringUtils.leftPad(cad, size, "0");
			}
		}
		return cad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.CuentaAdicionalService#getAdds
	 * (com.gem.sistema.business.domain.Polide, java.lang.Integer)
	 */
	public List<Polide> getAdds(Polide polide, Integer idSector) {
		List<Polide> toReturn = null;
		if (polide.getCuenta().matches(ACC_4XXX)) {
			toReturn = new ArrayList<Polide>();
			List<TcCuentasAdicionales> listadds = this.findByCuenta("4", StringUtils.EMPTY, idSector);
			if (!CollectionUtils.isEmpty(listadds)) {
				for (TcCuentasAdicionales tcCuentasAdicionales : listadds) {
					Polide polibuff = new Polide();
					polibuff.setCuenta(tcCuentasAdicionales.getAdicional());
					polibuff.setScta(null == this.fillOrCutLeftString(polide.getCuenta(), 15) ? "" : this.fillOrCutLeftString(polide.getCuenta(), 15) );
					polibuff.setSscta(null == this.fillOrCutLeftString(polide.getScta(),18) ? "" :this.fillOrCutLeftString(polide.getScta(),18));
					polibuff.setSsscta(null == this.fillOrCutLeftString(polide.getSscta(),4) ? "" :this.fillOrCutLeftString(polide.getSscta(),4) );
					polibuff.setSssscta(null == this.fillOrCutLeftString(polide.getSsscta(),4) ? "" : this.fillOrCutLeftString(polide.getSsscta(),4));
					polibuff.setConcep(polide.getConcep());
					polibuff.setRefpol(polide.getRefpol());
					polibuff.setDn(polide.getDn());
					toReturn.add(this.applyRule(polibuff, polide, tcCuentasAdicionales));
				}
			}
		} else {

			if (null != polide && !StringUtils.isEmpty(polide.getCuenta1())) {
				toReturn = new ArrayList<Polide>();
				List<TcCuentasAdicionales> listadds = this.findByCuenta(polide.getCuenta(), polide.getCuenta1(),
						idSector);
				if (!CollectionUtils.isEmpty(listadds)) {
					for (TcCuentasAdicionales tcCuentasAdicionales : listadds) {
						Polide polibuff = new Polide();
						polibuff.setCuenta(tcCuentasAdicionales.getAdicional());
						polibuff.setScta(null == polide.getScta() ? "" : polide.getScta());
						polibuff.setSscta(null == polide.getSscta() ? "" : polide.getSscta());
						polibuff.setSsscta(null == polide.getSsscta() ? "" :  polide.getSsscta());
						polibuff.setSssscta(null == polide.getSssscta()? "" : polide.getSssscta());
						polibuff.setConcep(polide.getConcep());
						polibuff.setRefpol(polide.getRefpol());
						polibuff.setDn(polide.getDn());
						polibuff.setCanpol(polide.getCanpol());
						polibuff.setCanpolh(polide.getCanpolh());
						toReturn.add(this.applyRule(polibuff, polide, tcCuentasAdicionales));
					}
				}
			}
		}

		return toReturn;

	}

	/**
	 * Apply rule.
	 *
	 * @param polibuff the polibuff
	 * @param polide the polide
	 * @param tcCuentasAdicionales the tc cuentas adicionales
	 * @return the polide
	 */
	private Polide applyRule(Polide polibuff, Polide polide, TcCuentasAdicionales tcCuentasAdicionales) {
		if (tcCuentasAdicionales.getInvertir().shortValue() == 1) {
			polibuff.setCanpol(polide.getCanpolh());
			polibuff.setCanpolh(polide.getCanpol());
		} else {
			polibuff.setCanpol(polide.getCanpol());
			polibuff.setCanpolh(polide.getCanpolh());
		}
		return polibuff;
	}

}

package com.gem.sistema.business.bs.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm5511BS;
import com.gem.sistema.business.domain.Pm5511;
import com.gem.sistema.business.repository.catalogs.Pm5511Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5511BSImpl.
 */
@Repository(value = "pm5511BS")
public class Pm5511BSImpl implements Pm5511BS {

	/** The Constant INITIAL_ZERO. */
	private static final BigDecimal INITIAL_ZERO = BigDecimal.ZERO;
	
	/** The Constant ZERO. */
	private static final Integer ZERO = 0;
	
	/** The pm 5511 repository. */
	@Autowired
	private Pm5511Repository pm5511Repository;

	/**
	 * Gets the pm 5511 repository.
	 *
	 * @return the pm 5511 repository
	 */
	public Pm5511Repository getPm5511Repository() {
		return pm5511Repository;
	}

	/**
	 * Sets the pm 5511 repository.
	 *
	 * @param pm5511Repository the new pm 5511 repository
	 */
	public void setPm5511Repository(Pm5511Repository pm5511Repository) {
		this.pm5511Repository = pm5511Repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5511BS#findAllOrderByMensual()
	 */
	@Override
	public List<Pm5511> findAllOrderByMensual() {
		return (List<Pm5511>) pm5511Repository.findAll(this.orderByAsc());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5511BS#orderByAsc()
	 */
	public Sort orderByAsc() {
		return new Sort(Sort.Direction.ASC, "mensual");
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5511BS#beginVariables()
	 */
	@Override
	public Pm5511 beginVariables() {
		Pm5511 pm5511 = new Pm5511();
		pm5511.setMensual(ZERO);
		pm5511.setMtam(INITIAL_ZERO);
		pm5511.setMmme(INITIAL_ZERO);
		pm5511.setMaoa(INITIAL_ZERO);
		
		pm5511.setObf(INITIAL_ZERO);
		pm5511.setPpea(INITIAL_ZERO);
		pm5511.setAeb(INITIAL_ZERO);
		pm5511.setEl(INITIAL_ZERO);
		pm5511.setApis(INITIAL_ZERO);
		pm5511.setPac(INITIAL_ZERO);
		pm5511.setDaj(INITIAL_ZERO);
		
		pm5511.setSubof(INITIAL_ZERO);
		pm5511.setPobf(INITIAL_ZERO);
		pm5511.setPppea(INITIAL_ZERO);
		pm5511.setPapis(INITIAL_ZERO);
		pm5511.setPaeb(INITIAL_ZERO);
		pm5511.setPel(INITIAL_ZERO);
		pm5511.setPpac(INITIAL_ZERO);
		pm5511.setPdaj(INITIAL_ZERO);
		pm5511.setPsubof(INITIAL_ZERO);
		
		pm5511.setSppc(INITIAL_ZERO);
		pm5511.setMnpos(INITIAL_ZERO);
		pm5511.setMnpob(INITIAL_ZERO);
		pm5511.setMnpop(INITIAL_ZERO);
		pm5511.setCap(INITIAL_ZERO);
		pm5511.setAdu(INITIAL_ZERO);
		pm5511.setEan(INITIAL_ZERO);
		pm5511.setAmer(INITIAL_ZERO);
		pm5511.setAes(INITIAL_ZERO);
		pm5511.setAep(INITIAL_ZERO);
		pm5511.setAdv(INITIAL_ZERO);
		pm5511.setMpv(INITIAL_ZERO);
		pm5511.setSubsp(INITIAL_ZERO);
		
		pm5511.setPsppc(INITIAL_ZERO);
		pm5511.setPmnpos(INITIAL_ZERO);
		pm5511.setPmnpob(INITIAL_ZERO);
		pm5511.setPmnpop(INITIAL_ZERO);
		pm5511.setPcap(INITIAL_ZERO);
		pm5511.setPadu(INITIAL_ZERO);
		pm5511.setPean(INITIAL_ZERO);
		pm5511.setPamer(INITIAL_ZERO);
		pm5511.setPaes(INITIAL_ZERO);
		pm5511.setPaep(INITIAL_ZERO);
		pm5511.setPadv(INITIAL_ZERO);
		pm5511.setPmpv(INITIAL_ZERO);
		pm5511.setPsubsp(INITIAL_ZERO);
		
		pm5511.setAocr(INITIAL_ZERO);
		pm5511.setPodc(INITIAL_ZERO);
		pm5511.setSubinf(INITIAL_ZERO);
		pm5511.setPodcs(INITIAL_ZERO);
		pm5511.setSubb(INITIAL_ZERO);
		pm5511.setOtros(INITIAL_ZERO);
		pm5511.setSubot(INITIAL_ZERO);
		
		pm5511.setPaocr(INITIAL_ZERO);
		pm5511.setPpodc(INITIAL_ZERO);
		pm5511.setPsubinf(INITIAL_ZERO);
		pm5511.setPpodcs(INITIAL_ZERO);
		pm5511.setPsubb(INITIAL_ZERO);
		pm5511.setPotros(INITIAL_ZERO);
		pm5511.setPsubot(INITIAL_ZERO);
		
		pm5511.setMt(INITIAL_ZERO);
		pm5511.setPmt(INITIAL_ZERO);
		
		return pm5511;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5511BS#save(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5511> save(List<Pm5511> listPm511, Integer index) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5511BS#delete(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5511> delete(List<Pm5511> listPm511, Integer index) {
		pm5511Repository.delete(listPm511.get(index).getId());
		listPm511 = this.findAllOrderByMensual();
		if(CollectionUtils.isEmpty(listPm511))
			listPm511.add(index, this.beginVariables());
		return listPm511;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5511BS#clean(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5511> clean(List<Pm5511> listPm511, Integer index) {
		listPm511.add(index, new Pm5511());
		return listPm511;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5511BS#cancel()
	 */
	@Override
	public List<Pm5511> cancel() {
		return this.findAllOrderByMensual();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5511BS#add()
	 */
	@Override
	public Pm5511 add() {
		return new Pm5511();
	}

}

/**
 * 
 */
package com.gem.sistema.web.datamodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.dto.SelectPagePolideDTO;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.service.catalogos.PolideService;

// TODO: Auto-generated Javadoc
/**
 * The Class PolideDataModel.
 *
 * @author Gerardo CUERO
 */
@ManagedBean
@ViewScoped
public class PolideDataModel extends CustomPagingModel<Polide> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant CUURENT_PAGE. */
	public static final Integer CUURENT_PAGE = 0;

	/** The Constant NEXT_PAGE. */
	public static final Integer NEXT_PAGE = 2;

	/** The Constant PREV_PAGE. */
	public static final Integer PREV_PAGE = 1;
	/**
	 * Repositorio de Polide.
	 */
	@ManagedProperty(value = "#{polideService}")
	private PolideService polideService;

	/** The polide repository. */
	@ManagedProperty(value = "#{polideRepository}")
	private PolideRepository polideRepository;

	/** The insert. */
	private Boolean insert = Boolean.FALSE;

	/** The reset. */
	private Boolean reset = Boolean.FALSE;

	/** The selected. */
	private Polide selected;

	/** The count. */
	private Integer count;

	/** The polien. */
	private Polien polien;

	/** The list polide. */
	private List<Polide> listPolide;

	/** The last page. */
	private Integer lastPage;

	/** The total elements. */
	private Long totalElements;

	/** The actual page. */
	private Page<Polide> actualPage;

	/** The last row. */
	private Polide lastRow;

	/** The go to page. */
	private Integer goToPage = 0;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#getId(java.lang.Object)
	 */
	@Override
	protected String getId(Polide entity) {
		return StringUtils.EMPTY + entity.getIndex();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData(java.util.Map)
	 */
	@Override
	protected int countData(Map<String, Object> filters) {
		if (null == count) {
			count = polideService.count(this.getfilters(filters));
		}
		return this.insert ? count + 1 : count;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData()
	 */
	@Override
	protected int countData() {
		if (null == count) {
			count = polideService.count(this.getfilters(null));
		}
		return this.insert ? count + 1 : count;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable, java.util.Map)
	 */
	@Override
	protected List<Polide> fetchData(Pageable pageable, Map<String, Object> filters) {

		Page<Polide> page = polideService.findAllByFilters(this.getfilters(filters), this.setPage(pageable),
				this.countData(this.getfilters(filters)));
		return getIfInsert(page);
	}

	/**
	 * Sets the page.
	 *
	 * @param pageable the pageable
	 * @return the pageable
	 */
	private Pageable setPage(Pageable pageable) {
		switch (this.goToPage) {
		case 1:
			this.goToPage = CUURENT_PAGE;
			return pageable.previousOrFirst();
		case 2:
			this.goToPage = CUURENT_PAGE;
			return pageable.next();
		default:
			return pageable;
		}
	}

	/**
	 * Page select.
	 *
	 * @param renglon the renglon
	 * @return the select page polide DTO
	 */
	public SelectPagePolideDTO pageSelect(Integer renglon) {
		Integer offset = 0;
		Integer bmin = 0;
		Pageable pageable = new PageRequest(this.getActualPage().getNumber(), this.getActualPage().getSize());
		List<Polide> currentData2 = polideService
				.findAllByFilters(this.getfilters(null), pageable, this.countData(this.getfilters(null)), renglon)
				.getContent();
		boolean bandera = CollectionUtils.isNotEmpty(currentData2) || currentData2.size() > 0;
		if (bandera) {
			for (Polide polide : currentData2) {
				bmin = polide.getBmin();
				if (renglon == polide.getRenpol().intValue()) {
					break;
				}
				offset++;
			}
		}
		return new SelectPagePolideDTO((bmin / this.getActualPage().getSize()), renglon, offset, currentData2, bandera);
	}

	/**
	 * Gets the if insert.
	 *
	 * @param page the page
	 * @return the if insert
	 */
	private List<Polide> getIfInsert(Page<Polide> page) {
		this.setLastPage(page.getTotalPages());
		this.actualPage = page;
		this.listPolide = new ArrayList<Polide>(page.getContent());
		if (insert) {
			if (null == this.selected) {
				this.selected = new Polide();
			}
			this.selected.setId(0L);
			// this.selected.setRenpol(this.getLastRenpol());
			// this.selected.setEditable(Boolean.TRUE);
			listPolide.add(this.selected);

		}
		if (CollectionUtils.isNotEmpty(listPolide)) {
			int index = 0;
			for (Polide polide : listPolide) {
				polide.setIndex(index++);
				if (null != this.getSelected() && this.getSelected().getId() != null
						&& this.getSelected().getId() > 0) {
					if (polide.getId() > 0 && polide.getId().equals(this.getSelected().getId())) {
						listPolide.set(polide.getIndex(), this.getSelected());
					}
				}
			}
		}
		return listPolide;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable)
	 */
	@Override
	protected List<Polide> fetchData(Pageable pageable) {
		Page<Polide> page = polideService.findAllByFilters(this.getfilters(null), this.setPage(pageable),
				this.countData(this.getfilters(null)));
		return getIfInsert(page);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#orderBy()
	 */
	@Override
	protected Sort orderBy() {
		return null;
	}

	/**
	 * Gets the insert.
	 *
	 * @return the insert
	 */
	public Boolean getInsert() {
		return insert;
	}

	/**
	 * Sets the insert.
	 *
	 * @param insert            the insert to set
	 */
	public void setInsert(Boolean insert) {
		this.insert = insert;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public Polide getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected            the selected to set
	 */
	public void setSelected(Polide selected) {
		this.selected = selected;
	}

	/**
	 * Gets the reset.
	 *
	 * @return the reset
	 */
	public Boolean getReset() {
		return reset;
	}

	/**
	 * Sets the reset.
	 *
	 * @param reset            the reset to set
	 */
	public void setReset(Boolean reset) {
		this.reset = reset;
	}

	/**
	 * Gets the filters.
	 *
	 * @param filters the filters
	 * @return the filters
	 */
	private Map<String, Object> getfilters(Map<String, Object> filters) {
		if (null == filters) {
			filters = new HashMap<String, Object>();
		}
		if (null != this.polien) {
			filters.put("tippol", polien.getTippol());
			filters.put("mespol", polien.getMespol());
			filters.put("conpol", polien.getConpol());
			filters.put("idsector", this.polien.getIdsector());
			filters.put("anopol", polien.getAnopol());
		}
		return filters;
	}

	/**
	 * Gets the polide service.
	 *
	 * @return the polideService
	 */
	public PolideService getPolideService() {
		return polideService;
	}

	/**
	 * Sets the polide service.
	 *
	 * @param polideService            the polideService to set
	 */
	public void setPolideService(PolideService polideService) {
		this.polideService = polideService;
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * Gets the last renpol.
	 *
	 * @return the last renpol
	 */
	public BigDecimal getLastRenpol() {
		BigDecimal maxrenpol = this.polideRepository.nextRenpol(polien.getAnopol(), polien.getMespol(), polien.getTippol(),
				polien.getConpol(), polien.getIdsector());
		return maxrenpol == null ? BigDecimal.ONE : maxrenpol;
	}

	/**
	 * Gets the polide repository.
	 *
	 * @return the polide repository
	 */
	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	/**
	 * Sets the polide repository.
	 *
	 * @param polideRepository the new polide repository
	 */
	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	/**
	 * Gets the polien.
	 *
	 * @return the polien
	 */
	public Polien getPolien() {
		return polien;
	}

	/**
	 * Sets the polien.
	 *
	 * @param polien the new polien
	 */
	public void setPolien(Polien polien) {
		this.polien = polien;
	}

	/**
	 * Gets the list polide.
	 *
	 * @return the listPolide
	 */
	public List<Polide> getListPolide() {
		return listPolide;
	}

	/**
	 * Sets the list polide.
	 *
	 * @param listPolide            the listPolide to set
	 */
	public void setListPolide(List<Polide> listPolide) {
		this.listPolide = listPolide;
	}

	/**
	 * Gets the last page.
	 *
	 * @return the lastPage
	 */
	public Integer getLastPage() {
		return lastPage;
	}

	/**
	 * Sets the last page.
	 *
	 * @param lastPage            the lastPage to set
	 */
	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * Gets the total elements.
	 *
	 * @return the totalElements
	 */
	public Long getTotalElements() {
		return totalElements;
	}

	/**
	 * Sets the total elements.
	 *
	 * @param totalElements            the totalElements to set
	 */
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	/**
	 * Gets the actual page.
	 *
	 * @return the actualPage
	 */
	public Page<Polide> getActualPage() {
		return actualPage;
	}

	/**
	 * Sets the actual page.
	 *
	 * @param actualPage            the actualPage to set
	 */
	public void setActualPage(Page<Polide> actualPage) {
		this.actualPage = actualPage;
	}

	/**
	 * Gets the last row.
	 *
	 * @return the lastRow
	 */
	public Polide getLastRow() {
		if (this.getActualPage().getNumber() == this.getPageSize()
				&& (CollectionUtils.isNotEmpty(this.listPolide) && this.listPolide.size() < 20)) {
			this.lastRow = this.listPolide.get(this.listPolide.size() - 1);
		} else {
			this.lastRow = this.polideService.getLastRow(this.polien.getAnopol(), this.polien.getTippol(),
					this.polien.getConpol(), this.polien.getIdsector(), this.polien.getMespol());
		}
		return lastRow;
	}

	/**
	 * Sets the last row.
	 *
	 * @param lastRow            the lastRow to set
	 */
	public void setLastRow(Polide lastRow) {
		this.lastRow = lastRow;
	}

	/**
	 * Gets the last index.
	 *
	 * @return the last index
	 */
	public Integer getLastIndex() {
		if (CollectionUtils.isNotEmpty(this.listPolide) && this.listPolide.size() < 20) {
			return listPolide.size();
		} else {
			return 0;
		}
	}

	/**
	 * Gets the go to page.
	 *
	 * @return the goToPage
	 */
	public Integer getGoToPage() {
		return goToPage;
	}

	/**
	 * Sets the go to page.
	 *
	 * @param goToPage            the goToPage to set
	 */
	public void setGoToPage(Integer goToPage) {
		this.goToPage = goToPage;
	}

}

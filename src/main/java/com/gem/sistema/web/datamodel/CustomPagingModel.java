/**
 * .
 */
package com.gem.sistema.web.datamodel;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

// TODO: Auto-generated Javadoc
/**
 * Clase de Paginacion Generica.
 *
 * @author gauss .
 * @param <T> .
 */
public abstract class CustomPagingModel<T> extends LazyDataModel<T> {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The items. */
	private List<T> items;
    
    /** The page size. */
    private int pageSize;
    
    /** The row index. */
    private int rowIndex;
    
    /** The row count. */
    private int rowCount;

    /**
     * Instantiates a new custom paging model.
     */
    public CustomPagingModel() {
        super();
    }

    /**
     * Gets the id.
     *
     * @param entity the entity
     * @return the id
     */
    protected abstract String getId(T entity);

    /**
     * Count data.
     *
     * @param filters the filters
     * @return the int
     */
    protected abstract int countData(Map<String, Object> filters);
    
    /**
     * Count data.
     *
     * @return the int
     */
    protected abstract int countData();

    /**
     * Fetch data.
     *
     * @param pageable the pageable
     * @param filters the filters
     * @return the list
     */
    protected abstract List<T> fetchData(Pageable pageable, Map<String, Object> filters);
    
    /**
     * Fetch data.
     *
     * @param pageable the pageable
     * @return the list
     */
    protected abstract List<T> fetchData(Pageable pageable);
    
    /**
     * Order by.
     *
     * @return the sort
     */
    protected abstract Sort orderBy();
    
    /**
     * Spring Data 4 no acepta Sort null (la 1.9 si): las subclases que devuelven null en orderBy()
     * se cargan sin orden. Las tablas sin paginador piden pageSize 0: se carga un bloque amplio
     * (no Pageable.unpaged(), porque los repositorios nativos llaman a getOffset()).
     */
    protected Pageable pageRequest(int first, int pageSize) {
        Sort sort = orderBy();
        if (sort == null) {
            sort = Sort.unsorted();
        }
        if (pageSize <= 0) {
            return PageRequest.of(0, 100_000, sort);
        }
        return PageRequest.of(first / pageSize, pageSize, sort);
    }

    /**
     * Load.
     *
     * @param first the first
     * @param pageSize the page size
     * @param filters the filters
     * @return the list
     */
    //@Override
    /**
     * PrimeFaces 16: el conteo se pide por separado antes de cargar la pagina.
     * Los filtros llegan como FilterMeta y se traducen al mapa campo -> valor que usan las subclases.
     */
    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return countData(toLegacyFilters(filterBy));
    }

    /**
     * PrimeFaces 16: sustituye a load(first, pageSize, sortField, sortOrder, filters) de PrimeFaces 5.
     * El orden lo define la subclase en orderBy(), igual que antes.
     */
    @Override
    public List<T> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        Pageable page = pageRequest(first, pageSize);
        return fetchData(page, toLegacyFilters(filterBy));
    }

    /**
     * Convierte los filtros de PrimeFaces 16 al formato de PrimeFaces 5 (campo -> valor capturado).
     *
     * @param filterBy filtros de la tabla
     * @return mapa campo -> valor, sin los filtros vacios
     */
    public static Map<String, Object> toLegacyFilters(Map<String, FilterMeta> filterBy) {
        Map<String, Object> filters = new java.util.HashMap<>();
        if (filterBy != null) {
            for (FilterMeta meta : filterBy.values()) {
                if (meta.getFilterValue() != null) {
                    filters.put(meta.getField(), meta.getFilterValue());
                }
            }
        }
        return filters;
    }

    /**
     * Convierte un mapa campo -> valor al formato de filtros de PrimeFaces 16.
     * Sustituye a DataTable.setFilters(Map) de PrimeFaces 5.
     *
     * @param filters mapa campo -> valor
     * @return filtros para DataTable.setFilterByAsMap
     */
    public static Map<String, FilterMeta> toFilterMeta(Map<String, Object> filters) {
        Map<String, FilterMeta> filterBy = new java.util.HashMap<>();
        if (filters != null) {
            filters.forEach((field, value) -> filterBy.put(field,
                    FilterMeta.builder().field(field).filterValue(value).build()));
        }
        return filterBy;
    }

    public List<T> load(int first, int pageSize,  Map<String, Object> filters) {
    	
        Pageable page = pageRequest(first, pageSize);
        setRowCount(countData(filters));
        return fetchData(page, filters);
    }
    
  /**
   * Load.
   *
   * @param first the first
   * @param pageSize the page size
   * @return the list
   */
  //@Override
    public List<T> load(int first, int pageSize) {
        Pageable page = pageRequest(first, pageSize);

        setRowCount(countData());
        return fetchData(page);
    }
    
    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
     */
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
    	//return load(first,  pageSize);
    	
    	
    	 Pageable page = pageRequest(first, pageSize);

         setRowCount(countData(filters));
         return fetchData(page, filters);
    	
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#isRowAvailable()
     */
    @Override
    public boolean isRowAvailable() {
        if ( CollectionUtils.isEmpty(items) ) {
            return false;
        }

        int index = pageSize > 0 ? rowIndex % pageSize : rowIndex;
        return index >= 0 && index < items.size();
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
     */
    @Override
    public String getRowKey(T entity) {
        return getId(entity);
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#getRowData()
     */
    @Override
    public T getRowData() {
        if (CollectionUtils.isEmpty(items)) {
            return null;
        }

        int index = pageSize > 0 ? rowIndex % pageSize : rowIndex;
        if (index >= items.size()) {
            return null;
        }

        return items.get(index);
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
     */
    @Override
    public T getRowData(String rowKey) {
        if (items == null) {
            return null;
        }

        for (T entity : items) {
            if (getId(entity).equals(rowKey)) {
                return entity;
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#setPageSize(int)
     */
    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#getPageSize()
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#getRowIndex()
     */
    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#setRowIndex(int)
     */
    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#setRowCount(int)
     */
    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#setWrappedData(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setWrappedData(Object list) {
        this.items = (List<T>) list;
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#getWrappedData()
     */
    @Override
    public List<T> getWrappedData() {
        return items;
    }

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
    
    
}
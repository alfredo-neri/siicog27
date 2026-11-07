/**
 * .
 */
package com.gem.sistema.web.datamodel;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.LazyDataModel;
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
     * Load.
     *
     * @param first the first
     * @param pageSize the page size
     * @param filters the filters
     * @return the list
     */
    //@Override
    public List<T> load(int first, int pageSize,  Map<String, Object> filters) {
    	
        Pageable page = new PageRequest(first / pageSize, pageSize, orderBy());
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
        Pageable page = new PageRequest(first / pageSize, pageSize, orderBy());

        setRowCount(countData());
        return fetchData(page);
    }
    
    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
     */
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
    	//return load(first,  pageSize);
    	
    	
    	 Pageable page = new PageRequest(first / pageSize, pageSize, orderBy());

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

        int index = rowIndex % pageSize;
        return index >= 0 && index < items.size();
    }

    /* (non-Javadoc)
     * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
     */
    @Override
    public Object getRowKey(T entity) {
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

        int index = rowIndex % pageSize;
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
    public Object getWrappedData() {
        return items;
    }

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
    
    
}
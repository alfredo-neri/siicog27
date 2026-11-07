package com.gem.sistema.web.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.gem.sistema.business.domain.Pm0511;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0511DataModel.
 */
@ManagedBean(name = "pm0511DataModel")
@ViewScoped
public class Pm0511DataModel extends LazyDataModel<Pm0511> {

	/** The list pm 0511. */
	private List<Pm0511> listPm0511;

	/**
	 * Gets the list pm 0511.
	 *
	 * @return the list pm 0511
	 */
	public List<Pm0511> getListPm0511() {
		return listPm0511;
	}

	/**
	 * Sets the list pm 0511.
	 *
	 * @param listPm0511 the new list pm 0511
	 */
	public void setListPm0511(List<Pm0511> listPm0511) {
		this.listPm0511 = listPm0511;
	}

	/**
	 * Ger row data.
	 *
	 * @param rowKey the row key
	 * @return the pm 0511
	 */
	public Pm0511 gerRowData(String rowKey) {
		for (Pm0511 pm0511 : listPm0511) {
			if (pm0511.getId().equals(rowKey)) {
				return pm0511;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	public Object getRowKey(Pm0511 pm0511) {
		return pm0511.getId();
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<Pm0511> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Pm0511> list = new ArrayList<Pm0511>();
		for (Pm0511 pm0511 : listPm0511) {
			boolean matched = Boolean.TRUE;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						String fieldValue = String.valueOf(pm0511.getClass().getField(filterProperty).get(pm0511));

						if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							matched = Boolean.TRUE;
						} else {
							matched = Boolean.FALSE;
							break;
						}
					} catch (Exception e) {
						matched = Boolean.FALSE;
					}
				}
			}

			if (matched) {
				list.add(pm0511);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(list, new LazySorter(sortField, sortOrder));
		}

		// rowCount
		int dataSize = list.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return list.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return list.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return list;
		}
	}

}

class LazySorter implements Comparator<Pm0511> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Pm0511 pm0511_1, Pm0511 pm0511_2) {
		try {
			Object value1 = Pm0511.class.getField(this.sortField).get(pm0511_1);
			Object value2 = Pm0511.class.getField(this.sortField).get(pm0511_2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}

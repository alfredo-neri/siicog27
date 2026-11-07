package com.gem.sistema.web.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.gem.sistema.business.domain.AbstractEntity;
import com.gem.sistema.business.domain.TcMenus;

public class DataModelGeneric<T extends AbstractEntity> extends LazyDataModel<T> {

	private T selected;

	private List<T> listT;

	public T getSelected() {
		return selected;
	}

	public void setSelected(T selected) {
		this.selected = selected;
	}

	public List<T> getListT() {
		return listT;
	}

	public void setListT(List<T> listT) {
		this.listT = listT;
	}

	public DataModelGeneric(List<T> listT) {
		this.listT = listT;
	}

	public Object getRowKey(T object) {
		return object.getId();
	}

	public T getRowData(String rowKey) {
		for (T object : listT) {
			if (object.getId().equals(rowKey)) {
				return object;
			}
		}
		return null;
	}

	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<T> data = new ArrayList<T>();

		// filter
		for (T t : listT) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						String fieldValue = String.valueOf(t.getClass().getField(filterProperty).get(t));

						if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						match = false;
					}
				}
			}

			if (match) {
				data.add(t);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new LazyGenericSort(sortField, sortOrder));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}
}

class LazyGenericSort<T extends AbstractEntity> implements Comparator<T> {

	private String sortField;

	private SortOrder sortOrder;

	public LazyGenericSort(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(T o1, T o2) {
		try {
			Object value1 = TcMenus.class.getField(this.sortField).get(o1);
			Object value2 = TcMenus.class.getField(this.sortField).get(o2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}

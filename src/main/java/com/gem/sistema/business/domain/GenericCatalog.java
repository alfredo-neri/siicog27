package com.gem.sistema.business.domain;

import java.util.Map;
import org.apache.commons.beanutils.BeanMap;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericCatalog.
 *
 * @param <T> the generic type
 */
public class GenericCatalog<T>{
  
  /** The id. */
  private T id;
  
  /** The name. */
  private String name;
  
  /** The attrs. */
  private Map<Object, Object> attrs;

  /**
   * Instantiates a new generic catalog.
   *
   * @param id the id
   * @param name the name
   * @param bean the bean
   */
  public GenericCatalog(T id, String name, Object bean){
    this.id = id;
    this.name = name;
    attrs = new BeanMap(bean);
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(T id){
    this.id = id;
  }
  
  /**
   * Gets the id.
   *
   * @return the id
   */
  public T getId(){
    return id;
  }
  
  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name){
    this.name = name;
  }
  
  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName(){
    return name;
  }

  /**
   * Gets the attrs.
   *
   * @return the attrs
   */
  public Map<Object,Object> getAttrs(){
    return attrs;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString(){
    return new StringBuffer("GenericCatalog[")
              .append("id: ")
              .append(id)
              .append(", name: ")
              .append(name)
              .append(", attrs: ")
              .append(attrs)
              .append("]")
              .toString();
  }

}

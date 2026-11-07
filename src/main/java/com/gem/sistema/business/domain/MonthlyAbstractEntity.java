package com.gem.sistema.business.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.gem.sistema.annotation.IgnoredQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class MonthlyAbstractEntity.
 */
@MappedSuperclass
public abstract class MonthlyAbstractEntity extends AbstractEntity{

  /**
   * Gets the ene.
   *
   * @return the ene
   */
  @Transient
  public abstract BigDecimal getEne();
  
  /**
   * Sets the ene.
   *
   * @param val the new ene
   */
  @Transient
  public abstract void setEne(BigDecimal val);
  
  /**
   * Gets the feb.
   *
   * @return the feb
   */
  @Transient
  public abstract BigDecimal getFeb();
  
  /**
   * Sets the feb.
   *
   * @param val the new feb
   */
  @Transient
  public abstract void setFeb(BigDecimal val);
  
  /**
   * Gets the mar.
   *
   * @return the mar
   */
  @Transient
  public abstract BigDecimal getMar();
  
  /**
   * Sets the mar.
   *
   * @param val the new mar
   */
  @Transient
  public abstract void setMar(BigDecimal val);
  
  /**
   * Gets the abr.
   *
   * @return the abr
   */
  @Transient
  public abstract BigDecimal getAbr();
  
  /**
   * Sets the abr.
   *
   * @param val the new abr
   */
  @Transient
  public abstract void setAbr(BigDecimal val);
  
  /**
   * Gets the may.
   *
   * @return the may
   */
  @Transient
  public abstract BigDecimal getMay();
  
  /**
   * Sets the may.
   *
   * @param val the new may
   */
  @Transient
  public abstract void setMay(BigDecimal val);
  
  /**
   * Gets the jun.
   *
   * @return the jun
   */
  @Transient
  public abstract BigDecimal getJun();
  
  /**
   * Sets the jun.
   *
   * @param val the new jun
   */
  @Transient
  public abstract void setJun(BigDecimal val);
  
  /**
   * Gets the jul.
   *
   * @return the jul
   */
  @Transient
  public abstract BigDecimal getJul();
  
  /**
   * Sets the jul.
   *
   * @param val the new jul
   */
  @Transient
  public abstract void setJul(BigDecimal val);
  
  /**
   * Gets the ago.
   *
   * @return the ago
   */
  @Transient
  public abstract BigDecimal getAgo();
  
  /**
   * Sets the ago.
   *
   * @param val the new ago
   */
  @Transient
  public abstract void setAgo(BigDecimal val);
  
  /**
   * Gets the sep.
   *
   * @return the sep
   */
  @Transient
  public abstract BigDecimal getSep();
  
  /**
   * Sets the sep.
   *
   * @param val the new sep
   */
  @Transient
  public abstract void setSep(BigDecimal val);
  
  /**
   * Gets the oct.
   *
   * @return the oct
   */
  @Transient
  public abstract BigDecimal getOct();
  
  /**
   * Sets the oct.
   *
   * @param val the new oct
   */
  @Transient
  public abstract void setOct(BigDecimal val);
  
  /**
   * Gets the nov.
   *
   * @return the nov
   */
  @Transient
  public abstract BigDecimal getNov();
  
  /**
   * Sets the nov.
   *
   * @param val the new nov
   */
  @Transient
  public abstract void setNov(BigDecimal val);
  
  /**
   * Gets the dic.
   *
   * @return the dic
   */
  @Transient
  public abstract BigDecimal getDic();
  
  /**
   * Sets the dic.
   *
   * @param val the new dic
   */
  @Transient
  public abstract void setDic(BigDecimal val);

  /** The empty entity. */
  private static MonthlyAbstractEntity _emptyEntity;

  /**
   * Gets the empty instance.
   *
   * @return the empty instance
   */
  public static MonthlyAbstractEntity getEmptyInstance(){
    if(_emptyEntity == null){
      _emptyEntity = new MonthlyAbstractEntity(){
        public BigDecimal getEne(){
          return null;
        }
        public void setEne(BigDecimal val){
        }
        public BigDecimal getFeb(){
          return null;
        }
        public void setFeb(BigDecimal val){
        }
        public BigDecimal getMar(){
          return null;
        }
        public void setMar(BigDecimal val){
        }
        public BigDecimal getAbr(){
          return null;
        }
        public void setAbr(BigDecimal val){
        }
        public BigDecimal getMay(){
          return null;
        }
        public void setMay(BigDecimal val){
        }
        public BigDecimal getJun(){
          return null;
        }
        public void setJun(BigDecimal val){
        }
        public BigDecimal getJul(){
          return null;
        }
        public void setJul(BigDecimal val){
        }
        public BigDecimal getAgo(){
          return null;
        }
        public void setAgo(BigDecimal val){
        }
        public BigDecimal getSep(){
          return null;
        }
        public void setSep(BigDecimal val){
        }
        public BigDecimal getOct(){
          return null;
        }
        public void setOct(BigDecimal val){
        }
        public BigDecimal getNov(){
          return null;
        }
        public void setNov(BigDecimal val){
        }
        public BigDecimal getDic(){
          return null;
        }
        public void setDic(BigDecimal val){
        }
      };
    }
    return _emptyEntity;
  }

  /**
   * Months as list.
   *
   * @return the list
   */
  public List<BigDecimal> monthsAsList(){
    return new ArrayList<BigDecimal>(Arrays.asList(new BigDecimal[]{
      getEne(), getFeb(), getMar(),
      getAbr(), getMay(), getJun(),
      getJul(), getAgo(), getSep(),
      getOct(), getNov(), getDic()
    }));
  }

  /**
   * Gets the suma.
   *
   * @return the suma
   */
  public BigDecimal getSuma(){
    BigDecimal suma = new BigDecimal(0.0);
    for(BigDecimal i : monthsAsList()){
      System.out.println("Adding: "+i);
      if(i != null){
        suma = suma.add(i);
      }
    }
    System.out.println("total: "+suma);
    return suma;
  }
}

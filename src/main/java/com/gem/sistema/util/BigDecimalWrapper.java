package com.gem.sistema.util;
import java.math.BigDecimal;

public class BigDecimalWrapper{
  private BigDecimal _inner;

  public BigDecimalWrapper(BigDecimal bd){
    _inner = bd;
  }

  public BigDecimalWrapper(){
    _inner = new BigDecimal(0);
  }

  public BigDecimal add(BigDecimal component){
    if (component == null) return _inner;
    return (_inner = _inner.add(component));
  }

  public BigDecimal toBigDecimal(){
    return _inner;
  }

  public String toString(){
    return _inner.toString();
  }
}

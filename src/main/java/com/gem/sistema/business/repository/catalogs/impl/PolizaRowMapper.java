package com.gem.sistema.business.repository.catalogs.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gem.sistema.business.domain.Polide;

// TODO: Auto-generated Javadoc
/**
 * The Class PolizaRowMapper.
 */
public class PolizaRowMapper implements RowMapper<Polide>{

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public Polide mapRow(ResultSet rs, int rowRows) 
        throws SQLException {
	    Polide polide = new Polide();    
	    polide.setId(rs.getLong("ID"));	    
	    polide.setTippol(rs.getString("TIPPOL"));
	    polide.setMespol(rs.getInt("MESPOL"));
	    polide.setConpol(rs.getInt("CONPOL"));
	    polide.setRenpol(rs.getBigDecimal("RENPOL"));
	    polide.setCuenta(rs.getString("CUENTA"));
	    polide.setScta(rs.getString("SCTA"));
	    polide.setSscta(rs.getString("SSCTA"));
	    polide.setSsscta(rs.getString("SSSCTA"));
	    polide.setSssscta(rs.getString("SSSSCTA"));
	    polide.setRefpol(rs.getString("REFPOL"));
	    polide.setConcep(rs.getString("CONCEP"));
	    polide.setCaopol(rs.getString("CAOPOL"));
	    polide.setCanpol(rs.getBigDecimal("CANPOL"));
	    polide.setAnopol(rs.getInt("ANOPOL")); 
	    polide.setRecpol(rs.getString("RECPOL"));	    
	    polide.setStacon(rs.getInt("STACON"));	    	    
	    polide.setClvban(rs.getInt("CLVBAN")); 
	    polide.setNumdet(rs.getBigDecimal("NUMDET"));  
	    polide.setTipcon(rs.getInt("TIPCON"));    
	    polide.setDn(rs.getString("DN"));   
	    polide.setRela(rs.getInt("RELA"));
	    polide.setTipr(rs.getString("TIPR"));
	    polide.setCanpolh(rs.getBigDecimal("CANPOLH"));
	    polide.setCuenta1(rs.getString("CUENTA1"));
	    polide.setRenpolr(rs.getBigDecimal("RENPOLR"));
	    polide.setClvcto(rs.getString("CLVCTO"));
	    polide.setAntpag(rs.getBigDecimal("ANTPAG"));
	    polide.setFecpol(rs.getDate("FECPOL"));
	    polide.setClvfuen(rs.getString("CLVFUEN"));
	    polide.setUserid(rs.getString("USERID"));
	    polide.setIdsector(rs.getInt("IDSECTOR"));
	    polide.setIdRef(rs.getLong("ID_REF"));
	    polide.setBmin(rs.getInt("BMIN"));
	    polide.setNomcta(rs.getString("NOMCTA"));
	    polide.setNumcto(rs.getString("NUMCTO"));
	    polide.setNumfac(rs.getString("NUMFAC"));
	    polide.setNumprov(rs.getString("NUMPROV"));
	    polide.setRfc(rs.getString("RFC"));
	    
	    return polide;
    }

}
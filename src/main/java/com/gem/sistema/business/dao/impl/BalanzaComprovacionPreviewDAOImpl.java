package com.gem.sistema.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.BalanzaComprovacionPreviewDAO;
import com.gem.sistema.business.dto.BalanzaDTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class BalanzaComprovacionPreviewDAOImpl.
 *
 * @author Mateo
 */
@Repository
public class BalanzaComprovacionPreviewDAOImpl implements BalanzaComprovacionPreviewDAO {

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.BalanzaComprovacionPreviewDAO#generateData(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generateData(Integer mes, Integer idSector, Integer anio) {
		StringBuilder sSql = new StringBuilder();
		Object[] obj = { mes, mes, mes, mes, mes, idSector, anio, idSector };
		sSql.append("SELECT D1.CUENTA, ").append(" D2.NOMCTA,")
				.append(" DECODE(D2.NATCTA , 'D', (D2.SALINI+SUM(D1.CANPOL_ANTERIOR)-SUM(D1.CANPOLH_ANTERIOR)), 0 ) SIC,")
				.append(" DECODE(D2.NATCTA , 'D', 0, (D2.SALINI-SUM(D1.CANPOL_ANTERIOR)+SUM(D1.CANPOLH_ANTERIOR)) ) SIA,")
				.append(" SUM(D1.CANPOL_ACTUAL) X5,").append(" SUM(D1.CANPOLH_ACTUAL) X6,").append("  CASE ")
				.append("  WHEN D2.NATCTA = 'D' THEN")
				.append(" (((D2.SALINI+ SUM(D1.CANPOL_ANTERIOR)-SUM(D1.CANPOLH_ANTERIOR))+SUM(D1.CANPOL_ACTUAL))-SUM(D1.CANPOLH_ACTUAL))")
				.append("  ELSE").append("   0.00").append(" END SFC,").append("  ").append(" CASE ")
				.append("  WHEN D2.NATCTA = 'D' THEN").append("    0.00").append("  ELSE")
				.append("  ((D2.SALINI-SUM(D1.CANPOL_ANTERIOR)+SUM(D1.CANPOLH_ANTERIOR))-SUM(D1.CANPOL_ACTUAL)+SUM(D1.CANPOLH_ACTUAL))")
				.append("  END SFA").append(

						"                             FROM ( SELECT DE.CUENTA,")
				.append(" CASE WHEN DE.MESPOL = " + mes + "  THEN SUM(DE.CANPOL) ELSE 0 END CANPOL_ACTUAL,")
				.append(" CASE WHEN DE.MESPOL = " + mes + "  THEN SUM(DE.CANPOLH) ELSE 0 END CANPOLH_ACTUAL,")
				.append(" CASE WHEN DE.MESPOL <  " + mes + " THEN SUM(DE.CANPOL) ELSE 0 END CANPOL_ANTERIOR,")
				.append(" CASE WHEN DE.MESPOL <  " + mes + " THEN SUM(DE.CANPOLH) ELSE 0 END  CANPOLH_ANTERIOR")
				.append(" FROM POLIDE DE,").append(" POLIEN EN").append(" WHERE DE.MESPOL <= " + mes)
				.append(" AND DE.IDSECTOR = "+ idSector).append(" AND DE.ANOPOL= "+ anio).append(" AND DE.MESPOL = EN.MESPOL")
				.append(" AND DE.TIPPOL = EN.TIPPOL").append(" AND DE.CONPOL = EN.CONPOL")
				.append("   AND DE.IDSECTOR = EN.IDSECTOR").append("   AND DE.ANOPOL=EN.ANOPOL")
				.append("  AND EN.STAPOL  = 'C'").append("  GROUP BY DE.CUENTA,").append("   DE.MESPOL")
				.append("  ) D1,").append("   ( SELECT CU.CUENTA,").append("   CU.NOMCTA,").append("   MA.NATCTA,")
				.append("   CU.SALINI SALINI").append("  FROM CUENTA CU,").append("  MAYCTA MA")
				.append("  WHERE CU.IDSECTOR = " + idSector).append("  AND CU.SCTA     = ''")
				.append("  AND CU.CUENTA   = MA.CUENTA").append("  AND MA.NATCTA   <> ''").append(

						"  ) D2")
				.append(

						"  WHERE D1.CUENTA = D2.CUENTA")
				.append("   GROUP BY  D1.CUENTA,").append("  D2.NOMCTA,").append("  D2.NATCTA,").append("    D2.SALINI")
				.append("  ORDER BY D1.CUENTA");
		return sSql.toString();
	}

}

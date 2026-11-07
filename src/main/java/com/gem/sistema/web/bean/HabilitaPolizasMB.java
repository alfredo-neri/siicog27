package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "habilitaPolizasMB")
@ViewScoped
public class HabilitaPolizasMB extends GenericExecuteProcedure  {
	private static final String PROCEDURE_NAME="SP_HABILITA_POLIZAS";
	
	private Integer mes;
	private boolean acction;
	private List<TcPeriodo> listMeses;
	
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;
	
	@PostConstruct
	public void init() {
		listMeses = periodoRepositoy.findByTipoPeriodo(1);
		
		if(!CollectionUtils.isEmpty(listMeses)) {
			mes = listMeses.get(0).getPeriodo();
		}
	}

	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameter.addValue("i_accion", acction ? 1: 0);
		parameter.addValue("i_mes", mes);

		return parameter;
	}

	@Override
	public void downLoadFile() throws ReportValidationException {

		Map<String, Object> out;
		out = executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
		
		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		}else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un Error");
		}
		
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public boolean isAcction() {
		return acction;
	}

	public void setAcction(boolean acction) {
		this.acction = acction;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}
	
}

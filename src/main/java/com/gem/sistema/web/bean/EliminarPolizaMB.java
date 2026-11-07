package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.event.SelectEvent;
import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.domain.TwCheque;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TwChequeRepository;
import com.gem.sistema.business.service.catalogos.PolizaService;

@ManagedBean(name = "eliminarPolizaMB")
@ViewScoped
public class EliminarPolizaMB extends AbstractMB {

	private Integer mes;
	private List<Integer> listMes = new ArrayList<Integer>();
	private String tipo;
	private List<Cattpo> listCattpo;
	private String numero;
	private List<Polien> polizas = new ArrayList<Polien>();
	private Integer idSector = this.getUserDetails().getIdSector();
	private Polien rowSelected = new Polien();
	private Boolean canDelete;
	private Boolean isAfected;
	private Conctb conctb;
	private String deleteMsn = "";
	private TwCheque chequeActual;

	@ManagedProperty(value = "#{polienRepository}")
	private PolienRepository polienRepository;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty(value = "#{cattpoRepository}")
	private CattpoRepository cattpoRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{polizaService}")
	private PolizaService polizaService;

	@ManagedProperty("#{twChequeRepository}")
	private TwChequeRepository chequeRepository;

	@PostConstruct
	public void init() {
		for (int i = 1; i < 13; i++) {
			this.listMes.add(i);
		}
		listCattpo = cattpoRepository.findAll();
		conctb = conctbRepository.findByIdsector(idSector);
		mes = conctb.getMesemp();
		if (!CollectionUtils.isEmpty(listCattpo)) {
			this.tipo = listCattpo.get(0).getTippol();
		}
		cargarPolizas();
	}

	public void cargarPolizas() {
		if (numeroValido()) {
			this.findWithNumero();
		} else {
			findWithOutNumero();
		}
		canEnabledButton();
	}

	public Boolean numeroValido() {
		if (numero != null && numero != "") {
			try {
				Integer.parseInt(numero);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}

	public void onBlurNumero() {
		if (!numeroValido()) {
			this.displayInfoMessage("El consecutivo solo puede ser numerico");
		}
	}

	public void findWithNumero() {
		polizas = new ArrayList<Polien>();
		this.polizas.add(this.polienRepository.findByMespolAndTippolAndConpolAndIdsector(Integer.valueOf(mes), tipo,
				Integer.valueOf(numero), Integer.valueOf(idSector)));
		validEmptyList();
	}

	public void findWithOutNumero() {
		this.polizas = polienRepository.getByMespolAndTippolAndIdSector(mes, tipo, idSector);
		validEmptyList();
	}

	public void validEmptyList() {
		if (CollectionUtils.isEmpty(polizas)) {
			this.displayInfoMessage("No se encontraron polizas para la busqueda actual");
		}
	}

	public void onRowSelect(SelectEvent event) {
		rowSelected = (Polien) event.getObject();
		changeDeleteMsn();
	}

	private Boolean haveCheque() {
		chequeActual = chequeRepository.findByPolideTippolAndPolideMespolAndPolideConpolAndPolideIdsector(
				rowSelected.getTippol(), rowSelected.getMespol(), rowSelected.getConpol(), rowSelected.getIdsector());
		return chequeActual != null;
	}

	private void deleteCheque() {
		chequeRepository.delete(chequeActual);
	}

	public void deletePolien() {
		if (rowSelected != null) {
			if (!getAffectedMont()) {
				try {
					if (haveCheque()) {
						deleteCheque();
						displayInfoMessage("Se ha eliminado correctamente el cheque " + chequeActual.getNumeroCheque());
					}
					polizas.remove(rowSelected);
					Poliza poliza = getPolizaService().delete(rowSelected, this.getUserDetails().getIdSector(),
							rowSelected.getCappol());
					this.displayInfoMessage(poliza.getMsgError());
					rowSelected = null;

				} catch (Exception e) {
					e.printStackTrace();
					this.displayInfoMessage("Ha ocurrido un error al eliminar la póliza");
				}
			} else {
				this.displayInfoMessage("No es posible eliminar la poliza de un mes afectado");
			}
		} else {
			this.displayInfoMessage("Seleccione una poliza");
		}
	}

	public Boolean getAffectedMont() {
		isAfected = this.getPolienRepository().count(
				PolienPredicates.byMesAfectado(Integer.valueOf(mes), this.getUserDetails().getIdSector(), "A")) > 0;
		if (isAfected) {
			this.displayInfoMessage("El mes ya ha sido afectado");
		}
		return isAfected;
	}

	public Boolean getAdmin() {
		return this.getUserDetails().getRole().equals("ROL ADMINISTRADOR");
	}

	public void canEnabledButton() {
		canDelete = !getAffectedMont() && getAdmin();
	}

	public void limpiar() {
		this.numero = "";
		cargarPolizas();
	}

	public void changeDeleteMsn() {
		Boolean haveCheque = haveCheque();
		deleteMsn = "";
		if (haveCheque) {
			deleteMsn = "El cheque con numero: " + chequeActual.getNumeroCheque()
					+ " ha sido asignado a la poliza actual \n";
		}
		this.deleteMsn += "¿Desea eliminar la póliza " + rowSelected.getConpol() + " del usuario "
				+ rowSelected.getCappol() + (haveCheque ? " y el cheque " + chequeActual.getNumeroCheque() : "") + " ?";
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public List<Integer> getListMes() {
		return listMes;
	}

	public void setListMes(List<Integer> listMes) {
		this.listMes = listMes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Cattpo> getListCattpo() {
		return listCattpo;
	}

	public void setListCattpo(List<Cattpo> listCattpo) {
		this.listCattpo = listCattpo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Polien> getPolizas() {
		return polizas;
	}

	public void setPolizas(List<Polien> polizas) {
		this.polizas = polizas;
	}

	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public CattpoRepository getCattpoRepository() {
		return cattpoRepository;
	}

	public void setCattpoRepository(CattpoRepository cattpoRepository) {
		this.cattpoRepository = cattpoRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public Polien getRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(Polien rowSelected) {
		this.rowSelected = rowSelected;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	public Boolean getIsAfected() {
		return isAfected;
	}

	public void setIsAfected(Boolean isAfected) {
		this.isAfected = isAfected;
	}

	public String getDeleteMsn() {
		return deleteMsn;
	}

	public void setDeleteMsn(String deleteMsn) {
		this.deleteMsn = deleteMsn;
	}

	public PolizaService getPolizaService() {
		return polizaService;
	}

	public void setPolizaService(PolizaService polizaService) {
		this.polizaService = polizaService;
	}

	public TwChequeRepository getChequeRepository() {
		return chequeRepository;
	}

	public void setChequeRepository(TwChequeRepository chequeRepository) {
		this.chequeRepository = chequeRepository;
	}

}

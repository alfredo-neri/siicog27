package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import com.gem.sistema.business.domain.Ingreso;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.repository.load.IngresoRepository;
import com.gem.sistema.web.datamodel.DataModelGeneric;

@ManagedBean(name = "plantillaIngresosOsfemMB")
@ViewScoped
public class PlantillaIngresosOsfemMB extends AbstractMB {
	private static final String PASSWORD = "xcargaplantilla";

	private Ingreso ingresoSelected;
	private List<Ingreso> listIngresos;
	private DataModelGeneric<Ingreso> dataModelIngreso;

	private Integer currentIndex;
	private String oldCuenta;
	private String oldScta;
	private String oldSscta;
	private String oldSsscta;
	private String oldSssscta;

	private UploadedFile file;
	private String validaPass;
	private boolean size;
	private boolean objDisabled;

	@ManagedProperty("#{parametrosRepository}")
	private ParametrosRepository parametrosRepository;

	@ManagedProperty("#{ingresoRepository}")
	private IngresoRepository ingresoRepository;

	@PostConstruct
	public void init() {
		objDisabled = true;
		this.refreshData();
	}

	public void validaPassword() {
		if (validaPass.equals(PASSWORD)) {
			objDisabled = false;
		} else {
			generateNotificationFront(SEVERITY_ERROR, "Error", "Password incorrecto");
		}
	}

	public void handleFileUpload(FileUploadEvent event) {

		try {

			Long idSector = Long.valueOf(this.getUserDetails().getIdSector());
			FileReader fR = this.saveFile(this.getPath("CARG_ARCHIVO"), event.getFile().getFileName(),
					event.getFile().getInputstream());
			BufferedReader b = new BufferedReader(fR);
			String cadena = "";
			while ((cadena = b.readLine()) != null) {

				Ingreso ingreso = convertLineToObject(cadena);

				Ingreso exist = ingresoRepository.findIngreso(idSector, ingreso.getCuenta(), ingreso.getScta(),
						ingreso.getSscta(), ingreso.getSsscta(), ingreso.getSssscta());

				if (null != exist) {
					exist.setCvetxt("1");
					ingresoRepository.save(exist);
				}

			}
			b.close();
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Datos actualizados correctamente.");
			this.refreshData();
			objDisabled = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FileReader saveFile(String path, String filename, InputStream input) throws IOException {
		OutputStream output = new FileOutputStream(new File(path, filename));

		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
		FileReader fileReader = new FileReader(new File(path, filename));
		return fileReader;
	}

	public String getPath(String cvePath) {
		return parametrosRepository.getValorByCv(cvePath);
	}

	public void inputFile(String filename, InputStream input) throws IOException {
		OutputStream output = new FileOutputStream(new File(getPath("CARG_ARCHIVO"), filename));

		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

	public boolean sizeFile(File file) {
		if (file.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	private Ingreso convertLineToObject(String line) {
		String[] columns = line.split(",");
		Integer count = line.split(",").length;

		Ingreso ingreso = new Ingreso();

		ingreso.setCuenta(columns[0]);
		if (count >= 2)
			ingreso.setScta(columns[1]);

		if (count >= 3)
			ingreso.setSscta(columns[2]);

		if (count >= 4)
			ingreso.setSsscta(columns[3]);

		if (count >= 5)
			ingreso.setSssscta(columns[4]);

		return ingreso;
	}

	public void refreshData() {
		listIngresos = ingresoRepository.getIngresosByIdsector((long) this.getUserDetails().getIdSector());
		dataModelIngreso = new DataModelGeneric<>(listIngresos);
	}

	public void onInitRowEdit(final RowEditEvent event) {
		ingresoSelected = (Ingreso) event.getObject();
		if (null != event.getObject()) {

			if (null != ingresoSelected.getId() && ingresoSelected.getId() != 0) {
				oldCuenta = this.ingresoSelected.getCuenta();
				oldScta = this.ingresoSelected.getScta();
				oldSscta = this.ingresoSelected.getSscta();
				oldSsscta = this.ingresoSelected.getSsscta();
				oldSssscta = this.ingresoSelected.getSssscta();

				for (int i = 0; i < dataModelIngreso.getListT().size(); i++) {

					if (dataModelIngreso.getListT().get(i).getId().equals(ingresoSelected.getId())) {
						currentIndex = i;
						break;
					}
				}
			}
		}
	}

	public void onRowEdit(RowEditEvent event) {
		ingresoSelected = (Ingreso) event.getObject();

		if (null == ingresoSelected.getCvetxt()) {
			ingresoSelected.setCvetxt(BigInteger.ZERO.toString());
		}

		ingresoRepository.save(ingresoSelected);

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Actualización finalizada");
	}

	public Ingreso getIngresoSelected() {
		return ingresoSelected;
	}

	public void setIngresoSelected(Ingreso ingresoSelected) {
		this.ingresoSelected = ingresoSelected;
	}

	public List<Ingreso> getListIngresos() {
		return listIngresos;
	}

	public void setListIngresos(List<Ingreso> listIngresos) {
		this.listIngresos = listIngresos;
	}

	public DataModelGeneric<Ingreso> getDataModelIngreso() {
		return dataModelIngreso;
	}

	public void setDataModelIngreso(DataModelGeneric<Ingreso> dataModelIngreso) {
		this.dataModelIngreso = dataModelIngreso;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public String getOldCuenta() {
		return oldCuenta;
	}

	public void setOldCuenta(String oldCuenta) {
		this.oldCuenta = oldCuenta;
	}

	public String getOldScta() {
		return oldScta;
	}

	public void setOldScta(String oldScta) {
		this.oldScta = oldScta;
	}

	public String getOldSscta() {
		return oldSscta;
	}

	public void setOldSscta(String oldSscta) {
		this.oldSscta = oldSscta;
	}

	public String getOldSsscta() {
		return oldSsscta;
	}

	public void setOldSsscta(String oldSsscta) {
		this.oldSsscta = oldSsscta;
	}

	public String getOldSssscta() {
		return oldSssscta;
	}

	public void setOldSssscta(String oldSssscta) {
		this.oldSssscta = oldSssscta;
	}

	public ParametrosRepository getParametrosRepository() {
		return parametrosRepository;
	}

	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

	public IngresoRepository getIngresoRepository() {
		return ingresoRepository;
	}

	public void setIngresoRepository(IngresoRepository ingresoRepository) {
		this.ingresoRepository = ingresoRepository;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getValidaPass() {
		return validaPass;
	}

	public void setValidaPass(String validaPass) {
		this.validaPass = validaPass;
	}

	public boolean isSize() {
		return size;
	}

	public void setSize(boolean size) {
		this.size = size;
	}

	public boolean isObjDisabled() {
		return objDisabled;
	}

	public void setObjDisabled(boolean objDisabled) {
		this.objDisabled = objDisabled;
	}

}

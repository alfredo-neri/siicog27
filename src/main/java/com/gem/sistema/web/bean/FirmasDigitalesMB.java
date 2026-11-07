package com.gem.sistema.web.bean;

import static com.gem.sistema.business.predicates.TcUsuarioPredicates.findByUsuario;

import java.util.Base64;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import com.gem.sistema.business.domain.TcFirmaDigital;
import com.gem.sistema.business.domain.TcUsuario;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcFirmaDigitalRepository;
import com.gem.sistema.business.repository.catalogs.TcUsuarioRepository;
import com.gem.sistema.web.util.ImagesFirmasDigitales;

@ManagedBean(name = "firmasDigitalesMB")
@ViewScoped
public class FirmasDigitalesMB extends AbstractMB {
	private static final String BASE64_TYPE = "data:image/png;base64";
	private String draw;
	private TcFirmaDigital firma;
	private TcUsuario usuario;
	private String imagenPath = "";
	private String imgBase64;
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcFirmaDigitalRepository}")
	private TcFirmaDigitalRepository firmaRepository;

	@ManagedProperty(value = "#{tcUsuarioRepository}")
	private TcUsuarioRepository tcUsuarioRepository;

	@PostConstruct
	private void init() {
		try {
			usuario = tcUsuarioRepository.findOne(findByUsuario(this.getUserDetails().getUsername()));
			firma = firmaRepository.findByUsername(usuario.getUsuario());
			if (firma != null) {
				imagenPath = ImagesFirmasDigitales.createPathTempFromImg(firma.getNombreArchivo(), firma.getArchivo());
			}
		} catch (Exception e) {
			displayInfoMessage("Ha ocurrido un error al cargar la informacion");
			e.printStackTrace();
		}

	}

	public void saveCanvas() {
		if (imgBase64.contains(BASE64_TYPE)) {
			try {
				String encodedImg = imgBase64.split(",")[1];
				byte[] decode1 = Base64.getDecoder().decode(encodedImg);
				String newName = UUID.randomUUID() + ".png";
				if (firma != null) {
					firma.setArchivo(decode1);
					firma.setNombreArchivo(newName);
					firma = this.firmaRepository.save(firma);
				} else {
					firma = firmaRepository.save(new TcFirmaDigital(decode1, usuario, newName));
				}
				imagenPath = ImagesFirmasDigitales.createPathTempFromImg(firma.getNombreArchivo(), firma.getArchivo());
				displayInfoMessage("El archivo se ha cargado correctamente");
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrido un error al guardar la firma");
			}
		}

	}

	public void handleFileUpload(FileUploadEvent event) {
		String newName = UUID.randomUUID() + "" + event.getFile().getFileName();
		if (event.getFile() != null) {

			if (StringUtils.isNotEmpty(newName)) {
				try {
					if (event.getFile().getSize() >= 0) {
						if (firma != null) {
							firma.setArchivo(event.getFile().getContents());
							firma.setNombreArchivo(newName);
							firma = this.firmaRepository.save(firma);
						} else {
							firma = firmaRepository
									.save(new TcFirmaDigital(event.getFile().getContents(), usuario, newName));
						}
						imagenPath = ImagesFirmasDigitales.createPathTempFromImg(firma.getNombreArchivo(),
								firma.getArchivo());
						displayInfoMessage(
								"El archivo " + event.getFile().getFileName() + " se ha cargado correctamente");
					} else {
						displayInfoMessage("El archivo no puede ser vacio");
					}
				} catch (Exception e) {
					displayInfoMessage("ha ocurrido un error al cargar el archivo intente nuevamente");
					e.printStackTrace();
				}
			} else {
				displayInfoMessage("El archivo no puede ser vacio");
			}
		} else {
			displayInfoMessage("El archivo no puede ser vacio");
		}
	}

	public void guardarSing() {

	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcFirmaDigitalRepository getFirmaRepository() {
		return firmaRepository;
	}

	public void setFirmaRepository(TcFirmaDigitalRepository firmaRepository) {
		this.firmaRepository = firmaRepository;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public TcFirmaDigital getFirma() {
		return firma;
	}

	public void setFirma(TcFirmaDigital firma) {
		this.firma = firma;
	}

	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}

	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}

	public TcUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TcUsuario usuario) {
		this.usuario = usuario;
	}

	public String getImagenPath() {
		return imagenPath;
	}

	public void setImagenPath(String imagenPath) {
		this.imagenPath = imagenPath;
	}

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

}

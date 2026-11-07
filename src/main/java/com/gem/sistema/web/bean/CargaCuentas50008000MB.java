package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.MayctaRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.service.catalogos.CargaCuentas58Service;
import com.gem.sistema.util.CopyFileUtil;
// import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.predicates.MayctaPredicates;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Maycta;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class CargaCuentas50008000MB.
 */
@ManagedBean(name = "cargacuenta58MB")
@ViewScoped
public class CargaCuentas50008000MB extends AbstractMB {
	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	private static final String PATH_FILE = "/gem/cuentas/";

	/** The msg error partida gasto. */
	private static String MSG_ERROR_PARTIDA_GASTO = "ERROR EN LA PARTIDA DE GASTO, DEBE SER CAPITULO";

	/** The accounts 5100. */
	private static String[] ACCOUNTS_5100 = { "5100", "8211", "8271", "8251", "8241", "8221" };

	/** The accounts 5200. */
	private static String[] ACCOUNTS_5200 = { "5200", "8212", "8272", "8252", "8242", "8222" };

	/** The accounts 5300. */
	private static String[] ACCOUNTS_5300 = { "5300", "8213", "8273", "8253", "8243", "8223" };

	/** The accounts 5400. */
	private static String[] ACCOUNTS_5400 = { "5400", "8214", "8274", "8254", "8244", "8224" };

	/** The accounts 5600. */
	private static String[] ACCOUNTS_5600 = { "5600", "8215", "8275", "8255", "8245", "8225" };

	/** The accounts 5700. */
	private static String[] ACCOUNTS_5700 = { "5700", "8216", "8276", "8256", "8246", "8226" };

	/** The accounts 8217. */
	private static String[] ACCOUNTS_8217 = { "8217", "8277", "8257", "8247", "8227" };

	/** The cta. */
	private String cta;

	/** The scta. */
	private String scta;

	/** The sscta. */
	private String sscta;

	/** The ssscta. */
	private String ssscta;

	/** The msg. */
	private String msg;

	private String stacta;

	private boolean active = true;

	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The xcatpro repository. */
	@ManagedProperty("#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	/** The natgas repository. */
	@ManagedProperty(value = "#{natgasRepository}")
	private NatgasRepository natgasRepository;

	/** The account service. */
	@ManagedProperty("#{accountService}")
	private AccountService accountService;

	@ManagedProperty("#{mayctaRepository}")
	private MayctaRepository mayctaRepository;

	@ManagedProperty("#{cargaCuentas58Service}")
	private CargaCuentas58Service cargaCuentas58Service;

	private UploadedFile file;

	private StreamedContent downloadFile;

	/**
	 * Sets the cta.
	 *
	 * @param cta the new cta
	 */
	public void setCta(String cta) {
		this.cta = cta;
	}

	/**
	 * Gets the cta.
	 *
	 * @return the cta
	 */
	public String getCta() {
		return cta;
	}

	/**
	 * Sets the scta.
	 *
	 * @param scta the new scta
	 */
	public void setScta(String scta) {
		this.scta = scta;
	}

	/**
	 * Gets the scta.
	 *
	 * @return the scta
	 */
	public String getScta() {
		return scta;
	}

	/**
	 * Sets the sscta.
	 *
	 * @param sscta the new sscta
	 */
	public void setSscta(String sscta) {
		this.sscta = sscta;
	}

	/**
	 * Gets the sscta.
	 *
	 * @return the sscta
	 */
	public String getSscta() {
		return sscta;
	}

	/**
	 * Sets the ssscta.
	 *
	 * @param ssscta the new ssscta
	 */
	public void setSsscta(String ssscta) {
		this.ssscta = ssscta;
	}

	/**
	 * Gets the ssscta.
	 *
	 * @return the ssscta
	 */
	public String getSsscta() {
		return ssscta;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdep repository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatpro repository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	/**
	 * Gets the natgas repository.
	 *
	 * @return the natgas repository
	 */
	public NatgasRepository getNatgasRepository() {
		return natgasRepository;
	}

	/**
	 * Sets the natgas repository.
	 *
	 * @param natgasRepository the new natgas repository
	 */
	public void setNatgasRepository(NatgasRepository natgasRepository) {
		this.natgasRepository = natgasRepository;
	}

	/**
	 * Gets the account service.
	 *
	 * @return the account service
	 */
	public AccountService getAccountService() {
		return accountService;
	}

	/**
	 * Sets the account service.
	 *
	 * @param accountService the new account service
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Load.
	 */
	private boolean validateCamps() {
		if (StringUtils.isBlank(cta)) {
			displayWarnMessage("El Campo Cuenta es Obligatorio");
			return true;
		} else if (StringUtils.isBlank(scta)) {
			displayWarnMessage("El Campo Scta es Obligatorio");
			return true;
		} else if (StringUtils.isBlank(sscta)) {
			displayWarnMessage("El Campo Sscta es Obligatorio");
			return true;
		} else if (StringUtils.isBlank(sscta)) {
			displayWarnMessage("El Campo Ssscta es Obligatorio");
			return true;
		}
		return false;
	}

	public void load() {
		System.out.println("loading :" + cta + "," + scta + "," + sscta + "," + ssscta);
		GemUser user = getUserDetails();
		Long idSector = Long.valueOf(user.getIdSector());
		String modScta = accountService.fillZeros(scta, 10);
		String modSscta = accountService.fillZeros(sscta, 15);
		String modSsscta = accountService.fillZeros(ssscta, 4);
		if (!this.validateCamps()) {
			if (cta.equals("5100")
					&& !(modSsscta.startsWith("1") || modSsscta.startsWith("2") || modSsscta.startsWith("3"))) {
				displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 1000, 2000 o 3000");
				return;
			} else if (cta.equals("5200") && !modSsscta.startsWith("4")) {
				displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 4000");
				return;
			} else if (cta.equals("5300") && !modSsscta.startsWith("8")) {
				displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 8000");
				return;
			} else if (cta.equals("5400") && !modSsscta.startsWith("9")) {
				displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 9000");
				return;
			} else if (cta.equals("5600") && !modSsscta.startsWith("6")) {
				displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 6000");
				return;
			} else if (cta.equals("5700") && !modSsscta.startsWith("5")) {
				displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 5000");
				return;
			}
			String[] names = getNames(modScta, modSscta, modSsscta, idSector);
			if (names == null)
				return;
			if (cta.equals("5100"))
				createLevelAccounts(ACCOUNTS_5100, names, idSector, modScta, modSscta, modSsscta);
			else if (cta.equals("5200"))
				createLevelAccounts(ACCOUNTS_5200, names, idSector, modScta, modSscta, modSsscta);
			else if (cta.equals("5300"))
				createLevelAccounts(ACCOUNTS_5300, names, idSector, modScta, modSscta, modSsscta);
			else if (cta.equals("5400"))
				createLevelAccounts(ACCOUNTS_5400, names, idSector, modScta, modSscta, modSsscta);
			else if (cta.equals("5600"))
				createLevelAccounts(ACCOUNTS_5600, names, idSector, modScta, modSscta, modSsscta);
			else if (cta.equals("5700"))
				createLevelAccounts(ACCOUNTS_5700, names, idSector, modScta, modSscta, modSsscta);
			else if (cta.equals("8217"))
				createLevelAccounts(ACCOUNTS_8217, names, idSector, modScta, modSscta, modSsscta);
		}

	}

	/**
	 * Gets the names.
	 *
	 * @param modScta   the mod scta
	 * @param modSscta  the mod sscta
	 * @param modSsscta the mod ssscta
	 * @param idSector  the id sector
	 * @return the names
	 */
	String[] getNames(String modScta, String modSscta, String modSsscta, Long idSector) {
		String[] values = new String[3];
		Catdep catdep = catdepRepository.findFirstByIdsectorAndClvdepAndUltnivOrderByIdAsc(idSector.intValue(), modScta,
				"S");
		if (catdep == null) {
			displayErrorMessage("no existe la dependencia o no es de último nivel");
			return null;
		} else {
			displayInfoMessage("si existe la dependencia y es de último nivel");
			values[0] = catdep.getNomdep();
		}
		Xcatpro xcatpro = xcatproRepository.findFirstByClvdepAndProgragramAndUltnivAndSector(modScta, modSscta, "S",
				idSector.intValue());
		if (xcatpro == null) {
			displayErrorMessage("no existe el programa o no es de último nivel");
			return null;
		} else {
			displayInfoMessage("si existe el programa y es de último nivel");
			values[1] = xcatpro.getNompro();
		}
		Natgas natgas = natgasRepository.findFirstByClvgasAndIndcapAndIdsector(modSsscta, "S", idSector.intValue());
		if (natgas == null) {
			displayErrorMessage("no existe la naturaleza de gasto.");
			return null;
		} else {
			if (modSsscta.substring(3).equals("0")) {
				displayErrorMessage("La naturaleza de gasto no es de ultimo nivel.");
				return null;
			} else {
				displayInfoMessage("Existe la naturaleza de gasto y es de último nivel");
				values[2] = natgas.getNomgas();
			}
		}
		return values;
	}

	/**
	 * Creates the level accounts.
	 *
	 * @param accounts  the accounts
	 * @param names     the names
	 * @param idSector  the id sector
	 * @param modScta   the mod scta
	 * @param modSscta  the mod sscta
	 * @param modSsscta the mod ssscta
	 */
	void createLevelAccounts(String[] accounts, String[] names, Long idSector, String modScta, String modSscta,
			String modSsscta) {
		for (String account : accounts) {
			createMissingAccounts(account, names, idSector, modScta, modSscta, modSsscta);
			this.cta = StringUtils.EMPTY;
			this.scta = StringUtils.EMPTY;
			this.sscta = StringUtils.EMPTY;
			this.ssscta = StringUtils.EMPTY;

		}
	}

	/**
	 * Creates the missing accounts.
	 *
	 * @param currentAccount the current account
	 * @param names          the names
	 * @param idSector       the id sector
	 * @param modScta        the mod scta
	 * @param modSscta       the mod sscta
	 * @param modSsscta      the mod ssscta
	 */
	void createMissingAccounts(String currentAccount, String[] names, Long idSector, String modScta, String modSscta,
			String modSsscta) {
		Maycta maycta = this.mayctaRepository.findOne(MayctaPredicates.existAccount(currentAccount));
		if (accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(currentAccount,
				modScta, modSscta, modSsscta, "", idSector) == null) {
			accountService.saveAccount(new Cuenta(currentAccount, modScta, modSscta, modSsscta, "", names[2],
					maycta.getNatcta(), "S", 4, this.getUserDetails().getUsername(), idSector));
		}
		if (accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(currentAccount,
				modScta, modSscta, "", "", idSector) == null) {
			accountService.saveAccount(new Cuenta(currentAccount, modScta, modSscta, "", "", names[1],
					maycta.getNatcta(), "N", 3, this.getUserDetails().getUsername(), idSector));
		}
		if (accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(currentAccount,
				modScta, "", "", "", idSector) == null) {
			accountService.saveAccount(new Cuenta(currentAccount, modScta, "", "", "", names[0], maycta.getNatcta(),
					"N", 2, this.getUserDetails().getUsername(), idSector));
		}

	}

	String fileName = StringUtils.EMPTY;
	Map<String, Object> outParameters = null;
	InputStream stream1 = null;

	public void handleFileUpload(FileUploadEvent event) {
		fileName = UUID.randomUUID() + event.getFile().getFileName();

		try {
			String pathFillFullName = CopyFileUtil.copyFile(fileName, event.getFile().getInputstream(), PATH_FILE);
			outParameters = this.cargaCuentas58Service.cargaCueta(pathFillFullName,
					this.getUserDetails().getUsername());
			if (Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) == 1) {
				displayInfoMessage(outParameters.get("O_MSG_ERROR").toString());
			} else {
				active = false;
				displayWarnMessage(outParameters.get("O_MSG_ERROR").toString() + "\n");
				try {
					stream1 = new FileInputStream(new File(outParameters.get("O_FULL_PATH").toString()));

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				downloadFile = new DefaultStreamedContent(stream1, "application/txt",
						outParameters.get("O_FULL_PATH").toString().substring(12));
			}
		} catch (IOException e) {
			displayInfoMessage("Error al cargar el archivo");
		}

	}

	public MayctaRepository getMayctaRepository() {
		return mayctaRepository;
	}

	public void setMayctaRepository(MayctaRepository mayctaRepository) {
		this.mayctaRepository = mayctaRepository;
	}

	public CargaCuentas58Service getCargaCuentas58Service() {
		return cargaCuentas58Service;
	}

	public void setCargaCuentas58Service(CargaCuentas58Service cargaCuentas58Service) {
		this.cargaCuentas58Service = cargaCuentas58Service;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public StreamedContent getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(StreamedContent downloadFile) {
		this.downloadFile = downloadFile;
	}

}

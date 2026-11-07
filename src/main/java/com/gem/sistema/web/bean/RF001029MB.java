package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ReporteGenericoService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc

/**
 * The Class RF001029MB.
 *
 * @author Alfredo Neri
 * @Date 31/05/2021
 * @Changes Se agrega opcion de TXT, se ajusta diseno del reporte grafico
 */
@ManagedBean(name = "rF001029MB")
@ViewScoped
public class RF001029MB extends BaseDirectReport {
    private static final String CLAVE_REPORT = "RPT_EDO_ANA_PRESUP_EGRE_CLASF_OBJ_GASTO";

    private String mes;
    private Integer saldo0;
    private String nameFile;
    private String pathName;
    private InputStream stream = null;
    private StreamedContent txtFile;
    private List<TcMes> listMes;

    /**
     * The tc mes repository.
     */
    @ManagedProperty("#{tcMesRepository}")
    private TcMesRepository tcMesRepository;

    @ManagedProperty("#{validatePolicyService}")
    private ValidatePolicyService validatePolicyService;

    /**
     * The conctb repository.
     */
    @ManagedProperty("#{conctbRepository}")
    private ConctbRepository conctbRepository;

    @ManagedProperty("#{puestosFirmasService}")
    private PuestosFirmasService puestosFirmasService;

    @ManagedProperty("#{reporteGenericoService}")
    private ReporteGenericoService reporteGenericoService;

    @ManagedProperty("#{tcReportesFirmasRepository}")
    private TcReportesFirmasRepository reporteFirmasRepository;

    public ValidatePolicyService getValidatePolicyService() {
        return validatePolicyService;
    }

    public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
        this.validatePolicyService = validatePolicyService;
    }

    /**
     * Gets the mes.
     *
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * Sets the mes.
     *
     * @param mes the new mes
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * Gets the list mes.
     *
     * @return the list mes
     */
    public List<TcMes> getListMes() {
        return listMes;
    }

    /**
     * Sets the list mes.
     *
     * @param listMes the new list mes
     */
    public void setListMes(List<TcMes> listMes) {
        this.listMes = listMes;
    }

    /**
     * Gets the tc mes repository.
     *
     * @return the tc mes repository
     */
    public TcMesRepository getTcMesRepository() {
        return tcMesRepository;
    }

    /**
     * Sets the tc mes repository.
     *
     * @param tcMesRepository the new tc mes repository
     */
    public void setTcMesRepository(TcMesRepository tcMesRepository) {
        this.tcMesRepository = tcMesRepository;
    }

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
        this.saldo0 = 0;
        jasperReporteName = "RF001029_Cap";

        listMes = tcMesRepository.findAll();

        if (!CollectionUtils.isEmpty(listMes)) {
            mes = listMes.get(0).getMes();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
     */
    @Override
    public Map<String, Object> getParametersReports() throws ReportValidationException {
        Map<String, Object> parameters = new HashMap<String, Object>();

        Integer sector = this.getUserDetails().getIdSector();
        TcMes descripMes = tcMesRepository.findByMes(mes);
        Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

        parameters.put("pImg", conctb.getImagePathRigth());
        parameters.put("pImg2", conctb.getImagePathLeft());
        parameters.put("pNomMun", conctb.getNomDep());
        parameters.put("pLastDay", getLastDayByAnoEmp(Integer.valueOf(mes), conctb.getAnoemp()));
        parameters.put("pDescripMes", descripMes.getDescripcion());
        parameters.put("pAnio", conctb.getAnoemp());


        TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

        parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

        endFilename = getNameFileReport(mes, conctb, reporte);


        parameters.put("pSsql", this.generaQuery(this.getUserDetails().getIdSector(), Integer.valueOf(mes)));

        return parameters;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
     */
    @Override
    public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Genera query.
     *
     * @param idsector the idsector
     * @param mes      the mes
     * @return the string
     */
    public String generaQuery(Integer idsector, Integer mes) {
        StringBuilder sSql = new StringBuilder();
        StringBuilder ampli = new StringBuilder();
        StringBuilder redu = new StringBuilder();
        StringBuilder ejpa = new StringBuilder();
        StringBuilder ejxpa = new StringBuilder();
        StringBuilder toeje = new StringBuilder();
        StringBuilder compro = new StringBuilder();

        sSql.append("SELECT ").append("T2.NOMBRE,").append("T2.PARTIDA,").append("T2.APROBADO,").append("T2.AMPLIREDU,")
                .append("T2.MODIFICADO,").append("T2.COMPROMETIDO,").append("T2.DEVENGADO,").append("T2.EJERCIDO,")
                .append("T2.PAGADO,").append("(T2.MODIFICADO - T2.DEVENGADO) SUBEJERCIDO").append(" FROM ( ")
                .append(" SELECT ").append("T1.NOMBRE,").append("T1.PARTIDA,").append("T1.AUTO APROBADO,")
                .append("(T1.AMPLI-T1.REDU)AMPLIREDU,").append("((T1.AUTO)+(T1.AMPLI)-(T1.REDU))MODIFICADO,")
                .append("T1.COMPRO COMPROMETIDO,").append("T1.EJXPA DEVENGADO,").append("T1.TOEJE EJERCIDO,")
                .append("T1.EJPA PAGADO,").append("T1.TOEJE").append(" FROM (SELECT ").append("N.NOMGAS NOMBRE,")
                .append("P.PARTIDA PARTIDA,")
                .append("SUM(P.AUTO1_1+P.AUTO1_2+P.AUTO1_3+P.AUTO1_4+P.AUTO1_5+P.AUTO1_6+P.AUTO1_7+P.AUTO1_8+P.AUTO1_9+P.AUTO1_10+P.AUTO1_11+P.AUTO1_12)AUTO,");

        for (int i = 1; i <= mes; i++) {

            ampli.append(" P.AMPLI1_" + i + "+");
            redu.append(" P.REDU1_" + i + "+");
            ejpa.append(" P.EJPA1_" + i + "+");
            ejxpa.append(" P.EJXPA1_" + i + "+");
            toeje.append(" P.TOEJE1_" + i + "+");
            compro.append(" P.COMPRO1_" + i + "+");

        }

        sSql.append(" SUM(").append(ampli.substring(1, ampli.length() - 1)).append(") AMPLI,").append(" SUM(")
                .append(redu.substring(1, redu.length() - 1)).append(") REDU,").append(" SUM(")
                .append(ejpa.substring(1, ejpa.length() - 1)).append(") EJPA,").append(" SUM(")
                .append(ejxpa.substring(1, ejxpa.length() - 1)).append(") EJXPA,").append(" SUM(")
                .append(toeje.substring(1, toeje.length() - 1)).append(" )TOEJE,").append(" SUM(")
                .append(compro.substring(1, compro.length() - 1)).append(" )COMPRO").append(" FROM PASO P")
                .append(" JOIN NATGAS N ON N.CLVGAS=P.PARTIDA AND N.IDSECTOR=P.IDSECTOR")
                .append(" WHERE P.IDSECTOR=" + idsector).append(" GROUP BY N.NOMGAS,").append("P.PARTIDA")
                .append(" ORDER BY").append(" P.PARTIDA ASC)T1)T2");

        if (this.saldo0 == 0) {
            sSql.append(" WHERE T2.APROBADO <> 0 OR T2.AMPLIREDU <> 0 OR T2.MODIFICADO <> 0 ")
                    .append(" OR T2.DEVENGADO <> 0 OR T2.PAGADO <> 0");
        }

        System.out.println(sSql.toString());
        return sSql.toString();

    }

    private String generateSqlForTXT(Integer mes, Integer idSector) {

        StringBuilder sSql = new StringBuilder();
        String auto = "SUM(";
        String redu = "(";
        String ejpa = "SUM(";
        String ampli = "(";
        String ejxpa = "SUM(";

        for (int y = 1; y <= mes; y++) {
            redu = redu + " P.REDU1_" + y + " +";
            ejpa = ejpa + " P.EJPA1_" + y + " +";
            ampli = ampli + " P.AMPLI1_" + y + " +";
            ejxpa = ejxpa + " P.EJXPA1_" + y + " +";
        }

        for (int y = 1; y <= 12; y++) {
            auto = auto + " P.AUTO1_" + y + " +";
        }

        auto = auto.substring(0, auto.length() - 2) + " ) EGRESO_APROBADO, ";
        redu = redu.substring(0, redu.length() - 2) + " ) ";
        ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO ";
        ampli = ampli.substring(0, ampli.length() - 2) + " ) ";
        ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";

        sSql.append(
                        "SELECT '\"' || RES.PARTIDA||'\"|\"'|| RES.NOMGAS||'\"|\"'|| FN_GET_FORMAT_NUMBER(RES.EGRESO_APROBADO)||'\"|\"'|| ")
                .append("FN_GET_FORMAT_NUMBER( RES.AMP_RDC)||'\"|\"'||")
                .append("FN_GET_FORMAT_NUMBER(RES.EGRESO_APROBADO+RES.AMP_RDC)||'\"|\"'|| ")
                .append("FN_GET_FORMAT_NUMBER(RES.DEVENGADO)||'\"|\"'|| FN_GET_FORMAT_NUMBER(RES.PAGADO)||'\"|\"'|| FN_GET_FORMAT_NUMBER((RES.EGRESO_APROBADO+RES.AMP_RDC) - RES.DEVENGADO)	||'\"' ")
                .append("FROM (SELECT P.PARTIDA, N.NOMGAS, ").append(auto).append("SUM(").append(ampli).append("-")
                .append(redu).append(") AMP_RDC, ").append(ejxpa).append(ejpa).append("FROM PASO P, NATGAS N WHERE ")
                .append("P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND P.IDSECTOR= ").append(idSector)
                .append(" GROUP BY P.PARTIDA, N.NOMGAS ORDER BY 1,2	) RES");

        if (this.saldo0 == 0) {
            sSql.append(" WHERE RES.EGRESO_APROBADO <> 0 OR RES.AMP_RDC <> 0 OR ")
                    .append(" (RES.EGRESO_APROBADO+RES.AMP_RDC) <> 0 OR RES.DEVENGADO <> 0 OR ")
                    .append(" RES.PAGADO <> 0 OR ((RES.EGRESO_APROBADO+RES.AMP_RDC) - RES.DEVENGADO) <> 0");
        }

        System.out.println(sSql.toString());
        return sSql.toString();
    }

    public void validateMonth() {
        this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
    }

    public ConctbRepository getConctbRepository() {
        return conctbRepository;
    }

    public void setConctbRepository(ConctbRepository conctbRepository) {
        this.conctbRepository = conctbRepository;
    }

    public PuestosFirmasService getPuestosFirmasService() {
        return puestosFirmasService;
    }

    public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
        this.puestosFirmasService = puestosFirmasService;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public StreamedContent getTxtFile() {

        Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
        nameFile = "EAEPE" + conctb.getClave() + conctb.getAnoemp() + StringUtils.leftPad(mes.toString(), 2, "0")
                + ".txt";

        pathName = this.reporteGenericoService.getFileTxtWithSql(
                this.generateSqlForTXT(Integer.valueOf(mes), this.getUserDetails().getIdSector()), nameFile);

        try {
            stream = new FileInputStream(new File(pathName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        txtFile = new DefaultStreamedContent(stream, "application/txt", pathName.substring(13));

        generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
                "Se Generó el Archivo: " + pathName.substring(13));
        return txtFile;
    }

    public void setTxtFile(StreamedContent txtFile) {
        this.txtFile = txtFile;
    }

    public ReporteGenericoService getReporteGenericoService() {
        return reporteGenericoService;
    }

    public void setReporteGenericoService(ReporteGenericoService reporteGenericoService) {
        this.reporteGenericoService = reporteGenericoService;
    }

    public TcReportesFirmasRepository getReporteFirmasRepository() {
        return reporteFirmasRepository;
    }

    public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
        this.reporteFirmasRepository = reporteFirmasRepository;
    }

    public Integer getSaldo0() {
        return saldo0;
    }

    public void setSaldo0(Integer saldo0) {
        this.saldo0 = saldo0;
    }

}

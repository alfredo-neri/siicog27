package com.gem.sistema.web.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.ennum.constans.RespaldoEnnum;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.ZipFolder.zipDirectory;

@ManagedBean(name = "respaldosMB")
@ViewScoped
public class RespaldosMB extends AbstractMB implements Runnable {

	private static final Long DURMIENDO = 120000l;
	private StreamedContent respaldo;

	public void generateBackup() {
		Thread thread = new Thread();

		try {
			executeRespaldo();
			thread.start();
			thread.sleep(DURMIENDO);
			executeCMD();

			InputStream pathBakup = new FileInputStream(
					new File(RespaldoEnnum.PATH_BAKUPS.getValue() + RespaldoEnnum.NAME_ZIP.getValue()));
			respaldo = new DefaultStreamedContent(pathBakup, "application/zip", RespaldoEnnum.NAME_ZIP.getValue());

			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se Generaro correctamente el respaldo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void executeRespaldo() throws IOException {
		Runtime.getRuntime().exec(RespaldoEnnum.CMD_BK.getValue());
	}

	private static void executeCMD() throws Exception {
		ProcessBuilder builder = new ProcessBuilder(RespaldoEnnum.EXE.getValue(), "/c",
				RespaldoEnnum.COMAND_LIST_DIR.getValue());
		builder.redirectErrorStream(true);

		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		List<String> lines = new ArrayList<String>();
		String line;

		while (true) {
			line = r.readLine();

			if (line == null) {
				break;
			}
			lines.add(line);
		}

		zipDirectory(new File(RespaldoEnnum.PATH_BAKUPS.getValue() + lines.get(lines.size() - 1)),
				RespaldoEnnum.PATH_BAKUPS.getValue() + RespaldoEnnum.NAME_ZIP.getValue());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public StreamedContent getRespaldo() {
		return respaldo;
	}

	public void setRespaldo(StreamedContent respaldo) {
		this.respaldo = respaldo;
	}

}

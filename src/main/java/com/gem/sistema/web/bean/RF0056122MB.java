package com.gem.sistema.web.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.IOException;
import java.util.Map;
import org.primefaces.model.StreamedContent;


import com.gem.sistema.business.service.callsp.CallSpService;
import com.gem.sistema.business.service.callsp.ParamsGenPedctDTO;
import com.gem.sistema.business.service.callsp.ParamsGenTxtFichaDisenoDTO;
import com.gem.sistema.business.service.callsp.ParamsSpDTO;
import com.gem.sistema.web.security.model.GemUser;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0056122MB.
 */
@Component("rF0056122MB")
@Scope("view")
public class RF0056122MB extends BaseDirectReportByPL {
	
	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public StreamedContent getFile() {
		StreamedContent streamedContent = null;
		try{
			GemUser user = this.getUserDetails();
			
			ParamsSpDTO params = new ParamsGenPedctDTO();
			params.setUser(user.getUsername());
			
			callSpService.getFile("SP_GENERA_TXT_PEDC", params);
	
			if (params.getCodError() == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
			} else {
				streamedContent = sendFileByStream(params.getResultFile());	
			}
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, e.getMessage());
		}    
		return streamedContent;
    }


}

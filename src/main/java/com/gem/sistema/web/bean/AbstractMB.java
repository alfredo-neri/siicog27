/**
 *
 */
package com.gem.sistema.web.bean;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractMB.
 *
 * @author gauss
 */
public class AbstractMB {



	/**
	 * getUserDetails Metodo para obtener el usuario logueado.
	 *
	 * @return the user details
	 */
   public GemUser getUserDetails() {
     if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
       return (GemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     } else {
       return null;
     }
   }

   /**
    * Gets the id sector for current user.
    *
    * @return the id sector for current user
    */
   public Long getIdSectorForCurrentUser(){
     return Long.valueOf(getUserDetails().getIdSector());
   }

   /**
    * Display error message.
    *
    * @param str the str
    */
   public void displayErrorMessage(String str){
     FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", str);
     FacesContext.getCurrentInstance().addMessage(null, message);
   }

   /**
    * Display warn message.
    *
    * @param str the str
    */
   public void displayWarnMessage(String str){
     FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Warn", str);
     FacesContext.getCurrentInstance().addMessage(null, message);
   }

   /**
    * Display info message.
    *
    * @param str the str
    */
   public void displayInfoMessage(String str){
     FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", str);
     FacesContext.getCurrentInstance().addMessage(null, message);
   }

}

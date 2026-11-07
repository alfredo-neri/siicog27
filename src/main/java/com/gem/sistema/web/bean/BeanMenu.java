package com.gem.sistema.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class BeanMenu.
 */
public class BeanMenu implements Serializable {
   
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The visible menu. */
	private boolean visibleMenu = false;
    
    /** The dis inicio. */
    private boolean disInicio;
    
    /** The dis usuarios. */
    private boolean disUsuarios;
    
    /** The dis alumnos. */
    private boolean disAlumnos;
    
    /** The dis articulos. */
    private boolean disArticulos;
    
    /** The img inventarios. */
    private List<String> imgInventarios;
    
    /**
     * Reset menu.
     */
    private void resetMenu() {
        setDisAlumnos(false);
        setDisArticulos(false);
        setDisInicio(false);
        setDisUsuarios(false);
    }
    
    /**
     * Load inventario.
     */
    public void loadInventario() {
        imgInventarios = new ArrayList<String>();
        for(int i = 1; i <= 12; i++) {
            imgInventarios.add("/resources/images/inventarios"+(i)+".png");
        }
    }

   

    /**
     * Checks if is visible menu.
     *
     * @return the visibleMenu
     */
    public boolean isVisibleMenu() {
        return visibleMenu;
    }

    /**
     * Sets the visible menu.
     *
     * @param visibleMenu the visibleMenu to set
     */
    public void setVisibleMenu(boolean visibleMenu) {
        this.visibleMenu = visibleMenu;
    }

    /**
     * Checks if is dis inicio.
     *
     * @return the disInicio
     */
    public boolean isDisInicio() {
        return disInicio;
    }

    /**
     * Sets the dis inicio.
     *
     * @param disInicio the disInicio to set
     */
    public void setDisInicio(boolean disInicio) {
        this.disInicio = disInicio;
    }

    /**
     * Checks if is dis usuarios.
     *
     * @return the disUsuarios
     */
    public boolean isDisUsuarios() {
        return disUsuarios;
    }

    /**
     * Sets the dis usuarios.
     *
     * @param disUsuarios the disUsuarios to set
     */
    public void setDisUsuarios(boolean disUsuarios) {
        this.disUsuarios = disUsuarios;
    }

    /**
     * Checks if is dis alumnos.
     *
     * @return the disAlumnos
     */
    public boolean isDisAlumnos() {
        return disAlumnos;
    }

    /**
     * Sets the dis alumnos.
     *
     * @param disAlumnos the disAlumnos to set
     */
    public void setDisAlumnos(boolean disAlumnos) {
        this.disAlumnos = disAlumnos;
    }

    /**
     * Checks if is dis articulos.
     *
     * @return the disArticulos
     */
    public boolean isDisArticulos() {
        return disArticulos;
    }

    /**
     * Sets the dis articulos.
     *
     * @param disArticulos the disArticulos to set
     */
    public void setDisArticulos(boolean disArticulos) {
        this.disArticulos = disArticulos;
    }

    /**
     * Gets the img inventarios.
     *
     * @return the imgInventarios
     */
    public List<String> getImgInventarios() {
        return imgInventarios;
    }

    /**
     * Sets the img inventarios.
     *
     * @param imgInventarios the imgInventarios to set
     */
    public void setImgInventarios(List<String> imgInventarios) {
        this.imgInventarios = imgInventarios;
    }
    
    /**
     * Disabled inicio.
     *
     * @return the string
     */
    public String disabledInicio() {
        resetMenu();
        setDisInicio(true);
        return "/content/welcome?faces-redirect=true";
    }
    
    /**
     * Disabled usuarios.
     *
     * @return the string
     */
    public String disabledUsuarios() {
        resetMenu();
        setDisUsuarios(true);
        return "/content/usuarios/users?faces-redirect=true";
    }
    
    /**
     * Disabled alumnos.
     *
     * @return the string
     */
    public String disabledAlumnos() {
        resetMenu();
        setDisAlumnos(true);
        return "/content/alumnos/students?faces-redirect=true";
    }
    
    /**
     * Disabled articulos.
     *
     * @return the string
     */
    public String disabledArticulos() {
        resetMenu();
        setDisArticulos(true);
        return "/content/articulos/products?faces-redirect=true";
    }
}

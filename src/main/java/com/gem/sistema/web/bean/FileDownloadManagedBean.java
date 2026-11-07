package com.gem.sistema.web.bean;

import javax.faces.bean.SessionScoped;

import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class FileDownloadManagedBean.
 */
@Component(value="fileDownloadManagedBean")
@SessionScoped
public class FileDownloadManagedBean {
	
	
	/** The Constant DEFAULT_PATH. */
	private static final String DEFAULT_PATH ="/tmp/";
	

	/** The content. */
	private StreamedContent content;
	
	{
        try {
//            content = new DefaultStreamedContent(new FileInputStream(
//                    new File(DEFAULT_PATH+"")),"application/pdf","primefaces_5");
        }
        catch(Exception e){
            // Nothing
        }
    }
 
    /**
     * Gets the content.
     *
     * @return the content
     */
    public StreamedContent getContent() {
        return content;
    }
 
    /**
     * Sets the content.
     *
     * @param content the new content
     */
    public void setContent(StreamedContent content) {
        this.content = content;
    }
    
    
    
}

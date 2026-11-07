package com.gem.sistema.web.bean;

import org.springframework.context.annotation.Scope;
import com.gem.sistema.web.util.StreamedContents;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class FileDownloadManagedBean.
 */
@Component(value="fileDownloadManagedBean")
// difem26: la anotacion de scope de JSF era ignorada por Spring (singleton compartido)
@Scope("session")
public class FileDownloadManagedBean {
	
	
	/** The Constant DEFAULT_PATH. */
	private static final String DEFAULT_PATH ="/tmp/";
	

	/** The content. */
	private StreamedContent content;
	
	{
        try {
//            content = StreamedContents.of(new FileInputStream(
//                    new File(DEFAULT_PATH+"")), "application/pdf", "primefaces_5", null);
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

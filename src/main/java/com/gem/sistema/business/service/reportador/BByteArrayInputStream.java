package com.gem.sistema.business.service.reportador;

import java.io.ByteArrayInputStream;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class BByteArrayInputStream.
 */
public class BByteArrayInputStream extends ByteArrayInputStream {
	
	/**
	 * Instantiates a new b byte array input stream.
	 *
	 * @param buf the buf
	 */
	public BByteArrayInputStream(byte[] buf) {
		super(buf);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.io.ByteArrayInputStream#close()
	 */
	@Override
	  public void close() throws IOException {
		  mark = 0;
	      reset();
	  }

}

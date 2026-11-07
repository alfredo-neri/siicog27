package com.gem.sistema.web.util;

import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;

/**
 * Sustituye a los constructores de {@code DefaultStreamedContent} que PrimeFaces retiro en la
 * version 10. El flujo se recibe ya abierto, como antes, para no cambiar el momento en que se genera
 * el archivo; el builder solo lo entrega cuando PrimeFaces lo pide.
 */
public final class StreamedContents {

	private StreamedContents() {
	}

	public static DefaultStreamedContent of(InputStream stream, String contentType, String name,
			String contentEncoding) {
		DefaultStreamedContent.Builder builder = DefaultStreamedContent.builder().stream(() -> stream);
		if (contentType != null) {
			builder.contentType(contentType);
		}
		if (name != null) {
			builder.name(name);
		}
		if (contentEncoding != null) {
			builder.contentEncoding(contentEncoding);
		}
		return builder.build();
	}
}

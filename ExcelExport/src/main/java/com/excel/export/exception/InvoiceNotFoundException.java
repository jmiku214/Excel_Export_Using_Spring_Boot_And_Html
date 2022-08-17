package com.excel.export.exception;

public class InvoiceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvoiceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceNotFoundException(String customMessage) {
		super(customMessage);
	}

}

package com.excel.export.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excel.export.exception.InvoiceNotFoundException;
import com.excel.export.model.Invoice;
import com.excel.export.repo.InvoiceRepository;
import com.excel.export.service.InvoiceService;

@Component
public class InvoiceServiceImpl implements InvoiceService {

	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	
	@Override
	public Invoice saveInvice(Invoice invoice) {
		
		return invoiceRepository.save(invoice);
	}

	@Override
	public List<Invoice> getAllInvoices() {
		
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice getInvoiceById(Long id) {
		Optional<Invoice> opt = invoiceRepository.findById(id);
	       if(opt.isPresent()) {
	           return opt.get();
	       } else {
	           throw new InvoiceNotFoundException("Invoice with Id : "+id+" Not Found");
	       }
	}

	@Override
	public void deleteInvoiceById(Long id) {
		invoiceRepository.delete(getInvoiceById(id));

	}

	@Override
	public void updateInvoice(Invoice invoice) {
	  invoiceRepository.save(invoice);	

	}

}

package com.excel.export.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excel.export.model.Invoice;

@Service
public interface InvoiceService {

	public Invoice saveInvice(Invoice invoice);
    public List<Invoice> getAllInvoices();
    public Invoice getInvoiceById(Long id);
    public void deleteInvoiceById(Long id);
    public void updateInvoice(Invoice invoice);
}

package com.excel.export.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excel.export.exception.InvoiceNotFoundException;
import com.excel.export.model.Invoice;
import com.excel.export.service.InvoiceService;
import com.excel.export.view.InvoiceDataExcelExport;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	 @GetMapping("/")
	    public String showHomePage() {
	       return "homePage";
	    }

	    @GetMapping("/register")
	    public String showRegistration() {
	       return "registerInvoicePage";
	    }

	    @PostMapping("/save")
	    public String saveInvoice(
	            @ModelAttribute Invoice invoice,
	            Model model
	            ) {
	        invoiceService.saveInvice(invoice);
	        Long id = invoiceService.saveInvice(invoice).getId();
	        String message = "Record with id : '"+id+"' is saved successfully !";
	        model.addAttribute("message", message);
	        return "registerInvoicePage";
	    }

	    @GetMapping("/getAllInvoices")
	    public String getAllInvoices(
	            @RequestParam(value = "message", required = false) String message,
	            Model model
	            ) {
	       List<Invoice> invoices= invoiceService.getAllInvoices();
	       model.addAttribute("list", invoices);
	       model.addAttribute("message", message);
	       return "allInvoicesPage";
	    }

	    @GetMapping("/edit")
	    public String getEditPage(
	            Model model,
	            RedirectAttributes attributes,
	            @RequestParam Long id
	            ) {
	       String page = null; 
	       try {
	       Invoice invoice = invoiceService.getInvoiceById(id);
	       model.addAttribute("invoice", invoice);
	       page="editInvoicePage";
	       } catch (InvoiceNotFoundException e) {
	           e.printStackTrace();
	           attributes.addAttribute("message", e.getMessage());
	           page="redirect:getAllInvoices";
	       }
	       return page; 
	    }

	    @PostMapping("/update")
	    public String updateInvoice(
	            @ModelAttribute Invoice invoice,
	            RedirectAttributes attributes
	            ) {
	       invoiceService.updateInvoice(invoice);
	       Long id = invoice.getId();
	       attributes.addAttribute("message", "Invoice with id: '"+id+"' is updated successfully !");
	       return "redirect:getAllInvoices";
	    }

	    @GetMapping("/delete")
	    public String deleteInvoice(
	            @RequestParam Long id,
	            RedirectAttributes attributes
	            ) {
	        try {
	        invoiceService.deleteInvoiceById(id);
	        attributes.addAttribute("message", "Invoice with Id : '"+id+"' is removed successfully!");
	        } catch (InvoiceNotFoundException e) {
	            e.printStackTrace();
	            attributes.addAttribute("message", e.getMessage());
	        }
	        return "redirect:getAllInvoices";
	    }
	    @GetMapping("/excelExport")
	    public ModelAndView exportToExcel() {
	        ModelAndView mav = new ModelAndView();
	        mav.setView(new InvoiceDataExcelExport());
	      //read data from DB
	        List<Invoice> list= invoiceService.getAllInvoices();
	      //send to excelImpl class
	        mav.addObject("list", list);
	        return mav; 
	    }
	
}

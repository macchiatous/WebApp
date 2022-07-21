package webApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import webApp.model.Customer;
import webApp.service.WebAppService;

@Controller
public class WebAppController {
	
	@Autowired
	WebAppService service;
	
	@RequestMapping(value = { "/customer/new", "new-customer.html" }, method = RequestMethod.GET)
	public ModelAndView displayForm() {
		ModelAndView mv = new ModelAndView("new-customer");
		mv.addObject("customer", new Customer());

		return mv;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ModelAndView processForm(@Valid @ModelAttribute Customer	customer, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("customer", customer);

		if (result.hasErrors())
			mv.setViewName("new-customer");
		else {
			mv.setViewName("customer-list");
			service.create(customer);
			mv.addObject("customers", service.findAll());
		}

		return mv;
	}
	
	@RequestMapping(value = {"/list-customers", "customer-list.html"}, method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("customer-list");
		mv.addObject("customers", service.findAll());
		
		return mv;
	}
	
	
	@RequestMapping(value = "/customer/view/{id}", method = RequestMethod.POST)
	public ModelAndView view(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("view-customer");
		Customer customer = service.findById(id);
		mv.addObject("customer", customer);
					
		return mv;
	}
	
	@RequestMapping(value = "/customer/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("customer-list");
		service.delete(id);
		mv.addObject("customers", service.findAll());
			
		return mv;
	}
		
	
	
}

package webApp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webApp.dao.WebAppRepository;
import webApp.model.Customer;

@Service
@Transactional
public class WebAppService {
	
	@Autowired
	WebAppRepository repository;
	
	
	public List<Customer> findAll() {
		return repository.findAll();
	}

	public Customer findById(long id) {
		return repository.findById(id);
	}

	public void create(Customer person) {
		repository.create(person);
	}

	public void delete(long id) {
		repository.delete(id);

	}
	
	


}

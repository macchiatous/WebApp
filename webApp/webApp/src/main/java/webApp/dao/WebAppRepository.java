package webApp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import webApp.model.Customer;



@Repository
public class WebAppRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Customer> findAllCustomers() {
		return entityManager.createQuery("from Customer", Customer.class).getResultList();
	}
	

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Customer> rowMapper = new RowMapper<Customer>() {

		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setId(rs.getLong("id"));
			customer.setFirstName(rs.getString("fname"));
			customer.setLastName(rs.getString("lname"));
			
			return customer;
		}
		
	};
	
	public List<Customer> findAll(){
		String sql = "select * from test";
		return jdbcTemplate.query(sql, rowMapper);		
	}
	
	public Customer findById(long id){
		String sql = "select * from test where id = ?";
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, rowMapper, id));
	}

	public void create(Customer customer) {
		String sql = "insert into test(id, fname, lname) values(?, ?, ?)";
		jdbcTemplate.update(sql,customer.getId() ,customer.getFirstName(), customer.getLastName());
	}

	public void delete(long id) {
		String sql = "delete from test where id = ?";
		jdbcTemplate.update(sql, id);
	}
	
	
}

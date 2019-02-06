package tiketbioskop.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tiketbioskop.model.Customer;

@Service
public class CustomerService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	
	public List<Customer> findByEmail() {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();			
		} else {
			username = principal.toString();
		}
		
		
		String sql = "select * from Customer where email='"+username+"'";
		RowMapper<Customer> rm = new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int i) throws SQLException {
				Customer cus = new Customer();
				cus.setId_customer(rs.getInt("id_customer"));
				cus.setName(rs.getString("name"));
				cus.setPhone(rs.getString("phone"));
				cus.setEmail(rs.getString("email"));
				cus.setPassword(rs.getString("password"));
				return cus;
			}
		};
		return jdbcTemplate.query(sql, rm);
	}
}

package tiketbioskop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import tiketbioskop.model.Order;


@Service
public class OrderService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public Order insertOrder(int id_ticket, int id_customer, int buy) {
		KeyHolder holder = new GeneratedKeyHolder();
		Order ord = new Order();
	
		ord.setId_customer(id_customer);
		ord.setId_ticket(id_ticket);
		ord.setOrder_quantity(buy);
		jdbcTemplate.update(new PreparedStatementCreator() {
					
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO `tiket_bioskop`.`order` (`id_ticket`, `id_customer`, `buy`) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, ord.getId_ticket());
				ps.setInt(2, ord.getId_customer());
				ps.setInt(3, ord.getOrder_quantity());
				return ps;
			}
		}, holder);
		int newOrderId = holder.getKey().intValue();
		ord.setId_order(newOrderId);
		return ord;
	}
}

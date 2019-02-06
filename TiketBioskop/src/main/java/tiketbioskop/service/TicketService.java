package tiketbioskop.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import tiketbioskop.model.Ticket;

@Service
public class TicketService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Ticket> getName() {
		String sql = "Select distinct(film) from ticket where date >= current_date() ";
		RowMapper<Ticket> rmT = new RowMapper<Ticket>() {

			@Override
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ticket tk = new Ticket();
				tk.setFilm(rs.getString("film"));
				return tk;
			}
		};
		return jdbcTemplate.query(sql, rmT);
	}

	public List<Ticket> findByName(String nama) {

		String sql = "Select * from ticket where film='" + nama + "' and date >= current_date() ";

		RowMapper<Ticket> rmT = new RowMapper<Ticket>() {

			@Override
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ticket tk = new Ticket();
				tk.setId_ticket(rs.getInt("id_ticket"));
				tk.setFilm(rs.getString("film"));
				tk.setDate(rs.getDate("date"));
				tk.setStart_time(rs.getTime("start_time"));
				tk.setEnd_time(rs.getTime("end_time"));
				tk.setQuantity(rs.getInt("quantity"));

				return tk;
			}
		};

		return jdbcTemplate.query(sql, rmT);
	}

	public List<Ticket> findById(int id) {
		String sql = "Select * from ticket where id_ticket=" + id;

		RowMapper<Ticket> rmT = new RowMapper<Ticket>() {

			@Override
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ticket tk = new Ticket();
				tk.setId_ticket(rs.getInt("id_ticket"));
				tk.setFilm(rs.getString("film"));
				tk.setDate(rs.getDate("date"));
				tk.setStart_time(rs.getTime("start_time"));
				tk.setEnd_time(rs.getTime("end_time"));
				tk.setQuantity(rs.getInt("quantity"));

				return tk;
			}
		};

		return jdbcTemplate.query(sql, rmT);
	}

	public int findQuantity(int id) {

		String sql = "SELECT quantity FROM ticket WHERE id_ticket = ?";

		int count = jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);

		return count;
	}

	public boolean timeStatus(int id) throws ParseException {

		String sql = "SELECT start_time FROM ticket WHERE id_ticket = ?";

		String time = jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);

		String startTime = time;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

		String endT = LocalTime.now().format(dtf);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date d1 = sdf.parse(startTime);
		Date d2 = sdf.parse(endT);

		if (d2.getTime() - d1.getTime() > 0) {
			return true;
		}

		return false;
	}

	public boolean dateStatus(int id) throws ParseException {
		String sql = "SELECT date FROM ticket WHERE id_ticket = ?";

		String date_db = jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);

		Date temp = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		String date_now = sdf.format(temp);
		Date date1 = sdf.parse(date_db);
		Date date2 = sdf.parse(date_now);

		if (date1.compareTo(date2) == 0) {
			return true;
		} else {
			return false;
		}

	}

	public void updateQuantity(int idFilm, int order_quantity, int quantity) {
		int new_quantity = quantity - order_quantity;
		String sql = "UPDATE `tiket_bioskop`.`ticket` SET `quantity`= ? WHERE `id_ticket`= ? ;";
		jdbcTemplate.update(sql, new_quantity, idFilm);

	}

}

package contacts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public ContactRepository(JdbcTemplate jdbc){
		this.jdbc = jdbc;
	}
	

	public List<Contact> findAll(){
		return jdbc.query("Select id, firstName from contacts order by firstName",
				new RowMapper<Contact>(){
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact contact = new Contact();
				contact.setFirstName(rs.getString(1));
				return contact;
			}
		});
	}


	public void save(Contact contact) {
		jdbc.update(" insert into contacts (firstName) values (?)", contact.getFirstName());
			
	}
}

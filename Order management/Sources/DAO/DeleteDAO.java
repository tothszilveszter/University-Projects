package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Connection.MySQLConnection;
/**
 * This class can delete the given objects (clients or products) from the tables from the mysql server. From the data given as parameter in the header, it generates the necessary query to complete the task, which will be transmitted to the workbench
 * @author Toth Szilveszter
 *
 */
public class DeleteDAO {
	public void delete(String data, String unde) throws SQLException {
		String[] arr=data.split(", ",-2);
		
		String query="delete from ";
		if (unde.equals("client:")) {
			query += "clientul where ";
			query = query + "nume='" + arr[0] + "' and oras='" + arr[1] +"'";
		}
			
		else {
			query += "produs where ";
			query = query + "nume='" + arr[0] + "'";
		}
		//System.out.println(query);
		Connection dbConnection=MySQLConnection.getConnection();
		PreparedStatement statement=dbConnection.prepareStatement(query.toString());
		statement.executeUpdate(query);
		
		MySQLConnection.close(statement);
		MySQLConnection.close(dbConnection);
	}
}

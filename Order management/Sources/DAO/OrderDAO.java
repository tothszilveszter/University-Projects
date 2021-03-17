package DAO;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import Connection.MySQLConnection;
/**
 * Each row from the in file is processed different based on the first word. The order basically means that we need to insert a new row in the order table, and to decrement the given product number.
 * @author Toth Szilveszter
 *
 */
public class OrderDAO {
	/**
	 * This function update the rows in tables. Depending from where it is called, it can also add(when a product is inserted which is already in the table) to product number, or subtract (when order).
	 * @param tabel
	 * @param data
	 * @param ch
	 */
	public void update(String tabel, String data, int ch) {
		String[] arr=data.split(", ",-2);
		String semn=null;
		String nume=null;
		String cantitate=null;
		if (ch==0) {
			nume=arr[1];
			cantitate=arr[2];
			semn="-";
		}
		else {
			nume=arr[0];
			cantitate=arr[1];
			semn="+";	
		}
		String query="update " + tabel + " set cantitate=cantitate"+ semn + cantitate + " where nume='" + nume +"'"; 
		Connection dbConnection=MySQLConnection.getConnection();
		PreparedStatement statement=null;
		try {
			statement = dbConnection.prepareStatement(query.toString());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		MySQLConnection.close(statement);
		MySQLConnection.close(dbConnection);
	}
	/**
	 * Verifies is there are enough products to complete the order. If there aren't, the order isn't created.
	 * @param data
	 * @return
	 */
	public boolean isEnoughStock(String data) {
		String[] arr=data.split(", ",-2);
		String nume=arr[1];
		String cantitate=arr[2];
		String query="select cantitate from produs where nume='" + nume +"'"; 
		Connection dbConnection=MySQLConnection.getConnection();
		PreparedStatement statement=null;
		ResultSet rs=null;
		int can_tabel=0;
		try {
			statement = dbConnection.prepareStatement(query.toString());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			rs=statement.executeQuery(query);
			rs.next();
			can_tabel=rs.getInt("cantitate");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		MySQLConnection.close(statement);
		MySQLConnection.close(dbConnection);
		int can_cerut=Integer.valueOf(cantitate);
		if (can_tabel<can_cerut)
			return false;
		else
			return true;
	}
	public void order(String data) {
		if (isEnoughStock(data)==true) {
		InsertDAO i=new InsertDAO();
		try {
			i.insert(data, "cumpara");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ReportDAO b=new ReportDAO();
		try {
			b.bill(data);
		} catch (FileNotFoundException | DocumentException | SQLException e) {
			e.printStackTrace();
		}
		update("produs",data,0);
		}
		else
			System.out.println("Nu sunt destule produse la: " + data);
	}

}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.MySQLConnection;
/**
 * The insert class is a bit tricky. It's able to insert products, clients, orders. It works like the delete class, certain query is created to get the job done.
 * @author Toth Szilveszter
 *
 */
public class InsertDAO {
	public static int idClient=1;
	public static int idProdus=1;
	public static int idOrder=1;
	/**
	 * This function is needed because in the order table are saved only the indexes of product, clients etc. So it's necessary to find the clients ID before we can realize the order from the in file.
	 * 
	 */
	public int getID(String data, String unde) {
		int id=0;
		String query="select id from " + unde + " where nume='"+data+"'";
		Connection dbConnection=MySQLConnection.getConnection();
		PreparedStatement statement=null;
		ResultSet rs=null;
		try {
			statement = dbConnection.prepareStatement(query.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=statement.executeQuery(query);
			rs.next();
			id=rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		MySQLConnection.close(statement);
		MySQLConnection.close(dbConnection);
		return id;
	}
	/**
	 * When multiple inserts are required, there are cases in which the client or product is already in the table. Let's assume that we find the object in the table: if we are looking for clients, we don't insert anything at all, but on the other hand, at product, we increment the number of available products.
	 * 
	 */
	public boolean isInTable(String nume, String unde) throws SQLException {
		String query=null;
		if (unde.equals("product")==true) {
			query="select nume from produs";
		}
		else if (unde.equals("client:")==true) {
			query="select nume from clientul";
		}
		else
			return false;
		Connection dbConnection=MySQLConnection.getConnection();
		PreparedStatement statement = null;
		statement = dbConnection.prepareStatement(query.toString());
		ResultSet rs=statement.executeQuery(query);
		
		while (rs.next())
		{
			String aux=rs.getString("nume");
			if (aux.equals(nume)==true)
				return true;
		}
		return false;
	}
	
	public void insert(String data, String unde) throws SQLException {
		String[] arr=data.split(", ",-2);
		if (isInTable(arr[0],unde)==false) {
		String query="insert into ";
		if (unde.equals("client:")) {
			query += "clientul values (";
			query += Integer.toString(idClient);
			query = query + ", '" + arr[0] + "', '" + arr[1] +"')";
			idClient++;
		}
			
		else if (unde.equals("product")){
			query += "produs values (";
			query += Integer.toString(idProdus);
			query = query + ", '" + arr[0] + "', " + arr[1] + ", " + arr[2] +")";
			idProdus++;
		}
		else
		{
			query += "cumpara values (";
			query += Integer.toString(idOrder);
			int idClient=getID(arr[0],"clientul");
			int idProdus=getID(arr[1],"produs");
			query = query + ", " + idClient + ", " + idProdus + ", " + arr[2] +")";
			idOrder++;
		}
		Connection dbConnection=MySQLConnection.getConnection();
		PreparedStatement statement=dbConnection.prepareStatement(query.toString());
		statement.executeUpdate(query);
		
		MySQLConnection.close(statement);
		MySQLConnection.close(dbConnection);
		}
		else {
			if (unde.equals("product")) {
				OrderDAO u=new OrderDAO();
				u.update("produs", data,1);
			}
		}
	}
	
}
package DAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Connection.MySQLConnection;
/**
 * This class simply creates pdf files in order to see what elements are in tables. And it also creates a bill as result of an order.
 * @author Toth Szilveszter
 *
 */
public class ReportDAO {
	
	public String getNume(int id, String tabel) {
		String toReturn=null;
		String query="select nume from " + tabel + " where id="+id;
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
			toReturn=rs.getString("nume");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		MySQLConnection.close(statement);
		MySQLConnection.close(dbConnection);
		return toReturn;
	}
	public static int nrClient=1;
	public static int nrProdus=1;
	public static int nrOrder=1;
	public void report(String ce) throws FileNotFoundException, DocumentException, SQLException {
		Document pdf=new Document();
		
		String query="select * from ";
		String nume=null;
		if (ce.equals("client"))
			{
				nume="Report"+ nrClient + "_" +  ce +".pdf";
				query += "clientul";
			}
		else if (ce.equals("product")) {
			nume="Report"+ nrProdus + "_" +  ce +".pdf";
			query += "produs";
		}
		else {
			nume="Report"+ nrOrder + "_" +  ce +".pdf";
			query += "cumpara";
		}
			
		PdfWriter.getInstance(pdf, new FileOutputStream(nume));
		pdf.open();
		Chunk title = new Chunk("Raportul pentru tabelul cerut:\n");
		pdf.add(title);
		
		Connection dbConnection=MySQLConnection.getConnection();
		PreparedStatement statement = null;
		statement = dbConnection.prepareStatement(query.toString());
		ResultSet rs=statement.executeQuery(query);
		
		if (ce.equals("client")) {
			while (rs.next())
			{
				int id=rs.getInt("id");
				String name=rs.getString("nume");
				String oras=rs.getString("oras");
				Chunk data=new Chunk("ID: " + id + " | Nume: " + name + " | Oras: " + oras);
				pdf.add(new Paragraph(data));
			}
			nrClient++;
		}
		else if (ce.equals("product")) {
			while (rs.next())
			{
				int id=rs.getInt("id");
				String name=rs.getString("nume");
				String cantitate=rs.getString("cantitate");
				float pret=rs.getFloat("pret");
				Chunk data=new Chunk("ID: " + id + " | Nume: " + name + " | Cantitate: " + cantitate + " | Pret: " + pret);
				pdf.add(new Paragraph(data));
			}
			nrProdus++;
		}
		else
		{
			while (rs.next())
			{
				int id=rs.getInt("id");
				int idClient=rs.getInt("idClient");
				int idProdus=rs.getInt("idProdus");
				String numeClient=getNume(idClient,"clientul");
				String numeProdus=getNume(idProdus,"produs");
				int cantitate=rs.getInt("cantitate");
				Chunk data=new Chunk("IdOrder: " + id + " | (ID)Nume: (" + idClient +")"+numeClient+ " | (ID)Produs: (" + idProdus +")"+numeProdus+ " | Cantitate: " + cantitate);
				pdf.add(new Paragraph(data));
			}
			nrOrder++;
		}
		
		MySQLConnection.close(statement);
		MySQLConnection.close(dbConnection);
		pdf.close();
	}
	
	public static int billOrder=1;
	
	public void bill(String data) throws FileNotFoundException, DocumentException, SQLException {
		Document pdf=new Document();
		String nume="Bill"+ billOrder +".pdf";
		PdfWriter.getInstance(pdf, new FileOutputStream(nume));
		String[] arr=data.split(", ",-2);
		String query="select pret from produs where nume='" + arr[1] + "'";
		
		pdf.open();
		Chunk title = new Chunk("Order " + billOrder + ")");
		pdf.add(title);
		
		Connection dbConnection=MySQLConnection.getConnection();
		PreparedStatement statement = null;
		statement = dbConnection.prepareStatement(query.toString());
		ResultSet rs=statement.executeQuery(query);
		rs.next();
		float pret=rs.getFloat("pret");
		int cantitate=Integer.valueOf(arr[2]);
		float pretTotal=pret*cantitate;
		Chunk toPrint=new Chunk("Nume: " + arr[0] + " | Produs: " + arr[1] + " | Cantitate: " + arr[2] + " | Pret Unitar: " + pret+ " | Pret Total: " + pretTotal);
		pdf.add(new Paragraph(toPrint));
		
		billOrder++;
		
		MySQLConnection.close(statement);
		MySQLConnection.close(dbConnection);
		pdf.close();
	}
}

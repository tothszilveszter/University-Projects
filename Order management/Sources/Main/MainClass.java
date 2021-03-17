package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

import DAO.DeleteDAO;
import DAO.InsertDAO;
import DAO.OrderDAO;
import DAO.ReportDAO;
import Validator.Validator;
/**
 * In the main class are read the instructions. Firstly they are checked if they respect the format. If not, an error message is printed. Depending on the first word, they are categorized, and called the right method to process them.
 * @author Toth Szilveszter
 *
 */
public class MainClass {
	
	public static void main(String[] args) throws SQLException, FileNotFoundException {
	
		File inFile=new File(args[0]);
		Scanner myRead=new Scanner(inFile);
		Validator v=new Validator();
		while(myRead.hasNextLine()) {
			String data=myRead.nextLine();
			if (v.isValid(data)==true)
			{
				String op=data.substring(0, 6);
				//System.out.println(op);
				
				switch (op) {
				case "Insert":
					String unde=data.substring(7, 14);
					if (unde.equals("client:")) {
						data = data.substring(15,data.length());
						
					}
					else if (unde.equals("product")) {
						data = data.substring(16,data.length());
						
					}
					InsertDAO i=new InsertDAO();
					i.insert(data, unde);
						
				break;
				
				case "Delete":
					String unde1=data.substring(7, 14);
					if (unde1.equals("client:"))
						data = data.substring(15,data.length());
					else
						data = data.substring(16,data.length());
					DeleteDAO d=new DeleteDAO();
					d.delete(data,unde1);
					
				break;
				
				case "Order:":
					data = data.substring(7,data.length());
					OrderDAO o=new OrderDAO();
					o.order(data);
				break;
				
				case "Report":
					ReportDAO r=new ReportDAO();
					String ce=data.substring(7, data.length());
					if (ce.equals("client")==false && ce.equals("product")==false && ce.equals("order")==false) {
						System.out.println("Nu se poate face report!");
					} 
					else {
						try {
							r.report(ce);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (DocumentException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					}
				break;
				default:
					System.out.println("Operatia ceruta este invalida!");
				break;
				}
			}
			else
				System.out.println("Input data not valid");
		}
		myRead.close();
	}
}


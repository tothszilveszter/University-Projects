package Assignment4;
import java.io.*;
import java.util.Scanner;

/**
 * In this class are created the objects for the overall project and initialized the GUI. Also, here is are some items read from an 
 * external source file in order to populate the menu. It contains the serialization and deserializaton, but honestly i didn't under-
 * stood why and when should we use the deserialization, so I put those lines as comments.
 * 
 * @author Toth Szilveszter
 *
 */
public class MainClass {
	@SuppressWarnings("deprecation")
	
	public static void main(String[] args) {
		Admin admin=new Admin();
		Waiter waiter=new Waiter(admin);
		Chef chef=new Chef();
		IRestaurantProcessing view=new IRestaurantProcessing();
		Restaurant restaurant=new Restaurant(admin, waiter, chef, view);
		
		String filename="restaurant.ser";
		//serialization
		try {
			FileOutputStream file=new FileOutputStream(filename);
			ObjectOutputStream out=new ObjectOutputStream(file);
			out.writeObject(restaurant);
			out.close();
			file.close();
		}
		catch (IOException ex)
		{
			System.out.println("IOException is caught!");
		}
		
		/*Restaurant restaurant1=null;
		//deserialization
		try
		{
			FileInputStream file=new FileInputStream(filename);
			ObjectInputStream in=new ObjectInputStream(file);
			
				restaurant1=(Restaurant)in.readObject();
				in.close();
				file.close();
		}
		catch(IOException e)
		{
			System.out.println("IOException is caught!");
		}
			
		catch (ClassNotFoundException e) 
		{
			System.out.println("ClassNotFoundException is caught!");
		}
		*/
		
		waiter.addObserver(chef);
		view.show(admin,waiter);
		
		File inFile=new File("menu_in.txt");  
		System.out.println("We will first add the items from the file to the menu!");
		Scanner s = null;
		try {
			s = new Scanner(inFile);
			while(s.hasNextLine()) {
				String data = s.nextLine();
				String[] arr=data.split(" ",-2);
				admin.addProduct(arr[0],arr[1]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could open/read from file!");
		}
		
		s.close();
		
	}
	}
	


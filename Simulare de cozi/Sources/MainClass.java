import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
	public static void Print(int time, List<Client> clients, FileWriter toFile) {
		try {
			toFile.write("Time=" + time + "\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//if (clients.isEmpty()==false)
		for (Client x : clients) {
			if (time != x.getArr_time())
				try {
					toFile.write("(" + x.getId() + "," + x.getArr_time() + "," + x.getServ_time() + ")\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	public static int IsFull(List<QueueThread> threads) {
		for (QueueThread x : threads) {
			if (x.isOpen()==false)
				return 0;
		}
		return 1;
	}
	public static void PrintQueues(List<QueueThread> threads, FileWriter toFile) {

		try {
			toFile.write("Queues:\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (QueueThread t : threads) {
			if (t.getClient() == null || t.getClient().getServ_time() == 0)
				try {
					toFile.write("closed\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			else
				try {
					toFile.write("(" + t.getClient().getId() + "," + t.getClient().getArr_time() + ","
							+ t.getClient().getServ_time() + ")\n");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		try {
			toFile.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		List<Client> clients = new ArrayList<Client>();
		int clientNumber = 0;
		int queueNumber = 0;
		int timeMax = 0;
		String arrTime = null;
		String servTime = null;
		
		try {
			File myObj = new File(args[0]);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {

				String data = myReader.nextLine();
				clientNumber = Integer.parseInt(data);
				data = myReader.nextLine();
				queueNumber = Integer.parseInt(data);
				data = myReader.nextLine();
				timeMax = Integer.parseInt(data);
				arrTime = myReader.nextLine();
				servTime = myReader.nextLine();

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		Random rn = new Random();

		String[] arr = arrTime.split(",", -2);
		int lowa = Integer.parseInt(arr[0]), higha = Integer.parseInt(arr[1]);

		String[] serv = servTime.split(",", -2);
		int lows = Integer.parseInt(serv[0]), highs = Integer.parseInt(serv[1]);

		for (int i = 1; i <= clientNumber; i++) {
			int a = rn.nextInt(higha - lowa) + lowa;
			int s = rn.nextInt(highs - lows) + lows;
			Client c = new Client(i, a, s);
			clients.add(c);
		}
		Collections.sort(clients, new Criteriu());
		List<QueueThread> threads = new ArrayList<QueueThread>(queueNumber);
		for (int i = 0; i < queueNumber; i++) {
			threads.add(new QueueThread(false, null));
		}
		int time = 0;
		float averageTime=0;
		Client x1 = null;
		Client x = null;
		int ok=1;
		int endingTime=clients.get(clientNumber-1).getArr_time()+highs;
		
		FileWriter toFile=new FileWriter(args[1]);
		
		while (time != timeMax && time!=endingTime) {
			if (ok==1)
				{
					ok=0;
					Print(time, clients,toFile);
				}
			if (x1 == null) {
				
				if (clients.isEmpty() == false)
					x = clients.get(0);
			} else {
				x = x1;
			}
			if (time >= x.getArr_time()) {
				if (IsFull(threads)==1) {
					time++;
					//Print(time, clients);
					PrintQueues(threads,toFile);
					for (QueueThread t : threads)
						if (t.isOpen() == true) {
							t.run();
							if (!(t.getClient() == null))
							averageTime++;
						}
				}
				else {
				for (QueueThread t : threads) {
					if (t.isOpen() == false) {
						t.setClient(x);
						if (clients.isEmpty() == false)
							clients.remove(0);
						t.setOpen(true);
						break;
					}
				}
				}
			}
			
			if (clients.isEmpty() == false)
				x1 = clients.get(0);

			if (clients.isEmpty() == true) {
				time++;
			} else if (time < x1.getArr_time()) {
				time++;
			}
			else
					continue;
			
			PrintQueues(threads,toFile);
			ok=1;
			for (QueueThread t : threads)
				if (t.isOpen() == true) {
					t.run();
					if (!(t.getClient() == null))
					averageTime++;
				}
					
		}
		toFile.write("Si tot asa va fi, pana cand time ajunge la: " + timeMax + "\n");
		toFile.write("Average waiting time: " + averageTime/clientNumber);
		toFile.close();
	}
	
}

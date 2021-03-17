import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

public class MainClass {
	
	
	public static void main(String[] args) throws IOException{
		ArrayList<MonitoredData> activityList=new ArrayList<>();
		
		//=======================Task1=====================================
		List<String> inList=Files.lines(Paths.get(args[0])) //sau in .get() punem path-ul fisierului de intrare
				.collect(Collectors.toList());
			
		for(String s : inList)
		{
			String arr[]=s.split("		",-2);
			int len=arr[2].length();
			for (int i=0; i<len; i++)
			{
				if (arr[2].charAt(i)==' ' || arr[2].charAt(i)=='	')
				{
					arr[2]=arr[2].substring(0,i) + arr[2].substring(i+1);
					
				}
			}
	        activityList.add(new MonitoredData(arr[0],arr[1],arr[2]));
		}
		
		FileWriter outWrite1=new FileWriter("Task_1.txt");
		
		for(MonitoredData d : activityList)
			outWrite1.write(d.getStart_time() + " - " + d.getEnd_time() + " - " + d.getActivity_label() + "\n");
		
		outWrite1.close();
		
		//=======================Task2=====================================
		FileWriter outWrite2=new FileWriter("Task_2.txt");
		Map<Object, List<MonitoredData>> groupDate = activityList.stream()
			.collect(Collectors.groupingBy(x -> x.getDate(0,10), Collectors.toList()));
		
		int cont=groupDate.size();
		outWrite2.write("There are " + cont + " different days!");
		
		outWrite2.close();
		
		//=======================Task3=====================================
		FileWriter outWrite3=new FileWriter("Task_3.txt");
		Map<Object, List<MonitoredData>> groupActivities = activityList.stream()
			.collect(Collectors.groupingBy(x -> x.getActivity_label(), Collectors.toList()));
		
		groupActivities.forEach((activity, number)-> {
			try {
				outWrite3.write(activity + " -> appears " + number.size() + " times!\n");
			} catch (IOException e) {
				System.out.println("Can't write the output at Task3!");
			}
		});

		outWrite3.close();
		
		//=======================Task4=====================================
		FileWriter outWrite4=new FileWriter("Task_4.txt");
		Map<Object, List<MonitoredData>> groupActivitiesDate = activityList.stream()
				.sorted(Comparator.comparing(x -> x.getDate(0,10)))
				.collect(Collectors.groupingBy(x -> x.getDate(0,10)));
		
		groupActivitiesDate.forEach((date, activities)-> {
				
				try {
					outWrite4.write(date + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			Map<Object, List<MonitoredData>> groupActivitiesNumber = activities.stream()
					.collect(Collectors.groupingBy(x -> x.getActivity_label(), Collectors.toList()));
				groupActivitiesNumber.forEach((activity, number)-> {
				
						try {
							outWrite4.write(activity + " -> appears " + number.size() + " times!\n");
						} catch (IOException e) {
							System.out.println("Can't write the output at Task4!");
						}
					
				});
				try {
					outWrite4.write("\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
		});

		outWrite4.close();
		
		//=======================Task5=====================================
		FileWriter outWrite5=new FileWriter("Task_5.txt");
		Map<Object, List<MonitoredData>> groupActivitiesTimes = activityList.stream()
				.collect(Collectors.groupingBy(x -> x.getActivity_label(), Collectors.toList()));
		groupActivitiesTimes.forEach((activity, number)-> {
			int time=0;
			for (MonitoredData m : number)
			{
				time+=calculateDuration(m.getStart_time(),m.getEnd_time());
			}
			try {
				outWrite5.write(activity + " took place for " + Math.abs(time/3600) + " hours " + Math.abs(time/3600/60) + " minutes and " + Math.abs(time%60) + " seconds\n");
			} catch (IOException e) {
				System.out.println("Can't write the output at Task5!");
			}
		});
		
		outWrite5.close();
	}
	
	public static int calculateDuration(String start, String end) {
		int secs=0;
		int startH, startM, startS, endH, endM, endS;
		start=((String) start).substring(11);
		end=((String) end).substring(11);
		String arr[]=((String) start).split(":",-2);
		startH=Integer.parseInt(arr[0]);
		startM=Integer.parseInt(arr[1]);
		startS=Integer.parseInt(arr[2]);
		String arr1[]=((String) end).split(":",-2);
		endH=Integer.parseInt(arr1[0]);
		endM=Integer.parseInt(arr1[1]);
		endS=Integer.parseInt(arr1[2]);
		
		endH-=startH;
		endM-=startM;
		endS-=startS;
		
		if (endS<0)
		{
			endS=60+endS;
			endM--;
		}
		if (endM<0)
		{
			endM=60+endM;
			endH--;
		}
		secs=(endH)*3600+(endM)*60+(endS);
		return secs;
	}

	
	
}


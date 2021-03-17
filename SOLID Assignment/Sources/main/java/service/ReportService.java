package service;

import java.io.*;
import java.util.Scanner;

public class ReportService {
    private static String message;
    private static int reportID=0;

    public void addEvent(Long employeeId, String event)
    {

        message = Long.toString(employeeId) + " -> " + event + "\n";
        try {
            if (!message.equals(null))
                updateFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateFile() throws IOException {
        File myFile= new File("Log.txt");
        RandomAccessFile raf=new RandomAccessFile(myFile,"rw");
        FileWriter myFileWriter=new FileWriter(myFile,true);
        try {
            if (!myFile.exists())
                myFile.createNewFile();
            else{
                raf.seek(myFile.length());
                myFileWriter.write(message);
                myFileWriter.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public boolean generateReport(Long id) throws IOException {

        File inFile=new File("Log.txt");
        String name="ReportForUserWithId"+String.valueOf(id)+".txt";
        File outFile=new File(name);
        Scanner myRead=new Scanner(inFile);
        FileWriter myWrite=new FileWriter(outFile);
        while(myRead.hasNext()){
            String data=myRead.nextLine();
            String splitData[]=data.split(" -> ", -2);
            if (Long.valueOf(splitData[0]).equals(id))
            {
                String message=splitData[1] + " at " + splitData[2] + "\n";
                myWrite.write(message);
            }
        }
        myWrite.close();
        return true;
    }
}

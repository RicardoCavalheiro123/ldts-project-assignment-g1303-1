package com.aor.Leaderboard;

import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    private String fileName;
    public Leaderboard(String fileName){
        this.fileName = fileName;
        try
        {
            File tempFile = new File(fileName);
            if(tempFile.exists())
                    return;
            else{
                tempFile.createNewFile();
                FileWriter myWriter = new FileWriter(fileName);
                myWriter.write("PlayerName,Time");
                myWriter.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    public ArrayList<Lead> getLeaderboardsList(){
        ArrayList<Lead> l = new ArrayList<Lead>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(",");
                Lead l2 = new Lead(parts[0].toString(),Integer.parseInt(parts[1].toString()));
                l.add(l2);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        l.sort(new LeadComparator());
        return l;
    }

    public void updateLeaderboardFile(Lead lead) throws IOException {
        ArrayList<Lead> leaderboard = getLeaderboardsList();
        leaderboard.add(lead);
        leaderboard.sort(new LeadComparator());
        PrintWriter erasor = new PrintWriter(fileName);
        erasor.close();
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write("PlayerName,Time");
        for(Lead l1 : leaderboard){
            Integer time = l1.getTime();
            String name = l1.getName();
            myWriter.write("\n" +name + "," + time.toString());
        }
        myWriter.close();
    }

}

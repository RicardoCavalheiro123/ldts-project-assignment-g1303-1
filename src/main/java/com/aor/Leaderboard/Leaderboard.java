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
                System.out.println("file nao exists");
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
        return l;
    }

}

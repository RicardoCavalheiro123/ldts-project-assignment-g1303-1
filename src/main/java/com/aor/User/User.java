package com.aor.User;

import com.aor.Models.PowerUpModel.PowerUpModel;

import java.util.ArrayList;

public class User {
    String name;
    long time = 0;
    ArrayList<PowerUpModel> powerUpModels = new ArrayList<>();
    int balence = 0;
    public User(String name){
        this.name = name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void addTime(long timeToAdd){
        time = time + timeToAdd;
    }
    public void setTime(long time){
        if(this.time == 0){
            this.time = time;
        }
        else if(time>this.time){
            return;
        }
        this.time = time;
    }
    public long getTime(){
        return time;
    }
    public int getBalence(){
        return balence;
    }
    public void addToBalence(int balenceToAdd){
        balence = balence +balenceToAdd;
    }
    public void takeFromBalence(int balenceToTake){
        if(balence -balenceToTake>0) {
            balence = balence - balenceToTake;
        }else {
            balence = 0;
        }
    }
    public String getName(){
        return name;
    }
    public ArrayList<PowerUpModel> getPowerUpList(){
        return powerUpModels;
    }
    public boolean deletePowerUp(PowerUpModel powerUpModel){
        for(PowerUpModel x : powerUpModels){
            if(x == powerUpModel){
                powerUpModels.remove(x);
                return true;
            }
        }
        return false;
    }
    public void addPowerUp(PowerUpModel powerUpModel){
        powerUpModels.add(powerUpModel);
    }
}

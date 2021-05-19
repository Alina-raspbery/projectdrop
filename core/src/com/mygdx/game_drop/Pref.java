package com.mygdx.game_drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Pref {
    private Preferences pref;
    private int hightScore;
    int points;
    public Pref(int points){
        pref = Gdx.app.getPreferences("My Preferences");
        hightScore = pref.getInteger("score",0);
        this.points = points;
    }


    public int getLevel(){
        if (hightScore<points) {
            //hightScore++;
            hightScore = points;
            pref.putInteger("score", hightScore);
            pref.flush();
        }
        return hightScore;
    }
}

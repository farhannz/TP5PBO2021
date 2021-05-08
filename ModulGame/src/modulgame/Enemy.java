/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulgame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
/**
 *
 * @author Asus
 */
public class Enemy extends GameObject {
    
    private int updateDuration = 30; // It will update every 30 ticks
    private int tickCounter = 0;
    private String difficulty;
    public Enemy(int x, int y, ID id, String difficulty){
        super(x, y, id);
        this.difficulty = difficulty;
        //speed = 1;
    }

    @Override
    public void tick() {
        if(tickCounter == updateDuration){
            int[] ar = new int[3];
            if(difficulty.compareTo("Easy") == 0){
                ar[0] = -3;
                ar[1] = 3;
                ar[2] = 0;
            }
            else if(difficulty.compareTo("Medium") == 0){
                ar[0] = -5;
                ar[1] = 5;
                ar[2] = 0;
            }
            else if(difficulty.compareTo("Hard") == 0){
                ar[0] = -8;
                ar[1] = 8;
                ar[2] = 0;
            }
            
            Random r = new Random();
            
            this.setVel_x(ar[r.nextInt(3-0) + 0]);
            this.setVel_y(ar[r.nextInt(3-0) + 0]);
            tickCounter = 0;
        }
        x += vel_x;
        y += vel_y;
        
        x = Game.clamp(x, 0, Game.WIDTH - 60);
        y = Game.clamp(y, 0, Game.HEIGHT - 80);
        ++tickCounter;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.decode("#8f8f8f"));
        g.fillRect(x, y, 50, 50);
        this.setSize(50);
    }
}

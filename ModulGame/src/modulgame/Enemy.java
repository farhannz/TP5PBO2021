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
    
    int updateDuration = 30; // It will update every 30 ticks
    int tickCounter = 0;
    public Enemy(int x, int y, ID id){
        super(x, y, id);
        
        //speed = 1;
    }

    @Override
    public void tick() {
        if(tickCounter == updateDuration){
            int[] ar = new int[3];
            ar[0] = -5;
            ar[1] = 5;
            ar[2] = 0;
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

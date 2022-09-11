/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author Ivan
 */
public class Pancake implements Comparable<Pancake> {

    private Color color;
    private int size;
    private boolean selected;

    public Pancake(int size, Color color) {
        Random rand = new Random();

        this.size = size;
        this.color = color;

    }

    @Override
    public int compareTo(Pancake other) {
        if (this.size == other.size) {
            return 0;
        }
        if (this.size < other.size) {
            return -1;
        } else // i.e this.size > other.size
        {
            return 1;
        }

    }

    public int getSize() {
        return this.size;
    }

    public void highlight(boolean selected) {
        this.selected = selected;
    }

    public void draw(Graphics g,  int x, int y, int width,int height) {

        //width = width/size;
        //if selected drwa a black rectangle around to simulate select
        g.setColor(color);
        g.fillRoundRect(x, y, width , height, 10, 10);
        if (this.selected)
        {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(x, y, width, height, 10, 10);
        }
        
        
        
    }

    public String toString() {
        return this.size + "";
    }

}

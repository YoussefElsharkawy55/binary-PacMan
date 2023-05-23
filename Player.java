/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author elmag
 */
public class Player extends GameObject implements Moveable {
    protected final Image[] anim = {
        new ImageIcon("C:\\Users\\elmag\\OneDrive\\Documents\\NetBeansProjects\\Pacman\\src\\pacman\\images\\down.gif").getImage(),
        new ImageIcon("C:\\Users\\elmag\\OneDrive\\Documents\\NetBeansProjects\\Pacman\\src\\pacman\\images\\up.gif").getImage(),
        new ImageIcon("C:\\Users\\elmag\\OneDrive\\Documents\\NetBeansProjects\\Pacman\\src\\pacman\\images\\left.gif").getImage(),
        new ImageIcon("C:\\Users\\elmag\\OneDrive\\Documents\\NetBeansProjects\\Pacman\\src\\pacman\\images\\right.gif").getImage()
    };
    
    protected int req_dx, req_dy;
    private boolean dead;
    
    
    public void setReq(int req_dx, int req_dy)
    {
        this.req_dx = req_dx;
        this.req_dy = req_dy;
    }
    
    public void setState(boolean d){dead = d;}
    
    public boolean checkState(){return dead;}

    @Override
    public void Move(LevelSetup LS) {
        int pos;
        short ch;
        speed = 4;

        if (x % LS.BLOCK_SIZE == 0 && y % LS.BLOCK_SIZE == 0) {
            pos = x / LS.BLOCK_SIZE + LS.N_BLOCKS * (int) (y / LS.BLOCK_SIZE);
            ch = LS.Map[pos];

            if ((ch & 16) != 0) {
                LS.Map[pos] = (short) (ch & 15);
                LS.score++;
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    dx = req_dx;
                    dy = req_dy;
                }
            }

            // Check for standstill
            if ((dx == -1 && dy == 0 && (ch & 1) != 0)
                    || (dx == 1 && dy == 0 && (ch & 4) != 0)
                    || (dx == 0 && dy == -1 && (ch & 2) != 0)
                    || (dx == 0 && dy == 1 && (ch & 8) != 0)) {
                dx = 0;
                dy = 0;
            }
        } 
        x = x + speed * dx;
        y = y + speed * dy;
    }
}

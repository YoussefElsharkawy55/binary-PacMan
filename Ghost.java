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
public class Ghost extends GameObject implements Moveable{
   protected final Image ghostImg = new ImageIcon("C:\\Users\\elmag\\OneDrive\\Documents\\NetBeansProjects\\Pacman\\src\\pacman\\images\\ghost.gif").getImage();
   
   
    @Override
    public void Move(LevelSetup LS) {
        int pos;
        int count; //count of possible movement
        int [][] req_dirc = new int[4][4]; // to get all the possible movement
        
            if (x % LS.BLOCK_SIZE == 0 && y % LS.BLOCK_SIZE == 0) {
                pos = x / LS.BLOCK_SIZE + LS.N_BLOCKS * (int) (y / LS.BLOCK_SIZE);

                count = 0;

                if ((LS.Map[pos] & 1) == 0 && dx != 1) {
                    req_dirc[count][0]= -1;
                    req_dirc[count][1]= 0;
                    count++;
                }

                if ((LS.Map[pos] & 2) == 0 && dy != 1) {
                    req_dirc[count][0]= 0;
                    req_dirc[count][1]= -1;
                    count++;                }

                if ((LS.Map[pos] & 4) == 0 && dx != -1) {
                    req_dirc[count][0]= 1;
                    req_dirc[count][1]= 0;
                    count++;
                }

                if ((LS.Map[pos] & 8) == 0 && dy != -1) {
                     req_dirc[count][0]= 0;
                    req_dirc[count][1]= 1;
                    count++;
                }

                if (count == 0) {

                    if ((LS.Map[pos] & 15) == 15) {
                        dx = 0;
                        dy = 0;
                    } else {
                        dx = -dx;
                        dy = -dy;
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                   dx = req_dirc[count][0];
                   dy = req_dirc[count][1];
                }
            
            }

            x = x + speed * dx;
            y = y + speed * dy;
        
            
            
        }
    
    }


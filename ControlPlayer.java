/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author elmag
 */
public class ControlPlayer extends KeyAdapter {
    
    protected static boolean inGame = false;
    
    private final Player _player;
    private final LevelSetup LS;
    
    public ControlPlayer(Player p, LevelSetup LS){_player = p; this.LS=LS;}
    
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (inGame) {
            if (key == KeyEvent.VK_LEFT) {
                    
                _player.setReq(-1, 0);
                    
            } else if (key == KeyEvent.VK_RIGHT) {
                    
                _player.setReq(1, 0);
                    
            } else if (key == KeyEvent.VK_UP) {
                    
                _player.setReq(0, -1);
                   
            } else if (key == KeyEvent.VK_DOWN) {
                    
                _player.setReq(0, 1);
                    
            } else if (key == KeyEvent.VK_ESCAPE && GameRender.timer.isRunning()) {
                inGame = false;
            } 
        } else {
            if (key == KeyEvent.VK_SPACE) {
                inGame = true;
                LS.SetLevel(3);
                
                
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import javax.swing.Timer;

/**
 *
 * @author elmag
 */
public class LevelSetup extends Level{
    
    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;
    
    protected int lives, score;
    protected int N_Ghost, currentSpeed;
    
    protected Ghost[] ghosts;
    protected Player _player;
    
    public LevelSetup()
    {
        currentSpeed = 3;
        N_Ghost = 8;
        ghosts = new Ghost[N_Ghost];
        for (int i = 0; i < N_Ghost; i++) {
            ghosts[i] = new Ghost();
        }
        _player = new Player();
        
        SetLevel(3);
    }
    
    protected void SetLevel(int lives) {

    	int dx = 1;
        int random;
        
        this.lives = lives;
        _player.setState(false);
        
        for (int i = 0; i < N_Ghost; i++) {
            ghosts[i].setPosition(5 * BLOCK_SIZE, 5 * BLOCK_SIZE); //start position
            ghosts[i].setDirection(dx, 0);
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }
            ghosts[i].setSpeed(validSpeeds[random]);
        }

        _player.setPosition(8 * BLOCK_SIZE, 12 * BLOCK_SIZE); //start position
        _player.setDirection(0, 0); //reset direction move
	_player.setReq(0, 0);	// reset direction controls
        if (lives ==3){
        initLevel(1);
        score =0;
        }
        
    }
}

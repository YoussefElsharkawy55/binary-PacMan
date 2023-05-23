/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author elmag
 */
public class GameRender extends JPanel implements ActionListener {
    
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private final Image heart = new ImageIcon("C:\\Users\\elmag\\OneDrive\\Documents\\NetBeansProjects\\Pacman\\src\\pacman\\images\\right.gif").getImage();
    private final int timing = 40;
    
    
    private final Dimension d;
    protected static Timer timer;
    protected static LevelSetup LS;
    
    private final Player _player;
    private final Ghost[] ghosts;
    
    public GameRender()
    {
        d = new Dimension(400, 400);
        LS = new LevelSetup();
        _player = LS._player;
        ghosts = LS.ghosts;
        ControlPlayer.inGame = false;
        addKeyListener(new ControlPlayer(_player,LS));
        setFocusable(true);
        timer = new Timer(timing, this);
        timer.start();
    }
    private void playGame(Graphics2D g2d) {

        if (_player.checkState()) {

            death();

        } else {

           _player.Move(LS);
            drawGOB(g2d,_player);
            
            
            for(int i =0;i< LS.N_Ghost ; i++)
            {
            ghosts[i].Move(LS);
            drawGOB(g2d,ghosts[i]);
            CheckHit(ghosts[i]);
            }
            checkMaze();
        }
    }
    
    private void CheckHit(Ghost g)
    {
        if (g.x > (_player.x - 22) && g.x < (_player.x + 22)
                    && g.y > (_player.y - 12) && g.y < (_player.y + 12)
                    && ControlPlayer.inGame) {

            _player.setState(true);
        }
    }
    
    private void drawGOB(Graphics2D g2d, GameObject GOB) {
        
    	if (GOB instanceof Ghost g )
        {
            //Ghost g = (Ghost) GOB;
         g2d.drawImage(g.ghostImg,g.x+1, g.y+1, this);
        }
        else if ( GOB instanceof Player p)
        {
            //Player p = (Player) GOB;
            if (p.req_dx == -1) {
        	g2d.drawImage(p.anim[2], p.x + 1, p.y + 1, this);
            } else if (p.req_dx == 1) {
        	g2d.drawImage(p.anim[3], p.x + 1, p.y + 1, this);
            } else if (p.req_dy == -1) {
        	g2d.drawImage(p.anim[1], p.x + 1, p.y + 1, this);
            } else {
        	g2d.drawImage(p.anim[0], p.x + 1, p.y + 1, this);
            }
        }
    }
     private void checkMaze() {

        int i = 0;
        boolean finished = true;

        while (i < LS.N_BLOCKS * LS.N_BLOCKS && finished) {

            if ((LS.Map[i] & 48) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {

            LS.score += 50;
            LS = new LevelSetup();
        }
    }
      private void death() {

        if (LS.lives == 0) {
            ControlPlayer.inGame = false;
        }
        else
        {
            LS.lives--;
            LS.SetLevel(LS.lives);
        }

    }
      
       private void showIntroScreen(Graphics2D g2d) {
 
    	String start = "Press SPACE to start";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (LS.SCREEN_SIZE)/4, 150);
    }

    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + LS.score;
        g.drawString(s, LS.SCREEN_SIZE / 2 + 96, LS.SCREEN_SIZE + 16);

        for (int i = 0; i < LS.lives; i++) {
            g.drawImage(heart, i * 28 + 8, LS.SCREEN_SIZE + 5, this);
        }
    }
    
    private void drawMaze(Graphics2D g2d) {

        int i = 0;
        int x, y;

        for (y = 0; y < LS.SCREEN_SIZE; y += LS.BLOCK_SIZE) {
            for (x = 0; x < LS.SCREEN_SIZE; x += LS.BLOCK_SIZE) {

                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(5));
                
                if ((LS.levelDesign1[i] == 0)) { 
                	g2d.fillRect(x, y, LS.BLOCK_SIZE, LS.BLOCK_SIZE);
                 }

                if ((LS.Map[i] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + LS.BLOCK_SIZE - 1);
                }

                if ((LS.Map[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + LS.BLOCK_SIZE - 1, y);
                }

                if ((LS.Map[i] & 4) != 0) { 
                    g2d.drawLine(x + LS.BLOCK_SIZE - 1, y, x + LS.BLOCK_SIZE - 1,
                            y + LS.BLOCK_SIZE - 1);
                }

                if ((LS.Map[i] & 8) != 0) { 
                    g2d.drawLine(x, y + LS.BLOCK_SIZE - 1, x + LS.BLOCK_SIZE - 1,
                            y + LS.BLOCK_SIZE - 1);
                }

                if ((LS.Map[i] & 16) != 0) { 
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
               }

                i++;
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);

        if (ControlPlayer.inGame) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }


    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

/**
 *
 * @author elmag
 */
public abstract class GameObject {
    protected int x, y;
    protected int dx, dy;
    protected int speed;
    
    public void setPosition(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    
    public void setDirection(int dx, int dy)
    {
        this.dx=dx;
        this.dy=dy;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
}

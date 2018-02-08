/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakeGame_Package;

/**
 *
 * @author hp
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Snake {
	
	public Vector<Point> snakePoints=new Vector<Point>();
	int xdir,ydir;
	int deflen;// default length of snake
	Point head,tail;
	boolean mv,elong;
	public Snake(int dl)
	{
		this.deflen=dl;
                
		this.xdir=1;
		this.ydir=0;
		this.mv=false;
                this.elong=false;
		int dx=(SnakeGame.width)/2;
		int dy=(SnakeGame.height)/2;
	    this.head=new Point(dx,dy);
	    for(int i=0;i<dl;i++)
	    {
	    	snakePoints.add(new Point(dx-i*4,dy));
	    }
	    this.tail= snakePoints.lastElement();
	}
	public boolean isMoving()
	{
	  return mv;
	}
	public void setMoving()
	{
		this.mv=true;
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.yellow);
		for(Point temp : snakePoints){
			g.fillRect(temp.getX(), temp.getY(), 4, 4); //default size of snake block is set 4by4
		}
                g.setColor(Color.magenta);
                g.fillRect(snakePoints.get(0).getX(),snakePoints.get(0).getY(),4,4);
	}
	public void setElong()
        {
            this.elong=true;
        }
	public void move()
	{
            
            System.out.println("Move");
		if(mv)
		{
	        System.out.println(xdir+" "+ydir);
		head=new Point(head.getX()+xdir*4,head.getY()+ydir*4);
		snakePoints.add(0,head);
                if(elong==true){elong=false;}
                else
		snakePoints.remove(snakePoints.size()-1);
		tail=snakePoints.lastElement();
		}
	   return;
	}
	public int getXdir()
	{
		return this.xdir;
		
	}
	public void setXdir(int dir)
	{
		 this.xdir=dir;
		
	}
	public int getYdir()
	{
		return this.ydir;
		
	}
	public void setYdir(int dir)
	{
		 this.ydir=dir;
		
	}
	
}


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
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class SnakeGame extends Applet implements KeyListener, Runnable{//Runnable

	static int height,width;
	Graphics gfx;
	Image img;
	Snake sn;
	Thread thread;
        Point fd;
        int  fl=0;
        Graphics tg;
        int score;
	public void init()
	{
            System.out.println("init");
            height=400;
	    width=400;
	    this.resize(new Dimension(width, height));
	    img=createImage(width,height);
	    gfx=img.getGraphics(); 
	    sn=new Snake(30);
	    this.addKeyListener(this);
	    thread=new Thread(this); 
            thread.start();
            this.score=0;
            fd=new Point(50,50);
	}
	
	public void paint(Graphics g)
	{
             tg=g;
             System.out.println("paint");
                g.setColor(Color.black);
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, width, height);
                drawObstracle(gfx);
                drawFood(gfx);
                if(Validate()){
                 sn.draw(gfx);// dtrawing on of screen graphics (in img)
                }
                else
                {
                    sn.draw(gfx);
                    gfx.setColor(Color.RED);
                    gfx.drawString("GAMEOVER",width/2 -50,height/2 -20);
                    gfx.drawString("SCORE :"+score,width/2 -50,height/2+20);
                    thread.stop();
                }
		g.drawImage(img,0,0,null);
	   
	}
        public void drawObstracle(Graphics g)
        {
             
             g.setColor(Color.LIGHT_GRAY);
             for(int i=0; i <width-9; i+=10)
             {
                g.fill3DRect(i,1,9,5,true);
                g.fill3DRect(i,height-6,9,5, true);
             }
             for(int i=0; i <height-9; i+=10)
             {
                g.fill3DRect(1,i,5,9,true);
                g.fill3DRect(width-6,i,5,9, true);
             }
             
        }
       
	public void Food()
        {
          System.out.println("Food");
          Random r=new Random(); 
          int x=r.nextInt(width-25)+15;
          int y=r.nextInt(height-25)+15;
          fd=new Point(x,y);
          while(sn.snakePoints.contains(fd))
          {
             
              x=r.nextInt(width-25)+15;
              y=r.nextInt(height-25)+15;
              fd=new Point(x,y);
          }
        }
        
        public void drawFood(Graphics g)
        {
            g.setColor(Color.GREEN);
            g.fillOval(fd.x, fd.y, 8, 8);
        }
        
        public boolean isEaten()
        {
            if(sn.head.getX()+2 >=fd.getX() && sn.head.getX()+2 <=fd.getX()+8  && sn.head.getY()+2 >=fd.getY() && sn.head.getY()+2 <=fd.getY()+8)
            {
                score++;
                sn.setElong();
                return true;
            }
            return false;
        }
       
        public boolean Validate()
        {
            boolean valid=true;
            for(int i=1; i < sn.snakePoints.size();i++)
            {
                if(sn.snakePoints.get(i).getX() ==sn.head.getX()  && sn.snakePoints.get(i).getY() ==sn.head.getY())
                {
                    valid=false;
                    break;
                }
            }
            if(valid==true)
            {
                if( sn.head.getX()<=6 || sn.head.getX()>width-6 || sn.head.getY()<=6 || sn.head.getY()>=height-6)
                {
                    valid=false;
                }
            }
            return valid;
        }
        int max(int a,int b)
        {
            if(a>b)
                return a;
            return b;
        }
	 public void run()
             {
                 System.out.println("run");
		while(true)
		{
			 
			sn.move();
                        if(Validate()){
                        if(isEaten())
                            Food();
                        repaint();
                        }
                        else
                        sn.mv=false;
                        repaint();
                        try 
                        {
		             thread.sleep(max(10,50-(score)));
			}
                        catch (InterruptedException e) 
                        {
			     e.printStackTrace();
			}
                }
             }

    // x: 1 for down -1 for up
    // y: 1 for rigrt -1 for left
    
	public void keyPressed(KeyEvent e)
	{
		System.out.println("Key");
		if(! sn.isMoving())
		sn.setMoving();
		
		if(e.getKeyCode()== KeyEvent.VK_UP)
		{
			if(sn.getYdir()!=1)
			{
				sn.setXdir(0);
				sn.setYdir(-1);
				
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_DOWN)
		{
			if(sn.getYdir()!=-1)
			{
				sn.setXdir(0);
				sn.setYdir(1);
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_LEFT)
		{
			if(sn.getXdir()!=1)
			{
				sn.setYdir(0);
				sn.setXdir(-1);
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_RIGHT)
		{
			if(sn.getXdir()!=-1)
			{
				sn.setYdir(0);
				sn.setXdir(1);
			}
		 }
		
	}

	
	public void keyReleased(KeyEvent e) 
	{
	     System.out.println("Key");	
		
	}

	
	public void keyTyped(KeyEvent e) 
        {
		System.out.println("Key");
		
	}
	

}

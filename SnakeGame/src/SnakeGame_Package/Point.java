
package SnakeGame_Package;

/**
 *
 * @author hp
 */
public class Point{
  
	int x,y;
	
	public Point()
	{
		this.x=0;
		this.y=0;
    }
	 
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void setX(int x)
	{
		this.x=x;
	}
	
	public int  getX()
	{
		return this.x;
	}
	
	public void setY(int y)
	{
		this.y=y;
	}
	
	public int  getY()
	{
		return this.y;
	}
}


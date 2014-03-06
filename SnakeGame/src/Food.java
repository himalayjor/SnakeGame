import java.awt.*;
import java.util.*;

public class Food {
	int x, y;
	Rectangle foodrect;
	
	public Food()
	{
		foodrect = new Rectangle(0,0,10,10);
		
	}
	
	public void drawNewFood(Random rg, Graphics g)
	{
		x = rg.nextInt(66) * 10;
		y = rg.nextInt(46) * 10 + 5;
		foodrect.x = x;
		foodrect.y = y;
		
		g.fillRect(x, y, 10, 10);
	}
	public void drawOldFood(Graphics g)
	{
		g.fillRect(x, y, 10, 10);
	}
	
	 public void drawNewFood(Random rg, Graphics g, Rectangle[] maze)
	{
            boolean flag = true;
            while (flag) {
		x = rg.nextInt(66) * 10;
		y = rg.nextInt(46) * 10 + 5;
		foodrect.x = x;
		foodrect.y = y;
                flag = false;
                int i = 0;
		for (; i < maze.length; i++) {
                    if (maze[i].intersects(foodrect)) {
                        flag = true;
                        break;
                    }
                }
            }
                
		g.fillRect(x, y, 10, 10);
	}
	 
}
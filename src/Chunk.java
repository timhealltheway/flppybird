import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Iterator;

class Chunk extends Sprite
{
	Model model;
	double xvel,yvel;
	int kickcount;
	//LinkedList<Sprite> sprites;
	
	Chunk(Model m)
	{
		model   = m;
		xvel    = 10;
		yvel    = -10;
		x		= model.bird.x;
		y		= model.bird.y;
		v		= -2;
		kickcount = 0;
		
	}
	Chunk(Chunk source)
	{
		this.x = source.x;
		this.y = source.y;
		this.v = source.v;
		this.xvel = source.xvel;
		this.yvel = source.yvel;
		this.model = new Model();
		this.kickcount = source.kickcount;
		this.im			= source.im;
	}
	void draw(Graphics g)
	{
		//when call chunk norris to hit the tube
		g.drawImage(loadImage("chuck_norris.png"), x, y,null);
	}
	int getwidth()
	{
		int width = 55;
			return width;
	}
	int getheight()
	{
		int height = 67;
			return height;
	}
	boolean isTube()
	{
		return false;
	}
	boolean isBird()
	{
		return false;
	}
	boolean isCollidable()
	{
		return false;
	}
	boolean isChunk()
	{
		return true;
	}
	boolean isHand()
	{
		return false;
	}
	boolean update()
	{
		this.x += xvel;
		this.y += yvel;
		
		this.yvel += 1.5; //keep same speed wit bird
	
		Iterator<Sprite> it = model.sprites.iterator();    //Initialize tube it;
		kickcount ++;
		while(it.hasNext())		//Returns true if the iteration has more elements.
		{
			Sprite s = it.next();
			if(s.isTube())
			{
				if(s.doesCollideWith(this))
				{
					Tube t = (Tube)s;
					t.xvel = xvel;			//Tube velocity match chunk velocity
					t.yvel = 10;
					xvel = -xvel;			//Then both tube and chunk fall 
					//System.out.println(kickcount);
					if(kickcount >=1)
						break;
				}
			}
		}
		
		return true;
	}
	
    Sprite clone(Model newModel)
	{
		return new Chunk(this);
	}
	
}

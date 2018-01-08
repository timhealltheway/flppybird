import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JButton;

class Hand extends Sprite
{
	//Member variable
	
	//Bird bird;
	Model model;
	//LinkedList <Sprite> s;
	
	Hand(Model m)
	{
		model = m;
		//this.s = k;
		//this.bird = b;
		this.x = 0;
		this.y = 600;
		this.v = 0;


	}
	Hand(Hand source)
	{
		//super(source);
		this.x = source.x;
		this.y = source.y;
		this.v = source.v;
		this.model = source.model;
	}

	boolean update()
	{
		if(!model.bird.birdDie())
			return true;
		if(model.bird.y <= this.y)
			this.y -= 40;
		else
		{
			this.y += 40;
			model.bird.y +=40;
			if(this.y >=600)
				System.exit(0);
		}
		return true;
	}
	
	void draw(Graphics g)
	{
		//Draw Hand
		if (model.bird.y < this.y)
		{
			g.drawImage(loadImage("hand1.png"), this.x, this.y, null);		
		}
		else
		{
			g.drawImage(loadImage("hand2.png"), this.x, this.y, null);
		}

	}
	
	int getwidth()
	{
		int width = 30;
		return width;
	}
	int getheight()
	{
		int height = 80;
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
		return false;
	}
	boolean isHand()
	{
		return true;
	}
	
	Sprite clone(Model newModel)
	{
		return new Hand(this);
	}
}
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

class Bird extends Sprite
{
	//Bird Member variabel
	Model model;
    
    double hp ; // Energy of bird
    int counter;	
    
	Bird(Model m)
	{
		super();
		model = m;
		
		//Initialize everything for bird
		this.hp = 100; 
		this.v  = 0;
		this.counter = 0;
		this.y  = 100;
		
	}
	
	Bird(Bird source)
	{
		//super(source);
		this.x = source.x;
		this.y = source.y;
		this.v = source.v;
		this.hp= source.hp;
		this.counter = source.counter;
		this.model   = new Model();
		
		
	}
 
	int getwidth()
	{
		int width = 55;
		return width;
	}
	int getheight()
	{
		int height = 40;
		return height;
	}
    
	boolean isTube()
	{
		return false;
	}
	boolean isBird()
	{
		return true;
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
		return false;
	}
	
	public boolean birdDie()
	{
		if(hp <= 0)
			return true;
		else
			return false;
	}
	
    boolean update()
    {
    	if(!this.birdDie())			//if energy of bird is > 0
    	{
    	counter++;
    	
    	//Initialize downward veloctiy 1 and acceleration. it coulde change
        v += 0.8    ; 
        // Move the bird
        this.y += v;   
        
        if(hp < 100 && hp >0)
        	hp += 0.2;
        
        return true;
    	}
    	else
    		return false;
        
        //System.out.println(bird_y);
    }
    
	public void draw(Graphics g)
	{
		//Draw Bird
		if (counter <10)
		{
			g.drawImage(loadImage("bird1.png"), this.x, this.y, null);		
		}
		else
		{
			g.drawImage(loadImage("bird.png"), this.x, this.y, null);
			counter = 0;
		}
		
		//Draw Energy bar
		g.setColor(Color.black);
		//Track Energy of bird as bar's length
		g.fillRect(0, 0, (int)hp, 20);
		

		
	}
    
     void flap()
    {
    	if(!birdDie())
    	{
        v = -9;
        this.y +=  v  ;
    	}
        
    }
     Sprite clone(Model newModel)
 	{
 		return new Bird(this);
 	}
	

}

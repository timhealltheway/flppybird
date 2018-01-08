
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Iterator;

class Tube extends Sprite
{
    //Tube member variable
    boolean direction;
    Random rnd ;
    double xvel,yvel;
    Model model;
	
    Tube(Model m,Random randy)
    {
    	model = m;
        rnd	 = randy;
        direction = rnd.nextBoolean();    //initialize direction
		//Setup original tube position and speed
        y    = -150;
        x    =  400;
        v    = 0;
        xvel = 0;
        yvel = 0;
        
        if(direction == true)
        {
        	this.x    =  500;
            this.y = rnd.nextInt(150) + 200  ;
            
        }
        else{
        	this.x    =  500;
            this.y = rnd.nextInt(150)-300;
        }
    } 
    
	Tube(Tube that)
	{
		this.x = that.x;
		this.y = that.y;
		this.v = that.v;
		this.xvel = that.xvel;
		this.yvel = that.yvel;
		this.direction   = that.direction;
		this.rnd    = new Random(that.rnd);
		this.im     = that.im;
		this.model  = new Model();
	}
    
    //Interface
  
	public void draw(Graphics g)
	{
    //draw images
		if(direction == true)
		{
			g.drawImage(loadImage("tube_up.png"), this.x, this.y, null);
		}
		else
		{
			g.drawImage(loadImage("tube_down.png"), this.x, this.y, null);
		}
	}
	
	 int getwidth()
	{
		 int w = 5;
		return w;
	}
	 int getheight()
	{
		 int height = 300;
		return height;
	}
	 boolean isTube()
	{
		return true;
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
    	return false;
    }

    boolean update()
    {

        // Moving tube_x from right to left
        this.x -=5;
        y += yvel;
        x += xvel;
        //System.out.println(tube_x);
        //Generate random number and direction for tube
        if(this.x < -30)
        	{
        		return false;   //kill me know 
        	}
        return true;   //live 
       }
    Sprite clone(Model newModel)
    {
    	return new Tube(this);
    }
 
}

//Random function 
class Random {
	long m_a;
	long m_b;

	Random(long seed) {
		setSeed(seed);
	}

	Random(Random that) {
		m_a = that.m_a;
		m_b = that.m_b;
	}

	void setSeed(long seed) {
		m_b = 0xCA535ACA9535ACB2l + seed;
		m_a = 0x6CCF6660A66C35E7l + (seed << 24);
	}

	long nextFullLong() {
		m_a = 0x141F2B69l * (m_a & 0x3ffffffffl) + (m_a >> 32);
		m_b = 0xC2785A6Bl * (m_b & 0x3ffffffffl) + (m_b >> 32);
		return m_a ^ m_b;
	}

	long nextLong(long range) {
		long n = (0xffffffffffffffffl % range) + 1;
		long x;
		do {
			x = nextFullLong();
		} while((x + n) < n);
		return x % range;
	}

	int nextInt(int range) {
		return (int)nextLong((long)range);
	}

	boolean nextBoolean() {
		return (nextLong(2) == 0 ? true : false);
	}
}
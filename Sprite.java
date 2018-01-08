import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

//Abstract class exist to be extended, can not be instantiated
abstract class Sprite
{
	int x,y;
	double v;
	Image im;
	abstract void draw(Graphics g);
	abstract int getwidth();
	abstract int getheight();
	abstract boolean isTube();
	abstract boolean isBird();
	abstract boolean update();		//Tube & bird in this game
	abstract boolean isCollidable();
	abstract boolean isChunk();
	abstract boolean isHand();
	abstract Sprite clone(Model newModel);
	
	Image loadImage(String filename)
	{
		Image i = null;
		try
		{
			i = ImageIO.read(new File(filename));
		}
		catch(Exception e)
		{
			System.err.println("cant find image");
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return i;
	}
	
	void setImage(Image im, int x, int y)
	{
		this.im = im;
		this.x = x;
		this.y = y;
	}
	
	
	/*void drawThing(Graphics g)
	{
		g.drawImage(this.im, x, y, null);
	}*/
	
	boolean doesCollideWith(Sprite that)
	{
		if(this.x + this.getwidth() < that.x)
			return false;
		if(this.x > that.x + that.getwidth())
			return false;
		if(this.y + this.getheight()+100 < that.y)
			return false;
		if(this.y > that.y + that.getheight()+100)
			return false;
		return true;
	}
	
 
}






import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Iterator;
import java.awt.Graphics;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.LinkedList;


class View extends JPanel
{
	Model model;
	LinkedList <Sprite> s;

	View(LinkedList <Sprite> sprite)					//Pass reference controller and Model
	{
		s = sprite;
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		Iterator<Sprite> it = s.iterator();
		while(it.hasNext())
		{
			Sprite s = it.next();
			s.draw(g);
		}
		
		/*
		//Draw Energy bar. Use hp as int x in this case
		//System.out.println(model.bird.hp);
		g.setColor(Color.black);
		g.fillRect(0, 0, (int)model.bird.hp, 20);
		
		if(model.gameOver() == false)
		{
			g.drawImage(model.handup, model.bird.bird_x,model.bird.bird_y, null);
			g.drawImage(model.handg, model.bird.bird_x,model.bird.bird_y, null);
			
			if(model.bird.bird_y > 480 && model.bird.bird_y <1000 )
				System.exit(0);
		}
		
		
			
		//Use counter to decide falpping. when click on mouse once, it will reset counter and make bird flap
		if (model.count <10)
		{
			g.drawImage(Bird.bird1, model.bird.bird_x, model.bird.bird_y, null);		
		}
		else
			g.drawImage(Bird.bird2, model.bird.bird_x, model.bird.bird_y, null);
		
		Iterator<Tube> tmp = model.tube.iterator();	
		while(tmp.hasNext()){
			Tube t = tmp.next();
		if(t.direction == true)
		{
			
			g.drawImage(Tube.tubeup, t.tube_x, t.tube_y, null);
		}
		else
			g.drawImage(Tube.tubedown, t.tube_x, t.tube_y, null);
		}
		*/

	}

	
	
    
}

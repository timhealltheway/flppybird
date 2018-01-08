import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.Iterator;

class Controller implements MouseListener
{
	//Member variable 
	//Bird bird;
	View view;
	Model model;
	LinkedList <Sprite> sprities;
	
	Controller(Model m)
	{
		model = m;
	}
	void update() 
	{
		// Evaluate each possible action
		double score_nothing = model.evaluateAction(Model.Action.nothing, 0);
		double score_flap = model.evaluateAction(Model.Action.flap, 0);
		double score_chuck = model.evaluateAction(Model.Action.call, 0);
		
		//System.out.println("nothing score: " + score_nothing);
		//System.out.println("flap score: " + score_flap);
		//System.out.println("chuck score: " + score_chuck);
		
		// Do the best one
		if(score_chuck > score_flap && score_chuck > score_nothing){
			model.doAction(Model.Action.call);
		//System.out.println(score_nothing);
			}
		else if(score_flap > score_nothing)
			model.doAction(Model.Action.flap);
		else {
			model.doAction(Model.Action.nothing);
		   if(score_nothing == 0.0)
			   System.out.println("Found no way to survive!");
		}
	}
	void setView(View v)
	{
		view = v;
		view.addMouseListener(this);
	}
	
		public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
				model.bird.flap();
		else
			model.onClick();

	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {
		
	}
	
	
}

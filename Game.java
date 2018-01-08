import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.util.LinkedList;

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;
	
	public Game()
	{
		model = new Model();						//Allocation
		controller = new Controller(this.model);
		view = new View(this.model.sprites);
		view.addMouseListener(controller);
		this.setTitle("Flying bIrd!");
		this.setSize(500, 500);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
	
	public void run()
	{	
	while(true)
	{
		controller.update();
		model.update();
		view.repaint(); // Indirectly calls View.paintComponent

		// Go to sleep for 40 miliseconds
		try
		{
			Thread.sleep(40);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	}
	
	
}



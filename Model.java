
import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;


import java.awt.Image;
import java.io.File;
import java.io.IOException;

class Model
{
	//Member variabel
	LinkedList <Sprite> sprites;  //Linkedlist sprite 
	Bird bird;
	Hand hand;
	//Tube tube;
	Random rnd;
	
	int count ;		//Counter
	int numColl ;       //Number of collision


	Model()
	{
		count = 0;
		numColl = 0;
		
		//initialize sprite
		sprites = new LinkedList<Sprite>();
		rnd = new Random(1234);
		
		//Initialize Hand and bird
		bird = new Bird(this);
		hand = new Hand(this);
		//tube = new Tube((this,rnd));
		
		//Adding bird, hand and tube to sprite
		sprites.add(this.bird);
		sprites.add(this.hand);
		//sprites.add(new Tube(this,rnd));
	}
	
	// deep copy--constructor 
	Model(Model source)
	{
		// Copy bird and hand
		this.bird = new Bird(source.bird);
		this.hand = new Hand(source.hand);

		// Copy the sprites
		sprites = new LinkedList<Sprite>();
		for(Iterator<Sprite> it = source.sprites.iterator(); it.hasNext(); )
		{
			Sprite s = it.next();
			sprites.add(s.clone(this));
		}
		
		// Copy the other stuff
		this.rnd = new Random(source.rnd);
		this.numColl = source.numColl;
		this.count = source.count;
	}

	void update()
	{
		count++;
		//System.out.println(count);
		if(count >25)
		{
			count = 0;
			sprites.add(new Tube(this,rnd));
		}
		
		//Trying to keep bird in "safety zoom"
        if(bird.y > 250)
        	bird.hp -= 0.0000001;	
        
		if(bird.x > 500 || bird.x < 0 || bird.y > 500 || bird.y < 0)
		{
			bird.hp -=10;
		}
        
		Iterator<Sprite> it = sprites.iterator();    //Initialize tube it;	
		while(it.hasNext())		//Returns true if the iteration has more elements.
		{
			Sprite s = it.next();
			if(s.update() == false)
				it.remove();
			
			if(s.isTube())
			{
				if(s.doesCollideWith(bird))
				{
					bird.hp -=3;
				}
			}
			
		}      
		//System.out.println(bird.x);
		//System.out.println(bird.y);
	}
	
	//Call Chuck norris
	public void onClick()
	{
		sprites.add(new Chunk(this));

		bird.hp -=8;
	}
	
	//AI 
	enum Action{flap,call,nothing};

	void doAction(Action a)
	{
        if(a == Action.flap)
            this.bird.flap();
        else if(a == Action.call)		//call chuck
            this.onClick();
        else if(a == Action.nothing)
        {
        	//do nothing
        }
        else
            throw new RuntimeException("Unexpected action " + a);
	}
	
	double evaluateAction(Action a, int depth) 
	{
		int d = 35;
		int k = 8;
		
		// Evaluate the state
		if(bird.hp <= 0.0)
			return 0.0;
		if(depth >= d)
			return bird.hp;

		// Simulate the action
		Model copy = new Model(this); // uses the copy constructor
		copy.doAction(a);
		copy.update(); // advance simulated time

		// Recurse
		if(depth % k != 0)
		   return copy.evaluateAction(Action.nothing, depth + 1);
		else {
		   double best = copy.evaluateAction(Action.nothing, depth + 1);
		   best = Math.max(best,
			   copy.evaluateAction(Action.flap, depth + 1));
		   best = Math.max(best,
			   copy.evaluateAction(Action.call, depth + 1));
		   return best;
		}
	}


	
}
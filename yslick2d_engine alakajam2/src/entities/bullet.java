package entities;

import org.newdawn.slick.SlickException;

import screens.game;
import yengine.yentity;
import yengine.ytimer;

public class bullet extends yentity {

	public int dmg;
	public ytimer life = new ytimer(700);
	public bullet(float x, float y, float speed, String img) throws SlickException 
	{
		super(x, y, speed, img);
		type = "bullet";
		dir = "left";
		no_cam = true;
		init();
	}//end constractor

	@Override
	public void init() throws SlickException 
	{
		set_w_h(10,10);
		super.init();
		
	}//end init

	@Override
	public void update() throws SlickException 
	{
		game g = (game)world;
		if(g.pause) {return;}
		move();
		super.update();
	}//end update
	
	public void move() throws SlickException 
	{
		bullet_movment();
		life.update();
		if(life.finished()) 
		{
			world.remove(this);
		}
		//if out of screen remove
		//if(y<0){world.remove(this);}
		//if(y>700){world.remove(this);}
	}//end move

}//end bullet class

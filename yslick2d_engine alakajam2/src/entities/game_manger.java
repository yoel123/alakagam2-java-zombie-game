package entities;

import org.newdawn.slick.SlickException;

import screens.game;
import yengine.yentity;
import yengine.ytimer;

public class game_manger extends yentity 
{
	public boolean did_init;
	public String ph = "blue_box.png";//place holder
	
	public ytimer spawn_timer,powerup_timer;
	
	public game_manger() throws SlickException {
		super(0,0, 0,"");
		spawn_timer = new ytimer(900);
		powerup_timer = new ytimer(3200);
	}//end constructor

	public void yinit() throws SlickException 
	{
		
	}
	
	@Override
	public void update() throws SlickException {
		super.update();
		yinit();
		game g = (game)world;
		if(g.pause) {return;}
		spawn();
		spawn_powerup();
	}//end update
	
	public void spawn() throws SlickException 
	{
		int floor = 300;
		
		float[] cam = world.get_camera();
		game g = (game)world;
		int kill_count = g.kill_count;
		spawn_timer.update();
		
		if(!spawn_timer.finished()){return;}//if timer not finished exit
		
		enemy zombie = new enemy(-20-cam[0],floor,ph);
		world.add(zombie);
		if(kill_count>3) 
		{
			enemy zombie2 = new enemy(620-cam[0],floor,ph);
			world.add(zombie2);
		}
		
		if(kill_count>7) 
		{
			enemy zombie3 = new enemy(650-cam[0],floor,ph);
			zombie3.set_speed(0.08f);
			world.add(zombie3);
		}
		
		if(kill_count>13) 
		{
			enemy flyer1 = new flyer(-20-cam[0],ph);
			flyer1.set_speed(0.08f);
			world.add(flyer1);
		}
		if(kill_count>20) 
		{
			enemy flyer1 = new flyer(650-cam[0],ph);
			flyer1.set_speed(0.08f);
			world.add(flyer1);
		}
	}//end spawn
	
	public void spawn_powerup() throws SlickException 
	{
		powerup p;
		powerup_timer.update();
		if(powerup_timer.finished()) 
		{
			p = new powerup(ph);
			world.add(p);
		}
	}
}

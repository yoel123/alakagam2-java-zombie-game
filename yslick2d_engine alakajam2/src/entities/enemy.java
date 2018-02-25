package entities;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import screens.game;
import yengine.yentity;
import yengine.ytimer;

public class enemy extends yentity {

	public boolean dead = false;
	public int hp=2,maxhp=2,dmg=2;
	public float old_speed, height2,width2;
	
	public ytimer atack_timer;
	
	public enemy(float x2, float y2,String img2)throws SlickException {
		super(x2,y2, 0.04f, "zombie_sprite.png");
		type="enemy";
		atack_timer = new ytimer(300);

		//debug = true;
	}//end constructor
	
	public void init() throws SlickException 
	{

		
		height2=63;
		width2=36;
		set_w_h(width2,height2);
		set_img_type("sprite");
		set_sxy(1,0);
		// TODO Auto-generated method stub
		super.init();
	}//end init
	
	@Override
	public void update() throws SlickException {
		
		super.update();
		game g = (game)world;
		if(g.pause) {return;}
		fade_remove();
		collide_test();
		move();
		
	}//end update
	
	public void collide_test() throws SlickException 
	{
		if(dead) {return;}
		
		//collide with bullet
		ArrayList<yentity> b = collide("bullet");
		if(b.size()>0)
		{
			//loop all colided bullets
			for (int i = 0; i < b.size(); i++) 
			{
				bullet cb = (bullet) b.get(0);//current bullet
				take_dmg(cb.dmg);//take damege
				world.remove(cb);//remove bullet from world
			}
			//yengine.o("collide");
		}
		
		//collide with player
		ArrayList<yentity> p = collide("player");
		if(p.size()>0)
		{
			player cp = (player) p.get(0);//current player
			attack(cp);
			

			//yengine.o("collide player");
		}

	}//end collide_test
	
	public void take_dmg(int dmg2) throws SlickException 
	{
		hp -=dmg2;
		if(hp<=0)
		{
			dead =true;
		}
	}//end take_dmg
	
	public void set_speed(float s) throws SlickException 
	{
		speed = s;
		old_speed=s;
	}//set_speed
	
	public void fade_remove() throws SlickException 
	{
		if(!dead) {return;}//if not dead exit func
		//if visable reduce alpha (alpha = opacity)
		if(alpha>0)
		{
			alpha -=0.009;
			//yengine.o(alpha);
		}
		if(alpha<0.005)//just incase its stops at that num
		{
			alpha=0;
		}
		if(alpha==0) //not visable remove from screen
		{
			world.remove(this);
			game g = (game)world;
			g.kill_count++;
		}
	}
	
	public void attack(player cp) throws SlickException 
	{
		//if attack imer is finished then deal damege
		atack_timer.update();
		if(atack_timer.finished()) 
		{
			//yengine.o("hit player");
			cp.take_dmg(dmg);
		}
	}//end attack
	
	public void move() throws SlickException 
	{
		if(dead) {return;}//dead enemy cant move
		player p;
		
		if(dir.equals("left")) {set_sxy(1,0);}else {set_sxy(0,0);}
		
		//get player by type
		ArrayList<yentity> mcs = get_by_type("player");
		if(mcs.get(0) != null) 
		{
			p = (player)mcs.get(0);
			move_to(p);//move towerd player
		}
		
	}//end move
	
	public void move_to(yentity t)
	{
		//ony folow on x axis by cam
		float[] cam = world.get_camera();
		float camx = t.x-cam[0];//cam[0] = camera on x axis
		if(this.x>camx){move_by(-speed,0); dir="left"; }
		if(this.x<camx){move_by(speed,0); dir="right";}
	}//end move_to
	

}

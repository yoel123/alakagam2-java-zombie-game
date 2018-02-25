package entities;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.SlickException;

import screens.game;
import yengine.yengine;
import yengine.yentity;
import yengine.ytimer;

public class powerup extends yentity {

	public int old_val;//old val of the att this powerup changes
	public ytimer duration;
	public boolean picked;
	public boolean did_init;
	public player cp;
	public String power_type;
	public String[] power_types = {"ammo","hp"};
	public float  height2,width2;
	public powerup( String img) throws SlickException {
		super(30, 300,0, "powerup_sprite.png");//powerup_sprite
		type = "powerup";
		duration = new ytimer(5000);
		power_type = "ammo";
		//seloect random powerup
		int rnd = new Random().nextInt(power_types.length);
		power_type= power_types[rnd];
		z=99;
	}//end constructor
	public void init() throws SlickException 
	{

		
		height2=62;
		width2=30;
		set_w_h(width2,height2);
		set_img_type("sprite");
	
		
		// TODO Auto-generated method stub
		super.init();
		
	}//end init
	public void yinit() throws SlickException 
	{
		if(did_init) {return;}
		rand_pos();
		if(power_type.equals("ammo")) {set_sxy(0,0);}
		if(power_type.equals("hp")) {set_sxy(1,0);}
		did_init=true;
	}
	@Override
	public void update() throws SlickException 
	{
		super.update();
		yinit();
		game g = (game)world;
		if(g.pause) {return;}
		fade_remove();
		pickup();
	
	}//end update

	public void pickup() throws SlickException
	{	
		//collide with player
		ArrayList<yentity> p = collide("player");
		if(p.size()>0 && !picked)
		{
		    cp = (player) p.get(0);//current player
			//cp.pup = this;
			picked = true;
		}
		do_powerup();
	}//end pickup
	
	public void do_powerup() throws SlickException
	{
		if(!picked){return;}
		
		duration.update();
		if(duration.finished()) 
		{
			world.remove(this);
		}else 
		{
			power_do();
		}
	}
	public void power_do() throws SlickException
	{
		//if(power_type.contains("")) {}
		if(power_type.contains("hp")) 
		{
			yengine.o("hp powerup");
			if(cp.hp< cp.maxhp) 
			{
				cp.hp_up(5);
				
			}
			world.remove(this);
		}
		if(power_type.contains("ammo"))
		{
			yengine.o("ammo powerup");
			cp.pwepon.ammo_left = cp.pwepon.max_ammo*2;
			cp.pwepon.reloading = false;
			world.remove(this);
		}
	}//end power_do
	

	
	public void rand_pos()
	{
		 Random r = new Random();
		 float[] cam = world.get_camera();
		 x = 20+r.nextInt(400)-cam[0];
		 if(cam[0]<-300) {x-=300;}
		 if(cam[0]>300) {x+=300;}
		
	}//end rand_pos
	public void fade_remove() throws SlickException 
	{
		if(picked) { alpha=0; return;}
		//if visable reduce alpha (alpha = opacity)
		if(alpha>0)
		{
			alpha -=0.0003;
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
		}
	}

}

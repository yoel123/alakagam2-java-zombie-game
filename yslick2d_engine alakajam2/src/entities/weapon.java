package entities;

import java.util.Random;

import org.newdawn.slick.SlickException;

import yengine.yengine;
import yengine.yentity;
import yengine.ytimer;

public class weapon extends yentity {
	
	public int dmg=1,ammo_left=10,max_ammo=10,fire_rate=100;
	public String dmg_type,name="hand_gun",shot_type="single";
	

	//ytimer tst_timr = new ytimer(100);
	public ytimer cooldown,reload;
	public boolean reloading;
	public player parent;
	
	public weapon(player p ) throws SlickException {
		super(p.x,p.y, 0, "blue_box.png");
		z=99;
		type="weapon";
		parent = p;
		no_cam = true;
		cooldown = new ytimer(fire_rate);
		reload = new ytimer(700);
		init();
	}//end constructor
	
	@Override
	public void update() throws SlickException {
		// TODO Auto-generated method stub
		super.update();
		x = parent.x;
		y = parent.y;
		//reload();
		
	}//end update
	
	public void fire() throws SlickException 
	{
		bullet b;
		if(cooldown.finished() && ammo_left>0) 
		{
			shot_type_do();
			
		}

	}//end fire
	
	public void shot_type_do() throws SlickException 
	{
		if(shot_type.equals("single")) {single_shot();}
		
		if(shot_type.equals("shotgun")) {shotgun_shot();}
	}//shot_type_do
	
	public void single_shot() throws SlickException 
	{
		bullet b;
		//create bullet
		b= new bullet(x+5,y+20,0.3f,"blue_box.png");
		b.dir = parent.dir;
		b.dmg = dmg;
		parent.world.add(b);
		ammo_left--;
	}//end single_shot
	
	public void shotgun_shot() throws SlickException 
	{
		Random r = new Random();
		for(int i =0;i<=3;i++) 
		{
			bullet b;
			//create bullet
			float x2 = x+5+r.nextFloat()*32;
			float y2 = y+20+r.nextFloat()*17;
			b= new bullet(x2,y2,0.7f,"blue_box.png");
			b.dir = parent.dir;
			b.dmg = dmg;
			b.life = new ytimer(30);
			parent.world.add(b);
			//yengine.o(i);
		}
		ammo_left--;
	}//end shotgun_shot
	
	public void reload() 
	{
		if(ammo_left<=0) 
		{
			reloading = true;
			reload.update();
			if(reload.finished()) 
			{
				ammo_left = max_ammo;
				reloading = false;
			}
		}
	}//end reload
	
	public void set_fire_rate(int r) throws SlickException 
	{
		cooldown = new ytimer(r);
	}
	public void set_reload_rate(int r) throws SlickException 
	{
		reload = new ytimer(r);
	}
}

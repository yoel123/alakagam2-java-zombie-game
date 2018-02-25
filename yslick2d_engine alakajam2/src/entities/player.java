package entities;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import screens.game;
import yengine.y_bar;
import yengine.yengine;
import yengine.yentity;

public class player extends yentity 
{

	public boolean did_init;
	public int hp=20,maxhp=20,dmg_bonus=0,current_weapon=0;
	public float height2,width2,cam_speed;
	public weapon pwepon;//player weapon
	public ArrayList<weapon> wepons;//player weapon
	public powerup pup;
	public y_bar hp_bar;
	
	public int[] move_limits = {500,-500};//the player cam can move between these
	
	public player(float x2, float y2,String img2) throws SlickException {
		super(x2,y2, 0, img2);
		z=99;
		type="player";
		no_cam = true;
		dir = "left";
		cam_speed = 0.16f;
		
		pwepon = new weapon(this);
		wepons = new ArrayList<weapon>();
		wepons.add(pwepon);
		wepons.add(weapons_fact.smg(this));
		wepons.add(weapons_fact.shutgun(this));
		
		//debug = true;
	}//end constructor
	
	@Override
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
	
	//init after add to world
	public void yinit() throws SlickException 
	{
		if(did_init) {return;}
		//create health bar obect
		hp_bar = new y_bar(30f,600f,"blue_box.png");
		hp_bar.val=hp;
		hp_bar.max_value = maxhp;
		hp_bar.z=0;
		hp_bar.type="player_hp";
		hp_bar.init();
		hp_bar.no_cam = true;
		hp_bar.max_width =300;
		hp_bar.calc_filler_val();
		world.add(hp_bar);
		did_init=true;
	}
	
	
	@Override
	public void update() throws SlickException {
		super.update();
		yinit();
		pause();
		game g = (game)world;
		if(g.pause) {return;}
		pwepon.update();
		update_weapons();
		if(pup !=null) {pup.update();}
		move();
		shot();
		change_weapon();
		
	}//end update
	
	private void change_weapon() 
	{
		
		boolean q,e;
		q = key_released(Input.KEY_Q);
		e = key_released(Input.KEY_E);
		if(q) {prev_weapon();}
		if(e) {next_weapon();}
	}//end change_weapon
	
	public void prev_weapon() 
	{
		current_weapon--;
		if(current_weapon<0){current_weapon=0;}
		pwepon = wepons.get(current_weapon);
	}//end prev_weapon
	
	public void next_weapon() 
	{
		current_weapon++;
		if(current_weapon>wepons.size()-1){current_weapon=wepons.size()-1;}
		pwepon = wepons.get(current_weapon);	
	}//end next_weapon
	
	public void update_weapons() 
	{
		for(weapon w :wepons) 
		{
			w.reload();
		}
	}//end update_weapons
	
	public void move() 
	{
		float[] cam = world.get_camera();
		boolean up,down,left,right,w,a,s,d,p;
		
		//catch key input
		up = yengine.key_pressed(gc,Input.KEY_UP);
		down = yengine.key_pressed(gc,Input.KEY_DOWN);
		left = yengine.key_pressed(gc,Input.KEY_LEFT);
		right = yengine.key_pressed(gc,Input.KEY_RIGHT);
		w = yengine.key_pressed(gc,Input.KEY_W);
		a = yengine.key_pressed(gc,Input.KEY_A);
		s = yengine.key_pressed(gc,Input.KEY_S);
		d = yengine.key_pressed(gc,Input.KEY_D);

		//move_limits = {500,-500}
		
		//if key press and not outside of move limits
		if((left || a) && move_limits[0]>cam[0]) 
		{
			cam[0] +=cam_speed*delta;
			dir = "left";
			set_sxy(1,0);
		}
		if((right || d) && move_limits[1]<cam[0]) 
		{
			cam[0] -=cam_speed*delta;
			dir = "right";
			set_sxy(0,0);
		}
		//yengine.o(cam[0]+" ");
	}//end move
	
	public void pause() 
	{
		boolean p;
		p= key_released(Input.KEY_P);
		
		game g = (game)world;
		//pause game
		if(p) 
		{
			g.pause =!g.pause;
		}
	}//end pause
	
	public void shot() throws SlickException 
	{
		bullet b;
		boolean space;
		
		space = yengine.key_pressed(gc,Input.KEY_SPACE);
		
		//if space is pressed fire weapon
		if(space ) 
		{
			pwepon.fire();
		}
		
		pwepon.cooldown.update();
	}//end move
	
	public void collide() {}//end move

	public void hp_up(int val) throws SlickException 
	{
		
		hp +=val;
		if(hp>maxhp) {hp = maxhp;}//just incase
		hp_bar.val=hp;
		
		//update hp bar
		hp_bar.calc_filler_val();
	}
	public void take_dmg(int dmg2) throws SlickException 
	{
		
		//reduce health by demage and set health bar
		hp -=dmg2;
		hp_bar.val=hp;
		
		//update hp bar
		hp_bar.calc_filler_val();
		
		//go to game over if no health left
		
		if(hp<=0)
		{
			
			sbg.getState( 3 ).init(gc, sbg);
			sbg.enterState(3);
		//	world.remove(this);
		}
	}//end take_dmg
	
	public void render( Graphics g) {

		
		//if weapon is reloading show on screen
		if(pwepon.reloading) 
		{
			yengine.ds(g, "reloading ", 350, 600);
		
		}else 
		{
			
		}
		yengine.ds(g, pwepon.name, 450, 600);
		super.render( g);
	

	}

}//end player

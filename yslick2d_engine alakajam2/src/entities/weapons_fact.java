package entities;

import org.newdawn.slick.SlickException;

public class weapons_fact {
	
	public static weapon smg(player p ) throws SlickException 
	{
		weapon ysmg = new weapon(p);
		ysmg.dmg = 1;
		ysmg.ammo_left = 20;
		ysmg.max_ammo = 20;
		ysmg.fire_rate = 40;
		ysmg.set_fire_rate(60);
		ysmg.set_reload_rate(800);
		ysmg.name = "smg";
		return ysmg;
		
	}
	public static weapon shutgun(player p ) throws SlickException 
	{
		weapon ysmg = new weapon(p);
		ysmg.dmg = 1;
		ysmg.shot_type = "shotgun";
		ysmg.ammo_left = 6;
		ysmg.max_ammo = 6;
		ysmg.set_fire_rate(260);
		ysmg.set_reload_rate(950);
		ysmg.name = "shotgun";
		return ysmg;
		
	}
}

package com.gmail.justbru00.blazesmp.listeners;

public class PvpListener {
	
	private static boolean PVP_ENABLED = true;
	
	public static void setPvpEnabled(boolean x) {
		PVP_ENABLED = x;
	}
	
	public static boolean isPvpEnabled() {
		return PVP_ENABLED;
	}

	// TODO
	/**
	 *   
  @EventHandler
  public void onAttack(EntityDamageByEntityEvent event)
  {
    if (((event.getDamager() instanceof Player)) && ((event.getEntity() instanceof Player)))
    {
      Player damaged = (Player)event.getEntity();
      Player damager = (Player)event.getDamager();
      if ((this.pvp.contains(damager.getName())) || (this.pvp.contains(damaged.getName()))) {
        event.setCancelled(true);
      }
    }
    else if (((event.getDamager() instanceof Projectile)) && ((event.getEntity() instanceof Player)))
    {
      Projectile p = (Projectile)event.getDamager();
      if ((p.getShooter() instanceof Player))
      {
        Player damager = (Player)event.getDamager();
        if (this.pvp.contains(damager.getName())) {
          event.setCancelled(true);
        }
      }
    }
  }
  
	 */
}

package com.dkabot.RSPassword;

import org.bukkit.block.Sign;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class RSPlayerListener extends PlayerListener {
	public RSPassword plugin;
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock().getState() instanceof Sign) {	
			if(event.isCancelled()) return;
			if(event.getAction() != Action.RIGHT_CLICK_BLOCK) {
				return;
			}
			Persistance interactClass = plugin.getDatabase().find(Persistance.class).where().ieq("location", event.getClickedBlock().getLocation().toString()).findUnique();
			if (interactClass == null) {
				return;
			}
			// TODO Implement chat intercept for use with this.
			event.getPlayer().sendMessage("This is an RSPassword sign...");
		}
	}
}

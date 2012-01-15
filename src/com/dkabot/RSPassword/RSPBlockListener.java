package com.dkabot.RSPassword;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.SignChangeEvent;
public class RSPBlockListener extends BlockListener {
	public RSPassword plugin;
	
	public void onSignChange(SignChangeEvent event) {
		if(event.isCancelled()) return;
		    if(event.getLine(0).equalsIgnoreCase("[RSPassword]") | event.getLine(0).equalsIgnoreCase("[RSP]")) {
			    if(!event.getPlayer().isOp() && !event.getPlayer().hasPermission("rspassword.create")) {
					event.getPlayer().sendMessage("You do not have permission to create RSPassword signs.");
					return;
			    }
			    if(event.getLine(2).contains(" ")) {
			    	event.getPlayer().sendMessage(ChatColor.RED + "Invalid RSP sign!");
			    	event.setCancelled(true);
			    	return;
			    }
			    String location = event.getBlock().getLocation().toString();
			    Persistance newClass = plugin.getDatabase().find(Persistance.class).where().ieq("location", location).findUnique();
			    if (newClass == null) {
			    	newClass = new Persistance(); 
			    	newClass.setCreatorName(event.getPlayer().getName());
			    	newClass.setLocation(event.getBlock().getLocation().toString());
			    	newClass.setPassword(event.getLine(2));
			    	plugin.getDatabase().save(newClass);
			    	event.setLine(0, ChatColor.RED + event.getLine(1));
			    	String obfupass = "_";
			    	for (int i = 2; (event.getLine(2).length())+1 > i;) {
			    		obfupass = obfupass + "_";
			    		i++;
			    	}
			    	event.setLine(1, ChatColor.GREEN + obfupass);
			    	return;
			    }
			    else {
			    	event.getPlayer().sendMessage("Unexpected error occurred! Please nag an administrator and/or the plugin author.");
			    	return;
			    }
		}
	}
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.isCancelled()) return;
		if(event.getBlock().getState() instanceof Sign){
			Persistance breakClass = plugin.getDatabase().find(Persistance.class).where().eq("location", event.getBlock().getLocation().toString()).findUnique();
			if (breakClass == null) {
				return;
			}
			else {
				if (event.getPlayer().getName() == breakClass.getCreatorName() | event.getPlayer().isOp() | event.getPlayer().hasPermission("RSPassword.breakany")) {
					event.getPlayer().sendMessage(ChatColor.GREEN + "RSPassword sign destroyed!");
					return;
				}
				else {
					event.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to break this RSP sign.");
					event.setCancelled(true);
				}
			}
		}
	}
}

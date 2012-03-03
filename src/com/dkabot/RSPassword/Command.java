package com.dkabot.RSPassword;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
	private RSPassword plugin;
	public Command(RSPassword plugin) {
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		String player = null;
		if (sender instanceof Player) {
			player = sender.getName();
		}
		if (cmd.getName().equalsIgnoreCase("rsp")) {
			if (player == null) {
				sender.sendMessage("This command can only be run by a player.");
				return true;
			}
			else {
				if (sender.isOp() | sender.hasPermission("rspassword.useany")) {
					sender.sendMessage("You can use any RSP sign by right clicking it. Due to this, you cannot use passwords.");
					return true;
				}
				else {
					if (args.length == 0) {
						sender.sendMessage(ChatColor.RED + "No arguments provided.");
						return true;
					}
					else if(args.length > 1) {
		            	sender.sendMessage(ChatColor.RED + "Too many arguments.");
		            	return true;
		            }
					plugin.password.put(sender.getName(), args[0]);
					sender.sendMessage(ChatColor.GREEN + "Ready to use password '" + args[0] + "'.");
					return true;
				}
			}
		}
		return false;
	}
}
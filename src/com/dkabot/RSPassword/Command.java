package com.dkabot.RSPassword;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
	//SuppressWarnings is used because Eclipse thinks plugin is unused even though it is. -_-
	@SuppressWarnings("unused")
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
			else if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "No arguments provided.");
				return false;
			}
			else {
				if (sender.isOp()) {
					sender.sendMessage("You are OP, and thus can use any RSP by right clicking it. You do not need to use passwords.");
					return true;
				}
				else if (sender.hasPermission("RSPassword.useany")) {
					sender.sendMessage("You have the permission to use any RSP by right clicking it. You do not need to use passwords.");
					return true;
				}
				else {
					// TODO Add a command based method to input the password.
	                String password = args[0];
					sender.sendMessage("This command will take the password you just put in, '" +  password + "', and let you use it on a sign!");
					sender.sendMessage(Integer.toString(args.length));
					return true;
				}
			}
		}
		return false;
	}
}
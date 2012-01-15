package com.dkabot.RSPassword;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.bukkit.event.Event;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class RSPassword extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	private Command Com;
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log.info(pdfFile.getName() + " is now disabled.");
	
	}
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled.");
		setupDatabase();
		Com = new Command(this);
		getCommand("rsp").setExecutor(Com);
		PlayerListener plistener = new RSPlayerListener();
		BlockListener blistener = new RSPBlockListener();
	    this.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_INTERACT,plistener, Event.Priority.Low, this);
	    this.getServer().getPluginManager().registerEvent(Event.Type.BLOCK_BREAK, blistener, Event.Priority.Low, this);
	    this.getServer().getPluginManager().registerEvent(Event.Type.SIGN_CHANGE, blistener, Event.Priority.Low, this);
	}

    private void setupDatabase() {
        try {
            getDatabase().find(Persistance.class).findRowCount();
        } catch (PersistenceException ex) {
            this.log.info("Installing database for " + getDescription().getName() + " due to first time usage");
            installDDL();
        }
    }
 
    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(Persistance.class);
        return list;
    }
    
}
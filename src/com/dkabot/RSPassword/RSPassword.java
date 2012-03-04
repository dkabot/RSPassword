package com.dkabot.RSPassword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.bukkit.block.Block;
import org.bukkit.material.Lever;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class RSPassword extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	public Map<String, String> password = new HashMap<String, String>();
	private Command Com;
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log.info(pdfFile.getName() + " is now disabled.");
	
	}
	@Override
	public void onEnable() {
		setupDatabase();
		Com = new Command(this);
		getCommand("rsp").setExecutor(Com);
		new RSPListener(this);
		this.log.info( getDescription().getName() + " version " + getDescription().getVersion() + " is enabled.");
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
    
    public boolean isParsableToInt(String toTest) {
    	try{
    		Integer.parseInt(toTest);
    		return true;
    	}
    	catch(NumberFormatException NFE) {
    		return false;
    	}
    }
    
    public void toggleLever(Block leverTarget) {
    	Lever lever = (Lever)leverTarget.getState().getData();
    	if(lever.isPowered()) leverTarget.setData((byte) (leverTarget.getData() & ~0x8));
    	else leverTarget.setData((byte) (leverTarget.getData() | 0x8));
    	leverTarget.getState().update();
    }
    
    public String obfupass(String pass) {
    	String obfupass = "_";
    	for (int i = 2; (pass.length())+1 > i;) {
    		obfupass = obfupass + "_";
    		i++;
    	}
    	return obfupass;
    }
}
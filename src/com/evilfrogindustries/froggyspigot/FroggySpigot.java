package com.evilfrogindustries.froggyspigot;

import com.evilfrogindustries.froggyspigot.commands.*;
import com.evilfrogindustries.froggyspigot.events.ActivateAlchStone;
import com.evilfrogindustries.froggyspigot.events.ServerWelcome;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

//The controller of the plugin.
public class FroggySpigot extends JavaPlugin {


    @Override
    public void onEnable() {
        createConfig();

        //Event listeners
        getServer().getPluginManager().registerEvents(new ServerWelcome(this), this);
        getServer().getPluginManager().registerEvents(new ActivateAlchStone(), this);

        //Register commands
        this.getCommand("frogkit").setExecutor(new FrogKit(this));
        this.getCommand("cgkit").setExecutor(new ConfigKit(this));
        this.getCommand("getalchstone").setExecutor(new getAlchStone(this));

    }

    @Override
    public void onDisable() {

    }

    //TODO Figure out why this works
    //Creates the config file
    public void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }

            File config = new File(getDataFolder(), "config.yml");
            if (!config.exists()) {
                getLogger().info("Config.yml not found, creating.");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Adds default values to the FileConfiguration object, specifies that they should be saved, and saves.
        } finally {
            String name = "BONE";
            getConfig().addDefault("Awesome", true);
            getConfig().addDefault("frogkit amount", 5);
            getConfig().addDefault("configkit item", name);
            getConfig().addDefault("configkit value", 10);
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }


}



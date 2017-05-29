package com.evilfrogindustries.froggyspigot;

/**
 * Created by Lbadv on 5/29/2017.
 */


import com.evilfrogindustries.froggyspigot.commands.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by Lbadv on 5/28/2017.
 */
public class FroggySpigot extends JavaPlugin {


    @Override
    public void onEnable() {
        createConfig();

        //Event listener for player joining
        getServer().getPluginManager().registerEvents(new Listener() {
            //Event handler for this class, effects the player joining
            @EventHandler
            public void onPlayerJoin(PlayerJoinEvent event) {
                Player player = event.getPlayer();
                if (getConfig().getBoolean("Awesome")) {
                    player.sendMessage("You are indeed awesome.");
                } else {
                    player.sendMessage("You suck.");
                }
            }
        }, this);

        //Register commands
        this.getCommand("frogkit").setExecutor(new FrogKit(this));
        this.getCommand("configkit").setExecutor(new ConfigKit(this));

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



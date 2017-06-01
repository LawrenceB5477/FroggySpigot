package com.evilfrogindustries.froggyspigot.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;


//TODO as the player levels make the text readable
//Creates a simple greeting
public class ServerWelcome implements Listener{
    private JavaPlugin plugin;
    private File  alchemyStats;
    private FileConfiguration alchemyStateMemory;

    public ServerWelcome(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    //Event handler for this class, effects the player joining.
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //Create a file for the player if it does not exist. If it does, load it.

        alchemyStats = new File(plugin.getDataFolder(), player.getUniqueId().toString() + ".yml");
        alchemyStateMemory = YamlConfiguration.loadConfiguration(alchemyStats);

        if(!alchemyStats.exists()) {
            player.sendMessage(ChatColor.MAGIC + "Another Alchemist Joins.");
                try {
                    //Create player stats
                    alchemyStateMemory.createSection("AlchLevel");
                    alchemyStateMemory.set("AlchLevel", 1);

                    alchemyStateMemory.createSection("UUID");
                    alchemyStateMemory.set("UUID", player.getUniqueId().toString());

                    alchemyStateMemory.save(alchemyStats);
                } catch (IOException e) {
                    e.printStackTrace();
                    player.sendMessage("There is an issue.");
                }
        } else {
            player.sendMessage("Welcome back.");
            int level = (int)alchemyStateMemory.get("AlchLevel");
            player.sendMessage("Player Level: " + level);
        }
    }


}


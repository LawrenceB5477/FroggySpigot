package com.evilfrogindustries.froggyspigot.commands;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

//Basic command, gives you lilypads
public class FrogKit implements CommandExecutor {
    //Get the instance of the main class. Used to read the config file.
    private JavaPlugin main;

    public FrogKit(JavaPlugin main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You need to be a player.");
            return true;
        }
        Player player = (Player) commandSender;

        //Get lilypad amount assigned in the config file and give to player.
        ItemStack lilyPads = new ItemStack(Material.WATER_LILY);
        lilyPads.setAmount(main.getConfig().getInt("frogkit amount"));
        player.getInventory().addItem(lilyPads);
        player.sendMessage("Ribbit Ribbit");

        return true;


    }
}

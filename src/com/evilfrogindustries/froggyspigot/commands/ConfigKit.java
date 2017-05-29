package com.evilfrogindustries.froggyspigot.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

//TODO add a help command, and mutli word support for set
/**
 * Creates a kit based on the config.yml, and allows the player to edit the item it gives
 */
public class ConfigKit implements CommandExecutor{
    private JavaPlugin main;

    public ConfigKit(JavaPlugin main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You need to be a player to run this command.");
            return true;
        }

        Player player = (Player)commandSender;

            //Give the item with the amount to give in the file config
        if (strings.length == 0) {
            Material item = Material.matchMaterial(main.getConfig().getString("configkit item"));
            int value = main.getConfig().getInt("configkit value");
            ItemStack items = new ItemStack(item, value);

            player.getInventory().addItem(items);
            return true;

            //Lets the player specify how many items they want
        } else if (strings.length > 0 && (!strings[0].equalsIgnoreCase("set"))) {
            try {
                Material item = Material.matchMaterial(main.getConfig().getString("configkit item"));
                ItemStack itemstack = new ItemStack(item, Integer.parseInt(strings[0]));
                player.getInventory().addItem(itemstack);
                return true;
            } catch (NumberFormatException e) {
                player.sendMessage("Specify a number of items you want.");
                return true;
            }

            //Set command called without any arguments
        } else if (strings.length == 1 && strings[0].equalsIgnoreCase("set")) {
            player.sendMessage("You must specify what you want to set the item to.");
            return true;

            //Set the item that is given by the command
        } else if (strings.length > 1 && strings[0].equalsIgnoreCase("set")) {
            try {
                Material item = Material.matchMaterial(strings[1]);
                if (item == null) {
                    throw new IllegalArgumentException();
                }
                String itemName = item.toString();
                main.getConfig().set("configkit item", itemName);
                player.sendMessage("Configkit item set to: " + itemName);
                return true;
            } catch (IllegalArgumentException e) {
                player.sendMessage("The command argument is not an item.");
                return true;
            }
        }

        //Default message
        player.sendMessage("A system error occurred.");
        return true;
    }
}

package com.evilfrogindustries.froggyspigot.commands;

import com.evilfrogindustries.froggyspigot.FroggySpigot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

//Creates a piece of coal that acts as your alchemy stone
public class getAlchStone implements CommandExecutor {
    private static ItemStack alchRock;
    private JavaPlugin plugin;
    public getAlchStone(FroggySpigot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player to return this command.");
            return true;
        }

        Player player = (Player)commandSender;

        alchRock = new ItemStack(Material.DIAMOND, 1);
        ItemMeta alchRockMeta = alchRock.getItemMeta();
        alchRockMeta.setDisplayName(ChatColor.DARK_RED + "Alchemy Stone");
        alchRockMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> lore = new ArrayList<String>();
        lore.add("An alchemist stone.");
        lore.add("Use to transmute objects.");
        alchRockMeta.setLore(lore);
        alchRock.setItemMeta(alchRockMeta);
        alchRock.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 20);

        player.getInventory().addItem(alchRock);
        return true;
    }

    //Getter
    public static ItemStack getAlchRock() {
        return alchRock;
    }
}

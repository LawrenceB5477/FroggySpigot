package com.evilfrogindustries.froggyspigot.events;

import com.evilfrogindustries.froggyspigot.commands.getAlchStone;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


//Event for each time an alchemy stone is right clicked.
public class ActivateAlchStone implements Listener{
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onStoneRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        //Sees if the player is right clicking the alchemy stone
        if ((e.getAction() ==Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK ) && e.getItem().isSimilar(getAlchStone.getAlchRock()) ) {
            player.sendMessage("It's working.");
        }


    }
}

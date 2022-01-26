package com.killers0992.kpglobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.List;

public class EventListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!KpgLobby.config.getBoolean("teleport_on_fall")){
            return;
        }

        if (event.getPlayer().getLocation().getY() <= KpgLobby.config.getInt("teleport_on_fall_below_y")) {
            List<Float> cg = KpgLobby.config.getFloatList("teleport_on_fall_teleport");
            Location l = new Location(Bukkit.getWorld(KpgLobby.config.getString("teleport_on_fall_world")), cg.get(0), cg.get(1), cg.get(2));
            event.getPlayer().teleport(l);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if (!KpgLobby.config.getBoolean("chat_disabled")){
            return;
        }

        if (event.getPlayer().hasPermission("kpgplugin.chatbypass")) {
            return;
        } else if (event.getPlayer().hasPermission("kpgplugin.*")) {
            return;
        }

        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', KpgLobby.config.getString("chat_disabled_message")));
        event.setCancelled(true);
    }
}

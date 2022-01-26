package com.killers0992.kpglobby.commands;

import com.killers0992.kpglobby.KpgLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class ReloadCommand implements CommandExecutor {
    public static KpgLobby plugin;
    public ReloadCommand(KpgLobby instance)
    {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reload-kpglobby")) {
            if (args.length > 0) {
                sender.sendMessage(ChatColor.RED + "Too many arguments!");
                return false;
            }
            PluginDescriptionFile pdffile = plugin.getDescription();
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (hasReload(player)) {
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.AQUA + pdffile.getFullName() + ChatColor.GRAY + " reloaded files succesfully");
                    System.out.println("[KpgLobby] " + (sender.getName() + " reloaded" + (pdffile.getFullName())));
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have access to that.");
                }
            } else {
                plugin.reloadConfig();
                plugin.getLogger().info("[KpgLobby] " + pdffile.getName() + " was succesfully reloaded");
            }
            return true;
        }
        return false;
    }

    public boolean hasReload (Player player){
        if (player.hasPermission("kpgplugin.reload")) {
            return true;
        } else if (player.hasPermission("kpgplugin.*")) {
            return true;
        }
        return false;
    }
}

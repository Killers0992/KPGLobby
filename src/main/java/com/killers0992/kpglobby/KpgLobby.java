package com.killers0992.kpglobby;

import com.killers0992.kpglobby.commands.ReloadCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class KpgLobby extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        this.getCommand("reload-kpglobby").setExecutor(new ReloadCommand(this));

        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
    }
}

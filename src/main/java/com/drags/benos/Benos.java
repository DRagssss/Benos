package com.drags.benos;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Benos extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getLogger().info("Benos has been enabled.");
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info("Benos has been disabled.");
    }
}

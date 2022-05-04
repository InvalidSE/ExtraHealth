package me.invalidstateexception.extrahealth.extrahealth;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExtraHealth extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("ExtraHealth by InvalidSE Enabled (InvalidSE#8824 for issues)");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

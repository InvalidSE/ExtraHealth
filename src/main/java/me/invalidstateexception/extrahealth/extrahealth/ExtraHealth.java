package me.invalidstateexception.extrahealth.extrahealth;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExtraHealth extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("ExtraHealth by InvalidSE Enabled (InvalidSE#8824 on Discord)");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new Listeners(), this);

        RegisterCustomCommands();
        RegisterCustomRecipes();
    }

    private void RegisterCustomCommands() {
        this.getCommand("addhearts").setExecutor(new CustomCommands());
        this.getCommand("removehearts").setExecutor(new CustomCommands());
        this.getCommand("checkhearts").setExecutor(new CustomCommands());
        this.getCommand("sethearts").setExecutor(new CustomCommands());
    }

    private void RegisterCustomRecipes() {
        NamespacedKey heartItemKey = new NamespacedKey(this, "heartRecipe");
        ShapedRecipe heartItemRecipe = new ShapedRecipe(heartItemKey, CustomItems.getHeartItem());
        heartItemRecipe.shape("NPN", "PEP", "NPN");
        heartItemRecipe.setIngredient('E', Material.ELYTRA);
        heartItemRecipe.setIngredient('P', Material.ENDER_PEARL);
        heartItemRecipe.setIngredient('N', Material.NETHERITE_SCRAP);
        Bukkit.addRecipe(heartItemRecipe);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

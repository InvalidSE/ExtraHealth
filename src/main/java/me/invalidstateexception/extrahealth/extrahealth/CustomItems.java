package me.invalidstateexception.extrahealth.extrahealth;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CustomItems {
    public static ItemStack getHeartItem(){
        ItemStack HeartItem = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta HeartMeta = HeartItem.getItemMeta();
        ArrayList<String> HeartLore = new ArrayList<>();
        HeartLore.add(ChatColor.RED + "Extra Heart Container");
        HeartLore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Added By InvalidSE");
        assert HeartMeta != null; // Only here because of IntelliJ yelling
        HeartMeta.setLore(HeartLore);
        HeartMeta.setDisplayName(ChatColor.RED + "Heart Container");
        HeartItem.setItemMeta(HeartMeta);
        return HeartItem;
    }
}

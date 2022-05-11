package me.invalidstateexception.extrahealth.extrahealth;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Listeners implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDeath(PlayerDeathEvent event){
        Player player = (Player) event.getEntity().getPlayer();
        double playerHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

        if(player.getKiller() != null) {
            Player killer = (Player) player.getKiller();
            double killerHealth = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            if(playerHealth <= 6){
                killer.sendMessage(ChatColor.RED + "You did not gain another heart as " + player.getDisplayName() + " was already on minimum hearts");
                return;
            }
            if(killerHealth >= 40){
                killer.sendMessage(ChatColor.RED + "You cannot have more than 20 hearts!");
                return;
            }
            killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(killerHealth + 2);
        }

        if(playerHealth <= 6){
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(6);
        } else {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerHealth - 2);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        // System.out.println("[DEBUG] Triggered Event");
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            ItemStack heartItem = CustomItems.getHeartItem();
            ItemStack mainHand = player.getInventory().getItemInMainHand();

            if(!mainHand.getType().equals(Material.BLAZE_POWDER)) return;
            if(!mainHand.getItemMeta().hasLore()) return;
            // System.out.println("[DEBUG] Got to stage 2");
            if(!mainHand.getItemMeta().getLore().contains(ChatColor.RED + "Extra Heart Container")) return;
            // System.out.println("[DEBUG] Got to stage 3");
            if(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() >= 40){
                player.sendMessage(ChatColor.RED + "You cannot have more than 20 hearts!");
                return;
            }
            // System.out.println("[DEBUG] Got to stage 4");

            double playerHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerHealth + 2);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

            if(mainHand.getAmount() >= 2){
                mainHand.setAmount(mainHand.getAmount() - 1);
                player.getInventory().setItemInMainHand(mainHand);
            } else {
                player.getInventory().setItemInMainHand(null);
            }

        }
    }
}

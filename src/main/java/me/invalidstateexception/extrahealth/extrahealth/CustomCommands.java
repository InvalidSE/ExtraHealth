package me.invalidstateexception.extrahealth.extrahealth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public class CustomCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Double healthDiff;
        Double health;
        Double newHealth;
        Player target;

        switch(label) {
            case "addhearts":
                if (args.length <= 1) {
                    sender.sendMessage(ChatColor.RED + "Please specify player and health number");
                    return false;
                }
                target = Bukkit.getPlayer(args[0]);
                if (checkPlayerArg(sender, target)) return false;
                health = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

                try {
                    healthDiff = Double.parseDouble(args[1]);
                } catch (Exception exception) {
                    sender.sendMessage(ChatColor.RED + "Please input health number into command");
                    return false;
                }

                newHealth = health + healthDiff;

                target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newHealth);
                sender.sendMessage(ChatColor.GREEN + "Sucessfully given " + target.getDisplayName() + " " + healthDiff + " Max HP");
                break;

            case "removehearts":
                if (args.length <= 1) {
                    sender.sendMessage(ChatColor.RED + "Please specify player and health number");
                    return false;
                }
                target = Bukkit.getPlayer(args[0]);
                if (checkPlayerArg(sender, target)) return false;
                health = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

                try {
                    healthDiff = Double.parseDouble(args[1]);
                } catch (Exception exception) {
                    sender.sendMessage(ChatColor.RED + "Please input health number into command");
                    return false;
                }

                newHealth = health - healthDiff;
                if(newHealth < 6){newHealth = 6.0;}

                target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newHealth);
                sender.sendMessage(ChatColor.GREEN + "Sucessfully taken " + healthDiff + " Max HP from" + target.getDisplayName());
                break;
            case "checkhearts":
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Please specify player");
                    return false;
                }
                target = Bukkit.getPlayer(args[0]);
                if (checkPlayerArg(sender, target)) return false;
                health = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                sender.sendMessage(ChatColor.GREEN + "" + target.getDisplayName() + " has " + health + " Max HP");
                break;
            case "sethearts":
                if (args.length <= 1) {
                    sender.sendMessage(ChatColor.RED + "Please specify player and health number");
                    return false;
                }
                target = Bukkit.getPlayer(args[0]);
                if (checkPlayerArg(sender, target)) return false;

                try {
                    newHealth = Double.parseDouble(args[1]);
                } catch (Exception exception) {
                    sender.sendMessage(ChatColor.RED + "Please input health number into command");
                    return false;
                }

                if(newHealth < 2){newHealth = 2.0;}

                target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newHealth);
                sender.sendMessage(ChatColor.GREEN + "Sucessfully set " + target.getDisplayName() + "'s Max HP to " + newHealth);
                break;
        }
        return true;
    }

    private boolean checkPlayerArg(CommandSender sender, Player target) {
        if(target == null){
            sender.sendMessage(ChatColor.RED + "Player not found");
            return true;
        }
        if(!target.isOnline()){
            sender.sendMessage(ChatColor.RED + "Player not found");
            return true;
        }
        return false;
    }

}

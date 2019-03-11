package com.github.dwesolowski.clearinv;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearInv extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        registerMetrics();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (getConfig().getBoolean("Enabled")) {
            Player p = e.getPlayer();
            if (!p.hasPermission("clearinv.keep")) {
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setArmorContents(null);
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if (getConfig().getBoolean("Enabled")) {
            Player p = e.getPlayer();
            if (!p.hasPermission("clearinv.keep")) {
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setArmorContents(null);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Command not allowed in console, must be used in-game only!");
            return true;
        }
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("clearinv")) {
            if (!p.hasPermission("clearinv.clear")) {
                p.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
                return true;
            }
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.sendMessage(ChatColor.GREEN + "Your inventory has been cleared!");
        } else if (cmd.getName().equalsIgnoreCase("clearinvreload")) {
            if (!p.hasPermission("clearinv.reload")) {
                p.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
                return true;
            }
            reloadConfig();
            p.sendMessage(ChatColor.GOLD + "Configuration Reloaded!");
        }
        return true;
    }

    private void registerMetrics() {
        final MetricsLite metrics = new MetricsLite(this);
    }
}

package com.github.dwesolowski.clearinv;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearInv extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        registerMetrics();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().getInventory().clear();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        e.getPlayer().getInventory().clear();
    }

    private void registerMetrics() {
        final MetricsLite metrics = new MetricsLite(this);
    }
}

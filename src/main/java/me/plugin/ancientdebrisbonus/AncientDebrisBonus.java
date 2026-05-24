package me.plugin.ancientdebrisbonus;

import org.bukkit.plugin.java.JavaPlugin;

public class AncientDebrisBonus extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AncientDebrisListener(this), this);
        getLogger().info("AncientDebrisBonus включён! Теперь Ancient Debris даёт 3 Netherite Scrap.");
    }

    @Override
    public void onDisable() {
        getLogger().info("AncientDebrisBonus отключён.");
    }
}

package com.minegun.demo;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.minestom.server.adventure.bossbar.BossBarManager;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoadedEvent;

public class HealthManagement {
    BossBar healthBar;
    public void bossBarMaker(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerLoadedEvent.class, event -> {
            BossBarManager bossBarManager = new BossBarManager();

            healthBar = BossBar.bossBar(
                    Component.text("Health"),
                    1f,
                    BossBar.Color.PINK,
                    BossBar.Overlay.PROGRESS
            );

            bossBarManager.addBossBar(event.getPlayer(), healthBar);
        });
    }
    public void damage(Player player, double hitDamage) {
        healthBar.progress((float) (healthBar.progress() - hitDamage));
    }
}

package com.minegun.demo;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.minestom.server.adventure.bossbar.BossBarManager;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoadedEvent;

public class HealthManagement {
    public void bossBarMaker(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerLoadedEvent.class, event -> {
            BossBarManager bossBarManager = new BossBarManager();

            BossBar healthBar = BossBar.bossBar(
                    Component.text("Health"),
                    1f,
                    BossBar.Color.RED,
                    BossBar.Overlay.PROGRESS
            );
            BossBar shieldBar = BossBar.bossBar(
                    Component.text("Shield"),
                    1f,
                    BossBar.Color.BLUE,
                    BossBar.Overlay.PROGRESS
            );
            bossBarManager.addBossBar(event.getPlayer(), healthBar);
            bossBarManager.addBossBar(event.getPlayer(), shieldBar);
        });
    }
}

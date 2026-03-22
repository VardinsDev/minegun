package com.minegun;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.minestom.server.adventure.bossbar.BossBarManager;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoadedEvent;
import net.minestom.server.event.player.PlayerTickEvent;

import java.util.HashMap;
import java.util.UUID;

public class HealthManagement {
    BossBarManager bossBarManager = new BossBarManager();

    HashMap<UUID, BossBar> healthBars = new HashMap<>();
    HashMap<UUID, BossBar> shieldBars = new HashMap<>();

    HashMap<UUID, Double> healthAmounts = new HashMap<>();
    HashMap<UUID, Double> shieldAmounts = new HashMap<>();

    BossBar healthBar;
    BossBar shieldBar;

    public void bossBarMaker(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerLoadedEvent.class, event -> {

            healthBar = BossBar.bossBar(
                    Component.text("Health"),
                    1f,
                    BossBar.Color.GREEN,
                    BossBar.Overlay.PROGRESS
            );

            shieldBar = BossBar.bossBar(
                    Component.text("Shield"),
                    1f,
                    BossBar.Color.BLUE,
                    BossBar.Overlay.PROGRESS
            );

            bossBarManager.addBossBar(event.getPlayer(), healthBar);
            bossBarManager.addBossBar(event.getPlayer(), shieldBar);

            healthBars.put(event.getPlayer().getUuid(), healthBar);
            shieldBars.put(event.getPlayer().getUuid(), shieldBar);

            healthAmounts.put(event.getPlayer().getUuid(), 100.0);
            shieldAmounts.put(event.getPlayer().getUuid(), 100.0);
      });
    }
    public void damage(Player player, double hitDamage) {
        double shield = getShield(player);
        double health = getHealth(player);
        double overShield = 0;

        if (shield < hitDamage) {
            shield = 0;
            overShield = shield - hitDamage;
            health -= overShield;
        } else if (shield == hitDamage) {
            shield = 0;
        } else {
            shield -= hitDamage;
        }

        healthAmounts.put(player.getUuid(), health);
        shieldAmounts.put(player.getUuid(), shield);
    }

    public void tickUpdate(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerTickEvent.class, event -> {
           BossBar playerHealthBar = healthBars.get(event.getPlayer().getUuid());
           BossBar playerShieldBar = shieldBars.get(event.getPlayer().getUuid());

           if (playerHealthBar == null) {
               return;
           }

           if (shieldAmounts == null) {
               return;
           }
           playerHealthBar.progress((float) (healthAmounts.get(event.getPlayer().getUuid()) / 100));
           playerShieldBar.progress((float) (shieldAmounts.get(event.getPlayer().getUuid()) / 100));
        });
    }

    public double getHealth(Player player) {
        return healthAmounts.get(player.getUuid());
    }

    public double getShield(Player player) {
        return shieldAmounts.get(player.getUuid());
    }
}

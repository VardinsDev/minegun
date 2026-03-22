package com.minegun;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import net.minestom.server.adventure.bossbar.BossBarManager;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoadedEvent;
import net.minestom.server.event.player.PlayerTickEvent;
import net.minestom.server.potion.Potion;
import net.minestom.server.potion.PotionEffect;
import net.minestom.server.tag.Tag;

import java.time.Duration;
import java.util.HashMap;
import java.util.UUID;

public class HealthManagement {
    BossBarManager bossBarManager = new BossBarManager();

    HashMap<UUID, BossBar> healthBars = new HashMap<>();
    HashMap<UUID, BossBar> shieldBars = new HashMap<>();

    Tag<Double> healthTag = Tag.Double("health");
    Tag<Double> shieldTag = Tag.Double("shield");

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

            event.getPlayer().setTag(healthTag, 100.0);
            event.getPlayer().setTag(shieldTag, 100.0);
      });
    }
    public void damage(Player player, double hitDamage) {
        double shield = getShield(player);
        double health = getHealth(player);
        double overShield;

        if (shield < hitDamage) {
            shield = 0;
            overShield = shield - hitDamage;
            health += overShield;
        } else if (shield == hitDamage) {
            shield = 0;
        } else {
            shield -= hitDamage;
        }

        if (health <= 0) {
            player.teleport(player.getRespawnPoint());
            player.showTitle(
                    Title.title(
                            Component.text("You DIED").color(NamedTextColor.RED),
                            Component.text(""),
                            Title.Times.times(Duration.ofMillis(500), Duration.ofSeconds(3), Duration.ofMillis(500))
                    )
            );
            minegunLogger.info(player.getUsername() + " has been killed");
            player.addEffect(new Potion(PotionEffect.BLINDNESS, 1, 100));
            health = 100;
            shield = 100;
        }

        player.setTag(healthTag, health);
        player.setTag(shieldTag, shield);
    }

    public void tickUpdate(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerTickEvent.class, event -> {
           BossBar playerHealthBar = healthBars.get(event.getPlayer().getUuid());
           BossBar playerShieldBar = shieldBars.get(event.getPlayer().getUuid());

           if (playerHealthBar == null) {
               return;
           }

           playerHealthBar.progress((float) (event.getPlayer().getTag(healthTag) / 100));
           playerShieldBar.progress((float) (event.getPlayer().getTag(shieldTag) / 100));
        });
    }

    public double getHealth(Player player) {
        return player.getTag(healthTag);
    }

    public double getShield(Player player) {
        return player.getTag(shieldTag);
    }
}

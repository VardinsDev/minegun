package com.minegun;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.component.DataComponents;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerHand;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.server.play.ParticlePacket;

import java.util.List;

public class Rifle {
    public static void givePlayer(Player player) {
        ItemStack item = ItemStack.builder(Material.WOODEN_HOE)
                .set(DataComponents.CUSTOM_NAME, Component.text("Rifle", NamedTextColor.YELLOW))
                .set(DataComponents.LORE, List.of(Component.text("Custom Made Weapon"), Component.text("By: VardinsDev")))
                .build();
        player.setItemInHand(PlayerHand.MAIN, item);
    }
    public static void handler(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerUseItemEvent.class, event -> {
            if (event.getPlayer().getItemInHand(PlayerHand.MAIN).equals(ItemStack.of(Material.WOODEN_HOE))) {
                for (int i = 0; i < 100; i++) {
                    //ParticlePacket gunTrail = new ParticlePacket();
                }
            }
        });
    }
}

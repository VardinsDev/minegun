package com.minegun.demo;

import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;

public class Main {
    static void main() {
        MinecraftServer minecraftServer = MinecraftServer.init(new Auth.Online());

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        instanceContainer.setGenerator(unit -> unit.modifier().fillHeight(39, 40, Block.STONE));

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();

        eventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
           event.setSpawningInstance(instanceContainer);
           event.getPlayer().setRespawnPoint(new Pos(0,42, 0));
        });

        minecraftServer.start("0.0.0.0", 25565);
    }
}

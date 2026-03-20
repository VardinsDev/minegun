package com.minegun.demo;

import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerGameModeRequestEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;

// This is how you make a comment you idiot.
public class Main {
    static void main() {
        MinecraftServer minecraftServer = MinecraftServer.init(new Auth.Online());

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        instanceContainer.setGenerator(unit -> unit.modifier().fillHeight(39, 40, Block.STONE));
        instanceContainer.setChunkSupplier(LightingChunk::new); // adds lighting

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();

        eventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
           event.setSpawningInstance(instanceContainer);
           event.getPlayer().setPermissionLevel(4);
            System.out.println(event.getPlayer().getPermissionLevel());
           event.getPlayer().setRespawnPoint(new Pos(0,42, 0));
        });

        eventHandler.addListener(PlayerGameModeRequestEvent.class, event -> {
           if (event.getPlayer().getPermissionLevel() >= 2 ) {
               event.getPlayer().setGameMode(event.getRequestedGameMode());
           }
        });

        minecraftServer.start("0.0.0.0", 25565);
    }
}

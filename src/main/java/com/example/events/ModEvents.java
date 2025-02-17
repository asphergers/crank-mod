package com.example.events;

import com.example.Crank;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.world.ServerWorld;

public class ModEvents {
    public void initalize() {
        ServerLifecycleEvents.SERVER_STARTED.register(s -> Crank.server = s);
    }
}

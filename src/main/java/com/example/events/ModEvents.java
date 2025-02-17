package com.example.events;

import com.example.Crank;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public  class ModEvents {
    public static void initialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(s -> Crank.server = s);
    }
}

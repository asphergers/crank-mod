package com.example.events;

import com.example.state.BlackHoleState;
import com.example.state.CrankState;

import java.util.ArrayList;

public class BlackHoleLogic {
    public static void updateBlackHoles(int currentTick) {
        ArrayList<BlackHoleState> blackHoles = CrankState.blackHoles;

        for (int i = 0; i < blackHoles.size(); i++) {
            BlackHoleState current = blackHoles.get(i);
            if (currentTick > current.expireTick) {
                current.hole.discard();
            }
        }

        CrankState.blackHoles.removeIf(request -> (currentTick > request.expireTick));
    }
}

package com.example.state;

import com.example.entity.custom.BlackHoleEntity;
import net.minecraft.item.ItemStack;

public class BlackHoleState {
    public BlackHoleState(BlackHoleEntity hole, int expireTick) {
        this.hole = hole;
        this.expireTick = expireTick;
    }

    BlackHoleEntity hole;
    int expireTick;
}

package net.mrqx.style_mod.client;

import net.minecraft.nbt.CompoundTag;

public class ClientData {
    private static CompoundTag Data = new CompoundTag();

    public static CompoundTag getData() {
        return Data;
    }

    public static void setData(CompoundTag data) {
        Data = data;
    }
}

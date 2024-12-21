package net.mrqx.style_mod.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import net.mrqx.style_mod.client.ClientData;

public class SyncDataMessage {
    public CompoundTag data;

    public static SyncDataMessage decode(FriendlyByteBuf buf) {
        SyncDataMessage msg = new SyncDataMessage();
        msg.data = buf.readAnySizeNbt();
        return msg;
    }

    public static void encode(SyncDataMessage msg, FriendlyByteBuf buf) {
        buf.writeNbt(msg.data);
    }

    static public void handle(SyncDataMessage msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientData.setData(msg.data);
        });
        ctx.get().setPacketHandled(true);
    }
}

package net.mrqx.style_mod.network;

import java.util.function.Supplier;

import net.mrqx.style_mod.utils.DevilTriggerUtils;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class DevilTriggerMessage {
    public Boolean active;

    public static DevilTriggerMessage decode(FriendlyByteBuf buf) {
        DevilTriggerMessage msg = new DevilTriggerMessage();
        msg.active = buf.readBoolean();
        return msg;
    }

    public static void encode(DevilTriggerMessage msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.active);
    }

    public static void handle(DevilTriggerMessage msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            if (DevilTriggerUtils.isDTActive(sender)) {
                DevilTriggerUtils.setDTActive(sender, false);
            } else {
                DevilTriggerUtils.setDTActive(sender, true);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

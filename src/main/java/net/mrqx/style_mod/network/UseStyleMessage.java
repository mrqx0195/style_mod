package net.mrqx.style_mod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import net.mrqx.style_mod.utils.StyleUtils;

public class UseStyleMessage {
    public String style;

    public static UseStyleMessage decode(FriendlyByteBuf buf) {
        UseStyleMessage msg = new UseStyleMessage();
        msg.style = buf.readUtf();
        return msg;
    }

    public static void encode(UseStyleMessage msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.style);
    }

    public static void handle(UseStyleMessage msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            if (StyleUtils.getActivatingStyle(sender).equals(msg.style)) {
                StyleUtils.setIsStyleKeyPressed(sender, true);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

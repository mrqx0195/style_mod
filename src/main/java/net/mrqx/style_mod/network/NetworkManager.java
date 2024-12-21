package net.mrqx.style_mod.network;

import net.mrqx.style_mod.StyleMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkManager {

        private static final String PROTOCOL_VERSION = "1";

        public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
                        new ResourceLocation(StyleMod.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
                        PROTOCOL_VERSION::equals);

        public static void register() {
                int id = 0;
                INSTANCE.registerMessage(id++, UseStyleMessage.class, UseStyleMessage::encode, UseStyleMessage::decode,
                                UseStyleMessage::handle);
                INSTANCE.registerMessage(id++, SyncDataMessage.class, SyncDataMessage::encode, SyncDataMessage::decode,
                                SyncDataMessage::handle);
                INSTANCE.registerMessage(id++, DevilTriggerMessage.class, DevilTriggerMessage::encode,
                                DevilTriggerMessage::decode, DevilTriggerMessage::handle);
        }

}

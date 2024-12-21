package net.mrqx.style_mod.registry.styles;

import net.mrqx.style_mod.StyleMod;
import net.mrqx.style_mod.config.StyleConfig;
import net.mrqx.style_mod.event.StyleEvent;
import net.mrqx.style_mod.network.NetworkManager;
import net.mrqx.style_mod.network.SyncDataMessage;
import net.mrqx.style_mod.utils.StyleUtils;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.PacketDistributor;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

@EventBusSubscriber
public class Style {
    public static final ResourceKey<Registry<Style>> REGISTRY_KEY = ResourceKey
            .createRegistryKey(new ResourceLocation(StyleMod.MODID, "style"));

    @SubscribeEvent
    public static void onStyleTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START || !event.side.isServer())
            return;

        Player player = event.player;

        CompoundTag tag = StyleUtils.getStyleTag(player);
        if (!tag.contains("MaxDTGauge")) {
            tag.putInt("MaxDTGauge", StyleConfig.MAX_DT_GAUGE.get() * 1000);
            StyleUtils.setStyleTag(player, tag);
        }
        if (!tag.contains("DTGauge")) {
            tag.putInt("DTGauge", 0);
            StyleUtils.setStyleTag(player, tag);
        }
        if (tag.getString("Style").isEmpty()) {
            tag.putString("Style", NullStyle.STYLE_ID.location().toString());
            StyleUtils.addStyle(NullStyle.STYLE_ID.location(), player);
            StyleUtils.setStyleTag(player, tag);
        }
        if (player instanceof ServerPlayer serverPlayer) {
            SyncDataMessage msg = new SyncDataMessage();
            msg.data = StyleUtils.getStyleTag(serverPlayer);
            NetworkManager.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), msg);
        }
    }

    @SubscribeEvent
    public static void styleChange(StyleEvent.StyleChangeEvent event) {
        Player player = event.getEntity();
        CompoundTag tag = StyleUtils.getStyleTag(player);
        tag.putString("Style", event.getNewStyle());
        StyleUtils.setStyleTag(player, tag);
    }
}

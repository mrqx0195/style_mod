package net.mrqx.style_mod.client;

import net.mrqx.style_mod.network.NetworkManager;
import net.mrqx.style_mod.network.UseStyleMessage;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class UseStyleHandler {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onPlayerPostTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END)
            return;

        if (!(event.player instanceof LocalPlayer))
            return;

        if (StyleKeyMappings.KEY_USE_STYLE.isDown()) {
            UseStyleMessage msg = new UseStyleMessage();
            NetworkManager.INSTANCE.sendToServer(msg);
        }
    }
}

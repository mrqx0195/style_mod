package net.mrqx.style_mod.common;

import net.mrqx.style_mod.utils.DevilTriggerUtils;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class DevilTriggerHandler {
    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START)
            return;
        Player player = event.player;
        if (DevilTriggerUtils.isDTActive(player)) {
            DevilTriggerUtils.setDTGauge(player, DevilTriggerUtils.getDTGauge(player) - 360 / 20);
            if (DevilTriggerUtils.getDTGauge(player) <= 0) {
                DevilTriggerUtils.setDTActive(player, false);
            }
        }
    }

    @SubscribeEvent
    public static void livingDamageEvent(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (DevilTriggerUtils.isDTActive(player)) {
                event.setAmount(event.getAmount() * 0.7f);
            }
        }
    }

    @SubscribeEvent
    public static void livingHurtEvent(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (DevilTriggerUtils.isDTActive(player)) {
                event.setAmount(event.getAmount() * 1.3f);
            }
        }
    }
}

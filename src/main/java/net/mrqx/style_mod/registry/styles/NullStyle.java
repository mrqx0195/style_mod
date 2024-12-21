package net.mrqx.style_mod.registry.styles;

import net.mrqx.style_mod.StyleMod;
import net.mrqx.style_mod.utils.StyleUtils;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
// import net.minecraft.nbt.CompoundTag;
// import net.minecraft.world.item.ItemStack;

@EventBusSubscriber
public class NullStyle extends Style {
    public static final ResourceKey<Registry<Style>> STYLE_ID = ResourceKey
            .createRegistryKey(new ResourceLocation(StyleMod.MODID, "null_style"));

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START)
            return;
        Player player = event.player;
        if (StyleUtils.getActivatingStyle(player).equals(STYLE_ID.location().toString())
                && !StyleUtils.isUsingStyle(player)
                && StyleUtils.isStyleKeyPressed(player)) {
            StyleUtils.setIsUsingStyle(player, true);

            // CompoundTag tag = new CompoundTag();
            // byte count = 1;
            // tag.putString("id", "apple");
            // tag.putByte("Count", count);
            // player.addItem(ItemStack.of(tag));
            // StyleMod.LOGGER.debug(player.getName().toString() + "use NullStyle at tick" +
            // player.level().getGameTime());

        } else if (StyleUtils.getActivatingStyle(player).equals(STYLE_ID.location().toString())
                && StyleUtils.isUsingStyle(player)
                && !StyleUtils.isStyleKeyPressed(player)) {
            // StyleMod.LOGGER.debug(player.getName().toString() + "not using NullStyle at
            // tick" +
            // player.level().getGameTime());
            StyleUtils.setIsUsingStyle(player, false);
        }
        StyleUtils.setIsStyleKeyPressed(player, false);
    }
}

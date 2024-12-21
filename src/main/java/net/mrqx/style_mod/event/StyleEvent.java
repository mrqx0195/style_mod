package net.mrqx.style_mod.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public class StyleEvent extends Event {
    @Cancelable
    public static class StyleChangeEvent extends net.minecraftforge.event.entity.player.PlayerEvent {
        private final String oldStyle;
        private final String newStyle;

        public StyleChangeEvent(Player player, String oldStyle, String newStyle) {
            super(player);
            this.oldStyle = oldStyle;
            this.newStyle = newStyle;
        }

        public String getOldStyle() {
            return oldStyle;
        }

        public String getNewStyle() {
            return newStyle;
        }
    }
}

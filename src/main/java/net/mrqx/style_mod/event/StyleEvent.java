package net.mrqx.style_mod.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public class StyleEvent extends Event {
    @Cancelable
    public static class StyleChangeEvent extends StyleEvent {
        private final Player player;
        private final String oldStyle;
        private final String newStyle;

        public StyleChangeEvent(Player player, String oldStyle, String newStyle) {
            this.player = player;
            this.oldStyle = oldStyle;
            this.newStyle = newStyle;
        }

        public Player getPlayer() {
            return player;
        }

        public String getOldStyle() {
            return oldStyle;
        }

        public String getNewStyle() {
            return newStyle;
        }
    }
}

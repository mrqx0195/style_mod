package net.mrqx.style_mod.utils;

import net.mrqx.style_mod.config.StyleConfig;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class DevilTriggerUtils {
    public static int getDTMax(Player player) {
        CompoundTag tag = StyleUtils.getStyleTag(player);
        if (tag.contains("MaxDTGauge")) {
            return tag.getInt("MaxDTGauge");
        }
        tag.putInt("MaxDTGauge", Math.min(StyleConfig.INITIAL_DT_GAUGE.get(), StyleConfig.MAX_DT_GAUGE.get()) * 1000);
        StyleUtils.setStyleTag(player, tag);
        return Math.max(StyleConfig.INITIAL_DT_GAUGE.get(), StyleConfig.MAX_DT_GAUGE.get()) * 1000;
    }

    public static int getDTGauge(Player player) {
        CompoundTag tag = StyleUtils.getStyleTag(player);
        if (tag.contains("DTGauge")) {
            return Math.min(tag.getInt("DTGauge"), getDTMax(player));
        }
        tag.putInt("DTGauge", 0);
        StyleUtils.setStyleTag(player, tag);
        return Math.min(tag.getInt("DTGauge"), getDTMax(player));
    }

    public static void setDTMax(Player player, int dtMax) {
        CompoundTag tag = StyleUtils.getStyleTag(player);
        tag.putInt("MaxDTGauge", dtMax);
        StyleUtils.setStyleTag(player, tag);
    }

    public static void setDTGauge(Player player, int dt) {
        CompoundTag tag = StyleUtils.getStyleTag(player);
        tag.putInt("DTGauge", Math.min(Math.max(dt, 0), getDTMax(player)));
        StyleUtils.setStyleTag(player, tag);
    }

    public static Boolean isDTActive(Player player) {
        CompoundTag tag = StyleUtils.getStyleTag(player);
        if (tag.contains("DTActive")) {
            return tag.getBoolean("DTActive");
        }
        tag.putBoolean("DTActive", false);
        StyleUtils.setStyleTag(player, tag);
        return false;
    }

    public static void setDTActive(Player player, Boolean isActive) {
        CompoundTag tag = StyleUtils.getStyleTag(player);
        tag.putBoolean("DTActive", isActive);
        StyleUtils.setStyleTag(player, tag);
    }
}

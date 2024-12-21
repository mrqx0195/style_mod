package net.mrqx.style_mod.utils;

import net.mrqx.style_mod.StyleMod;
import net.mrqx.style_mod.registry.styles.NullStyle;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class StyleUtils {
    public static CompoundTag getStyleTag(Player player) {
        CompoundTag tag = player.getPersistentData();
        if (tag.contains(StyleMod.MODID, 10)) {
            return tag.getCompound(StyleMod.MODID);
        }
        return new CompoundTag();
    }

    public static Tag setStyleTag(Player player, CompoundTag compoundTag) {
        return player.getPersistentData().put(StyleMod.MODID, compoundTag);
    }

    public static boolean hasStyle(ResourceLocation id, Player player) {
        CompoundTag tag = getStyleTag(player);
        ListTag styles = tag.getList("Styles", 8);
        if (styles.contains(StringTag.valueOf(id.toString()))) {
            return true;
        }
        return false;
    }

    public static boolean addStyle(ResourceLocation id, Player player) {
        CompoundTag tag = getStyleTag(player);
        ListTag styles = tag.getList("Styles", 8);
        if (styles.contains(StringTag.valueOf(id.toString()))) {
            return false;
        }
        styles.add(StringTag.valueOf(id.toString()));
        tag.put("Styles", styles);
        setStyleTag(player, tag);
        return true;
    }

    public static String getActivatingStyle(Player player) {
        CompoundTag tag = getStyleTag(player);
        if (tag.getString("Style").isEmpty()) {
            tag.putString("Style", NullStyle.STYLE_ID.location().toString());
            addStyle(NullStyle.STYLE_ID.location(), player);
            setStyleTag(player, tag);
        }
        return tag.getString("Style");
    }

    public static boolean isStyleActivating(ResourceLocation id, Player player) {
        return getActivatingStyle(player) == id.toString();
    }

    public static boolean isUsingStyle(Player player) {
        if (getStyleTag(player).contains("UsingStyle")) {
            return getStyleTag(player).getBoolean("UsingStyle");
        }
        return false;
    }

    public static void setIsUsingStyle(Player player, Boolean bool) {
        CompoundTag compoundTag = getStyleTag(player);
        compoundTag.putBoolean("UsingStyle", bool);
        setStyleTag(player, compoundTag);
    }

    public static boolean isStyleKeyPressed(Player player) {
        if (getStyleTag(player).contains("StyleKeyPressed")) {
            return getStyleTag(player).getBoolean("StyleKeyPressed");
        }
        return false;
    }

    public static void setIsStyleKeyPressed(Player player, Boolean bool) {
        CompoundTag compoundTag = getStyleTag(player);
        compoundTag.putBoolean("StyleKeyPressed", bool);
        setStyleTag(player, compoundTag);
    }
}

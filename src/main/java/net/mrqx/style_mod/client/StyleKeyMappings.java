package net.mrqx.style_mod.client;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;

public class StyleKeyMappings {
    public static final KeyMapping KEY_USE_STYLE = new KeyMapping("key.style_mod.use_style",
            KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_MIDDLE,
            "key.category.style_mod");

}

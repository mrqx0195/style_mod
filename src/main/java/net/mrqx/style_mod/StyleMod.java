package net.mrqx.style_mod;

import net.mrqx.style_mod.client.render.RegisterOverlays;
import net.mrqx.style_mod.config.StyleConfig;
import net.mrqx.style_mod.network.NetworkManager;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mod(StyleMod.MODID)
public class StyleMod {
    public static final String MODID = "style_mod";

    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogUtils.getLogger();

    public StyleMod() {
        MinecraftForge.EVENT_BUS.register(this);
        NetworkManager.register();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(RegisterOverlays::onRegisterOverlays);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, StyleConfig.COMMON_CONFIG);
    }
}

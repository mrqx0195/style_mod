package net.mrqx.style_mod.registry;

import java.util.function.Supplier;

import net.mrqx.style_mod.StyleMod;
import net.mrqx.style_mod.registry.styles.NullStyle;
import net.mrqx.style_mod.registry.styles.Style;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

public class StyleRegistry {
        public static final DeferredRegister<Style> STYLE = DeferredRegister.create(Style.REGISTRY_KEY,
                        StyleMod.MODID);

        public static final Supplier<IForgeRegistry<Style>> REGISTRY = STYLE.makeRegistry(RegistryBuilder::new);
        public static final RegistryObject<Style> NULL_STYLE = STYLE.register(NullStyle.STYLE_ID.toString(),
                        NullStyle::new);

}

package net.mrqx.style_mod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class StyleConfig {
        public static ForgeConfigSpec COMMON_CONFIG;

        public static ForgeConfigSpec.IntValue INITIAL_DT_GAUGE;
        public static ForgeConfigSpec.IntValue MAX_DT_GAUGE;
        public static ForgeConfigSpec.IntValue DT_REQUIRE_DT_GAUGE;

        static {
                ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
                COMMON_BUILDER.comment("General settings").push("general");

                INITIAL_DT_GAUGE = COMMON_BUILDER.comment(
                                "The maximum DT Gauge initially owned.")
                                .defineInRange("initial_dt_gauge", 3, 1, Integer.MAX_VALUE);

                MAX_DT_GAUGE = COMMON_BUILDER.comment(
                                "The maximum DT Gauge that players can obtain.")
                                .defineInRange("max_dt_gauge", 10, 1, Integer.MAX_VALUE);

                DT_REQUIRE_DT_GAUGE = COMMON_BUILDER.comment(
                                "The DT Gauge required to use Devil Trigger.")
                                .defineInRange("dt_require_dt_gauge", 3, 1, Integer.MAX_VALUE);

                COMMON_BUILDER.pop();
                COMMON_CONFIG = COMMON_BUILDER.build();
        }
}

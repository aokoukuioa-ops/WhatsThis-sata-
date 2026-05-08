package com.satania.whats_this;

import com.mojang.logging.LogUtils;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(WhatsThis.MODID)
public class WhatsThis {

    public static final String MODID = "whats_this";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WhatsThis() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 注册HUD覆盖层
        modEventBus.addListener(WhatsThis::registerGuiOverlays);

        // 注册配置
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // 注册事件总线
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * 注册GUI覆盖层
     */
    private static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "block_info",
                com.satania.whats_this.client.HudOverlay.BLOCK_INFO_OVERLAY);
    }
}

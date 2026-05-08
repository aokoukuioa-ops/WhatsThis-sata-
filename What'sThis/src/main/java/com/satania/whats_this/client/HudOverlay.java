package com.satania.whats_this.client;

import com.satania.whats_this.Config;
import com.satania.whats_this.util.ModUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.ArrayList;
import java.util.List;

public class HudOverlay {

    public static final IGuiOverlay BLOCK_INFO_OVERLAY = ((gui, guiGraphics, partialTick, width, height) -> {
        Minecraft mc = Minecraft.getInstance();

        // 检查配置是否启用
        if (!Config.showBlockInfo) {
            return;
        }

        // 检查是否在玩游戏
        if (mc.level == null || mc.player == null) {
            return;
        }

        // 获取准心指向的方块
        BlockHitResult hitResult = ModUtils.getTargetBlock();
        if (hitResult == null) {
            return;
        }

        BlockPos pos = hitResult.getBlockPos();
        BlockState blockState = mc.level.getBlockState(pos);

        // 获取方块信息
        String blockName = "unknown";
        String modId = "unknown";
        String localizedBlockName = "未知方块";

        try {
            var blockRegistry = mc.level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.BLOCK);
            var resourceKey = blockRegistry.getResourceKey(blockState.getBlock());

            if (resourceKey.isPresent()) {
                ResourceLocation location = resourceKey.get().location();
                blockName = location.getPath();
                modId = location.getNamespace();
            }

            // 获取方块的本地化名称（优先显示中文）
            Component blockComponent = blockState.getBlock().getName();
            localizedBlockName = blockComponent.getString();
        } catch (Exception e) {
            com.satania.whats_this.WhatsThis.LOGGER.error("获取方块信息失败", e);
            return;
        }

        // 准备显示的信息行（优先使用本地化名称）
        List<String> infoLines = new ArrayList<>();
        infoLines.add("方块: " + localizedBlockName);
        infoLines.add("ID名: " + blockName);
        infoLines.add("模组: " + modId);

        // 渲染方框和信息
        renderInfoBox(guiGraphics, infoLines, width);
    });

    /**
     * 渲染Minecraft风格的信息方框
     */
    private static void renderInfoBox(GuiGraphics guiGraphics, List<String> lines, int screenWidth) {
        Minecraft mc = Minecraft.getInstance();

        // 计算方框尺寸
        int padding = 3;
        int lineHeight = 10;
        int lineSpacing = 2;

        // 找出最长的文本宽度
        int maxTextWidth = 0;
        for (String line : lines) {
            int textWidth = mc.font.width(line);
            if (textWidth > maxTextWidth) {
                maxTextWidth = textWidth;
            }
        }

        int boxWidth = maxTextWidth + padding * 2;
        int boxHeight = lines.size() * lineHeight + (lines.size() - 1) * lineSpacing + padding * 2;

        // 计算位置（顶部中央）
        int x = (screenWidth - boxWidth) / 2;
        int y = 4;

        // 绘制半透明背景（极简风格）
        int backgroundColor = 0x99000000;
        guiGraphics.fill(x, y, x + boxWidth, y + boxHeight, backgroundColor);

        // 绘制白色边框（极简风格）
        int borderColor = 0xFFFFFFFF;
        guiGraphics.hLine(x, x + boxWidth, y, borderColor);
        guiGraphics.hLine(x, x + boxWidth, y + boxHeight, borderColor);
        guiGraphics.vLine(x, y, y + boxHeight, borderColor);
        guiGraphics.vLine(x + boxWidth, y, y + boxHeight, borderColor);

        // 绘制文本（左对齐）
        int textColor = 0xFFFFFFFF;
        int currentY = y + padding;

        for (String line : lines) {
            int textX = x + padding;
            guiGraphics.drawString(mc.font, line, textX, currentY, textColor, false);
            currentY += lineHeight + lineSpacing;
        }
    }
}

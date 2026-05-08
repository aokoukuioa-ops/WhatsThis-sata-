package com.satania.whats_this.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class ModUtils {
    /**
     * 获取玩家当前准心指向的方块信息
     *
     * @return BlockHitResult 如果指向方块则返回，否则返回null
     */
    public static BlockHitResult getTargetBlock() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return null;
        }

        // 使用玩家的视线进行射线追踪，距离20格
        HitResult hitResult = mc.player.pick(20.0D, 0.0F, false);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            return (BlockHitResult) hitResult;
        }

        return null;
    }

    /**
     * 判断方块是否可以被破坏
     *
     * @param blockState 方块状态
     * @return true 如果可以破坏
     */
    public static boolean canBreakBlock(BlockState blockState) {
        // 基岩等不可破坏的方块
        if (blockState.getBlock() == Blocks.BEDROCK) {
            return false;
        }

        // 检查方块硬度，-1表示不可破坏（此处仅为方便而存，特殊技术方块可能会显示不可挖掘）
        return blockState.getDestroySpeed(null, BlockPos.ZERO) >= 0;
    }
}

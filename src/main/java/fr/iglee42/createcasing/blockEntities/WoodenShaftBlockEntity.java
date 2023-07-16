package fr.iglee42.createcasing.blockEntities;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import fr.iglee42.createcasing.config.ModConfigs;
import fr.iglee42.createcasing.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class WoodenShaftBlockEntity extends BracketedKineticBlockEntity {
    public WoodenShaftBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        if (ModConfigs.common().kinetics.shouldWoodenShaftBreak.get()) {
            if ((getSpeed() > ModConfigs.common().kinetics.maxSpeedWoodenShaft.get() || getSpeed() < -ModConfigs.common().kinetics.maxSpeedWoodenShaft.get()) && !ModBlocks.isWoodenShaftHasState(getLevel().getBlockState(source))) {
                getLevel().destroyBlock(worldPosition, false);
            }
        }

    }


}

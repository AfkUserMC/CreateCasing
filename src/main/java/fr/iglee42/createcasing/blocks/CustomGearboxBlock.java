package fr.iglee42.createcasing.blocks;

import com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.contraptions.relays.gearbox.GearboxTileEntity;
import com.simibubi.create.foundation.block.ITE;
import com.tterrag.registrate.util.entry.ItemEntry;
import fr.iglee42.createcasing.ModTiles;
import fr.iglee42.createcasing.items.CustomVerticalGearboxItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.HitResult;

import java.util.Arrays;
import java.util.List;


/*
This class is a copy from the original class GearboxBlock
 */
public class CustomGearboxBlock extends RotatedPillarKineticBlock implements ITE<GearboxTileEntity> {

	private final ItemEntry<CustomVerticalGearboxItem> verticalItem;

	public CustomGearboxBlock(Properties properties , ItemEntry<CustomVerticalGearboxItem> verticalItem) {
		super(properties);
		this.verticalItem = verticalItem;
	}

	public BlockEntityType<? extends GearboxTileEntity> getTileEntityType() {
		return ModTiles.GEARBOX.get();
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		super.fillItemCategory(group, items);
		items.add(verticalItem.asStack());
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		if (state.getValue(AXIS).isVertical())
			return super.getDrops(state, builder);
		return Arrays.asList(new ItemStack(verticalItem.get()));
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos,
									   Player player) {
		if (state.getValue(AXIS).isVertical())
			return super.getCloneItemStack(state, target, world, pos, player);
		return new ItemStack(verticalItem.get());
	}


	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(AXIS, Direction.Axis.Y);
	}

	// IRotate:

	@Override
	public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
		return face.getAxis() != state.getValue(AXIS);
	}

	@Override
	public Direction.Axis getRotationAxis(BlockState state) {
		return state.getValue(AXIS);
	}

	@Override
	public Class<GearboxTileEntity> getTileEntityClass() {
		return GearboxTileEntity.class;
	}

}
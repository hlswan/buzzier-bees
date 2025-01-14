package com.minecraftabnormals.buzzier_bees.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrystallizedHoneyBlock extends Block {
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;


	public CrystallizedHoneyBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.FALSE));
	}

	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		boolean flag = worldIn.hasNeighborSignal(pos);
		if (flag != state.getValue(POWERED)) {
			worldIn.setBlock(pos, state.setValue(POWERED, flag), 3);
			worldIn.destroyBlock(pos, true);
		}

	}

	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (!(entityIn instanceof ItemEntity)) {
			worldIn.destroyBlock(pos, true);
		}
	}

	public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		if (!(entityIn instanceof ItemEntity)) {
			worldIn.destroyBlock(pos, true);
		}
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}
}

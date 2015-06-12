/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockIceMP;
import stevekung.mods.moreplanets.common.util.WorldUtilMP;

public class BlockKoentusIce extends BlockIceMP
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockKoentusIce(String name)
	{
		super(Material.ice);
		this.setUnlocalizedName(name);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.koentus_ice));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 2; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
	{
		player.addExhaustion(0.025F);

		if (this.canSilkHarvest(world, pos, world.getBlockState(pos), player) && EnchantmentHelper.getSilkTouchModifier(player))
		{
			List<ItemStack> items = new ArrayList<ItemStack>();
			ItemStack itemstack = this.createStackedBlock(state);

			if (itemstack != null)
			{
				items.add(itemstack);
			}

			ForgeEventFactory.fireBlockHarvesting(items, world, pos, world.getBlockState(pos), 0, 1.0f, true, player);

			for (ItemStack is : items)
			{
				spawnAsEntity(world, pos, is);
			}
		}
		else
		{
			if (world.provider.doesWaterVaporize())
			{
				world.setBlockToAir(pos);
				return;
			}
			else if (WorldUtilMP.isMercuryWorld(world, pos))
			{
				world.setBlockToAir(pos);
				return;
			}

			int i = EnchantmentHelper.getFortuneModifier(player);
			this.harvesters.set(player);
			this.dropBlockAsItem(world, pos, state, i);
			this.harvesters.set(null);
			Material material = world.getBlockState(pos.down()).getBlock().getMaterial();

			if (material.blocksMovement() || material.isLiquid())
			{
				world.setBlockState(pos, Blocks.flowing_water.getDefaultState());
			}
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (world.getLightFor(EnumSkyBlock.BLOCK, pos) > 11 - this.getLightOpacity() && this.getMetaFromState(state) != 1)
		{
			if (world.provider.doesWaterVaporize())
			{
				world.setBlockToAir(pos);
				return;
			}
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockState(pos, Blocks.water.getDefaultState());
		}
		if (WorldUtilMP.isMercuryWorld(world, pos))
		{
			world.setBlockToAir(pos);
			return;
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos)
	{
		if (world.getBlockState(pos) == world.getBlockState(pos).withProperty(VARIANT, BlockType.glowing_koentus_ice))
		{
			return 15;
		}
		return 0;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
	{
		return new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockType)state.getValue(VARIANT)).ordinal();
	}

	public static enum BlockType implements IStringSerializable
	{
		koentus_ice,
		glowing_koentus_ice;

		@Override
		public String toString()
		{
			return this.getName();
		}

		@Override
		public String getName()
		{
			return this.name();
		}
	}
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;

public class BlockFronosWoodenPlanks extends BlockBaseMP
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockFronosWoodenPlanks(String name)
	{
		super(Material.wood);
		this.setHardness(2.0F);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.coconut_wood_planks));
		this.setStepSound(soundTypeWood);
		this.setUnlocalizedName(name);
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
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
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
		coconut_wood_planks,
		maple_wood_planks;

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
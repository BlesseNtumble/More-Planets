/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import stevekung.mods.moreplanets.common.items.ItemFoodMP;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;

public class ItemSpacePotato extends ItemFoodMP implements IPlantable
{
    private int[] foodHunger = new int[] {
            1,
            6
    };
    private float[] foodSaturation = new float[] {
            0.3F,
            0.6F
    };

    public ItemSpacePotato(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setHasSubtypes(true);
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos)
    {
        return PlutoBlocks.space_potato_block.getDefaultState();
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Crop;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (side != EnumFacing.UP)
        {
            return false;
        }
        else if (!player.canPlayerEdit(pos.offset(side), side, itemStack))
        {
            return false;
        }
        else if (world.getBlockState(pos).getBlock().canSustainPlant(world, pos, EnumFacing.UP, this) && world.isAirBlock(pos.up()))
        {
            world.setBlockState(pos.up(), PlutoBlocks.space_potato_block.getDefaultState());
            --itemStack.stackSize;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return this.foodHunger[itemStack.getItemDamage()];
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return this.foodSaturation[itemStack.getItemDamage()];
    }

    @Override
    protected String[] getItemVariantsName()
    {
        return new String[] { "space_potato", "baked_space_potato" };
    }
}
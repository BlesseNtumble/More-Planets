/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.EnumParticleTypesMP;
import stevekung.mods.moreplanets.common.blocks.BlockGrassMP;
import stevekung.mods.moreplanets.common.blocks.IFronosGrass;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockGoldenGrass extends BlockGrassMP implements IFronosGrass
{
    public static PropertyBool HAS_TEA_CREAM = PropertyBool.create("tea");

    public BlockGoldenGrass(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setDefaultState(this.getDefaultState().withProperty(HAS_TEA_CREAM, false));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (rand.nextInt(1) == 0)
        {
            MorePlanetsCore.proxy.spawnParticle(EnumParticleTypesMP.GOLDEN_DUST, pos.getX() + rand.nextFloat(), pos.getY() + 1.3F, pos.getZ() + rand.nextFloat());
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        Block block = world.getBlockState(pos.up()).getBlock();
        return state.withProperty(HAS_TEA_CREAM, Boolean.valueOf(block == FronosBlocks.tea_cream_layer));
    }

    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        return 10;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockLightOpacity(pos.up()) > 2)
            {
                world.setBlockState(pos, FronosBlocks.fronos_dirt.getDefaultState());
            }
            else if (world.getLightFromNeighbors(pos.up()) >= 9)
            {
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos pos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                    if (world.getBlockState(pos1) == FronosBlocks.fronos_dirt.getDefaultState())
                    {
                        if (world.getLightFromNeighbors(pos1.up()) >= 4 && world.getBlockState(pos1.up()).getBlock().getLightOpacity() <= 2)
                        {
                            world.setBlockState(pos1, FronosBlocks.golden_grass.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(FronosBlocks.fronos_dirt);
    }

    @Override
    public Block getFarmlandBlock()
    {
        return FronosBlocks.fronos_farmland;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { HAS_TEA_CREAM });
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
}
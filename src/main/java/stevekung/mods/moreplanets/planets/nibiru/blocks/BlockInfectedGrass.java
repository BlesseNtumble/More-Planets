/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.EnumParticleTypesMP;
import stevekung.mods.moreplanets.common.blocks.BlockGrassMP;
import stevekung.mods.moreplanets.common.eventhandler.MorePlanetsEvents;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockInfectedGrass extends BlockGrassMP
{
    public BlockInfectedGrass(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getBlock().getLightOpacity(world, pos.up()) > 2)
            {
                world.setBlockState(pos, NibiruBlocks.infected_dirt.getDefaultState());
            }
            else
            {
                if (world.getLightFromNeighbors(pos.up()) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                        Block block = world.getBlockState(blockpos1.up()).getBlock();
                        IBlockState iblockstate1 = world.getBlockState(blockpos1);

                        if (iblockstate1 == NibiruBlocks.infected_dirt.getDefaultState() && world.getLightFromNeighbors(blockpos1.up()) >= 4 && block.getLightOpacity(world, blockpos1.up()) <= 2)
                        {
                            world.setBlockState(blockpos1, NibiruBlocks.infected_grass.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plant)
    {
        Block block = plant.getPlant(world, pos).getBlock();
        return block == NibiruBlocks.nibiru_sapling || plant.getPlantType(world, pos) == EnumPlantType.Plains;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(NibiruBlocks.infected_dirt);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (rand.nextInt(1) == 0)
        {
            if (!World.doesBlockHaveSolidTopSurface(world, pos.up()))
            {
                MorePlanetsCore.proxy.spawnParticle(EnumParticleTypesMP.INFECTED_SPORE, pos.getX() + rand.nextFloat(), pos.getY() + 1.1F, pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public Block getFarmlandBlock()
    {
        return NibiruBlocks.infected_farmland;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
    {
        super.harvestBlock(world, player, pos, state, tile);
        MorePlanetsEvents.addInfectedGas(player);
    }
}
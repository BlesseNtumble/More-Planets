/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.common.blocks.BlockSaplingMP;

public class WorldGenTreeMP extends WorldGenAbstractTree
{
    private int woodMeta;
    private int leavesMeta;
    private Block wood;
    private Block leaves;
    private Block sapling;
    private Block vine;
    private Block grass;
    private boolean grassBool;
    private Block dirt;
    private Block fruit;

    public WorldGenTreeMP(Block wood, Block leaves, int woodMeta, int leavesMeta, Block sapling, Block vine, Block grass, Block dirt, Block fruit)
    {
        super(false);
        this.woodMeta = woodMeta;
        this.wood = wood;
        this.leaves = leaves;
        this.sapling = sapling;
        this.leavesMeta = leavesMeta;
        this.vine = vine;
        this.grass = grass;
        this.dirt = dirt;
        this.fruit = fruit;
    }

    public WorldGenTreeMP(Block wood, Block leaves, int woodMeta, int leavesMeta, Block sapling, Block vine, boolean grass, Block dirt, Block fruit)
    {
        super(false);
        this.woodMeta = woodMeta;
        this.wood = wood;
        this.leaves = leaves;
        this.sapling = sapling;
        this.leavesMeta = leavesMeta;
        this.vine = vine;
        this.grassBool = grass;
        this.dirt = dirt;
        this.fruit = fruit;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(3) + 5;
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256)
        {
            byte b0;
            int l;

            for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j)
            {
                b0 = 1;

                if (j == pos.getY())
                {
                    b0 = 0;
                }
                if (j >= pos.getY() + 1 + i - 2)
                {
                    b0 = 2;
                }

                for (int k = pos.getX() - b0; k <= pos.getX() + b0 && flag; ++k)
                {
                    for (l = pos.getZ() - b0; l <= pos.getZ() + b0 && flag; ++l)
                    {
                        if (j >= 0 && j < 256)
                        {
                            if (!this.isReplaceable(world, new BlockPos(k, j, l)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                BlockPos down = pos.down();
                Block block1 = world.getBlockState(down).getBlock();
                boolean isSoil = block1.canSustainPlant(world, down, EnumFacing.UP, (BlockSaplingMP)this.sapling);

                if (isSoil && pos.getY() < 256 - i - 1)
                {
                    this.onPlantGrow(world, pos.down());
                    b0 = 3;
                    byte b1 = 0;
                    int i1;
                    int j1;
                    int k1;
                    int l1;
                    BlockPos blockpos1;

                    for (l = pos.getY() - b0 + i; l <= pos.getY() + i; ++l)
                    {
                        i1 = l - (pos.getY() + i);
                        j1 = b1 + 1 - i1 / 2;

                        for (k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1)
                        {
                            l1 = k1 - pos.getX();

                            for (int i2 = pos.getZ() - j1; i2 <= pos.getZ() + j1; ++i2)
                            {
                                int j2 = i2 - pos.getZ();

                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i1 != 0)
                                {
                                    blockpos1 = new BlockPos(k1, l, i2);
                                    Block block = world.getBlockState(blockpos1).getBlock();

                                    if (block.isAir(world, blockpos1) || block.isLeaves(world, blockpos1) || block.getMaterial() == Material.vine)
                                    {
                                        this.func_175905_a(world, blockpos1, this.leaves, this.leavesMeta);
                                    }
                                }
                            }
                        }
                    }

                    for (l = 0; l < i; ++l)
                    {
                        BlockPos upN = pos.up(l);
                        Block block2 = world.getBlockState(upN).getBlock();

                        if (block2.isAir(world, upN) || block2.isLeaves(world, upN) || block2.getMaterial() == Material.vine)
                        {
                            this.func_175905_a(world, pos.up(l), this.wood, this.woodMeta);

                            if (this.vine != null && l > 0)
                            {
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(-1, l, 0)))
                                {
                                    this.func_175905_a(world, pos.add(-1, l, 0), this.vine, BlockVine.EAST_FLAG);
                                }
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(1, l, 0)))
                                {
                                    this.func_175905_a(world, pos.add(1, l, 0), this.vine, BlockVine.WEST_FLAG);
                                }
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(0, l, -1)))
                                {
                                    this.func_175905_a(world, pos.add(0, l, -1), this.vine, BlockVine.SOUTH_FLAG);
                                }
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(0, l, 1)))
                                {
                                    this.func_175905_a(world, pos.add(0, l, 1), this.vine, BlockVine.NORTH_FLAG);
                                }
                            }
                        }
                    }

                    if (this.vine != null)
                    {
                        for (l = pos.getY() - 3 + i; l <= pos.getY() + i; ++l)
                        {
                            i1 = l - (pos.getY() + i);
                            j1 = 2 - i1 / 2;

                            for (k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1)
                            {
                                for (l1 = pos.getZ() - j1; l1 <= pos.getZ() + j1; ++l1)
                                {
                                    BlockPos blockpos3 = new BlockPos(k1, l, l1);

                                    if (world.getBlockState(blockpos3).getBlock().isLeaves(world, blockpos3))
                                    {
                                        BlockPos blockpos4 = blockpos3.west();
                                        blockpos1 = blockpos3.east();
                                        BlockPos blockpos5 = blockpos3.north();
                                        BlockPos blockpos2 = blockpos3.south();

                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos4).getBlock().isAir(world, blockpos4))
                                        {
                                            this.func_175923_a(world, blockpos4, BlockVine.EAST_FLAG);
                                        }
                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos1).getBlock().isAir(world, blockpos1))
                                        {
                                            this.func_175923_a(world, blockpos1, BlockVine.WEST_FLAG);
                                        }
                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos5).getBlock().isAir(world, blockpos5))
                                        {
                                            this.func_175923_a(world, blockpos5, BlockVine.SOUTH_FLAG);
                                        }
                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos2).getBlock().isAir(world, blockpos2))
                                        {
                                            this.func_175923_a(world, blockpos2, BlockVine.NORTH_FLAG);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if (this.fruit != null)
                    {
                        if (rand.nextInt(1) == 0 && i > 5)
                        {
                            for (l = 0; l < 2; ++l)
                            {
                                for (i1 = 0; i1 < 4; ++i1)
                                {
                                    if (rand.nextInt(4 - l) == 0)
                                    {
                                        j1 = rand.nextInt(3);
                                        EnumFacing enumfacing = EnumFacing.getHorizontal(i1).getOpposite();
                                        this.func_175905_a(world, pos.add(enumfacing.getFrontOffsetX(), i - 5 + l, enumfacing.getFrontOffsetZ()), this.fruit, j1 << 2 | EnumFacing.getHorizontal(i1).getHorizontalIndex());
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    private void onPlantGrow(World world, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock() == this.grass && this.grass != null && this.dirt != null)
        {
            world.setBlockState(pos, this.dirt.getDefaultState(), 2);
        }
        else if (this.grassBool && this.dirt != null)
        {
            world.setBlockState(pos, this.dirt.getDefaultState(), 2);
        }
        else if (world.getBlockState(pos).getBlock() == Blocks.grass)
        {
            world.setBlockState(pos, Blocks.dirt.getDefaultState(), 2);
        }
    }

    private void func_175923_a(World world, BlockPos pos, int meta)
    {
        this.func_175905_a(world, pos, this.vine, meta);
        int j = 4;

        for (pos = pos.down(); world.getBlockState(pos).getBlock().isAir(world, pos) && j > 0; --j)
        {
            this.func_175905_a(world, pos, this.vine, meta);
            pos = pos.down();
        }
    }
}
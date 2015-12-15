/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.world.gen.feature;

import static net.minecraftforge.common.ChestGenHooks.DUNGEON_CHEST;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.common.blocks.BlockAncientChestMP;
import stevekung.mods.moreplanets.common.tileentities.TileEntityAncientChestMP;
import stevekung.mods.moreplanets.common.util.MPLog;

import com.google.common.collect.Lists;

public class WorldGenSpaceDungeons extends WorldGenerator
{
    private static List CHESTCONTENT = Lists.newArrayList(new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 4, 10), new WeightedRandomChestContent(Items.bread, 0, 1, 1, 10), new WeightedRandomChestContent(Items.wheat, 0, 1, 4, 10), new WeightedRandomChestContent(Items.gunpowder, 0, 1, 4, 10), new WeightedRandomChestContent(Items.string, 0, 1, 4, 10), new WeightedRandomChestContent(Items.bucket, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1), new WeightedRandomChestContent(Items.redstone, 0, 1, 4, 10), new WeightedRandomChestContent(Items.record_13, 0, 1, 1, 4), new WeightedRandomChestContent(Items.record_cat, 0, 1, 1, 4), new WeightedRandomChestContent(Items.name_tag, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 2), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)});

    private Block chest;
    private Block cobblestone;
    private Block moss;
    private int cobbleMeta;
    private int mossMeta;

    public WorldGenSpaceDungeons(Block chest, Block cobblestone, Block moss, int cobbleMeta, int mossMeta)
    {
        this.chest = chest;
        this.cobblestone = cobblestone;
        this.cobbleMeta = cobbleMeta;
        this.moss = moss;
        this.mossMeta = mossMeta;
    }

    public WorldGenSpaceDungeons(Block chest, Block cobblestone, Block moss, int mossMeta)
    {
        this(chest, cobblestone, moss, 3, mossMeta);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(2) + 2;
        int j = -i - 1;
        int k = i + 1;
        int l = rand.nextInt(2) + 2;
        int i1 = -l - 1;
        int j1 = l + 1;
        int k1 = 0;
        int l1;
        int i2;
        int j2;
        BlockPos blockpos1;

        for (l1 = j; l1 <= k; ++l1)
        {
            for (i2 = -1; i2 <= 4; ++i2)
            {
                for (j2 = i1; j2 <= j1; ++j2)
                {
                    blockpos1 = pos.add(l1, i2, j2);
                    Material material = world.getBlockState(blockpos1).getBlock().getMaterial();
                    boolean flag3 = material.isSolid();

                    if (i2 == -1 && !flag3)
                    {
                        return false;
                    }
                    if (i2 == 4 && !flag3)
                    {
                        return false;
                    }

                    if ((l1 == j || l1 == k || j2 == i1 || j2 == j1) && i2 == 0 && world.isAirBlock(blockpos1) && world.isAirBlock(blockpos1.up()))
                    {
                        ++k1;
                    }
                }
            }
        }

        if (k1 >= 1 && k1 <= 5)
        {
            for (l1 = j; l1 <= k; ++l1)
            {
                for (i2 = 3; i2 >= -1; --i2)
                {
                    for (j2 = i1; j2 <= j1; ++j2)
                    {
                        blockpos1 = pos.add(l1, i2, j2);

                        if (l1 != j && i2 != -1 && j2 != i1 && l1 != k && i2 != 4 && j2 != j1)
                        {
                            if (world.getBlockState(blockpos1).getBlock() != this.chest)
                            {
                                world.setBlockToAir(blockpos1);
                            }
                        }
                        else if (blockpos1.getY() >= 0 && !world.getBlockState(blockpos1.down()).getBlock().getMaterial().isSolid())
                        {
                            world.setBlockToAir(blockpos1);
                        }
                        else if (world.getBlockState(blockpos1).getBlock().getMaterial().isSolid() && world.getBlockState(blockpos1).getBlock() != this.chest)
                        {
                            if (i2 == -1 && rand.nextInt(4) != 0)
                            {
                                world.setBlockState(blockpos1, this.moss.getStateFromMeta(this.mossMeta), 2);
                            }
                            else
                            {
                                world.setBlockState(blockpos1, this.cobblestone.getStateFromMeta(this.cobbleMeta), 2);
                            }
                        }
                    }
                }
            }

            l1 = 0;

            while (l1 < 2)
            {
                i2 = 0;

                while (true)
                {
                    if (i2 < 3)
                    {
                        label100:
                        {
                        j2 = pos.getX() + rand.nextInt(i * 2 + 1) - i;
                        int l2 = pos.getY();
                        int i3 = pos.getZ() + rand.nextInt(l * 2 + 1) - l;
                        BlockPos blockpos2 = new BlockPos(j2, l2, i3);

                        if (world.isAirBlock(blockpos2))
                        {
                            int k2 = 0;
                            Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

                            while (iterator.hasNext())
                            {
                                EnumFacing enumfacing = (EnumFacing)iterator.next();

                                if (world.getBlockState(blockpos2.offset(enumfacing)).getBlock().getMaterial().isSolid())
                                {
                                    ++k2;
                                }
                            }

                            if (k2 == 1)
                            {
                                world.setBlockState(blockpos2, ((BlockAncientChestMP)this.chest).correctFacing(world, blockpos2, this.chest.getDefaultState()), 2);
                                TileEntity tileentity1 = world.getTileEntity(blockpos2);

                                if (tileentity1 instanceof TileEntityAncientChestMP)
                                {
                                    WeightedRandomChestContent.generateChestContents(rand, ChestGenHooks.getItems(DUNGEON_CHEST, rand), (TileEntityAncientChestMP)tileentity1, ChestGenHooks.getCount(DUNGEON_CHEST, rand));
                                }
                                break label100;
                            }
                        }
                        ++i2;
                        continue;
                        }
                    }
                    ++l1;
                    break;
                }
            }

            world.setBlockState(pos, Blocks.mob_spawner.getDefaultState(), 2);
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityMobSpawner)
            {
                ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(rand));
            }
            else
            {
                MPLog.error("Failed to fetch mob spawner entity at (" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")");
            }
            MPLog.debug("Generate spawner %s at : %s %s %s", ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().getEntityNameToSpawn(), pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        else
        {
            return false;
        }
    }

    private String pickMobSpawner(Random rand)
    {
        if (rand.nextInt(5) == 0)
        {
            return "EvolvedSpider";
        }
        else if (rand.nextInt(10) == 0)
        {
            return "EvolvedSkeleton";
        }
        else if (rand.nextInt(25) == 0)
        {
            return "EvolvedCreeper";
        }
        else if (rand.nextInt(30) == 0)
        {
            return "MorePlanets.EvolvedWitch";
        }
        return "EvolvedZombie";
    }

    static
    {
        ChestGenHooks.init(DUNGEON_CHEST, CHESTCONTENT, 8, 8);
        ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Items.enchanted_book, 1, 0), 1, 1, 1));
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.dungeon;

import java.util.ArrayList;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonBoundingBox;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonRoom;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.common.config.ConfigManagerMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class RoomSpawnerFronos extends DungeonRoom
{
    int sizeX;
    int sizeY;
    int sizeZ;
    Random rand;

    private ArrayList<BlockPos> spawners = new ArrayList<BlockPos>();

    public RoomSpawnerFronos(MapGenDungeon dungeon, int x, int y, int z, EnumFacing facing)
    {
        super(dungeon, x, y, z, facing);

        if (this.worldObj != null)
        {
            this.rand = new Random(this.worldObj.getSeed() * x * y * 57 * z);
            this.sizeX = this.rand.nextInt(5) + 6;
            this.sizeY = this.rand.nextInt(2) + 4;
            this.sizeZ = this.rand.nextInt(5) + 6;
        }
    }

    @Override
    public void generate(ChunkPrimer chunk, int cx, int cz)
    {
        for (int i = this.posX - 1; i <= this.posX + this.sizeX; i++)
        {
            for (int j = this.posY - 1; j <= this.posY + this.sizeY; j++)
            {
                for (int k = this.posZ - 1; k <= this.posZ + this.sizeZ; k++)
                {
                    if (i == this.posX - 1 || i == this.posX + this.sizeX || j == this.posY - 1 || j == this.posY + this.sizeY || k == this.posZ - 1 || k == this.posZ + this.sizeZ)
                    {
                        this.placeBlock(chunk, i, j, k, cx, cz, this.dungeonInstance.DUNGEON_WALL_ID, this.dungeonInstance.DUNGEON_WALL_META);
                    }
                    else
                    {
                        this.placeBlock(chunk, i, j, k, cx, cz, Blocks.air, 0);

                        if (this.rand.nextFloat() < 0.05F)
                        {
                            this.placeBlock(chunk, i, j, k, cx, cz, FronosBlocks.cheese_web, 0);
                        }
                    }
                }
            }
        }
        if (this.placeBlock(chunk, this.posX + 1, this.posY - 1, this.posZ + 1, cx, cz, Blocks.mob_spawner, 0))
        {
            this.spawners.add(new BlockPos(this.posX + 1, this.posY - 1, this.posZ + 1));
        }
        if (this.placeBlock(chunk, this.posX + this.sizeX - 1, this.posY - 1, this.posZ + this.sizeZ - 1, cx, cz, Blocks.mob_spawner, 0))
        {
            this.spawners.add(new BlockPos(this.posX + this.sizeX - 1, this.posY - 1, this.posZ + this.sizeZ - 1));
        }
    }

    @Override
    public DungeonBoundingBox getBoundingBox()
    {
        return new DungeonBoundingBox(this.posX, this.posZ, this.posX + this.sizeX, this.posZ + this.sizeZ);
    }

    @Override
    protected DungeonRoom makeRoom(MapGenDungeon dungeon, int x, int y, int z, EnumFacing facing)
    {
        return new RoomSpawnerFronos(dungeon, x, y, z, facing);
    }

    @Override
    protected void handleTileEntities(Random rand)
    {
        for (BlockPos spawnerCoords : this.spawners)
        {
            if (this.worldObj.getBlockState(spawnerCoords) == Blocks.mob_spawner.getDefaultState())
            {
                TileEntityMobSpawner spawner = (TileEntityMobSpawner) this.worldObj.getTileEntity(spawnerCoords);

                if (spawner != null)
                {
                    spawner.getSpawnerBaseLogic().setEntityName(RoomSpawnerFronos.getMob(rand));
                }
            }
        }
    }

    private static String getMob(Random rand)
    {
        if (!ConfigManagerMP.allowMobCreatureSpawningOnFronos)
        {
            switch (rand.nextInt(6))
            {
            case 0:
                return "EvolvedSpider";
            case 1:
                return "EvolvedZombie";
            case 2:
                return "EvolvedCreeper";
            case 3:
                return "EvolvedSkeleton";
            case 4:
                return "MorePlanets.EvolvedEnderman";
            default:
                return "EvolvedZombie";
            }
        }
        else
        {
            switch (rand.nextInt(6))
            {
            case 0:
                return "Zombie";
            case 1:
                return "Skeleton";
            case 2:
                return "Creeper";
            case 3:
                return "Spider";
            case 4:
                return "Enderman";
            default:
                return "Zombie";
            }
        }
    }
}
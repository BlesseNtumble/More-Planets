/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.worldgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.perlin.NoiseModule;
import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
import micdoodle8.mods.galacticraft.core.world.gen.EnumCraterSize;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.entities.EntityEvolvedWitch;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.worldgen.MapGenCaveMP;
import stevekung.mods.moreplanets.core.worldgen.dungeon.RoomEmptyMP;
import stevekung.mods.moreplanets.core.worldgen.dungeon.RoomSpawnerMP;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenSpaceDungeons;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseCow;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseSlime;
import stevekung.mods.moreplanets.planets.polongnius.worldgen.dungeon.RoomBossPolongnius;
import stevekung.mods.moreplanets.planets.polongnius.worldgen.dungeon.RoomChestsPolongnius;
import stevekung.mods.moreplanets.planets.polongnius.worldgen.dungeon.RoomTreasurePolongnius;

public class ChunkProviderPolongnius extends ChunkProviderGenerate
{
    Block topBlockID = PolongniusBlocks.polongnius_block;
    byte topBlockMeta = 0;
    Block fillBlockID = PolongniusBlocks.polongnius_block;
    byte fillBlockMeta = 1;
    Block lowerBlockID = PolongniusBlocks.polongnius_block;
    byte lowerBlockMeta = 2;

    private Random rand;

    private NoiseModule noiseGen1;
    private NoiseModule noiseGen2;
    private NoiseModule noiseGen3;
    private NoiseModule noiseGen4;

    public BiomeDecoratorPolongnius biomedecoratorplanet = new BiomeDecoratorPolongnius();

    private World worldObj;

    private MapGenDungeon dungeonGenerator = new MapGenDungeon(PolongniusBlocks.polongnius_block, 13, 8, 24, 4);
    {
        this.dungeonGenerator.otherRooms.add(new RoomEmptyMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomChestsPolongnius(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomChestsPolongnius(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.bossRooms.add(new RoomBossPolongnius(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.treasureRooms.add(new RoomTreasurePolongnius(null, 0, 0, 0, ForgeDirection.UNKNOWN));
    }

    private MapGenDungeon dungeonGenerator2 = new MapGenDungeon(PolongniusBlocks.polongnius_block, 14, 8, 24, 4);
    {
        this.dungeonGenerator2.otherRooms.add(new RoomEmptyMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.otherRooms.add(new RoomChestsPolongnius(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.otherRooms.add(new RoomChestsPolongnius(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.bossRooms.add(new RoomBossPolongnius(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator2.treasureRooms.add(new RoomTreasurePolongnius(null, 0, 0, 0, ForgeDirection.UNKNOWN));
    }

    private BiomeGenBase[] biomesForGeneration = { BiomeGenBasePolongnius.polongnius };

    private MapGenCaveMP caveGenerator = new MapGenCaveMP(PolongniusBlocks.polongnius_block);

    private static int CRATER_PROB = 600;

    // DO NOT CHANGE
    private static int MID_HEIGHT = 64;
    private static int CHUNK_SIZE_X = 16;
    private static int CHUNK_SIZE_Y = 128;
    private static int CHUNK_SIZE_Z = 16;

    public ChunkProviderPolongnius(World par1World, long par2, boolean par4)
    {
        super(par1World, par2, par4);
        this.worldObj = par1World;
        this.rand = new Random(par2);
        this.noiseGen1 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noiseGen2 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noiseGen3 = new Gradient(this.rand.nextLong(), 1, 0.25F);
        this.noiseGen4 = new Gradient(this.rand.nextLong(), 1, 0.25F);
    }

    public void generateTerrain(int chunkX, int chunkZ, Block[] idArray, byte[] metaArray)
    {
        this.noiseGen1.setFrequency(0.0125F);
        this.noiseGen2.setFrequency(0.015F);
        this.noiseGen3.setFrequency(0.01F);
        this.noiseGen4.setFrequency(0.02F);

        for (int x = 0; x < ChunkProviderPolongnius.CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < ChunkProviderPolongnius.CHUNK_SIZE_Z; z++)
            {
                double d = this.noiseGen1.getNoise(x + chunkX * 16, z + chunkZ * 16) * 8;
                double d2 = this.noiseGen2.getNoise(x + chunkX * 16, z + chunkZ * 16) * 24;
                double d3 = this.noiseGen3.getNoise(x + chunkX * 16, z + chunkZ * 16) - 0.1;
                d3 *= 4;

                double yDev = 0;

                if (d3 < 0.0D)
                {
                    yDev = d;
                }
                else if (d3 > 1.0D)
                {
                    yDev = d2;
                }
                else
                {
                    yDev = d + (d2 - d) * d3;
                }

                for (int y = 0; y < ChunkProviderPolongnius.CHUNK_SIZE_Y; y++)
                {
                    if (y < ChunkProviderPolongnius.MID_HEIGHT + yDev)
                    {
                        idArray[this.getIndex(x, y, z)] = this.lowerBlockID;
                        metaArray[this.getIndex(x, y, z)] = this.lowerBlockMeta;
                    }
                }
            }
        }
    }

    @Override
    public void replaceBlocksForBiome(int chunkX, int chunkZ, Block[] blockArray, byte[] metaArray, BiomeGenBase[] biome)
    {
        int var5 = 20;

        for (int x = 0; x < 16; ++x)
        {
            for (int z = 0; z < 16; ++z)
            {
                int var12 = (int) (this.noiseGen4.getNoise(x + chunkX * 16, z * chunkZ * 16) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var13 = -1;
                Block var14 = this.topBlockID;
                byte var14m = this.topBlockMeta;
                Block var15 = this.fillBlockID;
                byte var15m = this.fillBlockMeta;

                for (int y = 255; y >= 0; --y)
                {
                    int index = this.getIndex(x, y, z);
                    metaArray[index] = 0;

                    if (y <= 0 + this.rand.nextInt(5))
                    {
                        blockArray[index] = Blocks.bedrock;
                    }
                    else
                    {
                        Block block = blockArray[index];

                        if (Blocks.air == block)
                        {
                            var13 = -1;
                        }
                        else if (block == this.lowerBlockID)
                        {
                            metaArray[index] = this.lowerBlockMeta;

                            if (var13 == -1)
                            {
                                if (var12 <= 0)
                                {
                                    var14 = Blocks.air;
                                    var14m = 0;
                                    var15 = this.lowerBlockID;
                                    var15m = this.lowerBlockMeta;
                                }
                                else if (y >= var5 - -16 && y <= var5 + 1)
                                {
                                    var14 = this.topBlockID;
                                    var14m = this.topBlockMeta;
                                    var14 = this.fillBlockID;
                                    var14m = this.fillBlockMeta;
                                }

                                var13 = var12;

                                if (y >= var5 - 1)
                                {
                                    blockArray[index] = var14;
                                    metaArray[index] = var14m;
                                }
                                else if (y < var5 - 1 && y >= var5 - 2)
                                {
                                    blockArray[index] = var15;
                                    metaArray[index] = var15m;
                                }
                            }
                            else if (var13 > 0)
                            {
                                --var13;
                                blockArray[index] = var15;
                                metaArray[index] = var15m;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
        Block[] ids = new Block[16 * 16 * 256];
        byte[] meta = new byte[16 * 16 * 256];
        Arrays.fill(ids, Blocks.air);
        this.generateTerrain(par1, par2, ids, meta);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.createCraters(par1, par2, ids, meta);
        this.replaceBlocksForBiome(par1, par2, ids, meta, this.biomesForGeneration);
        this.caveGenerator.generate(this, this.worldObj, par1, par2, ids, meta);
        this.dungeonGenerator.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), par1 * 16, 25, par2 * 16, par1, par2, ids, meta);
        this.dungeonGenerator2.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), par1 * 16, 25, par2 * 16, par1, par2, ids, meta);
        Chunk var4 = new Chunk(this.worldObj, ids, meta, par1, par2);
        var4.generateSkylightMap();
        return var4;
    }

    public void createCraters(int chunkX, int chunkZ, Block[] chunkArray, byte[] metaArray)
    {
        for (int cx = chunkX - 2; cx <= chunkX + 2; cx++)
        {
            for (int cz = chunkZ - 2; cz <= chunkZ + 2; cz++)
            {
                for (int x = 0; x < ChunkProviderPolongnius.CHUNK_SIZE_X; x++)
                {
                    for (int z = 0; z < ChunkProviderPolongnius.CHUNK_SIZE_Z; z++)
                    {
                        if (Math.abs(this.randFromPoint(cx * 16 + x, (cz * 16 + z) * 1000)) < this.noiseGen4.getNoise(x * ChunkProviderPolongnius.CHUNK_SIZE_X + x, cz * ChunkProviderPolongnius.CHUNK_SIZE_Z + z) / ChunkProviderPolongnius.CRATER_PROB)
                        {
                            Random random = new Random(cx * 16 + x + (cz * 16 + z) * 5000);
                            EnumCraterSize cSize = EnumCraterSize.sizeArray[random.nextInt(EnumCraterSize.sizeArray.length)];
                            int size = random.nextInt(cSize.MAX_SIZE - cSize.MIN_SIZE) + cSize.MIN_SIZE;
                            this.makeCrater(cx * 16 + x, cz * 16 + z, chunkX * 16, chunkZ * 16, size, chunkArray, metaArray);
                        }
                    }
                }
            }
        }
    }

    public void makeCrater(int craterX, int craterZ, int chunkX, int chunkZ, int size, Block[] chunkArray, byte[] metaArray)
    {
        for (int x = 0; x < ChunkProviderPolongnius.CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < ChunkProviderPolongnius.CHUNK_SIZE_Z; z++)
            {
                double xDev = craterX - (chunkX + x);
                double zDev = craterZ - (chunkZ + z);
                if (xDev * xDev + zDev * zDev < size * size)
                {
                    xDev /= size;
                    zDev /= size;
                    double sqrtY = xDev * xDev + zDev * zDev;
                    double yDev = sqrtY * sqrtY * 6;
                    yDev = 5 - yDev;
                    int helper = 0;
                    for (int y = 127; y > 0; y--)
                    {
                        if (Blocks.air != chunkArray[this.getIndex(x, y, z)] && helper <= yDev)
                        {
                            chunkArray[this.getIndex(x, y, z)] = Blocks.air;
                            metaArray[this.getIndex(x, y, z)] = 0;
                            helper++;
                        }
                        if (helper > yDev)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    @Override
    public int getLoadedChunkCount()
    {
        return 0;
    }

    private int getIndex(int x, int y, int z)
    {
        return (x * 16 + z) * 256 + y;
    }

    private double randFromPoint(int x, int z)
    {
        int n;
        n = x + z * 57;
        n = n << 13 ^ n;
        return 1.0 - (n * (n * n * 15731 + 789221) + 1376312589 & 0x7fffffff) / 1073741824.0;
    }

    public void decoratePlanet(World par1World, Random par2Random, int par3, int par4)
    {
        this.biomedecoratorplanet.decorate(par1World, par2Random, par3, par4);
    }

    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockFalling.fallInstantly = true;
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(par2 * var7 + par3 * var9 ^ this.worldObj.getSeed());
        this.dungeonGenerator.handleTileEntities(this.rand);
        this.dungeonGenerator2.handleTileEntities(this.rand);
        this.decoratePlanet(this.worldObj, this.rand, var4, var5);

        for (int i = 0; i < 8; ++i)
        {
            int x = var4 + this.rand.nextInt(16) + 8;
            int y = this.rand.nextInt(256);
            int z = var5 + this.rand.nextInt(16) + 8;
            new WorldGenSpaceDungeons(PolongniusBlocks.polongnius_ancient_chest, PolongniusBlocks.polongnius_ancient_chest, MPBlocks.space_mossy_cobblestone, 1).generate(this.worldObj, this.rand, x, y, z);
        }
        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    @Override
    public boolean canSave()
    {
        return true;
    }

    @Override
    public String makeString()
    {
        return "PolongniusLevelSource";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int i, int j, int k)
    {
        if (par1EnumCreatureType == EnumCreatureType.monster)
        {
            List monsters = new ArrayList();
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedZombie.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedSpider.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedSkeleton.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedCreeper.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityCheeseSlime.class, 15, 2, 4));
            monsters.add(new SpawnListEntry(EntityEvolvedWitch.class, 5, 1, 1));
            return monsters;
        }
        else if (par1EnumCreatureType == EnumCreatureType.creature)
        {
            List creatures = new ArrayList();
            creatures.add(new BiomeGenBase.SpawnListEntry(EntityCheeseCow.class, 8, 4, 4));
            return creatures;
        }
        return null;
    }
}
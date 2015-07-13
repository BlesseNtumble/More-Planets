/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.world.gen;

import java.util.ArrayList;
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
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;
import stevekung.mods.moreplanets.common.world.gen.MapGenCaveMP;
import stevekung.mods.moreplanets.common.world.gen.dungeon.RoomEmptyMP;
import stevekung.mods.moreplanets.common.world.gen.dungeon.RoomSpawnerMP;
import stevekung.mods.moreplanets.common.world.gen.feature.WorldGenSplashBlock;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.BlockKapteynB;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.world.gen.dungeon.RoomBossKapteynB;
import stevekung.mods.moreplanets.planets.kapteynb.world.gen.dungeon.RoomChestsKapteynB;
import stevekung.mods.moreplanets.planets.kapteynb.world.gen.dungeon.RoomTreasureKapteynB;

public class ChunkProviderKapteynB extends ChunkProviderGenerate
{
	IBlockState topBlock = KapteynBBlocks.kapteyn_b_block.getDefaultState();
	IBlockState fillBlock = KapteynBBlocks.kapteyn_b_block.getDefaultState().withProperty(BlockKapteynB.VARIANT, BlockKapteynB.BlockType.kapteyn_b_sub_surface_ice);
	IBlockState lowerBlock = KapteynBBlocks.kapteyn_b_block.getDefaultState().withProperty(BlockKapteynB.VARIANT, BlockKapteynB.BlockType.kapteyn_b_surface_ice);

	private Random rand;

	private NoiseModule noiseGen1;
	private NoiseModule noiseGen2;
	private NoiseModule noiseGen3;
	private NoiseModule noiseGen4;

	public BiomeDecoratorKapteynB biomedecoratorplanet = new BiomeDecoratorKapteynB();
	private MapGenKapteynBRavine ravineGenerator = new MapGenKapteynBRavine();
	private World worldObj;
	private MapGenDungeon dungeonGenerator = new MapGenDungeon(KapteynBBlocks.kapteyn_b_block, 14, 8, 24, 4);
	{
		this.dungeonGenerator.otherRooms.add(new RoomEmptyMP(null, 0, 0, 0, null));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, null));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, null));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, null));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, null));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, null));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, null));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, null));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, null));
		this.dungeonGenerator.otherRooms.add(new RoomChestsKapteynB(null, 0, 0, 0, null));
		this.dungeonGenerator.otherRooms.add(new RoomChestsKapteynB(null, 0, 0, 0, null));
		this.dungeonGenerator.bossRooms.add(new RoomBossKapteynB(null, 0, 0, 0, null));
		this.dungeonGenerator.treasureRooms.add(new RoomTreasureKapteynB(null, 0, 0, 0, null));
	}

	private BiomeGenBase[] biomesForGeneration = { BiomeGenBaseKapteynB.kapteynB };
	private MapGenCaveMP caveGenerator = new MapGenCaveMP(KapteynBBlocks.kapteyn_b_block);

	private static int CRATER_PROB = 2000;

	private static int MID_HEIGHT = 64;
	private static int CHUNK_SIZE_X = 16;
	private static int CHUNK_SIZE_Y = 128;
	private static int CHUNK_SIZE_Z = 16;

	public ChunkProviderKapteynB(World world, long seed, boolean genFeature)
	{
		super(world, seed, genFeature, "");
		this.worldObj = world;
		this.rand = new Random(seed);
		this.noiseGen1 = new Gradient(this.rand.nextLong(), 4, 0.25F);
		this.noiseGen2 = new Gradient(this.rand.nextLong(), 4, 0.25F);
		this.noiseGen3 = new Gradient(this.rand.nextLong(), 1, 0.25F);
		this.noiseGen4 = new Gradient(this.rand.nextLong(), 1, 0.25F);
	}

	public void generateTerrain(int chunkX, int chunkZ, ChunkPrimer chunk)
	{
		this.noiseGen1.setFrequency(0.0125F);
		this.noiseGen2.setFrequency(0.015F);
		this.noiseGen3.setFrequency(0.01F);
		this.noiseGen4.setFrequency(0.02F);

		for (int x = 0; x < ChunkProviderKapteynB.CHUNK_SIZE_X; x++)
		{
			for (int z = 0; z < ChunkProviderKapteynB.CHUNK_SIZE_Z; z++)
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

				for (int y = 0; y < ChunkProviderKapteynB.CHUNK_SIZE_Y; y++)
				{
					if (y < ChunkProviderKapteynB.MID_HEIGHT + yDev)
					{
						chunk.setBlockState(this.getIndex(x, y, z), this.lowerBlock);
					}
				}
			}
		}
	}

	@Override
	public void func_180517_a(int x, int z, ChunkPrimer chunk, BiomeGenBase[] biomeGen)
	{
		int var5 = 20;

		for (int var8 = 0; var8 < 16; ++var8)
		{
			for (int var9 = 0; var9 < 16; ++var9)
			{
				int var12 = (int) (this.noiseGen4.getNoise(var8 + x * 16, var9 * z * 16) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int var13 = -1;
				IBlockState topBlock = this.topBlock;
				IBlockState fillBlock = this.fillBlock;

				for (int var16 = 127; var16 >= 0; --var16)
				{
					int index = this.getIndex(var8, var16, var9);

					if (var16 <= 0 + this.rand.nextInt(5))
					{
						chunk.setBlockState(index, Blocks.bedrock.getDefaultState());
					}
					else
					{
						Block block = chunk.getBlockState(index).getBlock();

						if (Blocks.air == block)
						{
							var13 = -1;
						}
						else if (block == this.lowerBlock)
						{
							if (var13 == -1)
							{
								if (var12 <= 0)
								{
									topBlock = Blocks.air.getDefaultState();
									fillBlock = this.lowerBlock;
								}
								else if (var16 >= var5 - -16 && var16 <= var5 + 1)
								{
									topBlock = this.topBlock;
									topBlock = this.fillBlock;
								}

								var13 = var12;

								if (var16 >= var5 - 1)
								{
									chunk.setBlockState(index, topBlock);
								}
								else if (var16 < var5 - 1 && var16 >= var5 - 2)
								{
									chunk.setBlockState(index, fillBlock);
								}
							}
							else if (var13 > 0)
							{
								--var13;
								chunk.setBlockState(index, fillBlock);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public Chunk provideChunk(int x, int z)
	{
		ChunkPrimer primer = new ChunkPrimer();
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		this.generateTerrain(x, z, primer);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		this.createCraters(x, z, primer);
		this.func_180517_a(x, z, primer, this.biomesForGeneration);
		this.caveGenerator.generate(this, this.worldObj, x, z, primer);
		this.ravineGenerator.func_175792_a(this, this.worldObj, x, z, primer);
		this.dungeonGenerator.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), x * 16, 25, z * 16, x, z, primer);
		Chunk var4 = new Chunk(this.worldObj, primer, x, z);
		var4.generateSkylightMap();
		return var4;
	}

	public void createCraters(int chunkX, int chunkZ, ChunkPrimer chunk)
	{
		for (int cx = chunkX - 2; cx <= chunkX + 2; cx++)
		{
			for (int cz = chunkZ - 2; cz <= chunkZ + 2; cz++)
			{
				for (int x = 0; x < ChunkProviderKapteynB.CHUNK_SIZE_X; x++)
				{
					for (int z = 0; z < ChunkProviderKapteynB.CHUNK_SIZE_Z; z++)
					{
						if (Math.abs(this.randFromPoint(cx * 16 + x, (cz * 16 + z) * 1000)) < this.noiseGen4.getNoise(x * ChunkProviderKapteynB.CHUNK_SIZE_X + x, cz * ChunkProviderKapteynB.CHUNK_SIZE_Z + z) / ChunkProviderKapteynB.CRATER_PROB)
						{
							Random random = new Random(cx * 16 + x + (cz * 16 + z) * 5000);
							EnumCraterSize cSize = EnumCraterSize.sizeArray[random.nextInt(EnumCraterSize.sizeArray.length)];
							int size = random.nextInt(cSize.MAX_SIZE - cSize.MIN_SIZE) + cSize.MIN_SIZE;
							this.makeCrater(cx * 16 + x, cz * 16 + z, chunkX * 16, chunkZ * 16, size, chunk);
						}
					}
				}
			}
		}
	}

	public void makeCrater(int craterX, int craterZ, int chunkX, int chunkZ, int size, ChunkPrimer chunk)
	{
		for (int x = 0; x < ChunkProviderKapteynB.CHUNK_SIZE_X; x++)
		{
			for (int z = 0; z < ChunkProviderKapteynB.CHUNK_SIZE_Z; z++)
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
						if (Blocks.air != chunk.getBlockState(this.getIndex(x, y, z)).getBlock() && helper <= yDev)
						{
							chunk.setBlockState(this.getIndex(x, y, z), Blocks.air.getDefaultState());
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
	public boolean chunkExists(int x, int z)
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

	public void decoratePlanet(World world, Random rand, int x, int z)
	{
		this.biomedecoratorplanet.decorate(world, rand, x, z);
	}

	@Override
	public void populate(IChunkProvider chunk, int chunkX, int chunkZ)
	{
		BlockFalling.fallInstantly = true;
		int var4 = chunkX * 16;
		int var5 = chunkZ * 16;
		this.worldObj.getBiomeGenForCoords(new BlockPos(var4 + 16, 0, var5 + 16));
		this.rand.setSeed(this.worldObj.getSeed());
		long var7 = this.rand.nextLong() / 2L * 2L + 1L;
		long var9 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(chunkX * var7 + chunkZ * var9 ^ this.worldObj.getSeed());
		this.dungeonGenerator.handleTileEntities(this.rand);
		this.decoratePlanet(this.worldObj, this.rand, var4, var5);
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(chunk, this.worldObj, this.rand, chunkX, chunkZ, false));

		boolean doGen = TerrainGen.populate(chunk, this.worldObj, this.rand, chunkX, chunkZ, false, EventType.FIRE);

		for (int i = 0; doGen && i < 4; i++)
		{
			if (this.rand.nextInt(5) == 0)
			{
				int x = var4 + this.rand.nextInt(16) + 8;
				int y = this.rand.nextInt(256 - 16) + 16;
				int z = var5 + this.rand.nextInt(16) + 8;
				new WorldGenSplashBlock(KapteynBBlocks.frozen_water_geyser.getDefaultState(), KapteynBBlocks.kapteyn_b_block.getDefaultState()).generate(this.worldObj, this.rand, new BlockPos(x, y, z));
			}
		}
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(chunk, this.worldObj, this.rand, chunkX, chunkZ, false));
		BlockFalling.fallInstantly = false;
	}

	@Override
	public boolean saveChunks(boolean save, IProgressUpdate update)
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
		return "KapteynBLevelSource";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List func_177458_a(EnumCreatureType type, BlockPos pos)
	{
		if (type == EnumCreatureType.MONSTER)
		{
			List monsters = new ArrayList();
			monsters.add(new SpawnListEntry(EntityEvolvedZombie.class, 100, 4, 4));
			monsters.add(new SpawnListEntry(EntityEvolvedSpider.class, 100, 4, 4));
			monsters.add(new SpawnListEntry(EntityEvolvedSkeleton.class, 100, 4, 4));
			monsters.add(new SpawnListEntry(EntityEvolvedCreeper.class, 100, 4, 4));
			monsters.add(new SpawnListEntry(EntityEvolvedEnderman.class, 100, 1, 4));
			return monsters;
		}
		return null;
	}
}
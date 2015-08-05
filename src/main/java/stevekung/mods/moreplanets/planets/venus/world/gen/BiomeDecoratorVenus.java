/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.world.gen;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import stevekung.mods.moreplanets.common.world.biome.BiomeDecoratorMP;
import stevekung.mods.moreplanets.common.world.gen.feature.WorldGenLiquidLakes;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;
import stevekung.mods.moreplanets.planets.venus.world.gen.feature.WorldGenSurfaceLava;

public class BiomeDecoratorVenus extends BiomeDecoratorMP
{
	private WorldGenerator dirtGen;
	private WorldGenerator ironGen;
	private WorldGenerator amberuzGen;
	private WorldGenerator sulfurGen;
	private WorldGenerator tinGen;
	private WorldGenerator copperGen;
	private WorldGenerator coalGen;
	private WorldGenerator goldGen;
	private WorldGenerator redstoneGen;
	private WorldGenerator sandGen;
	private WorldGenerator sandGen1;
	private WorldGenerator magmaGen;

	private int lavaLakePerChunk;

	public BiomeDecoratorVenus()
	{
		// Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
		this.dirtGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 32, 1, true, VenusBlocks.venus_block, 2);
		this.ironGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 8, 9, true, VenusBlocks.venus_block, 2);
		this.sulfurGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 8, 4, true, VenusBlocks.venus_block, 2);
		this.amberuzGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 4, 5, true, VenusBlocks.venus_block, 2);
		this.tinGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 8, 6, true, VenusBlocks.venus_block, 2);
		this.copperGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 8, 7, true, VenusBlocks.venus_block, 2);
		this.coalGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 16, 8, true, VenusBlocks.venus_block, 2);
		this.goldGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 8, 10, true, VenusBlocks.venus_block, 2);
		this.redstoneGen = new WorldGenMinableMeta(VenusBlocks.venus_redstone_ore, 8, 0, true, VenusBlocks.venus_block, 2);
		this.sandGen = new WorldGenMinableMeta(VenusBlocks.venus_sand, 32, 0, true, VenusBlocks.venus_block, 0);
		this.sandGen1 = new WorldGenMinableMeta(VenusBlocks.venus_sand, 32, 0, true, VenusBlocks.venus_block, 1);
		this.magmaGen = new WorldGenMinableMeta(VenusBlocks.venus_magma_rock, 4, 0, true, VenusBlocks.venus_block, 2);

		this.lavaLakePerChunk = 8;
	}

	@Override
	public void decorate(World world, Random rand, BiomeGenBase biome, BlockPos pos)
	{
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.currentWorld, this.randomGenerator, this.field_180294_c));

		this.generateOre(32, this.dirtGen, 0, 255);
		this.generateOre(16, this.ironGen, 0, 64);
		this.generateOre(16, this.sulfurGen, 0, 128);
		this.generateOre(4, this.amberuzGen, 0, 24);
		this.generateOre(16, this.tinGen, 0, 64);
		this.generateOre(16, this.copperGen, 0, 64);
		this.generateOre(16, this.coalGen, 0, 255);
		this.generateOre(8, this.goldGen, 0, 24);
		this.generateOre(8, this.redstoneGen, 0, 16);
		this.generateOre(32, this.sandGen, 0, 255);
		this.generateOre(32, this.sandGen1, 0, 255);
		this.generateOre(32, this.magmaGen, 0, 128);

		int i;
		int x;
		int y;
		int z;

		for (i = 0; i < this.lavaLakePerChunk; ++i)
		{
			if (this.randomGenerator.nextInt(20) == 0)
			{
				x = this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(32 - 16) + 16;
				z = this.randomGenerator.nextInt(16) + 8;
				new WorldGenLiquidLakes(Blocks.lava).generate(this.currentWorld, this.randomGenerator, new BlockPos(x, y, z));
			}
		}
		for (i = 0; i < 16; ++i)
		{
			x = this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(255);
			z = this.randomGenerator.nextInt(16) + 8;
			new WorldGenSurfaceLava().generate(this.currentWorld, this.randomGenerator, new BlockPos(x, y, z));
		}
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.currentWorld, this.randomGenerator, this.field_180294_c));
	}
}
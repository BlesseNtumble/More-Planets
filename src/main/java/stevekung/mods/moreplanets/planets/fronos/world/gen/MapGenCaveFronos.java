/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.common.blocks.IFronosGrass;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class MapGenCaveFronos extends MapGenBaseMeta
{
	public static int BREAK_THROUGH_CHANCE = 25; // 1 in n chance

	protected void generateLargeCaveNode(long seed, int x, int z, ChunkPrimer chunk, double par6, double par8, double par10)
	{
		this.generateCaveNode(seed, x, z, chunk, par6, par8, par10, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
	}

	protected void generateCaveNode(long seed, int x, int z, ChunkPrimer chunk, double par6, double par8, double par10, float par12, float par13, float par14, int par15, int par16, double par17)
	{
		double d4 = x * 16 + 8;
		double d5 = z * 16 + 8;
		float f3 = 0.0F;
		float f4 = 0.0F;
		Random rand = new Random(seed);

		if (par16 <= 0)
		{
			int j1 = this.range * 16 - 16;
			par16 = j1 - rand.nextInt(j1 / 4);
		}

		boolean flag = false;

		if (par15 == -1)
		{
			par15 = par16 / 2;
			flag = true;
		}

		int k1 = rand.nextInt(par16 / 2) + par16 / 4;

		for (boolean flag1 = rand.nextInt(6) == 0; par15 < par16; ++par15)
		{
			double d6 = 1.5D + MathHelper.sin(par15 * (float) Math.PI / par16) * par12 * 1.0F;
			double d7 = d6 * par17;
			float f5 = MathHelper.cos(par14);
			float f6 = MathHelper.sin(par14);
			par6 += MathHelper.cos(par13) * f5;
			par8 += f6;
			par10 += MathHelper.sin(par13) * f5;

			if (flag1)
			{
				par14 *= 0.92F;
			}
			else
			{
				par14 *= 0.7F;
			}

			par14 += f4 * 0.1F;
			par13 += f3 * 0.1F;
			f4 *= 0.9F;
			f3 *= 0.75F;
			f4 += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 2.0F;
			f3 += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 4.0F;

			if (!flag && par15 == k1 && par12 > 1.0F && par16 > 0)
			{
				this.generateCaveNode(rand.nextLong(), x, z, chunk, par6, par8, par10, rand.nextFloat() * 0.5F + 0.5F, par13 - (float) Math.PI / 2F, par14 / 3.0F, par15, par16, 1.0D);
				this.generateCaveNode(rand.nextLong(), x, z, chunk, par6, par8, par10, rand.nextFloat() * 0.5F + 0.5F, par13 + (float) Math.PI / 2F, par14 / 3.0F, par15, par16, 1.0D);
				return;
			}

			if (flag || rand.nextInt(4) != 0)
			{
				double d8 = par6 - d4;
				double d9 = par10 - d5;
				double d10 = par16 - par15;
				double d11 = par12 + 2.0F + 16.0F;

				if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11)
				{
					return;
				}

				if (par6 >= d4 - 16.0D - d6 * 2.0D && par10 >= d5 - 16.0D - d6 * 2.0D && par6 <= d4 + 16.0D + d6 * 2.0D && par10 <= d5 + 16.0D + d6 * 2.0D)
				{
					int l1 = MathHelper.floor_double(par6 - d6) - x * 16 - 1;
					int i2 = MathHelper.floor_double(par6 + d6) - x * 16 + 1;
					int j2 = MathHelper.floor_double(par8 - d7) - 1;
					int k2 = MathHelper.floor_double(par8 + d7) + 1;
					int l2 = MathHelper.floor_double(par10 - d6) - z * 16 - 1;
					int i3 = MathHelper.floor_double(par10 + d6) - z * 16 + 1;

					if (l1 < 0)
					{
						l1 = 0;
					}
					if (i2 > 16)
					{
						i2 = 16;
					}
					if (j2 < 1)
					{
						j2 = 1;
					}
					if (k2 > 120)
					{
						k2 = 120;
					}
					if (l2 < 0)
					{
						l2 = 0;
					}
					if (i3 > 16)
					{
						i3 = 16;
					}

					int j3;
					for (j3 = l1; j3 < i2; ++j3)
					{
						for (int l3 = l2; l3 < i3; ++l3)
						{
							for (int i4 = k2 + 1; i4 >= j2 - 1; --i4)
							{
								if (i4 >= 0 && i4 < 128)
								{
									if (i4 != j2 - 1 && j3 != l1 && j3 != i2 - 1 && l3 != l2 && l3 != i3 - 1)
									{
										i4 = j2;
									}
								}
							}
						}
					}

					if (true)
					{

						for (int localY = j2; localY < k2; localY++)
						{
							double yfactor = (localY + 0.5D - par8) / d7;
							double yfactorSq = yfactor * yfactor;

							for (int localX = l1; localX < i2; localX++)
							{
								double zfactor = (localX + x * 16 + 0.5D - par6) / d6;
								double zfactorSq = zfactor * zfactor;

								for (int localZ = l2; localZ < i3; localZ++)
								{
									double xfactor = (localZ + z * 16 + 0.5D - par10) / d6;
									double xfactorSq = xfactor * xfactor;

									if (xfactorSq + zfactorSq < 1.0D)
									{
										int coords = (localX * 16 + localZ) * 256 + localY;

										if (yfactor > -0.7D && xfactorSq + yfactorSq + zfactorSq < 1.0D)
										{
											if (chunk.getBlockState(coords) == FronosBlocks.fronos_dirt.getDefaultState() || chunk.getBlockState(coords) == FronosBlocks.fronos_block.getDefaultState())
											{
												chunk.setBlockState(coords, Blocks.air.getDefaultState());
											}
											else if (chunk.getBlockState(coords).getBlock() instanceof IFronosGrass && rand.nextInt(MapGenCaveFronos.BREAK_THROUGH_CHANCE) == 0)
											{
												chunk.setBlockState(coords, Blocks.air.getDefaultState());
											}
										}
									}
								}
							}
						}

						if (flag)
						{
							break;
						}
					}
				}
			}
		}
	}

	@Override
	protected void recursiveGenerate(World world, int x, int z, int orX, int orZ, ChunkPrimer chunk)
	{
		int var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(40) + 1) + 1);

		if (this.rand.nextInt(15) != 0)
		{
			var7 = 0;
		}

		for (int var8 = 0; var8 < var7; ++var8)
		{
			double var9 = x * 16 + this.rand.nextInt(16);
			double var11 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			double var13 = z * 16 + this.rand.nextInt(16);
			int var15 = 1;

			if (this.rand.nextInt(4) == 0)
			{
				this.generateLargeCaveNode(this.rand.nextLong(), orX, orZ, chunk, var9, var11, var13);
				var15 += this.rand.nextInt(4);
			}

			for (int var16 = 0; var16 < var15; ++var16)
			{
				float var17 = this.rand.nextFloat() * (float) Math.PI * 2.0F;
				float var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float var19 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

				if (this.rand.nextInt(10) == 0)
				{
					var19 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
				}
				this.generateCaveNode(this.rand.nextLong(), orX, orZ, chunk, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
			}
		}
	}
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.world.gen.blazepit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;

public abstract class MapGenVenusianBlazePitStructure extends MapGenBaseMeta
{
	protected Map<Long, StructureStart> structureMap = new HashMap<Long, StructureStart>();

	@Override
	protected void recursiveGenerate(World world, int x, int z, int orX, int orZ, ChunkPrimer chunk)
	{
		if (this.canSpawnStructureAtCoords(x, z))
		{
			StructureStart var7 = this.getStructureStart(x, z);
			this.structureMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(x, z)), var7);
		}
	}

	public boolean generateStructuresInChunk(World world, Random rand, ChunkCoordIntPair chunk)
	{
		int var5 = (chunk.chunkXPos << 4) + 8;
		int var6 = (chunk.chunkZPos << 4) + 8;
		boolean flag = false;
		Iterator<?> var8 = this.structureMap.values().iterator();

		while (var8.hasNext())
		{
			StructureStart var9 = (StructureStart) var8.next();

			if (var9 != null && var9.isSizeableStructure() && var9.getBoundingBox().intersectsWith(var5, var6, var5 + 15, var6 + 15))
			{
				var9.generateStructure(world, rand, new StructureBoundingBox(var5, var6, var5 + 15, var6 + 15));
				flag = true;
			}
		}
		return flag;
	}

	protected abstract boolean canSpawnStructureAtCoords(int x, int z);
	protected abstract StructureStart getStructureStart(int x, int z);
}
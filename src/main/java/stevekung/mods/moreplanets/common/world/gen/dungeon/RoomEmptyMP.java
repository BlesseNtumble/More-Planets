/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.world.gen.dungeon;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonBoundingBox;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonRoom;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

public class RoomEmptyMP extends DungeonRoom
{
	int sizeX;
	int sizeY;
	int sizeZ;

	public RoomEmptyMP(MapGenDungeon dungeon, int x, int y, int z, EnumFacing facing)
	{
		super(dungeon, x, y, z, facing);

		if (this.worldObj != null)
		{
			Random rand = new Random(this.worldObj.getSeed() * x * y * 57 * z);
			this.sizeX = rand.nextInt(4) + 5;
			this.sizeY = rand.nextInt(2) + 4;
			this.sizeZ = rand.nextInt(4) + 5;
		}
	}

	@Override
	public void generate(Block[] chunk, byte[] meta, int cx, int cz)
	{
		for (int i = this.posX - 1; i <= this.posX + this.sizeX; i++)
		{
			for (int j = this.posY - 1; j <= this.posY + this.sizeY; j++)
			{
				for (int k = this.posZ - 1; k <= this.posZ + this.sizeZ; k++)
				{
					if (i == this.posX - 1 || i == this.posX + this.sizeX || j == this.posY - 1 || j == this.posY + this.sizeY || k == this.posZ - 1 || k == this.posZ + this.sizeZ)
					{
						this.placeBlock(chunk, meta, i, j, k, cx, cz, this.dungeonInstance.DUNGEON_WALL_ID, this.dungeonInstance.DUNGEON_WALL_META);
					}
					else
					{
						this.placeBlock(chunk, meta, i, j, k, cx, cz, Blocks.air, 0);
					}
				}
			}
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
		return new RoomEmptyMP(dungeon, x, y, z, facing);
	}

	@Override
	protected void handleTileEntities(Random rand) {}
}
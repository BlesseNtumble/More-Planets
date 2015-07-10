/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.world.gen.dungeon;

import java.util.ArrayList;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonBoundingBox;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonRoom;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.siriusb.tileentities.TileEntitySiriusBTreasureChest;

public class RoomTreasureSiriusB extends DungeonRoom
{
	int sizeX;
	int sizeY;
	int sizeZ;

	private ArrayList<BlockPos> chests = new ArrayList<BlockPos>();

	public RoomTreasureSiriusB(MapGenDungeon dungeon, int x, int y, int z, EnumFacing facing)
	{
		super(dungeon, x, y, z, facing);

		if (this.worldObj != null)
		{
			Random rand = new Random(this.worldObj.getSeed() * x * y * 57 * z);
			this.sizeX = rand.nextInt(6) + 7;
			this.sizeY = rand.nextInt(2) + 5;
			this.sizeZ = rand.nextInt(6) + 7;
		}
	}

	@Override
	public void generate(Block[] chunk, byte[] meta, int cx, int cz)
	{
		for (int i = this.posX - 1; i <= this.posX + this.sizeX; i++)
		{
			for (int k = this.posZ - 1; k <= this.posZ + this.sizeZ; k++)
			{
				for (int j = this.posY - 1; j <= this.posY + this.sizeY; j++)
				{
					if (i == this.posX - 1 || i == this.posX + this.sizeX || j == this.posY - 1 || j == this.posY + this.sizeY || k == this.posZ - 1 || k == this.posZ + this.sizeZ)
					{
						this.placeBlock(chunk, meta, i, j, k, cx, cz, this.dungeonInstance.DUNGEON_WALL_ID, this.dungeonInstance.DUNGEON_WALL_META);
					}
					else
					{
						if ((i == this.posX || i == this.posX + this.sizeX - 1) && (k == this.posZ || k == this.posZ + this.sizeZ - 1))
						{
							this.placeBlock(chunk, meta, i, j, k, cx, cz, SiriusBBlocks.sirius_glowstone, 0);
						}
						else
						{
							this.placeBlock(chunk, meta, i, j, k, cx, cz, Blocks.air, 0);
						}
					}
				}
			}
		}

		int hx = (this.posX + this.posX + this.sizeX) / 2;
		int hz = (this.posZ + this.posZ + this.sizeZ) / 2;

		if (this.placeBlock(chunk, meta, hx, this.posY, hz, cx, cz, SiriusBBlocks.sirius_b_treasure_chest, 0))
		{
			this.chests.add(new BlockPos(hx, this.posY, hz));
		}
	}

	@Override
	public DungeonBoundingBox getBoundingBox()
	{
		return new DungeonBoundingBox(this.posX, this.posZ, this.posX + this.sizeX, this.posZ + this.sizeZ);
	}

	public ItemStack getGuaranteedLoot(Random rand)
	{
		switch (rand.nextInt(4))
		{
		case 0:
			return new ItemStack(SiriusBItems.sirius_obsidian_bucket, 1, 0);
		case 1:
			return new ItemStack(SiriusBItems.sirius_glowstone_dust, rand.nextInt(16) + 1, 0);
		case 2:
		default:
			return new ItemStack(SiriusBItems.sirius_fire_charge, rand.nextInt(6) + 1, 0);
		}
	}

	@Override
	protected void handleTileEntities(Random rand)
	{
		for (BlockPos chestCoords : this.chests)
		{
			TileEntity chest = this.worldObj.getTileEntity(chestCoords);

			if (chest != null && chest instanceof TileEntitySiriusBTreasureChest)
			{
				ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
				WeightedRandomChestContent.generateChestContents(rand, info.getItems(rand), (TileEntitySiriusBTreasureChest) chest, info.getCount(rand));
				((TileEntitySiriusBTreasureChest) chest).setInventorySlotContents(rand.nextInt(((TileEntitySiriusBTreasureChest) chest).getSizeInventory()), this.getGuaranteedLoot(rand));
			}
		}
	}

	@Override
	protected DungeonRoom makeRoom(MapGenDungeon dungeon, int x, int y, int z, EnumFacing facing)
	{
		return new RoomTreasureSiriusB(dungeon, x, y, z, facing);
	}
}
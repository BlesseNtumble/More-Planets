/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.world.gen.blazepit;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureSiriusBlazePitStart extends StructureStart
{
	public StructureSiriusBlazePitStart(World world, Random random, int x, int z)
	{
		int var5 = (x << 4) + 8;
		int var6 = (z << 4) + 8;
		ComponentSiriusBlazePitRoom room = new ComponentSiriusBlazePitRoom(0, world, random, var5, 90, var6, 40, 7, 0);

		if (room != null)
		{
			this.components.add(room);
			room.buildComponent(room, this.components, random);
		}
		this.updateBoundingBox();
	}
}
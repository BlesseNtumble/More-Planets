/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.util;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.mercury.dimension.WorldProviderMercury;
import stevekung.mods.moreplanets.planets.pluto.dimension.WorldProviderPluto;

public class WorldUtilMP
{
	public static boolean isMercuryWorld(World world, BlockPos pos)
	{
		return world.provider instanceof WorldProviderMercury && world.isDaytime() && world.canBlockSeeSky(pos);
	}

	public static boolean isPlutoWorld(World world)
	{
		return world.provider instanceof WorldProviderPluto;
	}

	public static boolean isEuropaWorld(World world)
	{
		return false;//world.provider instanceof WorldProviderEuropa; TODO
	}
}
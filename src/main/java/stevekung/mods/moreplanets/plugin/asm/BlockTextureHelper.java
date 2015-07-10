/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.plugin.asm;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;
import stevekung.mods.stevecore.RegisterHelper;

@SideOnly(Side.CLIENT)
public class BlockTextureHelper
{
	public static TextureAtlasSprite getTexture(IBlockState state)
	{
		Block block = state.getBlock();

		if (block == DionaBlocks.diona_ancient_chest)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/diona_ancient_chest");
		}
		else if (block == PolongniusBlocks.polongnius_ancient_chest)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/polongnius_ancient_chest");
		}
		else if (block == NibiruBlocks.nibiru_ancient_chest)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/nibiru_ancient_chest");
		}
		else if (block == KoentusBlocks.koentus_ancient_chest)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/koentus_ancient_chest");
		}
		else if (block == FronosBlocks.fronos_ancient_chest)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/fronos_ancient_chest");
		}
		else if (block == KapteynBBlocks.kapteyn_b_ancient_chest)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/kapteyn_b_ancient_chest");
		}
		else if (block == SiriusBBlocks.sirius_b_ancient_chest)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/sirius_b_ancient_chest");
		}
		else if (block == MercuryBlocks.mercury_ancient_chest)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/mercury_ancient_chest");
		}
		else if (block == VenusBlocks.venus_ancient_chest)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/venus_ancient_chest");
		}
		else if (block == PlutoBlocks.pluto_ancient_chest)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/pluto_ancient_chest");
		}
		else if (block == KapteynBBlocks.icy_poison_crystal)
		{
			return RegisterHelper.registerBlockTexture("moreplanets:blocks/fallen_ice_crystal_meteor");
		}
		return RegisterHelper.getTexture(state);
	}
}
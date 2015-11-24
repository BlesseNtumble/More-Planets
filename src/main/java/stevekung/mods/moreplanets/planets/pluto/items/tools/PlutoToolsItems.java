/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.stevecore.CommonRegisterHelper;

public class PlutoToolsItems
{
	public static Item xeonium_pickaxe;
	public static Item xeonium_axe;
	public static Item xeonium_hoe;
	public static Item xeonium_shovel;
	public static Item xeonium_sword;

	// Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability
	public static ToolMaterial xeonium = EnumHelper.addToolMaterial("xeonium", 4, 0, 11.0F, 5.0F, 8);

	public static void init()
	{
		// Init
		PlutoToolsItems.xeonium_pickaxe = new ItemXeoniumPickaxe("xeonium_pickaxe", PlutoToolsItems.xeonium);
		PlutoToolsItems.xeonium_axe = new ItemXeoniumAxe("xeonium_axe", PlutoToolsItems.xeonium);
		PlutoToolsItems.xeonium_hoe = new ItemXeoniumHoe("xeonium_hoe", PlutoToolsItems.xeonium);
		PlutoToolsItems.xeonium_shovel = new ItemXeoniumShovel("xeonium_shovel", PlutoToolsItems.xeonium);
		PlutoToolsItems.xeonium_sword = new ItemXeoniumSword("xeonium_sword", PlutoToolsItems.xeonium);

		// Register
		CommonRegisterHelper.registerItem(PlutoToolsItems.xeonium_pickaxe);
		CommonRegisterHelper.registerItem(PlutoToolsItems.xeonium_axe);
		CommonRegisterHelper.registerItem(PlutoToolsItems.xeonium_hoe);
		CommonRegisterHelper.registerItem(PlutoToolsItems.xeonium_shovel);
		CommonRegisterHelper.registerItem(PlutoToolsItems.xeonium_sword);

		// Set harvest level
		CommonRegisterHelper.setToolHarvestLevel(PlutoToolsItems.xeonium_pickaxe, "pickaxe", 3);
		CommonRegisterHelper.setToolHarvestLevel(PlutoToolsItems.xeonium_axe, "axe", 3);
		CommonRegisterHelper.setToolHarvestLevel(PlutoToolsItems.xeonium_shovel, "shovel", 3);
	}
}
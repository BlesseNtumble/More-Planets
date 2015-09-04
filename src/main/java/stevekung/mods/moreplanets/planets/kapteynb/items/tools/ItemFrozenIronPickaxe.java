/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items.tools;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;

public class ItemFrozenIronPickaxe extends ItemPickaxe
{
	public ItemFrozenIronPickaxe(String name, ToolMaterial material)
	{
		super(material);
		this.setUnlocalizedName(name);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MorePlanetsCore.mpToolsTab;
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase entity2)
	{
		entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60));
		itemStack.damageItem(1, entity2);
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemStack)
	{
		return ClientProxyCore.galacticraftItem;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemStack, ItemStack itemStack2)
	{
		if (itemStack2.getItem() == KapteynBItems.kapteyn_b_item && itemStack2.getItemDamage() == 2)
		{
			return true;
		}
		return false;
	}
}
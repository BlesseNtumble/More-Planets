/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.items.armor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.stevecore.CommonRegisterHelper;

public class NibiruArmorItems
{
    public static Item red_gem_helmet;
    public static Item red_gem_chestplate;
    public static Item red_gem_leggings;
    public static Item red_gem_boots;
    public static Item norium_helmet;
    public static Item norium_chestplate;
    public static Item norium_leggings;
    public static Item norium_boots;
    public static Item breathable_red_gem_helmet;
    public static Item breathable_norium_helmet;

    // Name,Durability,ReductionAmounts[Helm,Chest,Leg,Boot],Enchantability
    public static ArmorMaterial red_gem = EnumHelper.addArmorMaterial("red_gem", "red_gem", 40, new int[] { 10, 14, 11, 6 }, 16);
    public static ArmorMaterial norium = EnumHelper.addArmorMaterial("norium", "norium", 40, new int[] { 10, 14, 11, 8 }, 16);

    public static void init()
    {
        // Init
        NibiruArmorItems.red_gem_helmet = new ArmorRedGem("red_gem_helmet", NibiruArmorItems.red_gem, 7, 0);
        NibiruArmorItems.red_gem_chestplate = new ArmorRedGem("red_gem_chestplate", NibiruArmorItems.red_gem, 7, 1);
        NibiruArmorItems.red_gem_leggings = new ArmorRedGem("red_gem_leggings", NibiruArmorItems.red_gem, 7, 2);
        NibiruArmorItems.red_gem_boots = new ArmorRedGem("red_gem_boots", NibiruArmorItems.red_gem, 7, 3);
        NibiruArmorItems.norium_helmet = new ArmorNorium("norium_helmet", NibiruArmorItems.norium, 7, 0);
        NibiruArmorItems.norium_chestplate = new ArmorNorium("norium_chestplate", NibiruArmorItems.norium, 7, 1);
        NibiruArmorItems.norium_leggings = new ArmorNorium("norium_leggings", NibiruArmorItems.norium, 7, 2);
        NibiruArmorItems.norium_boots = new ArmorNorium("norium_boots", NibiruArmorItems.norium, 7, 3);
        NibiruArmorItems.breathable_red_gem_helmet = new ArmorBreathableRedGem("breathable_red_gem_helmet", NibiruArmorItems.red_gem, 7, 0);
        NibiruArmorItems.breathable_norium_helmet = new ArmorBreathableNorium("breathable_norium_helmet", NibiruArmorItems.norium, 7, 0);

        // Register
        CommonRegisterHelper.registerItem(NibiruArmorItems.red_gem_helmet);
        CommonRegisterHelper.registerItem(NibiruArmorItems.red_gem_chestplate);
        CommonRegisterHelper.registerItem(NibiruArmorItems.red_gem_leggings);
        CommonRegisterHelper.registerItem(NibiruArmorItems.red_gem_boots);
        CommonRegisterHelper.registerItem(NibiruArmorItems.norium_helmet);
        CommonRegisterHelper.registerItem(NibiruArmorItems.norium_chestplate);
        CommonRegisterHelper.registerItem(NibiruArmorItems.norium_leggings);
        CommonRegisterHelper.registerItem(NibiruArmorItems.norium_boots);
        CommonRegisterHelper.registerItem(NibiruArmorItems.breathable_red_gem_helmet);
        CommonRegisterHelper.registerItem(NibiruArmorItems.breathable_norium_helmet);
    }
}
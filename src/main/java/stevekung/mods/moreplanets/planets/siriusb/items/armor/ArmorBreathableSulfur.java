/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.common.items.armor.ItemBreathableArmor;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;

public class ArmorBreathableSulfur extends ItemBreathableArmor
{
    public ArmorBreathableSulfur(String name, ArmorMaterial material, int render, int type)
    {
        super(material, render, type);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if (stack.getItem() == SiriusBArmorItems.breathable_sulfur_helmet)
        {
            return "moreplanets:textures/model/armor/breathable_sulfur.png";
        }
        return null;
    }

    @Override
    protected Item getRepairItems()
    {
        return SiriusBItems.sirius_b_item;
    }

    @Override
    protected int getRepairItemsMetadata()
    {
        return 4;
    }

    @Override
    protected EnumGearType getGearType()
    {
        return EnumGearType.HELMET;
    }

    @Override
    protected Item getBreathableArmor()
    {
        return SiriusBArmorItems.breathable_sulfur_helmet;
    }
}
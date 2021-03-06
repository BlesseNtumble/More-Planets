/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.items.ItemFoodMP;

public class ItemFruitsJuice extends ItemFoodMP
{
    public ItemFruitsJuice(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.DRINK;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityPlayer player)
    {
        super.onItemUseFinish(itemStack, world, player);

        if (!player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle)))
        {
            player.entityDropItem(new ItemStack(Items.glass_bottle, 1, 0), 0.0F);
        }

        if (!world.isRemote)
        {
            switch (itemStack.getItemDamage())
            {
            case 0:
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 1));
                break;
            case 1:
                player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 2400, 1));
                break;
            case 2:
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2400, 1));
                break;
            case 3:
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2400, 1));
                break;
            }
        }
        return itemStack;
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return 5;
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return 0.6F;
    }

    @Override
    protected String[] getItemVariantsName()
    {
        return new String[] { "strawberry_juice", "berry_juice", "kiwi_juice", "lemon_juice" };
    }
}
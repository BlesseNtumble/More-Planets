/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.entities.ai.EntityAITemptMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;

public class EntityCheeseCow extends EntityAnimal implements IEntityBreathable
{
    public EntityCheeseCow(World world)
    {
        super(world);
        this.setSize(0.9F, 1.3F);
        ((PathNavigateGround)this.getNavigator()).func_179690_a(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITemptMP(this, 1.25D, new ItemStack(PolongniusItems.cheese_food, 1, 0), false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.worldObj.getBlockState(this.getPosition().down()) == PolongniusBlocks.polongnius_block.getDefaultState();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
    }

    @Override
    protected int getExperiencePoints(EntityPlayer player)
    {
        return 1 + this.worldObj.rand.nextInt(5);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack)
    {
        return itemStack != null && itemStack.getItem() == PolongniusItems.cheese_food && itemStack.getItemDamage() == 0;
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.cow.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.cow.hurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.cow.hurt";
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block)
    {
        this.playSound("mob.cow.step", 0.15F, 1.0F);
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemStack = player.inventory.getCurrentItem();

        if (itemStack != null && itemStack.getItem() == FronosItems.cup && itemStack.getItemDamage() == 0)
        {
            if (itemStack.stackSize-- == 1 && !player.capabilities.isCreativeMode)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(FronosItems.cup, 1, 4));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 4)) && !player.capabilities.isCreativeMode)
            {
                player.entityDropItem(new ItemStack(FronosItems.cup, 1, 0), 1.0F);
            }
            return true;
        }
        else
        {
            return super.interact(player);
        }
    }

    @Override
    public EntityCheeseCow createChild(EntityAgeable entity)
    {
        return new EntityCheeseCow(this.worldObj);
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + fortune);

        for (int i = 0; i < j; ++i)
        {
            this.entityDropItem(new ItemStack(PolongniusItems.polongnius_item, 1, 10), 1.0F);
        }

        j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + fortune);

        for (int i = 0; i < j; ++i)
        {
            if (this.isBurning())
            {
                this.entityDropItem(new ItemStack(PolongniusItems.cheese_food, 1, 2), 1.0F);
            }
            else
            {
                this.entityDropItem(new ItemStack(PolongniusItems.cheese_food, 1, 1), 1.0F);
            }
        }
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }
}
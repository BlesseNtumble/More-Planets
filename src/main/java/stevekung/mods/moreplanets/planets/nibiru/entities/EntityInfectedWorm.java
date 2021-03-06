/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.planets.mars.entities.EntitySlimeling;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.entities.IEntityLivingPlanet;
import stevekung.mods.moreplanets.common.util.EnumDimensionType;
import stevekung.mods.moreplanets.core.init.MPPotions;

public class EntityInfectedWorm extends EntityMob implements IEntityBreathable, IEntityLivingPlanet
{
    public EntityInfectedWorm(World world)
    {
        super(world);
        this.setSize(0.2F, 0.2F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 0.25F, true));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityEvolvedZombie.class, false, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityEvolvedSkeleton.class, false, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityEvolvedSpider.class, false, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityEvolvedCreeper.class, false, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntitySlimeling.class, false));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public float getEyeHeight()
    {
        return 0.1F;
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.silverfish.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.silverfish.hit";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.silverfish.kill";
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (super.attackEntityAsMob(entity))
        {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MPPotions.infected_gas.id, 20, 0));
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block)
    {
        this.worldObj.playSoundAtEntity(this, "mob.silverfish.step", 1.0F, 1.0F);
    }

    @Override
    protected Item getDropItem()
    {
        return null;
    }

    @Override
    public void onUpdate()
    {
        this.renderYawOffset = this.rotationYaw;
        super.onUpdate();
    }

    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        if (super.getCanSpawnHere())
        {
            EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 5.0D);
            return player == null;
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public EnumDimensionType canLivingInDimension()
    {
        return EnumDimensionType.NIBIRU;
    }
}
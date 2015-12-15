package stevekung.mods.moreplanets.planets.pluto.entities.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.pluto.entities.EntityPlutoDog;

public class EntityAIPlutoDogBeg extends EntityAIBase
{
    private EntityPlutoDog theWolf;
    private EntityPlayer thePlayer;
    private World worldObject;
    private float minPlayerDistance;
    private int field_75384_e;

    public EntityAIPlutoDogBeg(EntityPlutoDog p_i1617_1_, float p_i1617_2_)
    {
        this.theWolf = p_i1617_1_;
        this.worldObject = p_i1617_1_.worldObj;
        this.minPlayerDistance = p_i1617_2_;
        this.setMutexBits(2);
    }

    @Override
    public boolean shouldExecute()
    {
        this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theWolf, this.minPlayerDistance);
        return this.thePlayer == null ? false : this.hasPlayerGotBoneInHand(this.thePlayer);
    }

    @Override
    public boolean continueExecuting()
    {
        return !this.thePlayer.isEntityAlive() ? false : this.theWolf.getDistanceSqToEntity(this.thePlayer) > this.minPlayerDistance * this.minPlayerDistance ? false : this.field_75384_e > 0 && this.hasPlayerGotBoneInHand(this.thePlayer);
    }

    @Override
    public void startExecuting()
    {
        this.theWolf.func_70918_i(true);
        this.field_75384_e = 40 + this.theWolf.getRNG().nextInt(40);
    }

    @Override
    public void resetTask()
    {
        this.theWolf.func_70918_i(false);
        this.thePlayer = null;
    }

    @Override
    public void updateTask()
    {
        this.theWolf.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, this.theWolf.getVerticalFaceSpeed());
        --this.field_75384_e;
    }

    private boolean hasPlayerGotBoneInHand(EntityPlayer p_75382_1_)
    {
        ItemStack itemstack = p_75382_1_.inventory.getCurrentItem();
        return itemstack == null ? false : !this.theWolf.isTamed() && itemstack.getItem() == Items.bone ? true : this.theWolf.isBreedingItem(itemstack);
    }
}
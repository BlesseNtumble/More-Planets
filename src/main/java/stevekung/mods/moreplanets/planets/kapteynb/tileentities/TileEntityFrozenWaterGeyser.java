/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.tileentities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityFrozenWaterGeyser extends TileEntity
{
    @Override
    public void updateEntity()
    {
        if (!World.doesBlockHaveSolidTopSurface(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord))
        {
            if (!this.worldObj.isRemote)
            {
                List<EntityLivingBase> living = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 8, this.zCoord + 1));

                if (living.size() > 0)
                {
                    living.get(0).motionY = 1.5D;
                    living.get(0).fallDistance = 0.0F;
                    living.get(0).extinguish();
                    living.get(0).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
                }
            }
            else
            {
                List<EntityPlayer> player = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 8, this.zCoord + 1));

                if (player.size() > 0)
                {
                    if (!player.get(0).capabilities.isFlying)
                    {
                        player.get(0).motionY = 1.5D;
                        player.get(0).fallDistance = 0.0F;
                        player.get(0).extinguish();
                        player.get(0).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
                    }
                }
            }
        }
    }
}
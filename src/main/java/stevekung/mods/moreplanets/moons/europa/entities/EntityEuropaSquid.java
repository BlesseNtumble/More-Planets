/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.entities;

import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.init.MPItems;

public class EntityEuropaSquid extends EntityWaterMob
{
	public float squidPitch;
	public float prevSquidPitch;
	public float squidYaw;
	public float prevSquidYaw;
	public float squidRotation;
	public float prevSquidRotation;
	public float tentacleAngle;
	public float lastTentacleAngle;
	private float randomMotionSpeed;
	private float rotationVelocity;
	private float field_70871_bB;
	private float randomMotionVecX;
	private float randomMotionVecY;
	private float randomMotionVecZ;

	public EntityEuropaSquid(World world)
	{
		super(world);
		this.setSize(0.95F, 0.95F);
		this.rand.setSeed(1 + this.getEntityId());
		this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
		this.tasks.addTask(0, new EntityEuropaSquid.AIMoveRandom());
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1037);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 0.5F;
	}

	@Override
	protected String getLivingSound()
	{
		return null;
	}

	@Override
	protected String getHurtSound()
	{
		return null;
	}

	@Override
	protected String getDeathSound()
	{
		return null;
	}

	@Override
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected void dropFewItems(boolean drop, int fortune)
	{
		int j = this.rand.nextInt(3 + fortune) + 1;

		for (int k = 0; k < j; ++k)
		{
			this.entityDropItem(new ItemStack(Items.dye, 1, EnumDyeColor.BLACK.getDyeDamage()), 0.0F);
		}
	}

	@Override
	public boolean isInWater()
	{
		return this.worldObj.handleMaterialAcceleration(this.getEntityBoundingBox().expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		this.prevSquidPitch = this.squidPitch;
		this.prevSquidYaw = this.squidYaw;
		this.prevSquidRotation = this.squidRotation;
		this.lastTentacleAngle = this.tentacleAngle;
		this.squidRotation += this.rotationVelocity;

		if (this.squidRotation > Math.PI * 2D)
		{
			if (this.worldObj.isRemote)
			{
				this.squidRotation = (float)Math.PI * 2F;
			}
			else
			{
				this.squidRotation = (float)(this.squidRotation - Math.PI * 2D);

				if (this.rand.nextInt(10) == 0)
				{
					this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
				}

				this.worldObj.setEntityState(this, (byte)19);
			}
		}

		if (this.inWater)
		{
			float f;

			if (this.squidRotation < (float)Math.PI)
			{
				f = this.squidRotation / (float)Math.PI;
				this.tentacleAngle = MathHelper.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;

				if (f > 0.75D)
				{
					this.randomMotionSpeed = 1.0F;
					this.field_70871_bB = 1.0F;
				}
				else
				{
					this.field_70871_bB *= 0.8F;
				}
			}
			else
			{
				this.tentacleAngle = 0.0F;
				this.randomMotionSpeed *= 0.9F;
				this.field_70871_bB *= 0.99F;
			}

			if (!this.worldObj.isRemote)
			{
				this.motionX = this.randomMotionVecX * this.randomMotionSpeed;
				this.motionY = this.randomMotionVecY * this.randomMotionSpeed;
				this.motionZ = this.randomMotionVecZ * this.randomMotionSpeed;
			}

			f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI - this.renderYawOffset) * 0.1F;
			this.rotationYaw = this.renderYawOffset;
			this.squidYaw = (float)(this.squidYaw + Math.PI * this.field_70871_bB * 1.5D);
			this.squidPitch += (-((float)Math.atan2(f, this.motionY)) * 180.0F / (float)Math.PI - this.squidPitch) * 0.1F;
		}
		else
		{
			this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float)Math.PI * 0.25F;

			if (!this.worldObj.isRemote)
			{
				this.motionX = 0.0D;
				this.motionY -= 0.08D;
				this.motionY *= 0.9800000190734863D;
				this.motionZ = 0.0D;
			}
			this.squidPitch = (float)(this.squidPitch + (-90.0F - this.squidPitch) * 0.02D);
		}
	}

	@Override
	public void moveEntityWithHeading(float strafe, float forward)
	{
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return this.posY > 16.0D && this.posY < 64.0D && super.getCanSpawnHere();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte health)
	{
		if (health == 19)
		{
			this.squidRotation = 0.0F;
		}
		else
		{
			super.handleHealthUpdate(health);
		}
	}

	public void func_175568_b(float x, float y, float z)
	{
		this.randomMotionVecX = x;
		this.randomMotionVecY = y;
		this.randomMotionVecZ = z;
	}

	public boolean func_175567_n()
	{
		return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
	}

	class AIMoveRandom extends EntityAIBase
	{
		private EntityEuropaSquid squid = EntityEuropaSquid.this;

		@Override
		public boolean shouldExecute()
		{
			return true;
		}

		@Override
		public void updateTask()
		{
			int i = this.squid.getAge();

			if (i > 100)
			{
				this.squid.func_175568_b(0.0F, 0.0F, 0.0F);
			}
			else if (this.squid.getRNG().nextInt(50) == 0 || !this.squid.inWater || !this.squid.func_175567_n())
			{
				float f = this.squid.getRNG().nextFloat() * (float)Math.PI * 2.0F;
				float f1 = MathHelper.cos(f) * 0.2F;
				float f2 = -0.1F + this.squid.getRNG().nextFloat() * 0.2F;
				float f3 = MathHelper.sin(f) * 0.2F;
				this.squid.func_175568_b(f1, f2, f3);
			}
		}
	}
}
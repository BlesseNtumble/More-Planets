/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.world.gen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianVillager;

public abstract class ComponentVenusVillage extends StructureComponent
{
    static
    {
        MapGenStructureIO.registerStructure(StructureVenusVillageStart.class, "VenusVillage");
        MapGenStructureIO.registerStructureComponent(ComponentVenusVillageField.class, "VenusField1");
        MapGenStructureIO.registerStructureComponent(ComponentVenusVillageField2.class, "VenusField2");
        MapGenStructureIO.registerStructureComponent(ComponentVenusVillageHouse.class, "VenusHouse");
        MapGenStructureIO.registerStructureComponent(ComponentVenusVillageRoadPiece.class, "VenusRoadPiece");
        MapGenStructureIO.registerStructureComponent(ComponentVenusVillagePathGen.class, "VenusPath");
        MapGenStructureIO.registerStructureComponent(ComponentVenusVillageTorch.class, "VenusTorch");
        MapGenStructureIO.registerStructureComponent(ComponentVenusVillageStartPiece.class, "VenusWell");
        MapGenStructureIO.registerStructureComponent(ComponentVenusVillageHut.class, "VenusHut");
    }

    private int villagersSpawned;
    protected ComponentVenusVillageStartPiece startPiece;

    public ComponentVenusVillage() {}

    protected ComponentVenusVillage(ComponentVenusVillageStartPiece component, int type)
    {
        super(type);
        this.startPiece = component;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("VCount", this.villagersSpawned);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound nbt)
    {
        this.villagersSpawned = nbt.getInteger("VCount");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected StructureComponent getNextComponentNN(ComponentVenusVillageStartPiece component, List list, Random rand, int par4, int par5)
    {
        if (this.coordBaseMode != null)
        {
            switch (SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
            {
            case 0:
                return StructureVenusVillagePieces.getNextStructureComponent(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, EnumFacing.WEST, this.getComponentType());
            case 1:
                return StructureVenusVillagePieces.getNextStructureComponent(component, list, rand, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
            case 2:
                return StructureVenusVillagePieces.getNextStructureComponent(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, EnumFacing.WEST, this.getComponentType());
            case 3:
                return StructureVenusVillagePieces.getNextStructureComponent(component, list, rand, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
            }
        }
        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected StructureComponent getNextComponentPP(ComponentVenusVillageStartPiece component, List list, Random rand, int par4, int par5)
    {
        if (this.coordBaseMode != null)
        {
            switch (SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
            {
            case 0:
                return StructureVenusVillagePieces.getNextStructureComponent(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, EnumFacing.EAST, this.getComponentType());
            case 1:
                return StructureVenusVillagePieces.getNextStructureComponent(component, list, rand, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
            case 2:
                return StructureVenusVillagePieces.getNextStructureComponent(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, EnumFacing.EAST, this.getComponentType());
            case 3:
                return StructureVenusVillagePieces.getNextStructureComponent(component, list, rand, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
            }
        }
        return null;
    }

    protected int getAverageGroundLevel(World world, StructureBoundingBox box)
    {
        int i = 0;
        int j = 0;

        for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ;)
        {
            for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
            {
                BlockPos pos = new BlockPos(l, 64, k);

                if (box.func_175898_b(pos))
                {
                    i += Math.max(world.getTopSolidOrLiquidBlock(pos).getY(), world.provider.getAverageGroundLevel());
                    ++j;
                }
            }
            if (j == 0)
            {
                return -1;
            }
            else
            {
                return i / j;
            }
        }
        return 0;
    }

    protected static boolean canVillageGoDeeper(StructureBoundingBox box)
    {
        return box != null && box.minY > 10;
    }

    protected void spawnVillagers(World world, StructureBoundingBox box, int x, int y, int z, int par6)
    {
        if (this.villagersSpawned < par6)
        {
            for (int i1 = this.villagersSpawned; i1 < par6; ++i1)
            {
                int j1 = this.getXWithOffset(x + i1, z);
                int k1 = this.getYWithOffset(y);
                int l1 = this.getZWithOffset(x + i1, z);

                if (!box.func_175898_b(new BlockPos(j1, k1, l1)))
                {
                    break;
                }
                ++this.villagersSpawned;
                EntityVenusianVillager entityvillager = new EntityVenusianVillager(world);
                entityvillager.setLocationAndAngles(j1 + 0.5D, k1, l1 + 0.5D, 0.0F, 0.0F);
                world.spawnEntityInWorld(entityvillager);
            }
        }
    }

    protected IBlockState func_175847_a(IBlockState state)
    {
        return state;
    }

    @Override
    protected void func_175811_a(World world, IBlockState state, int x, int y, int z, StructureBoundingBox box)
    {
        IBlockState iblockstate1 = this.func_175847_a(state);
        super.func_175811_a(world, iblockstate1, x, y, z, box);
    }

    @Override
    protected void func_175804_a(World world, StructureBoundingBox box, int x, int y, int z, int par6, int par7, int par8, IBlockState state, IBlockState state1, boolean bool)
    {
        IBlockState iblockstate2 = this.func_175847_a(state);
        IBlockState iblockstate3 = this.func_175847_a(state1);
        super.func_175804_a(world, box, x, y, z, par6, par7, par8, iblockstate2, iblockstate3, bool);
    }

    protected static class SwitchEnumFacing
    {
        protected static int[] field_176064_a = new int[EnumFacing.values().length];

        static
        {
            try
            {
                field_176064_a[EnumFacing.NORTH.ordinal()] = 1;
            }
            catch (NoSuchFieldError var4)
            {
            }

            try
            {
                field_176064_a[EnumFacing.SOUTH.ordinal()] = 2;
            }
            catch (NoSuchFieldError var3)
            {
            }

            try
            {
                field_176064_a[EnumFacing.WEST.ordinal()] = 3;
            }
            catch (NoSuchFieldError var2)
            {
            }

            try
            {
                field_176064_a[EnumFacing.EAST.ordinal()] = 4;
            }
            catch (NoSuchFieldError var1)
            {
            }
        }
    }
}
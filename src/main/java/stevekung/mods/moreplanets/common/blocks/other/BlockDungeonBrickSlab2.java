/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks.other;

import java.util.List;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockDungeonBrickSlab2 extends BlockSlab
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockDungeonBrickSlab2(String name, Material material)
    {
        super(material);
        this.setUnlocalizedName(name);
        this.useNeighborBrightness = true;
        this.setHardness(4.0F);
        this.setResistance(20.0F);
    }

    public BlockDungeonBrickSlab2(Material material)
    {
        super(material);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 2; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public IProperty getVariantProperty()
    {
        return VARIANT;
    }

    @Override
    public Object getVariant(ItemStack itemStack)
    {
        return BlockType.byMetadata(itemStack.getMetadata() & 7);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state) & 7;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos)) & 7);
    }

    @Override
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName();
    }

    @Override
    public boolean isDouble()
    {
        return false;
    }

    public void setHarvestLevel(String toolClass, int level, int meta)
    {
        this.setHarvestLevel(toolClass, level, this.getStateFromMeta(meta));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockType.byMetadata(meta & 7));

        if (!this.isDouble())
        {
            state = state.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | ((BlockType)state.getValue(VARIANT)).getMetadata();

        if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP)
        {
            i |= 8;
        }
        return i;
    }

    @Override
    protected BlockState createBlockState()
    {
        return this.isDouble() ? new BlockState(this, new IProperty[] {VARIANT}): new BlockState(this, new IProperty[] {HALF, VARIANT});
    }

    public static enum BlockType implements IStringSerializable
    {
        venus_dungeon_brick_slab(0),
        pluto_dungeon_brick_slab(1);

        private int meta;
        private static BlockType[] META_LOOKUP = new BlockType[values().length];

        private BlockType(int meta)
        {
            this.meta = meta;
        }

        @Override
        public String toString()
        {
            return this.getName();
        }

        @Override
        public String getName()
        {
            return this.name();
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public static BlockType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }
            return META_LOOKUP[meta];
        }

        static
        {
            BlockType[] var0 = values();
            int var1 = var0.length;

            for (int var2 = 0; var2 < var1; ++var2)
            {
                BlockType var3 = var0[var2];
                META_LOOKUP[var3.getMetadata()] = var3;
            }
        }
    }
}
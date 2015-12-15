/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.spacestation.jupiter;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.core.init.MPBlocks;

public class WorldGenJupiterSpaceStation extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        world.setBlockState(pos.add(0, 0, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 0, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 0, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 0, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 0, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 0, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 0, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 1, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 1, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 1, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 1, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 1, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(0, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(1, 0, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(1, 0, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(1, 0, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(1, 0, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(1, 0, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(1, 0, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(1, 0, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(1, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(1, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(2, 0, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(2, 0, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(2, 0, 2), MPBlocks.polished_space_decoration.getStateFromMeta(0), 3);
        world.setBlockState(pos.add(2, 0, 3), MPBlocks.polished_space_decoration.getStateFromMeta(0), 3);
        world.setBlockState(pos.add(2, 0, 4), MPBlocks.polished_space_decoration.getStateFromMeta(0), 3);
        world.setBlockState(pos.add(2, 0, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(2, 0, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(2, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(2, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(3, 0, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(3, 0, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(3, 0, 2), MPBlocks.polished_space_decoration.getStateFromMeta(0), 3);
        world.setBlockState(pos.add(3, 0, 3), MPBlocks.polished_space_decoration.getStateFromMeta(0), 3);
        world.setBlockState(pos.add(3, 0, 4), MPBlocks.polished_space_decoration.getStateFromMeta(0), 3);
        world.setBlockState(pos.add(3, 0, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(3, 0, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(3, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(3, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(4, 0, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(4, 0, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(4, 0, 2), MPBlocks.polished_space_decoration.getStateFromMeta(0), 3);
        world.setBlockState(pos.add(4, 0, 3), MPBlocks.polished_space_decoration.getStateFromMeta(0), 3);
        world.setBlockState(pos.add(4, 0, 4), MPBlocks.polished_space_decoration.getStateFromMeta(0), 3);
        world.setBlockState(pos.add(4, 0, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(4, 0, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(4, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(4, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(5, 0, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(5, 0, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(5, 0, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(5, 0, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(5, 0, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(5, 0, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(5, 0, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(5, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(5, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 1, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 1, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 1, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 1, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 1, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 2, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 2, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 3, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 3, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 4, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 4, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 5, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 5, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 5, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 5, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 5, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 5, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(6, 5, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 1, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 1, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 1, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 1, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 1, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 2, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 2, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 2, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 2, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 3, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 3, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 3, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 3, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 4, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 4, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 4, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 4, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 5, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 5, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 5, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 5, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 5, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 5, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 5, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 6, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 6, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 6, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 6, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 6, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 6, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(7, 6, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 1, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 1, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 1, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 1, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 1, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 2, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 2, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 3, 0), MPBlocks.tinted_glass_pane.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(8, 3, 6), MPBlocks.tinted_glass_pane.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(8, 4, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 4, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 5, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 5, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 6, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 6, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 6, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 6, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 6, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 6, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(8, 6, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 1, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 1, 2), MPBlocks.tinted_glass.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(9, 1, 3), MPBlocks.tinted_glass.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(9, 1, 4), MPBlocks.tinted_glass.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(9, 1, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 5, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 5, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 6, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 6, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 6, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 6, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 6, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 6, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(9, 6, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 1, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 1, 2), MPBlocks.tinted_glass.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(10, 1, 3), MPBlocks.tinted_glass.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(10, 1, 4), MPBlocks.tinted_glass.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(10, 1, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 5, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 5, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 6, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 6, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 6, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 6, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 6, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 6, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(10, 6, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 1, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 1, 2), MPBlocks.tinted_glass.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(11, 1, 3), MPBlocks.tinted_glass.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(11, 1, 4), MPBlocks.tinted_glass.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(11, 1, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 5, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 5, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 6, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 6, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 6, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 6, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 6, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 6, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(11, 6, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 1, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 1, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 1, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 1, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 1, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 2, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 2, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 3, 0), MPBlocks.tinted_glass_pane.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(12, 3, 6), MPBlocks.tinted_glass_pane.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(12, 4, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 4, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 5, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 5, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 6, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 6, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 6, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 6, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 6, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 6, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(12, 6, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 1, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 1, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 1, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 1, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 1, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 1, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 1, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 2, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 2, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 2, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 2, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 3, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 3, 1), MPBlocks.tinted_glass_pane.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(13, 3, 5), MPBlocks.tinted_glass_pane.getStateFromMeta(8), 3);
        world.setBlockState(pos.add(13, 3, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 4, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 4, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 4, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 4, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 5, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 5, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 5, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 5, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 5, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 5, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 5, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 6, 0), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 6, 1), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 6, 2), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 6, 3), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 6, 4), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 6, 5), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        world.setBlockState(pos.add(13, 6, 6), MPBlocks.polished_space_decoration.getStateFromMeta(1), 3);
        return true;
    }
}

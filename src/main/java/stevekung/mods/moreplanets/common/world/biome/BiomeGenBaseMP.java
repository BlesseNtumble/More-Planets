/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import stevekung.mods.moreplanets.common.config.ConfigManagerMP;

public class BiomeGenBaseMP extends BiomeGenBase
{
    public static BiomeGenBase basePlanetBiome = new BiomeGenBaseMP(ConfigManagerMP.idBasePlanetBiome).setBiomeName("Planet");
    public static BiomeGenBase baseMoonBiome = new BiomeGenBaseMP(ConfigManagerMP.idBaseMoonBiome).setBiomeName("Moon");

    public BiomeGenBaseMP(int id)
    {
        super(id);
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.rainfall = 0F;
        this.setColor(-16744448);
    }

    @Override
    public BiomeGenBase setColor(int color)
    {
        return this.func_150557_a(-16744448, false);
    }

    @Override
    public float getSpawningChance()
    {
        return 0.1F;
    }
}
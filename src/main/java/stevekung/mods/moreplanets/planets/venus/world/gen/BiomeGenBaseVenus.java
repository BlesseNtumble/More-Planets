/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.world.gen;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import stevekung.mods.moreplanets.client.handler.IPlanetFog;
import stevekung.mods.moreplanets.common.config.ConfigManagerMP;
import stevekung.mods.moreplanets.common.world.biome.BiomeGenBaseMP;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;

public class BiomeGenBaseVenus extends BiomeGenBaseMP implements IPlanetFog
{
    public BiomeGenBaseVenus()
    {
        super(ConfigManagerMP.idBasePlanetBiome);
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedEnderman.class, 8, 1, 2));
    }

    @Override
    public float getFogDensity(int x, int y, int z)
    {
        return 0.65F;
    }

    @Override
    public int getFogColor(int x, int y, int z)
    {
        return -4227008;
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.client.render.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.polongnius.entities.projectiles.EntityCheeseSpore;

@SideOnly(Side.CLIENT)
public class RenderCheeseSpore extends Render<EntityCheeseSpore>
{
    public RenderCheeseSpore(RenderManager render)
    {
        super(render);
    }

    @Override
    public void doRender(EntityCheeseSpore entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        float f2 = 2.0F;
        GlStateManager.scale(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
        TextureAtlasSprite textureatlassprite = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(MPItems.cheese_spore);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        float f3 = textureatlassprite.getMinU();
        float f4 = textureatlassprite.getMaxU();
        float f5 = textureatlassprite.getMinV();
        float f6 = textureatlassprite.getMaxV();
        GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181710_j);
        worldrenderer.func_181662_b(-0.5D, -0.25D, 0.0D).func_181673_a(f3, f6).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
        worldrenderer.func_181662_b(0.5D, -0.25D, 0.0D).func_181673_a(f4, f6).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
        worldrenderer.func_181662_b(0.5D, 0.75D, 0.0D).func_181673_a(f4, f5).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
        worldrenderer.func_181662_b(-0.5D, 0.75D, 0.0D).func_181673_a(f3, f5).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
        tessellator.draw();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCheeseSpore entity)
    {
        return TextureMap.locationBlocksTexture;
    }
}
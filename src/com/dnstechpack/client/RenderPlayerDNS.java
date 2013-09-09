package com.dnstechpack.client;

import com.dnstechpack.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

import java.util.Locale;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

public class RenderPlayerDNS extends RenderPlayer {

    private ModelBiped modelBipedMain;

    public RenderPlayerDNS() {

        super();
        this.modelBipedMain = (ModelBiped)this.mainModel;
    }

    // DO NOT MESS WITH
    @Override
    protected void renderSpecials(AbstractClientPlayer par1AbstractClientPlayer, float par2)
    {
        RenderPlayerEvent.Specials.Pre event = new RenderPlayerEvent.Specials.Pre(par1AbstractClientPlayer, this, par2);
        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return;
        }

        float f1 = 1.0F;
        GL11.glColor3f(f1, f1, f1);
        //        super.renderEquippedItems(par1AbstractClientPlayer, par2);
        //        super.renderArrowsStuckInEntity(par1AbstractClientPlayer, par2);
        ItemStack itemstack = par1AbstractClientPlayer.inventory.armorItemInSlot(3);

        if (itemstack != null && event.renderHelmet)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedHead.postRender(0.0625F);
            float f2;

            if (itemstack != null && itemstack.getItem() instanceof ItemBlock)
            {
                IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
                boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

                if (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType()))
                {
                    f2 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(f2, -f2, -f2);
                }

                this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, itemstack, 0);
            }
            else if (itemstack.getItem().itemID == Item.skull.itemID)
            {
                f2 = 1.0625F;
                GL11.glScalef(f2, -f2, -f2);
                String s = "";

                if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("SkullOwner"))
                {
                    s = itemstack.getTagCompound().getString("SkullOwner");
                }

                TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), s);
            }

            GL11.glPopMatrix();
        }

        if (par1AbstractClientPlayer.getCommandSenderName().equals("deadmau5") && par1AbstractClientPlayer.getTextureSkin().isTextureUploaded())
        {
            this.bindTexture(par1AbstractClientPlayer.getLocationSkin());

            for (int i = 0; i < 2; ++i)
            {
                float f3 = par1AbstractClientPlayer.prevRotationYaw + (par1AbstractClientPlayer.rotationYaw - par1AbstractClientPlayer.prevRotationYaw) * par2 - (par1AbstractClientPlayer.prevRenderYawOffset + (par1AbstractClientPlayer.renderYawOffset - par1AbstractClientPlayer.prevRenderYawOffset) * par2);
                float f4 = par1AbstractClientPlayer.prevRotationPitch + (par1AbstractClientPlayer.rotationPitch - par1AbstractClientPlayer.prevRotationPitch) * par2;
                GL11.glPushMatrix();
                GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.375F * (float)(i * 2 - 1), 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
                GL11.glRotatef(-f4, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-f3, 0.0F, 1.0F, 0.0F);
                float f5 = 1.3333334F;
                GL11.glScalef(f5, f5, f5);
                this.modelBipedMain.renderEars(0.0625F);
                GL11.glPopMatrix();
            }
        }

        renderCape(par1AbstractClientPlayer, par2);

        //        boolean flag = par1AbstractClientPlayer.func_110310_o().func_110557_a();
        //        boolean flag1 = !par1AbstractClientPlayer.isInvisible();
        //        boolean flag2 = !par1AbstractClientPlayer.getHideCape();
        //        flag = event.renderCape && flag;
        float f6;
        //
        //        if (flag && flag1 && flag2)
        //        {
        //            this.func_110776_a(par1AbstractClientPlayer.func_110303_q());
        //            GL11.glPushMatrix();
        //            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
        //            double d0 = par1AbstractClientPlayer.field_71091_bM + (par1AbstractClientPlayer.field_71094_bP - par1AbstractClientPlayer.field_71091_bM) * (double)par2 - (par1AbstractClientPlayer.prevPosX + (par1AbstractClientPlayer.posX - par1AbstractClientPlayer.prevPosX) * (double)par2);
        //            double d1 = par1AbstractClientPlayer.field_71096_bN + (par1AbstractClientPlayer.field_71095_bQ - par1AbstractClientPlayer.field_71096_bN) * (double)par2 - (par1AbstractClientPlayer.prevPosY + (par1AbstractClientPlayer.posY - par1AbstractClientPlayer.prevPosY) * (double)par2);
        //            double d2 = par1AbstractClientPlayer.field_71097_bO + (par1AbstractClientPlayer.field_71085_bR - par1AbstractClientPlayer.field_71097_bO) * (double)par2 - (par1AbstractClientPlayer.prevPosZ + (par1AbstractClientPlayer.posZ - par1AbstractClientPlayer.prevPosZ) * (double)par2);
        //            f6 = par1AbstractClientPlayer.prevRenderYawOffset + (par1AbstractClientPlayer.renderYawOffset - par1AbstractClientPlayer.prevRenderYawOffset) * par2;
        //            double d3 = (double)MathHelper.sin(f6 * (float)Math.PI / 180.0F);
        //            double d4 = (double)(-MathHelper.cos(f6 * (float)Math.PI / 180.0F));
        //            float f7 = (float)d1 * 10.0F;
        //
        //            if (f7 < -6.0F)
        //            {
        //                f7 = -6.0F;
        //            }
        //
        //            if (f7 > 32.0F)
        //            {
        //                f7 = 32.0F;
        //            }
        //
        //            float f8 = (float)(d0 * d3 + d2 * d4) * 100.0F;
        //            float f9 = (float)(d0 * d4 - d2 * d3) * 100.0F;
        //
        //            if (f8 < 0.0F)
        //            {
        //                f8 = 0.0F;
        //            }
        //
        //            float f10 = par1AbstractClientPlayer.prevCameraYaw + (par1AbstractClientPlayer.cameraYaw - par1AbstractClientPlayer.prevCameraYaw) * par2;
        //            f7 += MathHelper.sin((par1AbstractClientPlayer.prevDistanceWalkedModified + (par1AbstractClientPlayer.distanceWalkedModified - par1AbstractClientPlayer.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * f10;
        //
        //            if (par1AbstractClientPlayer.isSneaking())
        //            {
        //                f7 += 25.0F;
        //            }
        //
        //            GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
        //            GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
        //            GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
        //            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        //            this.modelBipedMain.renderCloak(0.0625F);
        //            GL11.glPopMatrix();
        //        }

        ItemStack itemstack1 = par1AbstractClientPlayer.inventory.getCurrentItem();

        if (itemstack1 != null && event.renderItem)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedRightArm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

            if (par1AbstractClientPlayer.fishEntity != null)
            {
                itemstack1 = new ItemStack(Item.stick);
            }

            EnumAction enumaction = null;

            if (par1AbstractClientPlayer.getItemInUseCount() > 0)
            {
                enumaction = itemstack1.getItemUseAction();
            }

            float f11;

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack1, BLOCK_3D));
            boolean isBlock = itemstack1.itemID < Block.blocksList.length && itemstack1.getItemSpriteNumber() == 0;

            if (is3D || (isBlock && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack1.itemID].getRenderType())))
            {
                f11 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f11 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f11, -f11, f11);
            }
            else if (itemstack1.itemID == Item.bow.itemID)
            {
                f11 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f11, -f11, f11);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (Item.itemsList[itemstack1.itemID].isFull3D())
            {
                f11 = 0.625F;

                if (Item.itemsList[itemstack1.itemID].shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                if (par1AbstractClientPlayer.getItemInUseCount() > 0 && enumaction == EnumAction.block)
                {
                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f11, -f11, f11);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f11 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f11, f11, f11);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float f12;
            float f13;
            int j;

            if (itemstack1.getItem().requiresMultipleRenderPasses())
            {
                for (j = 0; j < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++j)
                {
                    int k = itemstack1.getItem().getColorFromItemStack(itemstack1, j);
                    f13 = (float)(k >> 16 & 255) / 255.0F;
                    f12 = (float)(k >> 8 & 255) / 255.0F;
                    f6 = (float)(k & 255) / 255.0F;
                    GL11.glColor4f(f13, f12, f6, 1.0F);
                    this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, itemstack1, j);
                }
            }
            else
            {
                j = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
                float f14 = (float)(j >> 16 & 255) / 255.0F;
                f13 = (float)(j >> 8 & 255) / 255.0F;
                f12 = (float)(j & 255) / 255.0F;
                GL11.glColor4f(f14, f13, f12, 1.0F);
                this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, itemstack1, 0);
            }

            GL11.glPopMatrix();
        }
        MinecraftForge.EVENT_BUS.post(new RenderPlayerEvent.Specials.Post(par1AbstractClientPlayer, this, par2));
    }

    protected void renderCape(AbstractClientPlayer player, float par2) {

        super.renderSpecials(player, par2);

        if (!player.getHideCape()) {

            GL11.glPushMatrix();

            ResourceLocation capeRL = null;

            if(Reference.staffList.contains(player.username.toLowerCase(Locale.ENGLISH))) {

                capeRL = new ResourceLocation("dns:/capes/AdminCape.png");
            } else {

                capeRL = new ResourceLocation("dns:/capes/UserCape.png");
            }
            Minecraft.getMinecraft().renderEngine.bindTexture(capeRL);
        }


        GL11.glTranslatef(0.0F, 0.0F, 0.125F);
        double var22 = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * (double) par2 - (player.prevPosX + (player.posX - player.prevPosX) * (double) par2);
        double var24 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * (double) par2 - (player.prevPosY + (player.posY - player.prevPosY) * (double) par2);
        double var9 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * (double) par2 - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double) par2);
        float var11 = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * par2;
        double var12 = (double) MathHelper.sin(var11 * (float) Math.PI / 180.0F);
        double var14 = (double) (-MathHelper.cos(var11 * (float) Math.PI / 180.0F));
        float var16 = (float) var24 * 10.0F;

        if (var16 < -6.0F) {
            var16 = -6.0F;
        }

        if (var16 > 32.0F) {
            var16 = 32.0F;
        }

        float var17 = (float) (var22 * var12 + var9 * var14) * 100.0F;
        float var18 = (float) (var22 * var14 - var9 * var12) * 100.0F;

        if (var17 < 0.0F) {
            var17 = 0.0F;
        }

        float var19 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * par2;
        var16 += MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * var19;

        if (player.isSneaking()) {
            var16 += 25.0F;
        }
        GL11.glRotatef(6.0F + var17 / 2.0F + var16, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(var18 / 2.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-var18 / 2.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        this.modelBipedMain.renderCloak(0.0625F);
        GL11.glPopMatrix();
    }
}
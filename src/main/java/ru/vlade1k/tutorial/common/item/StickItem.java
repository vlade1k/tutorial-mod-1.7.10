package ru.vlade1k.tutorial.common.item;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.handler.ModTab;


public class StickItem extends Item {
    public StickItem(){
        setCreativeTab(ModTab.INSTANCE);
        setUnlocalizedName("lightStick");
        setTextureName(TutorialMod.MOD_ID + ":stickOfTheTruth");
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        MovingObjectPosition objectLookingAt = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
        //MovingObjectPosition objectLookingAt2 = player.rayTrace(200, 1.0F);

        if (objectLookingAt != null) {
            world.spawnEntityInWorld(new EntityLightningBolt(world, objectLookingAt.blockX, objectLookingAt.blockY, objectLookingAt.blockZ));
        }

        return itemStack;
    }


}

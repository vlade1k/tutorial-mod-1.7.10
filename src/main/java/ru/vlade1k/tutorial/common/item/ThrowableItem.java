package ru.vlade1k.tutorial.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ru.vlade1k.tutorial.common.handler.ModTab;
import ru.vlade1k.tutorial.common.entities.EntityThrowableItem;

import static ru.vlade1k.tutorial.TutorialMod.MOD_ID;

public class ThrowableItem extends Item {
    public ThrowableItem(){
        setUnlocalizedName("throwableItem");
        setCreativeTab(ModTab.INSTANCE);
        setMaxStackSize(64);
        setTextureName(MOD_ID+":throwableItem");
    }

    @Override
    public boolean hasEffect(ItemStack itemStack, int pass){
        return true;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }

        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityThrowableItem(world, player));
        }

        return stack;
    }


}

package ru.vlade1k.tutorial.common.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import ru.vlade1k.tutorial.TutorialMod;


public class ModTab extends CreativeTabs {
    public static final ModTab INSTANCE = new ModTab();

    private ModTab(){
        super(TutorialMod.MOD_ID);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem(){
        return Item.getItemFromBlock(Blocks.chest);
    }
}

package ru.vlade1k.tutorial.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.handler.ModTab;
import ru.vlade1k.tutorial.common.tile.StorageTile;

public class StorageBlock extends BlockContainer {
    public StorageBlock() {
        super(Material.wood);
        setBlockName("storage");
        setBlockTextureName(TutorialMod.MOD_ID + ":storage");
        setCreativeTab(ModTab.INSTANCE);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata){
        return new StorageTile();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer activator, int side, float hitX, float hitY, float hitZ){

        if(!world.isRemote){
            TileEntity tile = world.getTileEntity(x, y, z);

            if (tile instanceof StorageTile){
                StorageTile storage = (StorageTile) tile;
                storage.handleInputStack(activator, activator.getHeldItem());
            }
        }

        return true;
    }
}

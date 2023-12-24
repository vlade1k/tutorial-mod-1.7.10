package ru.vlade1k.tutorial.common.handler;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;
import ru.vlade1k.tutorial.common.entities.EntityThrowableItem;

public class ModEntity {
    public static void register(){
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableItem.class, new RenderSnowball(ModItems.THROWABLE_ITEM));
    }
}

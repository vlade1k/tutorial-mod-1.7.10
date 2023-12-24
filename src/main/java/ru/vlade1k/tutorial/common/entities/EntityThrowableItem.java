package ru.vlade1k.tutorial.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrowableItem extends EntityThrowable {

    public EntityThrowableItem(World world) {
        super(world);
    }

    public EntityThrowableItem(World world, EntityLivingBase p_i1777_2_) {
        super(world, p_i1777_2_);
    }

    public EntityThrowableItem(World world, double p_i1778_2_, double p_i1778_4_, double p_i1778_6_) {
        super(world, p_i1778_2_, p_i1778_4_, p_i1778_6_);
    }

    @Override
    protected void onImpact(MovingObjectPosition hitObject) {

        if (hitObject.entityHit != null)
        {
            byte damage = 10;

            hitObject.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)damage);
        }


        //If the server is online and works, kill this entity
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }

}

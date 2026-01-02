package dev.amble.ait.mixin.client.rendering;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;

import dev.amble.ait.core.entities.FlightTardisEntity;

/**
 * Client-side mixin to disable fire rendering for entities riding TARDIS.
 * Uses low priority (1500) for Blueprint mod compatibility.
 */
@Mixin(value = Entity.class, priority = 1500)
public class EntityMixin {
    @Inject(method = "doesRenderOnFire", at = @At("HEAD"), cancellable = true)
    public void ait$doesRenderOnFire(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;
        if (!entity.hasVehicle()) return;

        if (entity.getVehicle() instanceof FlightTardisEntity) {
            cir.setReturnValue(false);
        }
    }
}

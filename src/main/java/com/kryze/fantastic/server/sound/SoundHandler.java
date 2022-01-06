package com.kryze.fantastic.server.sound;

import com.kryze.fantastic.Fantastic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundHandler {

    public static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Fantastic.MOD_ID);

    public static final RegistryObject<SoundEvent> CHOCOBO_AMBIENT = create("entity.chocobo.ambient");
    public static final RegistryObject<SoundEvent> CHOCOBO_HURT = create("entity.chocobo.hurt");
    public static final RegistryObject<SoundEvent> CHOCOBO_DEATH = create("entity.chocobo.death");

    private static RegistryObject<SoundEvent> create(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(Fantastic.MOD_ID, name)));
    }
}

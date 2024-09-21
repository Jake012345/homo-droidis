package net.jake012345.homo_droidis.item;

import net.jake012345.homo_droidis.HomoDroidis;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HomoDroidis.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("droid_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EDIBLE_IRON.get()))
                    .title(Component.translatable("creativetab.droid_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.EDIBLE_IRON.get());
                        output.accept(ModItems.EDIBLE_IRON_PLATE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

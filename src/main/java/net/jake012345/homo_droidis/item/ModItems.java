package net.jake012345.homo_droidis.item;

import com.google.common.io.LittleEndianDataInputStream;
import net.jake012345.homo_droidis.HomoDroidis;
import net.jake012345.homo_droidis.item.custom.MetalDetectorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HomoDroidis.MOD_ID);

    public static final RegistryObject<Item> EDIBLE_IRON = ITEMS.register("edible_iron",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EDIBLE_IRON_PLATE = ITEMS.register("edible_iron_plate",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR_ITEM = ITEMS.register("metal_detector_item",
            () -> new MetalDetectorItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

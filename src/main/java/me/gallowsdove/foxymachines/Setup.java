package me.gallowsdove.foxymachines;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.StringUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.gallowsdove.foxymachines.implementation.consumables.CustomMobSpawnEgg;
import me.gallowsdove.foxymachines.implementation.consumables.SimpleConsumable;
import me.gallowsdove.foxymachines.implementation.consumables.UnbreakableRune;
import me.gallowsdove.foxymachines.implementation.machines.BoostedRail;
import me.gallowsdove.foxymachines.implementation.machines.ChunkLoader;
import me.gallowsdove.foxymachines.implementation.machines.ElectricGoldRefinery;
import me.gallowsdove.foxymachines.implementation.machines.ForcefieldDome;
import me.gallowsdove.foxymachines.implementation.machines.ImprovementForge;
import me.gallowsdove.foxymachines.implementation.machines.PotionMixer;
import me.gallowsdove.foxymachines.implementation.materials.GhostBlock;
import me.gallowsdove.foxymachines.implementation.materials.ShardMaterial;
import me.gallowsdove.foxymachines.implementation.materials.SimpleMaterial;
import me.gallowsdove.foxymachines.implementation.materials.SimpleRadioactiveMaterial;
import me.gallowsdove.foxymachines.implementation.mobs.HeadlessHorseman;
import me.gallowsdove.foxymachines.implementation.mobs.Helldog;
import me.gallowsdove.foxymachines.implementation.mobs.Pixie;
import me.gallowsdove.foxymachines.implementation.mobs.PixieQueen;
import me.gallowsdove.foxymachines.implementation.mobs.RiddenSkeletonHorse;
import me.gallowsdove.foxymachines.implementation.multiblock.SacrificialAltarPiece;
import me.gallowsdove.foxymachines.implementation.multiblock.SacrificialAltarPressurePlate;
import me.gallowsdove.foxymachines.implementation.tools.BerryBushTrimmer;
import me.gallowsdove.foxymachines.implementation.tools.ElectricFireStaff;
import me.gallowsdove.foxymachines.implementation.tools.ElectricFireStaffII;
import me.gallowsdove.foxymachines.implementation.tools.ElectricWindStaff;
import me.gallowsdove.foxymachines.implementation.tools.FillWand;
import me.gallowsdove.foxymachines.implementation.tools.GhostBlockRemover;
import me.gallowsdove.foxymachines.implementation.tools.PositionSelector;
import me.gallowsdove.foxymachines.implementation.tools.RemoteController;
import me.gallowsdove.foxymachines.implementation.tools.SpongeWand;
import me.gallowsdove.foxymachines.implementation.weapons.CelestialSword;
import me.gallowsdove.foxymachines.implementation.weapons.CursedSword;
import me.gallowsdove.foxymachines.implementation.weapons.Elucidator;
import me.gallowsdove.foxymachines.implementation.weapons.HealingBow;
import me.gallowsdove.foxymachines.types.FoxyRecipeType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

final class ItemSetup {
    static final ItemSetup INSTANCE = new ItemSetup();
    private boolean initialised;

    private ItemSetup() {}

    public void init() {
        if (initialised) return;

        initialised = true;

        boolean customMobs = FoxyMachines.getInstance().getConfig().getBoolean("custom-mobs");

        new SimpleMaterial(Items.MAGIC_LUMP_4, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.MAGIC_LUMP_3.item(), SlimefunItems.MAGIC_LUMP_3.item(), null,
                SlimefunItems.MAGIC_LUMP_3.item(), SlimefunItems.MAGIC_LUMP_3.item(), null,
                null, null, null
        }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.MAGIC_LUMP_5, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                Items.MAGIC_LUMP_4.item(), Items.MAGIC_LUMP_4.item(), null,
                Items.MAGIC_LUMP_4.item(), Items.MAGIC_LUMP_4.item(), null,
                null, null, null
        }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.REINFORCED_STRING, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                new ItemStack(Material.STRING), SlimefunItems.REINFORCED_ALLOY_INGOT.item(), new ItemStack(Material.STRING),
                SlimefunItems.REINFORCED_ALLOY_INGOT.item(), new ItemStack(Material.STRING), SlimefunItems.REINFORCED_ALLOY_INGOT.item(),
                new ItemStack(Material.STRING), SlimefunItems.REINFORCED_ALLOY_INGOT.item(), new ItemStack(Material.STRING)
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.MACHINES_ITEM_GROUP, Items.IMPROVEMENT_CORE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.DAMASCUS_STEEL_INGOT.item(), Items.REINFORCED_STRING.item(), SlimefunItems.DAMASCUS_STEEL_INGOT.item(),
                Items.REINFORCED_STRING.item(), SlimefunItems.DAMASCUS_STEEL_INGOT.item(), Items.REINFORCED_STRING.item(),
                SlimefunItems.DAMASCUS_STEEL_INGOT.item(), Items.REINFORCED_STRING.item(), SlimefunItems.DAMASCUS_STEEL_INGOT.item()
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.STABILIZED_BLISTERING_BLOCK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.HARDENED_METAL_INGOT.item(), SlimefunItems.REINFORCED_ALLOY_INGOT.item(),SlimefunItems.HARDENED_METAL_INGOT.item(),
                SlimefunItems.REINFORCED_ALLOY_INGOT.item(), SlimefunItems.BLISTERING_INGOT_3.item(), SlimefunItems.REINFORCED_ALLOY_INGOT.item(),
                SlimefunItems.HARDENED_METAL_INGOT.item(), SlimefunItems.REINFORCED_ALLOY_INGOT.item(), SlimefunItems.HARDENED_METAL_INGOT.item()
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.FORCEFIELD_ENGINE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                Items.AQUATIC_NETHERITE_INGOT.item(), SlimefunItems.ELECTRIC_MOTOR.item(), Items.AQUATIC_NETHERITE_INGOT.item(),
                Items.DEMONIC_PLATE.item(), Items.STABILIZED_BLISTERING_BLOCK.item(), Items.DEMONIC_PLATE.item(),
                Items.AQUATIC_NETHERITE_INGOT.item(), SlimefunItems.ELECTRIC_MOTOR.item(), Items.AQUATIC_NETHERITE_INGOT.item()
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.FORCEFIELD_STABILIZER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                Items.SWEET_INGOT.item(), Items.DAMIENIUM.item(), Items.SWEET_INGOT.item(),
                Items.DAMIENIUM.item(), Items.STABILIZED_BLISTERING_BLOCK.item(), Items.DAMIENIUM.item(),
                Items.STABILIZED_BLISTERING_BLOCK.item(), Items.STABILIZED_BLISTERING_BLOCK.item(), Items.STABILIZED_BLISTERING_BLOCK.item()
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.WIRELESS_TRANSMITTER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.REDSTONE_ALLOY.item(), Items.DAMIENIUM.item(), SlimefunItems.REDSTONE_ALLOY.item(),
                Items.DAMIENIUM.item(), SlimefunItems.ELECTRIC_MOTOR.item(), Items.DAMIENIUM.item(),
                Items.DAMIENIUM.item(), SlimefunItems.BATTERY.item(), Items.DAMIENIUM.item()
                }, 1).register(FoxyMachines.getInstance());
        new SimpleRadioactiveMaterial(Items.NUCLEAR_SALT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                null, SlimefunItems.BOOSTED_URANIUM.item(), null,
                SlimefunItems.BOOSTED_URANIUM.item(), SlimefunItems.SALT.item(), SlimefunItems.BOOSTED_URANIUM.item(),
                null, SlimefunItems.BOOSTED_URANIUM.item(), null
            }, 1, Radioactivity.VERY_HIGH).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.COMPRESSED_SPONGE, RecipeType.COMPRESSOR, new ItemStack[]{
                new ItemStack(Material.SPONGE, 24), null, null,
                null, null, null,
                null, null, null
        }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.DEMONIC_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.LAVA_CRYSTAL.item(), SlimefunItems.BLISTERING_INGOT_3.item(), new ItemStack(Material.GHAST_TEAR),
                SlimefunItems.REINFORCED_ALLOY_INGOT.item(), SlimefunItems.NECROTIC_SKULL.item(), SlimefunItems.STRANGE_NETHER_GOO.item(),
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.AQUATIC_NETHERITE_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.DAMASCUS_STEEL_INGOT.item(), new ItemStack(Material.NETHERITE_INGOT), new ItemStack(Material.PRISMARINE_SHARD),
                new ItemStack(Material.PRISMARINE_CRYSTALS), new ItemStack(Material.NAUTILUS_SHELL), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.DAMIENIUM, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.DAMASCUS_STEEL_INGOT.item(), SlimefunItems.GOLD_24K.item(), new ItemStack(Material.GOLD_INGOT),
                SlimefunItems.HARDENED_METAL_INGOT.item(), SlimefunItems.CORINTHIAN_BRONZE_INGOT.item(), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.SWEET_INGOT, RecipeType.SMELTERY, new ItemStack[] {
                SlimefunItems.DAMASCUS_STEEL_INGOT.item(), SlimefunItems.COBALT_INGOT.item(), SlimefunItems.BILLON_INGOT.item(),
                new ItemStack(Material.HONEYCOMB_BLOCK), new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.HONEY_BLOCK),
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.SWEETENED_SWEET_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                Items.SWEET_INGOT.item(), SlimefunItems.DAMASCUS_STEEL_INGOT.item(), SlimefunItems.COBALT_INGOT.item(),
                new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.SUGAR), new ItemStack(Material.HONEY_BLOCK),
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.DEMONIC_PLATE, RecipeType.COMPRESSOR, new ItemStack[]{
                new SlimefunItemStack(Items.DEMONIC_INGOT, 8).item(), null, null,
                null, null, null,
                null, null, null
               }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.AQUATIC_HELMET_FRAME, RecipeType.ARMOR_FORGE, new ItemStack[]{
                Items.AQUATIC_NETHERITE_INGOT.item(), Items.AQUATIC_NETHERITE_INGOT.item(), Items.AQUATIC_NETHERITE_INGOT.item(),
                Items.AQUATIC_NETHERITE_INGOT.item(), null, Items.AQUATIC_NETHERITE_INGOT.item(),
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.RESISTANT_CHESTPLATE_FRAME, RecipeType.ARMOR_FORGE, new ItemStack[]{
                Items.DAMIENIUM.item(), null, Items.DAMIENIUM.item(),
                Items.DEMONIC_INGOT.item(), Items.DEMONIC_INGOT.item(), Items.DEMONIC_INGOT.item(),
                Items.DEMONIC_INGOT.item(), Items.DEMONIC_INGOT.item(), Items.DEMONIC_INGOT.item()
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.FIERY_LEGGINGS_FRAME, RecipeType.ARMOR_FORGE, new ItemStack[]{
                Items.DEMONIC_INGOT.item(), Items.DAMIENIUM.item(), Items.DEMONIC_INGOT.item(),
                Items.DEMONIC_INGOT.item(), null, Items.DEMONIC_INGOT.item(),
                Items.DAMIENIUM.item(), null, Items.DAMIENIUM.item()
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.LIGHT_BOOTS_FRAME, RecipeType.ARMOR_FORGE, new ItemStack[]{
                null, null, null,
                Items.SWEETENED_SWEET_INGOT.item(), null, Items.SWEETENED_SWEET_INGOT.item(),
                Items.DAMIENIUM.item(), null, Items.DAMIENIUM.item()
        }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.POSEIDONS_BLESSING, FoxyRecipeType.FISHING, new ItemStack[] {
                null, null, null,
                null, Items.POSEIDONS_FISHING_ROD.item(), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.ALTAR_ITEM_GROUP, Items.BLOOD, FoxyRecipeType.SACRIFICIAL_ALTAR, new ItemStack[] {
                null, null, null,
                null, CustomItemStack.create(Material.NETHERITE_SWORD, "&eMob Drop"), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.ALTAR_ITEM_GROUP, Items.CURSED_RABBIT_PAW, FoxyRecipeType.SACRIFICIAL_ALTAR, new ItemStack[] {
                null, null, null,
                null, CustomItemStack.create(Material.RABBIT_SPAWN_EGG, "&eRabbit Drop"), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.ALTAR_ITEM_GROUP, Items.HUMAN_SKULL, FoxyRecipeType.SACRIFICIAL_ALTAR, new ItemStack[] {
                null, null, null,
                null, CustomItemStack.create(Material.PLAYER_HEAD, "&ePlayer Drop"), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.ALTAR_ITEM_GROUP, Items.TROPICAL_FISH_SCALE, FoxyRecipeType.SACRIFICIAL_ALTAR, new ItemStack[] {
                null, null, null,
                null, CustomItemStack.create(Material.TROPICAL_FISH, "&eTropical Fish Drop"), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.ALTAR_ITEM_GROUP, Items.POLAR_FOX_HIDE, FoxyRecipeType.SACRIFICIAL_ALTAR, new ItemStack[] {
                null, null, null,
                null, CustomItemStack.create(Material.POLAR_BEAR_SPAWN_EGG, "&ePolar Fox Drop"), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.ALTAR_ITEM_GROUP, Items.MAGMA_ESSENCE, FoxyRecipeType.SACRIFICIAL_ALTAR, new ItemStack[] {
                null, null, null,
                null, CustomItemStack.create(Material.MAGMA_CUBE_SPAWN_EGG, "&eMagma Cube Drop"), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.ALTAR_ITEM_GROUP, Items.PARROT_FEATHER, FoxyRecipeType.SACRIFICIAL_ALTAR, new ItemStack[] {
                null, null, null,
                null, CustomItemStack.create(Material.PARROT_SPAWN_EGG, "&eParrot Drop"), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.ALTAR_ITEM_GROUP, Items.UNHOLY_WITHER_SKELETON_BONE, FoxyRecipeType.SACRIFICIAL_ALTAR, new ItemStack[] {
                null, null, null,
                null, CustomItemStack.create(Material.WITHER_SKELETON_SPAWN_EGG, "&eWither Skeleton Drop"), null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.BLOOD_INFUSED_SKULL, RecipeType.ANCIENT_ALTAR, new ItemStack[] {
                Items.MAGIC_LUMP_4.item(), Items.BLOOD.item(), Items.MAGIC_LUMP_4.item(),
                Items.BLOOD.item(), Items.HUMAN_SKULL.item(), Items.BLOOD.item(),
                Items.MAGIC_LUMP_4.item(), Items.BLOOD.item(), Items.MAGIC_LUMP_4.item()
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.PURIFIED_BONE, RecipeType.ANCIENT_ALTAR, new ItemStack[] {
                new ItemStack(Material.BLUE_ORCHID), new ItemStack(Material.ALLIUM), new ItemStack(Material.OXEYE_DAISY),
                new ItemStack(Material.POPPY), Items.UNHOLY_WITHER_SKELETON_BONE.item(), new ItemStack(Material.RED_TULIP),
                new ItemStack(Material.LILY_OF_THE_VALLEY), new ItemStack(Material.PINK_TULIP), new ItemStack(Material.CORNFLOWER)
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.PURE_BONE_DUST, RecipeType.COMPRESSOR, new ItemStack[] {
                new SlimefunItemStack(Items.PURIFIED_BONE, 6).item(), null, null,
                null, null, null,
                null, null, null
                }, 1).register(FoxyMachines.getInstance());
        new SimpleMaterial(Items.BUCKET_OF_BLOOD, RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
                Items.BLOOD.item(), Items.BLOOD.item(), Items.BLOOD.item(),
                Items.BLOOD.item(), new ItemStack(Material.BUCKET), Items.BLOOD.item(),
                Items.BLOOD.item(), Items.BLOOD.item(), Items.BLOOD.item()
                }, 1).register(FoxyMachines.getInstance());

        if (customMobs) {
            new SimpleConsumable(Items.PIXIE_DUST, RecipeType.GRIND_STONE, new ItemStack[]{
                    Items.PIXIE_QUEEN_HEART.item(), null, null,
                    null, null, null,
                    null, null, null
                    }, new PotionEffect[]{new PotionEffect(PotionEffectType.STRENGTH, 1600, 3, false, false)},
                    8).register(FoxyMachines.getInstance());
            new SimpleConsumable(Items.VILE_SEEDS, RecipeType.GRIND_STONE, new ItemStack[]{
                    Items.VILE_PUMPKIN.item(), null, null,
                    null, null, null,
                    null, null, null
                    }, new PotionEffect[]{new PotionEffect(PotionEffectType.HEALTH_BOOST, 2700, 4, false, false)},
                    6).register(FoxyMachines.getInstance());
            new SimpleMaterial(Items.BOSSES_ITEM_GROUP, Items.PIXIE_QUEEN_HEART, FoxyRecipeType.CUSTOM_MOB_DROP, new ItemStack[]{
                    null, null, null,
                    null, CustomItemStack.create(Material.CREEPER_SPAWN_EGG, "&aPixie Queen", "&7Summon it using &aPixie Queen Spawn Egg"), null,
                    null, null, null
                    }, 1).register(FoxyMachines.getInstance());
            new SimpleMaterial(Items.BOSSES_ITEM_GROUP, Items.VILE_PUMPKIN, FoxyRecipeType.CUSTOM_MOB_DROP, new ItemStack[]{
                    null, null, null,
                    null, CustomItemStack.create(Material.SPIDER_SPAWN_EGG, "&cHeadless Horseman", "&7Summon it using &cHeadless Horseman Spawn Egg"), null,
                    null, null, null
                    }, 1).register(FoxyMachines.getInstance());
        }

        new ShardMaterial(Items.MATERIALS_ITEM_GROUP, Items.CURSED_SHARD, FoxyRecipeType.QUEST, new ItemStack[] {
                null, null, null,
                null, Items.CURSED_SWORD.item(), null,
                null, null, null
                }, ChatColor.RED).register(FoxyMachines.getInstance());
        new ShardMaterial(Items.MATERIALS_ITEM_GROUP, Items.CELESTIAL_SHARD, FoxyRecipeType.QUEST, new ItemStack[] {
                null, null, null,
                null, Items.CELESTIAL_SWORD.item(), null,
                null, null, null
                }, ChatColor.YELLOW).register(FoxyMachines.getInstance());
        new SlimefunItem(Items.MATERIALS_ITEM_GROUP, Items.EQUANIMOUS_GEM, RecipeType.ANCIENT_ALTAR, new ItemStack[] {
                Items.CURSED_SHARD.item(), Items.CELESTIAL_SHARD.item(), Items.CURSED_SHARD.item(),
                Items.CELESTIAL_SHARD.item(), new ItemStack(Material.EMERALD), Items.CELESTIAL_SHARD.item(),
                Items.CURSED_SHARD.item(), Items.CELESTIAL_SHARD.item(), Items.CURSED_SHARD.item()
                }).register(FoxyMachines.getInstance());
        new SacrificialAltarPiece(Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS, new ItemStack[]{
                new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS), new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS), new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS),
                new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS), SlimefunItems.REINFORCED_PLATE.item(), new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS),
                new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS), new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS), new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS)
                }, 8).register(FoxyMachines.getInstance());
        new SacrificialAltarPiece(Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_STAIRS, new ItemStack[] {
                Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(), null, null,
                Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(), Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(), null,
                Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(), Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(), Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item()
                }, 4).register(FoxyMachines.getInstance());
        new SacrificialAltarPiece(Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_WALL, new ItemStack[] {
                null, null, null,
                Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(), Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(), Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(),
                Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(), Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(), Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item()
                }, 4).register(FoxyMachines.getInstance());
        new SacrificialAltarPiece(Items.SACRIFICIAL_ALTAR_SOUL_TORCH, new ItemStack[] {
                null, Items.MAGIC_LUMP_4.item(), null,
                Items.MAGIC_LUMP_4.item(), SlimefunItems.STRANGE_NETHER_GOO.item(), Items.MAGIC_LUMP_4.item(),
                null, new ItemStack(Material.BLAZE_ROD), null
                }, 1).register(FoxyMachines.getInstance());
        new SacrificialAltarPressurePlate().register(FoxyMachines.getInstance());
        new UnbreakableRune().register(FoxyMachines.getInstance());
        new SlimefunItem(Items.TOOLS_ITEM_GROUP, Items.POSEIDONS_FISHING_ROD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                null, null, Items.AQUATIC_NETHERITE_INGOT.item(),
                null, Items.AQUATIC_NETHERITE_INGOT.item(), Items.REINFORCED_STRING.item(),
                Items.AQUATIC_NETHERITE_INGOT.item(), null, new ItemStack(Material.HEART_OF_THE_SEA)
                }).register(FoxyMachines.getInstance());
        new ElectricWindStaff().register(FoxyMachines.getInstance());
        new ElectricFireStaff().register(FoxyMachines.getInstance());
        new ElectricFireStaffII().register(FoxyMachines.getInstance());
        new HealingBow().register(FoxyMachines.getInstance());
        new SlimefunItem(Items.WEAPONS_AND_ARMORS_ITEM_GROUP, Items.ACRI_ARCUM, RecipeType.ANCIENT_ALTAR, new ItemStack[]{
                Items.EQUANIMOUS_GEM.item(), customMobs ? new ItemStack(Material.BOW) : Items.DEMONIC_PLATE.item(), Items.EQUANIMOUS_GEM.item(),
                Items.BUCKET_OF_BLOOD.item(), customMobs ? Items.VILE_PUMPKIN.item() : new ItemStack(Material.BOW), Items.BUCKET_OF_BLOOD.item(),
                Items.EQUANIMOUS_GEM.item(), customMobs ? new ItemStack(Material.BOW) : Items.DEMONIC_PLATE.item(), Items.EQUANIMOUS_GEM.item()
        }).register(FoxyMachines.getInstance());
        new CursedSword().register(FoxyMachines.getInstance());
        new CelestialSword().register(FoxyMachines.getInstance());
        new Elucidator(customMobs).register(FoxyMachines.getInstance());
        new SlimefunArmorPiece(Items.WEAPONS_AND_ARMORS_ITEM_GROUP, Items.AQUATIC_HELMET, RecipeType.ANCIENT_ALTAR, new ItemStack[]{
                Items.EQUANIMOUS_GEM.item(), Items.MAGIC_LUMP_5.item(), Items.EQUANIMOUS_GEM.item(),
                Items.TROPICAL_FISH_SCALE.item(), Items.AQUATIC_HELMET_FRAME.item(), Items.TROPICAL_FISH_SCALE.item(),
                Items.EQUANIMOUS_GEM.item(), Items.MAGIC_LUMP_5.item(), Items.EQUANIMOUS_GEM.item()},
                new PotionEffect[] { new PotionEffect(PotionEffectType.WATER_BREATHING, 300, 0, false, false, false),
                new PotionEffect(PotionEffectType.NIGHT_VISION, 500, 0, false, false, false)})
                .register(FoxyMachines.getInstance());
        new SlimefunArmorPiece(Items.WEAPONS_AND_ARMORS_ITEM_GROUP, Items.RESISTANT_CHESTPLATE, RecipeType.ANCIENT_ALTAR, new ItemStack[]{
                Items.EQUANIMOUS_GEM.item(), Items.MAGIC_LUMP_5.item(), Items.EQUANIMOUS_GEM.item(),
                Items.POLAR_FOX_HIDE.item(), Items.RESISTANT_CHESTPLATE_FRAME.item(), Items.POLAR_FOX_HIDE.item(),
                Items.EQUANIMOUS_GEM.item(), Items.MAGIC_LUMP_5.item(), Items.EQUANIMOUS_GEM.item()},
                new PotionEffect[] { new PotionEffect(PotionEffectType.RESISTANCE, 300, 0, false, false, false),
                        new PotionEffect(PotionEffectType.REGENERATION, 300, 0, false, false, false)})
                .register(FoxyMachines.getInstance());
        new SlimefunArmorPiece(Items.WEAPONS_AND_ARMORS_ITEM_GROUP, Items.FIERY_LEGGINGS, RecipeType.ANCIENT_ALTAR, new ItemStack[]{
                Items.EQUANIMOUS_GEM.item(), Items.MAGIC_LUMP_5.item(), Items.EQUANIMOUS_GEM.item(),
                Items.MAGMA_ESSENCE.item(), Items.FIERY_LEGGINGS_FRAME.item(), Items.MAGMA_ESSENCE.item(),
                Items.EQUANIMOUS_GEM.item(), Items.MAGIC_LUMP_5.item(), Items.EQUANIMOUS_GEM.item()},
                new PotionEffect[] { new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 0, false, false, false)})
                .register(FoxyMachines.getInstance());
        new SlimefunArmorPiece(Items.WEAPONS_AND_ARMORS_ITEM_GROUP, Items.LIGHT_BOOTS, RecipeType.ANCIENT_ALTAR, new ItemStack[]{
                Items.EQUANIMOUS_GEM.item(), Items.MAGIC_LUMP_5.item(), Items.EQUANIMOUS_GEM.item(),
                Items.PARROT_FEATHER.item(), Items.LIGHT_BOOTS_FRAME.item(), Items.PARROT_FEATHER.item(),
                Items.EQUANIMOUS_GEM.item(), Items.MAGIC_LUMP_5.item(), Items.EQUANIMOUS_GEM.item()},
                new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, 300, 1, false, false, false),
                        new PotionEffect(PotionEffectType.JUMP_BOOST, 300, 1, false, false, false)})
                .register(FoxyMachines.getInstance());
        new ImprovementForge().register(FoxyMachines.getInstance());
        new PotionMixer().register(FoxyMachines.getInstance());
        new ElectricGoldRefinery().register(FoxyMachines.getInstance());
        new ChunkLoader().register(FoxyMachines.getInstance());
        new BoostedRail(Items.BOOSTED_RAIL, new ItemStack[] {
                SlimefunItems.LEAD_INGOT.item(), SlimefunItems.COPPER_WIRE.item(), SlimefunItems.LEAD_INGOT.item(),
                SlimefunItems.LEAD_INGOT.item(), new ItemStack(Material.STICK), SlimefunItems.LEAD_INGOT.item(),
                SlimefunItems.LEAD_INGOT.item(), SlimefunItems.COPPER_WIRE.item(), SlimefunItems.LEAD_INGOT.item()}, 4)
                .register(FoxyMachines.getInstance());
        new BoostedRail(Items.BOOSTED_ACTIVATOR_RAIL, new ItemStack[] {
                SlimefunItems.BRASS_INGOT.item(), SlimefunItems.COPPER_WIRE.item(), SlimefunItems.BRASS_INGOT.item(),
                SlimefunItems.BRASS_INGOT.item(), new ItemStack(Material.REPEATER), SlimefunItems.BRASS_INGOT.item(),
                SlimefunItems.BRASS_INGOT.item(), SlimefunItems.COPPER_WIRE.item(), SlimefunItems.BRASS_INGOT.item()}, 4)
                .register(FoxyMachines.getInstance());
        new BoostedRail(Items.BOOSTED_DETECTOR_RAIL, new ItemStack[] {
                SlimefunItems.BRASS_INGOT.item(), SlimefunItems.COPPER_WIRE.item(), SlimefunItems.BRASS_INGOT.item(),
                SlimefunItems.BRASS_INGOT.item(), new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE), SlimefunItems.BRASS_INGOT.item(),
                SlimefunItems.BRASS_INGOT.item(), SlimefunItems.COPPER_WIRE.item(), SlimefunItems.BRASS_INGOT.item()}, 4)
                .register(FoxyMachines.getInstance());
        new BoostedRail(Items.BOOSTED_POWERED_RAIL, new ItemStack[] {
                SlimefunItems.GOLD_24K.item(), SlimefunItems.COPPER_WIRE.item(), SlimefunItems.GOLD_24K.item(),
                SlimefunItems.GOLD_24K.item(), SlimefunItems.ELECTRO_MAGNET.item(), SlimefunItems.GOLD_24K.item(),
                SlimefunItems.GOLD_24K.item(), SlimefunItems.COPPER_WIRE.item(), SlimefunItems.GOLD_24K.item()}, 4)
                .register(FoxyMachines.getInstance());
        new BerryBushTrimmer().register(FoxyMachines.getInstance());

        new GhostBlockRemover().register(FoxyMachines.getInstance());
        ForcefieldDome.INSTANCE.register(FoxyMachines.getInstance());
        new RemoteController().register(FoxyMachines.getInstance());
        new PositionSelector().register(FoxyMachines.getInstance());
        new FillWand().register(FoxyMachines.getInstance());
        new SpongeWand().register(FoxyMachines.getInstance());

        if (customMobs) {
            new CustomMobSpawnEgg(Items.BOSSES_ITEM_GROUP, "PIXIE_QUEEN", Items.PIXIE_QUEEN_SPAWN_EGG, RecipeType.ANCIENT_ALTAR, new ItemStack[]{
                    Items.EQUANIMOUS_GEM.item(), Items.DEMONIC_PLATE.item(), Items.EQUANIMOUS_GEM.item(),
                    Items.PARROT_FEATHER.item(), new ItemStack(Material.EGG), Items.PARROT_FEATHER.item(),
                    Items.EQUANIMOUS_GEM.item(), Items.DEMONIC_PLATE.item(), Items.EQUANIMOUS_GEM.item()
                    }).register(FoxyMachines.getInstance());
            new CustomMobSpawnEgg(Items.BOSSES_ITEM_GROUP, "HEADLESS_HORSEMAN", Items.HEADLESS_HORSEMAN_SPAWN_EGG, RecipeType.ANCIENT_ALTAR, new ItemStack[]{
                    Items.EQUANIMOUS_GEM.item(), Items.DEMONIC_PLATE.item(), Items.EQUANIMOUS_GEM.item(),
                    Items.CURSED_RABBIT_PAW.item(), Items.PIXIE_QUEEN_HEART.item(), Items.CURSED_RABBIT_PAW.item(),
                    Items.EQUANIMOUS_GEM.item(), Items.DEMONIC_PLATE.item(), Items.EQUANIMOUS_GEM.item()
                    }).register(FoxyMachines.getInstance());

            new PixieQueen();
            new Pixie();
            new RiddenSkeletonHorse();
            new HeadlessHorseman();
            new Helldog();
        } else {
            Items.MAIN_ITEM_GROUP.removeSubGroup(Items.BOSSES_ITEM_GROUP);
        }

        if (FoxyMachines.getInstance().getConfig().getBoolean("ghost-blocks")) {
            for (Material material : Material.values()) {
                if (material.isBlock() && material.isSolid() && material.isOccluding() && !GhostBlock.EXCLUDED.contains(material)) {
                    SlimefunItemStack stack = new SlimefunItemStack(
                            "GHOST_BLOCK_" + material.name().toUpperCase(),
                            material,
                            "&fGhost Block: &6" + StringUtils.capitalize(material.name().replace("_", " ").toLowerCase()),
                            "",
                            "&7An intangible block.");

                    new GhostBlock(stack).register(FoxyMachines.getInstance());
                }
            }
        } else {
            Items.MAIN_ITEM_GROUP.removeSubGroup(Items.GHOST_BLOCKS_ITEM_GROUP);
        }
    }
}

final class ResearchSetup {
    static final ResearchSetup INSTANCE = new ResearchSetup();
    private boolean initialised;

    private ResearchSetup() {}

    public void init() {
        if (initialised) return;

        initialised = true;

        boolean customMobs = FoxyMachines.getInstance().getConfig().getBoolean("custom-mobs");

        new Research(new NamespacedKey(FoxyMachines.getInstance(), "electric_wind_staff"),
                6669666, "On the wind with the power of electricity", 22)
                .addItems(Items.ELECTRIC_WIND_STAFF.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "electric_fire_staffs"),
                6669667, "Create inferno", 34)
                .addItems(Items.ELECTRIC_FIRE_STAFF.item(), Items.ELECTRIC_FIRE_STAFF_II.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "healing_bow"),
                6669668, "Support", 30)
                .addItems(Items.HEALING_BOW.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "reinforced_string"),
                6669669, "Harder, Better, Stronger", 18)
                .addItems(Items.REINFORCED_STRING.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "improvement_forge"),
                6669670, "Beyond imaginations", 48)
                .addItems(Items.IMPROVEMENT_FORGE.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "improvement_core"),
                6669671, "Up 1", 28)
                .addItems(Items.IMPROVEMENT_CORE.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "potion_mixer"),
                6669672, "Brewing like never before", 28)
                .addItems(Items.POTION_MIXER.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "electric_gold_refinery"),
                6669673, "Get rid of the smelteries", 28)
                .addItems(Items.ELECTRIC_GOLD_REFINERY.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "chunk_loader"),
                6669674, "Quite a useful utility", 52)
                .addItems(Items.CHUNK_LOADER.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "stabilized_blistering_block"),
                6669675, "What a material", 26)
                .addItems(Items.STABILIZED_BLISTERING_BLOCK.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "boosted_rail"),
                6669676, "Faster, Better, Stronger", 16)
                .addItems(Items.BOOSTED_RAIL.item(), Items.BOOSTED_ACTIVATOR_RAIL.item(), Items.BOOSTED_DETECTOR_RAIL.item(), Items.BOOSTED_POWERED_RAIL.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "berry_bush_trimmer"),
                6669677, "Stay safe!", 20)
                .addItems(Items.BERRY_BUSH_TRIMMER.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "forcefield_dome"),
                6669678, "A safe space", 66)
                .addItems(Items.FORCEFIELD_DOME.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "remote_controller"),
                6669679, "From far away", 32)
                .addItems(Items.REMOTE_CONTROLLER.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "ingots"),
                6669680, "More ingots", 24)
                .addItems(Items.DEMONIC_INGOT.item(), Items.AQUATIC_NETHERITE_INGOT.item(), Items.DAMIENIUM.item(), Items.SWEET_INGOT.item(), Items.SWEETENED_SWEET_INGOT.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "sacrificial_altar"),
                6669681, "Sacrifice", 40)
                .addItems(Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_STAIRS.item(), Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_WALL.item(),
                        Items.SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS.item(), Items.SACRIFICIAL_ALTAR_BLACKSTONE_PRESSURE_PLATE.item(),
                        Items.SACRIFICIAL_ALTAR_SOUL_TORCH.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "sacrificial_altar_drops"),
                6669682, "Profit", 28)
                .addItems(Items.CURSED_RABBIT_PAW.item(), Items.HUMAN_SKULL.item(), Items.BLOOD.item(), Items.UNHOLY_WITHER_SKELETON_BONE.item(),
                        Items.POLAR_FOX_HIDE.item(), Items.MAGMA_ESSENCE.item(), Items.TROPICAL_FISH_SCALE.item(), Items.PARROT_FEATHER.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "demonic_plate"),
                6669683, "Demonic plate", 16)
                .addItems(Items.DEMONIC_PLATE.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "forcefield_materials"),
                6669684, "On my way to Forcefield", 36)
                .addItems(Items.FORCEFIELD_ENGINE.item(), Items.FORCEFIELD_STABILIZER.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "wireless_transmitter"),
                6669685, "No more wires", 26)
                .addItems(Items.WIRELESS_TRANSMITTER.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "poseidons_fishing_rod"),
                6669686, "With the power of Poseidon", 32)
                .addItems(Items.POSEIDONS_FISHING_ROD.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "fishing_materials"),
                6669687, "Better Fishing", 26)
                .addItems(Items.POSEIDONS_BLESSING.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "swords"),
                6669688, "Better Swords", 44)
                .addItems(Items.CURSED_SWORD.item(), Items.CELESTIAL_SWORD.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "magic_lumps"),
                6669689, "More Lumps, More Magic", 12)
                .addItems(Items.MAGIC_LUMP_4.item(), Items.MAGIC_LUMP_5.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "purified_bone"),
                6669690, "More Lumps, More Magic", 22)
                .addItems(Items.PURIFIED_BONE.item(), Items.PURE_BONE_DUST.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "blood_infused_skull"),
                6669691, "Bloodier", 16)
                .addItems(Items.BLOOD_INFUSED_SKULL.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "armor"),
                6669692, "Better Armor", 42)
                .addItems(Items.AQUATIC_HELMET.item(), Items.RESISTANT_CHESTPLATE.item(), Items.FIERY_LEGGINGS.item(), Items.LIGHT_BOOTS.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "bloody_bucket"),
                6669693, "Bloody Bucket", 20)
                .addItems(Items.BUCKET_OF_BLOOD.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "equanimous_gem"),
                6669694, "Balanced, as things should be", 36)
                .addItems(Items.EQUANIMOUS_GEM.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "ultimate_sword"),
                6669695, "The Ultimate Sword", 52)
                .addItems(Items.ELUCIDATOR.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "armor_frame"),
                6669696, "Armor Frame", 24)
                .addItems(Items.AQUATIC_HELMET_FRAME.item(), Items.RESISTANT_CHESTPLATE_FRAME.item(), Items.FIERY_LEGGINGS_FRAME.item(), Items.LIGHT_BOOTS_FRAME.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "unbreakable_rune"),
                6669697, "Who needs anvil?", 32)
                .addItems(Items.UNBREAKABLE_RUNE.item())
                .register();
        if (customMobs) {
            new Research(new NamespacedKey(FoxyMachines.getInstance(), "pixie_queen"),
                    6669698, "A worthy opponent.", 52)
                    .addItems(Items.PIXIE_QUEEN_SPAWN_EGG.item(), Items.PIXIE_QUEEN_HEART.item())
                    .register();
            new Research(new NamespacedKey(FoxyMachines.getInstance(), "pixie_dust"),
                    6669699, "Strength Boost.", 22)
                    .addItems(Items.PIXIE_DUST.item())
                    .register();
            new Research(new NamespacedKey(FoxyMachines.getInstance(), "headless_horseman"),
                    6669700, "Good luck", 56)
                    .addItems(Items.HEADLESS_HORSEMAN_SPAWN_EGG.item(), Items.VILE_PUMPKIN.item())
                    .register();
            new Research(new NamespacedKey(FoxyMachines.getInstance(), "vile_seeds"),
                    6669701, "Health Boost.", 24)
                    .addItems(Items.VILE_SEEDS.item())
                    .register();
        }
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "acri_arcum"),
                6669702, "Bow.", 46)
                .addItems(Items.ACRI_ARCUM.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "ghost_block_remover"),
                6669703, "Ghost Block Remover.", 44)
                .addItems(Items.GHOST_BLOCK_REMOVER.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "build_wands"),
                6669704, "Building made easier.", 36)
                .addItems(Items.POSITION_SELECTOR.item(), Items.FILL_WAND.item(), Items.SPONGE_WAND.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "nuclear_salt"),
                6669704, "Don't forget your hazmat suit.", 16)
                .addItems(Items.NUCLEAR_SALT.item())
                .register();
        new Research(new NamespacedKey(FoxyMachines.getInstance(), "compressed_sponge"),
                6669704, "Succ.", 14)
                .addItems(Items.COMPRESSED_SPONGE.item())
                .register();
    }
}

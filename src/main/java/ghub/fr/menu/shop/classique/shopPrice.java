package ghub.fr.menu.shop.classique;

import org.bukkit.Material;

public class shopPrice {
    public static int getPrix(Material material) {
        switch (material) {
            // Blocks
            case COBBLESTONE:
                return 10;
            case MOSSY_COBBLESTONE:
                return 10;
            case GRASS_BLOCK:
                return 500;
            case DIRT:
                return 100;
            case CLAY:
                return 50;
            case GRAVEL:
                return 50;
            case SAND:
                return 50;
            case RED_SAND:
                return 50;
            case ICE:
                return 10;
            case BLUE_ICE:
                return 1000;
            case FROSTED_ICE:
                return 100;
            case PACKED_ICE:
                return 100;
            case END_STONE:
                return 25;
            case OAK_LOG:
                return 20;
            case ACACIA_LOG:
                return 20;
            case BIRCH_LOG:
                return 20;
            case SPRUCE_LOG:
                return 20;
            case JUNGLE_LOG:
                return 20;
            case DARK_OAK_LOG:
                return 20;
            case PRISMARINE:
                return 400;
            case PRISMARINE_BRICKS:
                return 900;
            case DARK_PRISMARINE:
                return 900;
            // Seeds
            case WHEAT_SEEDS:
                return 10;
            case BEETROOT_SEEDS:
                return 10;
            case BEETROOT:
                return 10;
            case COCOA_BEANS:
                return 50;
            case MELON_SEEDS:
                return 50;
            case PUMPKIN_SEEDS:
                return 50;
            case MELON_SLICE:
                return 50;
            case MELON:
                return 450;
            case PUMPKIN:
                return 10;
            case NETHER_WART:
                return 100;
            case CACTUS:
                return 10;
            case SUGAR_CANE:
                return 10;
            case CHORUS_FLOWER:
                return 100;
            case CHORUS_FRUIT:
                return 20;
            case KELP:
                return 10;
            case SEA_PICKLE:
                return 50;
            case BAMBOO:
                return 10;
            case LILY_PAD:
                return 100;
            // Ores
            case COAL:
                return 100;
            case CHARCOAL:
                return 100;
            case REDSTONE:
                return 200;
            case LAPIS_LAZULI:
                return 200;
            case GLOWSTONE:
                return 250;
            case QUARTZ:
                return 500;
            case IRON_INGOT:
                return 500;
            case COPPER_INGOT:
                return 500;
            case GOLD_INGOT:
                return 1000;
            case DIAMOND:
                return 5000;
            case EMERALD:
                return 2500;
            case NETHERITE_SCRAP:
                return 25000;
            // Monstres
            case HONEY_BOTTLE:
                return 200;
            case HONEYCOMB:
                return 100;
            case BONE:
                return 10;
            case MAGMA_CREAM:
                return 250;
            case SCUTE:
                return 150;
            case ARROW:
                return 10;
            case STRING:
                return 10;
            case SPIDER_EYE:
                return 10;
            case GUNPOWDER:
                return 10;
            case ROTTEN_FLESH:
                return 10;
            case ENDER_PEARL:
                return 250;
            case BLAZE_ROD:
                return 500;
            case GHAST_TEAR:
                return 5000;
            case SLIME_BALL:
                return 200;
            case INK_SAC:
                return 10;
            case PRISMARINE_CRYSTALS:
                return 100;
            case PRISMARINE_SHARD:
                return 100;
            case RABBIT_FOOT:
                return 100;
            case WHITE_WOOL:
                return 10;
            case NAUTILUS_SHELL:
                return 500;
            case LEATHER:
                return 50;
            case EGG:
                return 10;
            case FEATHER:
                return 10;
            case TURTLE_EGG:
                return 500;
            case PUFFERFISH:
                return 250;
            // Plantes
            case DEAD_BUSH:
                return 750;
            case BROWN_MUSHROOM:
                return 10;
            case RED_MUSHROOM:
                return 10;
            case OAK_SAPLING:
                return 50;
            case SPRUCE_SAPLING:
                return 50;
            case BIRCH_SAPLING:
                return 50;
            case JUNGLE_SAPLING:
                return 50;
            case ACACIA_SAPLING:
                return 50;
            case DARK_OAK_SAPLING:
                return 50;
            // Foods
            case APPLE:
                return 50;
            case GOLDEN_APPLE:
                return 2_500;
            case ENCHANTED_GOLDEN_APPLE:
                return 5_000;
            case POTATO:
                return 10;
            case CARROT:
                return 10;
            case PUMPKIN_PIE:
                return 250;
            case COOKIE:
                return 50;
            case BREAD:
                return 100;
            case CAKE:
                return 250;
            case SWEET_BERRIES:
                return 50;
            case COOKED_BEEF:
                return 10;
            case COOKED_CHICKEN:
                return 10;
            case COOKED_COD:
                return 10;
            case COOKED_MUTTON:
                return 10;
            case COOKED_PORKCHOP:
                return 10;
            case COOKED_RABBIT:
                return 10;
            case COOKED_SALMON:
                return 10;
            // Specials
            case LEATHER_HORSE_ARMOR:
                return 10_000;
            case IRON_HORSE_ARMOR:
                return 30_000;
            case GOLDEN_HORSE_ARMOR:
                return 75_000;
            case DIAMOND_HORSE_ARMOR:
                return 100_000;
            case EXPERIENCE_BOTTLE:
                return 1_000;
            case BELL:
                return 5_000;
            case END_PORTAL_FRAME:
                return 25_000;
            case HEART_OF_THE_SEA:
                return 25_000;
            case CONDUIT:
                return 30000;
            case DRAGON_HEAD:
                return 25_000;
            case SHULKER_SHELL:
                return 15_000;
            case NAME_TAG:
                return 5_000;
            case SADDLE:
                return 2_500;
            case WITHER_SKELETON_SKULL:
                return 25_000;
            case DRAGON_BREATH:
                return 1_000;
            case ELYTRA:
                return 250_000;
            case DRAGON_EGG:
                return 200_000;
            case SPONGE:
                return 1_500;
            case TOTEM_OF_UNDYING:
                return 50_000;
            case COBWEB:
                return 750;
            case BEE_NEST:
                return 5_000;
            case BEEHIVE:
                return 5_000;
            case LAVA_BUCKET:
                return 5_000;
            case OBSIDIAN:
                return 3_000;
            case PUFFERFISH_BUCKET:
                return 2_500;
            case SALMON_BUCKET:
                return 2_500;
            case TROPICAL_FISH_BUCKET:
                return 2_500;
            // Oeufs
            case BEE_SPAWN_EGG:
                return 10_000;
            case CAT_SPAWN_EGG:
                return 50_000;
            case DOLPHIN_SPAWN_EGG:
                return 100_000;
            case DONKEY_SPAWN_EGG:
                return 25_000;
            case FOX_SPAWN_EGG:
                return 75_000;
            case HORSE_SPAWN_EGG:
                return 50_000;
            case LLAMA_SPAWN_EGG:
                return 100_000;
            case MOOSHROOM_SPAWN_EGG:
                return 100_000;
            case MULE_SPAWN_EGG:
                return 25_000;
            case OCELOT_SPAWN_EGG:
                return 50_000;
            case PANDA_SPAWN_EGG:
                return 100_000;
            case PARROT_SPAWN_EGG:
                return 100_000;
            case PILLAGER_SPAWN_EGG:
                return 50_000;
            case POLAR_BEAR_SPAWN_EGG:
                return 25_000;
            case RABBIT_SPAWN_EGG:
                return 10_000;
            case SKELETON_HORSE_SPAWN_EGG:
                return 250_000;
            case SLIME_SPAWN_EGG:
                return 100_000;
            case SQUID_SPAWN_EGG:
                return 25_000;
            case TRADER_LLAMA_SPAWN_EGG:
                return 100_000;
            case VILLAGER_SPAWN_EGG:
                return 250_000;
            case WANDERING_TRADER_SPAWN_EGG:
                return 100_000;
            case WOLF_SPAWN_EGG:
                return 50_000;
            case ZOMBIE_HORSE_SPAWN_EGG:
                return 250_000;
            // Coraux
            case BRAIN_CORAL_FAN:
                return 500;
            case BUBBLE_CORAL_FAN:
                return 500;
            case HORN_CORAL_FAN:
                return 500;
            case FIRE_CORAL_FAN:
                return 500;
            case TUBE_CORAL_FAN:
                return 500;
            case DEAD_BRAIN_CORAL_FAN:
                return 500;
            case DEAD_BUBBLE_CORAL_FAN:
                return 500;
            case DEAD_HORN_CORAL_FAN:
                return 500;
            case DEAD_FIRE_CORAL_FAN:
                return 500;
            case DEAD_TUBE_CORAL_FAN:
                return 500;
            case BRAIN_CORAL:
                return 500;
            case BUBBLE_CORAL:
                return 500;
            case HORN_CORAL:
                return 500;
            case FIRE_CORAL:
                return 500;
            case TUBE_CORAL:
                return 500;
            case DEAD_BRAIN_CORAL:
                return 500;
            case DEAD_BUBBLE_CORAL:
                return 500;
            case DEAD_HORN_CORAL:
                return 500;
            case DEAD_FIRE_CORAL:
                return 500;
            case DEAD_TUBE_CORAL:
                return 500;
            case BRAIN_CORAL_BLOCK:
                return 500;
            case BUBBLE_CORAL_BLOCK:
                return 500;
            case HORN_CORAL_BLOCK:
                return 500;
            case FIRE_CORAL_BLOCK:
                return 500;
            case TUBE_CORAL_BLOCK:
                return 500;
            case DEAD_BRAIN_CORAL_BLOCK:
                return 500;
            case DEAD_BUBBLE_CORAL_BLOCK:
                return 500;
            case DEAD_HORN_CORAL_BLOCK:
                return 500;
            case DEAD_FIRE_CORAL_BLOCK:
                return 500;
            case DEAD_TUBE_CORAL_BLOCK:
                return 500;
            // Dye
            case BLACK_DYE:
                return 5;
            case BLUE_DYE:
                return 5;
            case BROWN_DYE:
                return 5;
            case CYAN_DYE:
                return 5;
            case GRAY_DYE:
                return 5;
            case GREEN_DYE:
                return 5;
            case LIGHT_BLUE_DYE:
                return 5;
            case LIGHT_GRAY_DYE:
                return 5;
            case LIME_DYE:
                return 5;
            case MAGENTA_DYE:
                return 5;
            case ORANGE_DYE:
                return 5;
            case PINK_DYE:
                return 5;
            case PURPLE_DYE:
                return 5;
            case RED_DYE:
                return 5;
            case WHITE_DYE:
                return 5;
            case YELLOW_DYE:
                return 5;
            // Nether
            case CRIMSON_FUNGUS:
                return 100;
            case WARPED_FUNGUS:
                return 100;
            case CRIMSON_ROOTS:
                return 100;
            case WEEPING_VINES:
                return 100;
            case WARPED_ROOTS:
                return 100;
            case TWISTING_VINES:
                return 100;
            case NETHER_SPROUTS:
                return 100;
            case NETHER_BRICK:
                return 20;
            case BLACKSTONE:
                return 200;
            case WARPED_NYLIUM:
                return 1000;
            case CRIMSON_NYLIUM:
                return 1000;
            case WARPED_STEM:
                return 200;
            case CRIMSON_STEM:
                return 200;
            case WARPED_WART_BLOCK:
                return 150;
            case CRYING_OBSIDIAN:
                return 2500;
            case SHROOMLIGHT:
                return 100;
            case BASALT:
                return 50;
            case MAGMA_BLOCK:
                return 750;
            case NETHERRACK:
                return 50;
            case SOUL_SAND:
                return 200;
            case SOUL_SOIL:
                return 500;
            case NETHER_STAR:
                return 75000;
            case WITHER_ROSE:
                return 1000;
            //
            default:
                return Integer.MAX_VALUE;
        }
    }
}
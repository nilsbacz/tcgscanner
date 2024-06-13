package com.nb.tcgscanner.card.trait;

public enum Rarity {
    COMMON,
    UNCOMMON,
    RARE,
    RARE_HOLO,
    REVERSE_HOLO;

    // You can add more rarity levels as needed

    // Optional: Method to convert a string to CardRarity enum
    public static Rarity fromString(String rarity) {
        return switch (rarity.toLowerCase()) {
            case "common" -> COMMON;
            case "uncommon" -> UNCOMMON;
            case "rare" -> RARE;
            case "rare holo" -> RARE_HOLO;
            case "reverse holo" -> REVERSE_HOLO;
            default -> throw new IllegalArgumentException("Invalid rarity: " + rarity);
        };
    }
}
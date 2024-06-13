package com.nb.tcgscanner.card;

import com.nb.tcgscanner.card.trait.ElementalType;
import com.nb.tcgscanner.card.trait.Rarity;

public abstract class Card {
    private final String name;
    private final ElementalType elementalType;
    private final String number;
    private final Rarity rarity;

    protected Card(CardBuilder<?> builder) {
        this.name = builder.name;
        this.elementalType = builder.elementalType;
        this.number = builder.number;
        this.rarity = builder.rarity;
    }

    public String getName() {
        return name;
    }

    public String getElementalType() {
        return elementalType != null ? elementalType.toString() : null;
    }

    public String getRarity() {
        return rarity != null ? rarity.toString() : null;
    }

    public String getNumber() {
        return number;
    }

    public static abstract class CardBuilder<T extends CardBuilder<T>> {
        protected String name;
        protected ElementalType elementalType;
        protected String number;
        protected Rarity rarity;

        public T withName(String name) {
            this.name = name;
            return self();
        }

        public T withElementalType(ElementalType elementalType) {
            this.elementalType = elementalType;
            return self();
        }

        public T withNumber(String number) {
            this.number = number;
            return self();
        }

        public T withRarity(Rarity rarity) {
            this.rarity = rarity;
            return self();
        }

        protected abstract T self();

        public abstract Card build();
    }
}

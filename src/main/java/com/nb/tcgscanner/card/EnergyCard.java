package com.nb.tcgscanner.card;

import com.nb.tcgscanner.card.trait.Ability;

public final class EnergyCard extends Card {

    private final Ability ability;

    private EnergyCard(EnergyCardBuilder builder) {
        super(builder);
        this.ability = builder.ability;
    }

    public Ability getAbility() {
        return ability;
    }

    public static EnergyCard.EnergyCardBuilder builder() {
        return new EnergyCard.EnergyCardBuilder();
    }

    public static class EnergyCardBuilder extends Card.CardBuilder<EnergyCardBuilder> {
        private Ability ability;

        public EnergyCardBuilder withAbility(Ability ability) {
            this.ability = ability;
            return self();
        }

        @Override
        protected EnergyCardBuilder self() {
            return this;
        }

        @Override
        public EnergyCard build() {
            return new EnergyCard(this);
        }
    }
}

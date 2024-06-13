package com.nb.tcgscanner.card;

import com.nb.tcgscanner.card.trait.TrainerType;

public final class TrainerCard extends Card {
    private final TrainerType trainerType;
    private final String description;

    private TrainerCard(TrainerCardBuilder builder) {
        super(builder);
        this.trainerType = builder.trainerType;
        this.description = builder.description;
    }

    public String getDescription() {
        return description;
    }

    public TrainerType getTrainerType() {
        return trainerType;
    }

    public static TrainerCard.TrainerCardBuilder builder() {
        return new TrainerCard.TrainerCardBuilder();
    }

    public static class TrainerCardBuilder extends Card.CardBuilder<TrainerCardBuilder> {
        private TrainerType trainerType;
        private String description;

        public TrainerCardBuilder withTrainerType(TrainerType trainerType) {
            this.trainerType = trainerType;
            return self();
        }

        public TrainerCardBuilder withDescription(String description) {
            this.description = description;
            return self();
        }

        @Override
        protected TrainerCardBuilder self() {
            return this;
        }

        @Override
        public TrainerCard build() {
            return new TrainerCard(this);
        }
    }
}

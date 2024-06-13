package com.nb.tcgscanner.card;

public final class StadiumCard extends Card{
    private final String description;

    private StadiumCard(StadiumCardBuilder builder) {
        super(builder);
        this.description = builder.description;
    }

    public String getDescription() {
        return description;
    }

    public static StadiumCardBuilder builder() {
        return new StadiumCardBuilder();
    }

    public static class StadiumCardBuilder extends Card.CardBuilder<StadiumCardBuilder> {
        private String description;

        public StadiumCardBuilder withDescription(String description) {
            this.description = description;
            return self();
        }

        @Override
        protected StadiumCard.StadiumCardBuilder self() {
            return this;
        }

        @Override
        public StadiumCard build() {
            return new StadiumCard(this);
        }
    }
}

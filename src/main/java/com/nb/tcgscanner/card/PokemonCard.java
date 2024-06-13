package com.nb.tcgscanner.card;

import com.nb.tcgscanner.card.trait.Ability;
import com.nb.tcgscanner.card.trait.Attack;
import com.nb.tcgscanner.card.trait.AttackCost;
import com.nb.tcgscanner.card.trait.ElementalType;

import java.util.ArrayList;

public final class PokemonCard extends Card {
    private final Ability ability;
    private final int hp;
    private final ArrayList<Attack> attackList;
    private final ElementalType weakness;
    private final ElementalType resistance;
    private final int retreat;

    private PokemonCard(PokemonCardBuilder builder) {
        super(builder);
        this.ability = builder.ability;
        this.hp = builder.hp;
        this.attackList = builder.attackList;
        this.weakness = builder.weakness;
        this.resistance = builder.resistance;
        this.retreat = builder.retreat;
    }

    public int getHp() {
        return hp;
    }

    public Ability getAbility() {
        return ability;
    }

    public ArrayList<Attack> getAttackList() {
        return attackList;
    }

    public ElementalType getWeakness() {
        return weakness;
    }

    public ElementalType getResistance() {
        return resistance;
    }

    public int getRetreat() {
        return retreat;
    }

    public static PokemonCardBuilder builder() {
        return new PokemonCardBuilder();
    }

    public static class PokemonCardBuilder extends Card.CardBuilder<PokemonCardBuilder> {
        private Ability ability;
        private int hp;
        private final ArrayList<Attack> attackList = new ArrayList<>();
        private ElementalType weakness;
        private ElementalType resistance;
        private int retreat;

        public PokemonCardBuilder withAbility(Ability ability) {
            this.ability = ability;
            return self();
        }

        public PokemonCardBuilder withHp(int hp) {
            this.hp = hp;
            return self();
        }

        public PokemonCardBuilder withAttack(Attack attack) {
            this.attackList.add(attack);
            return self();
        }

        public PokemonCardBuilder withAttackNoCosts(String name, int power, String description) {
            ArrayList<AttackCost> attackCostList = new ArrayList<>();
            attackCostList.add(new AttackCost(0, ElementalType.NONE));

            this.attackList.add(new Attack(name, power, description, attackCostList));
            return self();
        }

        public PokemonCardBuilder withWeakness(ElementalType weakness) {
            this.weakness = weakness;
            return self();
        }

        public PokemonCardBuilder withResistance(ElementalType resistance) {
            this.resistance = resistance;
            return self();
        }

        public PokemonCardBuilder withRetreat(int retreat) {
            this.retreat = retreat;
            return self();
        }

        @Override
        protected PokemonCardBuilder self() {
            return this;
        }

        @Override
        public PokemonCard build() {
            return new PokemonCard(this);
        }
    }
}

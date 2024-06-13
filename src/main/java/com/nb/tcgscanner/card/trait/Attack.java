package com.nb.tcgscanner.card.trait;

import java.util.ArrayList;

public record Attack(String name, int power, String description, ArrayList<AttackCost> attackCostList) {
}

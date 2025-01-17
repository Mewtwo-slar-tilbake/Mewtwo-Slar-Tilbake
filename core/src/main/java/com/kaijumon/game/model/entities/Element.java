package com.kaijumon.game.model.entities;

/**
 * Represents kaijumon and move elements. Elements are weak or strong against other elements.
 */
public enum Element {

    NORMAL(
            new String[]{},
            new String[]{"Rock"},
            new String[]{"Ghost"}
    ),
    FIGHTING(
            new String[]{"Normal", "Rock", "Ice"},
            new String[]{"Flying", "Poison", "Bug", "Psychic"},
            new String[]{"Ghost"}
    ),
    FLYING(
            new String[]{"Fighting", "Grass"},
            new String[]{"Rock", "Electric"},
            new String[]{}
    ),
    POISON(
            new String[]{"Grass"},
            new String[]{"Poison", "Ground", "Rock", "Bug"},
            new String[]{}
    ),
    GROUND(
            new String[]{"Poison", "Rock", "Fire", "Electric"},
            new String[]{"Bug", "Grass"},
            new String[]{"Flying"}
    ),
    ROCK(
            new String[]{"Flying", "Bug", "Fire"},
            new String[]{"Fighting", "Ground"},
            new String[]{}
    ),
    BUG(
            new String[]{"Grass", "Psychic"},
            new String[]{"Fighting", "Flying", "Poison", "Ghost", "Fire"},
            new String[]{}
    ),
    GHOST(
            new String[]{"Ghost", "Psychic"},
            new String[]{},
            new String[]{"Normal"}
    ),
    FIRE(
            new String[]{"Grass", "Ice", "Bug"},
            new String[]{"Fire", "Water", "Rock", "Dragon"},
            new String[]{}
    ),
    WATER(
            new String[]{"Fire", "Rock", "Ground"},
            new String[]{"Water", "Grass", "Dragon"},
            new String[]{}
    ),
    GRASS(
            new String[]{"Water", "Ground", "Rock"},
            new String[]{"Fire", "Grass", "Poison", "Flying", "Bug", "Dragon"},
            new String[]{}
    ),
    ELECTRIC(
            new String[]{"Water", "Flying"},
            new String[]{"Grass", "Electric", "Dragon"},
            new String[]{"Ground"}
    ),
    PSYCHIC(
            new String[]{"Fighting", "Poison"},
            new String[]{"Psychic"},
            new String[]{}
    ),
    ICE(
            new String[]{"Grass", "Ground", "Flying", "Dragon"},
            new String[]{"Fire", "Water", "Ice"},
            new String[]{}
    ),
    DRAGON(
            new String[]{"Dragon"},
            new String[]{},
            new String[]{}
    );

    private final String[] SUPER_EFFECTIVE, NOT_VERY_EFFECTIVE, NO_EFFECT;

    Element(String[] superEffective, String[] notVeryEffective, String[] noEffect) {
        SUPER_EFFECTIVE = superEffective;
        NOT_VERY_EFFECTIVE = notVeryEffective;
        NO_EFFECT = noEffect;
    }

    private boolean contains(String[] t, Element p)
    {
        if(t.length == 0)
            return false;

        for(String type : t)
            if(type.equalsIgnoreCase(p + ""))
                return true;

        return false;
    }

    /**
     * Determines whether this element is "Super Effective" (x2) against the given element
     * @param type the element to check
     * @return true if this element is super effective against the given element.
     */
    public boolean isSuperEffectiveAgainst(Element type)
    {
        return contains(SUPER_EFFECTIVE, type);
    }

    /**
     * Determines whether this element is "Not Very Effective" (x0.5) against the given element
     * @param type the element to check
     * @return true if this element is not very effective against the given element.
     */
    public boolean isNotVeryEffectiveAgainst(Element type)
    {
        return contains(NOT_VERY_EFFECTIVE, type);
    }

    /**
     * Determines whether this element has "No Effect" (x0) against the given element
     * @param type the element to check
     * @return true if this element has no effect against the given element.
     */
    public boolean hasNoEffectOn(Element type)
    {
        return contains(NO_EFFECT, type);
    }

}

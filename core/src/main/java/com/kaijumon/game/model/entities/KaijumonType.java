package com.kaijumon.game.model.entities;

public enum KaijumonType {

    GRASS(
            new String[] { "Water", "Rock" },
            new String[] { "Fire", "Grass", "Poison", "Flying" },
            new String[] { }
    ),
    WATER(
            new String[] { "Fire", "Rock" },
            new String[] { "Water", "Grass" },
            new String[] { }
    ),
    FIRE(
            new String[] { "Grass" },
            new String[] { "Fire", "Water", "Rock" },
            new String[] { }
    ),
    ELECTRIC(
            new String[] { "Water", "Flying" },
            new String[] { "Grass", "Electric" },
            new String[] { }
    ),
    ROCK(
            new String[] { "Flying", "Fire" },
            new String[] { },
            new String[] { }
    ),
    FLYING(
            new String[] { "Grass" },
            new String[] { "Rock", "Electric" },
            new String[] { }
    ),
    PSYCHIC(
            new String[] { "Poison" },
            new String[] { "Psychic" },
            new String[] { }
    ),
    POISON(
            new String[] { "Grass" },
            new String[] { "Poison", "Rock" },
            new String[] { }
    ),
    GHOST(
            new String[] { "Ghost", "Psychic" },
            new String[] { },
            new String[] { }
    );

    private final String[] SUPER_EFFECTIVE, NOT_VERY_EFFECTIVE, NO_EFFECT;

    KaijumonType(String[] superEffective, String[] notVeryEffective, String[] noEffect) {
        SUPER_EFFECTIVE = superEffective;
        NOT_VERY_EFFECTIVE = notVeryEffective;
        NO_EFFECT = noEffect;
    }

    private boolean contains(String[] t, KaijumonType p)
    {
        if(t.length == 0)
        {
            return false;
        }

        for(String type : t)
        {
            if(type.equalsIgnoreCase(p + ""))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines whether this type is "Super Effective" (x2) against the given type
     * @param type the KaijumonType to check
     * @return true if this type is super effective against the given type.
     */
    public boolean isSuperEffectiveAgainst(KaijumonType type)
    {
        return contains(SUPER_EFFECTIVE, type);
    }

    /**
     * Determines whether this type is "Not Very Effective" (x0.5) against the given type
     * @param type the KaijumonType to check
     * @return true if this type is not very effective against the given type.
     */
    public boolean isNotVeryEffectiveAgainst(KaijumonType type)
    {
        return contains(NOT_VERY_EFFECTIVE, type);
    }

    /**
     * Determines whether this type has "No Effect" (x0) against the given type
     * @param type the KaijumonType to check
     * @return true if this type has no effect against the given type.
     */
    public boolean hasNoEffectOn(KaijumonType type)
    {
        return contains(NO_EFFECT, type);
    }

}
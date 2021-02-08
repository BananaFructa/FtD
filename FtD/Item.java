public class Item {

    private int dmg = 0;
    private int manaDrain = 0;
    private int manaPoint = 0;
    private int defPoint = 0;
    private int lvl = 0;
    private int manaRegenPoint = 0;
    private int hpRegenPoint = 0;
    private int criticalChance = 0;
    private int hpGain;
    private int manaGain;
    private String clasa;// Wand Robe Hat Boots Ring Potion

    public Item(){

    }

    public void generateStat() {
        dmg *= lvl;
        manaDrain *= lvl;
        manaPoint *= lvl;
        defPoint *= lvl;
        manaRegenPoint *= lvl;
        criticalChance *= lvl;
    }

    public int getCriticalChance() {
        return criticalChance;
    }

    public int getDefPoint() {
        return defPoint;
    }

    public int getDmg() {
        return dmg;
    }

    public int getHpGain() {
        return hpGain;
    }

    public int getHpRegenPoint() {
        return hpRegenPoint;
    }

    public int getLvl() {
        return lvl;
    }

    public int getManaDrain() {
        return manaDrain;
    }

    public int getManaGain() {
        return manaGain;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public int getManaRegenPoint() {
        return manaRegenPoint;
    }
}

public class Item {

    public String tex = "fig.png";
    public float Damage;
    public float ManaCost;
    public float AttackSpeed;
    public float KnockBack;

    public ProprietatiParticuleEmise ppe;
    public ProprietatiProiectil pp;

    public boolean Attacks = false;

    public Item() {

    }

    public Item(String tex,float Damage,float ManaCost,float AttackSpeed,float KnockBack,boolean Attacks) {
        this.tex = tex;
        this.Damage = Damage;
        this.ManaCost = ManaCost;
        this.AttackSpeed = AttackSpeed;
        this.KnockBack = KnockBack;
        this.Attacks = Attacks;
    }

    public Item(String tex,float Damage,float ManaCost,float AttackSpeed,float KnockBack,boolean Attacks,ProprietatiParticuleEmise ppe,ProprietatiProiectil pp) {
        this(tex,Damage,ManaCost,AttackSpeed,KnockBack,Attacks);
        this.ppe = ppe;
        this.pp = pp;
        this.pp.Damage = Damage;
        this.pp.Knockback = KnockBack;
    }

    @Override
    protected Item clone() {
        return new Item(tex,Damage,ManaCost,AttackSpeed,KnockBack,Attacks,ppe,pp);
    }
}

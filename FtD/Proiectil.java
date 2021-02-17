public class Proiectil extends SistemParticule implements IContainer {

    public Proiectil(int x,int y,float Unghi,ProprietatiParticuleEmise proprietatiParticuleEmise,ProprietatiProiectil proprietatiProiectil) {
        super(x,y,proprietatiParticuleEmise,proprietatiProiectil.Viteza,Unghi,proprietatiProiectil.Lifetime);
        this.setImage(proprietatiProiectil.tex);
    }

    @Override
    public void Update() {
        super.Update();
    }


}

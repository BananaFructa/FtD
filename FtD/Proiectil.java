public class Proiectil extends SistemParticule {

    public Proiectil(int x,int y,float Unghi,ProprietatiParticuleEmise proprietatiParticuleEmise,ProprietatiProiectil proprietatiProiectil) {
        super(x,y,proprietatiParticuleEmise,proprietatiProiectil.Viteza,Unghi,proprietatiProiectil.Lifetime);
    }

    @Override
    public void Update() {
        super.Update();
    }


}

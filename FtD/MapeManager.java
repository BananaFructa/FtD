import greenfoot.GreenfootImage;

import java.io.FileNotFoundException;

public class MapeManager {
    GreenfootImage []Images=new GreenfootImage[4];
    Mape map=new Mape();
    UIActor [][][]world;
    GreenfootImage[][] firstImage;
    GreenfootImage[][] secondImage;
    GreenfootImage[][] thirdImage;
    GreenfootImage[][] forthImage;
    int a1=18,d1=16,a2=18,d2=8,a3=20,d3=40,a4=21,d4=19;
   public MapeManager() throws FileNotFoundException {
       Images[0]=new GreenfootImage("mape/[Base]BaseChip_pipo.png");
       Images[2]=new GreenfootImage("mape/ashlands_tileset.png");
       Images[1]=new GreenfootImage("mape/4 BigSet.png");
       Images[3]=new GreenfootImage("mape/Serene_Village_16x16_jMKXuK");
       int w=Images[0].getWidth(), h=Images[0].getHeight();
       firstImage = new GreenfootImage[a1][d1];
       for(int x=0; x<a1; x++)
           for(int y=0; y<d1; y++)
           {
               firstImage[x][y]=new GreenfootImage(w/a1, h/d1);
               firstImage[x][y].drawImage(Images[0], -x*w/a1, -y*h/d1);
           }
       h=Images[1].getHeight(); w=Images[1].getWidth();
       secondImage = new GreenfootImage[a2][d2];
       for(int x=0; x<a2; x++)
           for(int y=0; y<d2; y++)
           {
               secondImage[x][y]=new GreenfootImage(w/a2, h/d2);
               secondImage[x][y].drawImage(Images[1], -x*w/a2, -y*h/d2);
           }
       h=Images[2].getHeight(); w=Images[2].getWidth();
       thirdImage = new GreenfootImage[a3][d3];
       for(int x=0; x<a3; x++)
           for(int y=0; y<d3; y++)
           {
               thirdImage[x][y]=new GreenfootImage(w/a3, h/d3);
               thirdImage[x][y].drawImage(Images[2], -x*w/a3, -y*h/d3);
           }
       h=Images[3].getHeight(); w=Images[3].getWidth();
       forthImage = new GreenfootImage[a4][d4];
       for(int x=0; x<a4; x++)
           for(int y=0; y<d4; y++)
           {
               forthImage[x][y]=new GreenfootImage(w/a4, h/d4);
               forthImage[x][y].drawImage(Images[3], -x*w/a4, -y*h/d4);
           }
       world=new UIActor[4][100][100];
   }
   public void createLayer(int layer){
       int c;
     for(int i=0;i<100;i++){
         for(int j=0;j<100;j++){
             c=map.mape[layer][i][j]-1;
             if(c!=0){
                 c=map.mape[layer][i][j]-1;
                 world[layer][i][j]=new UIActor(j*16,i*16);
                 if(c>=0 && c<=287){
                     world[layer][i][j].setImage(firstImage[c/d1][c%d1]);
                 }
                 if(c>=4256 && c<=5056){
                     c-=4256;
                     world[layer][i][j].setImage(thirdImage[c/d3][c%d3]);
                 }
                 if(c>=5096 && c<=5240){
                     c-=5096;
                     world[layer][i][j].setImage(secondImage[c/d2][c%d2]);
                 }
                 if(c>=5312 && c<=5711){
                     c-=5312;
                     world[layer][i][j].setImage(forthImage[c/d4][c%d4]);
                 }
                 world[layer][i][j].Resize(16);
                 Lume.Instanta.managerObiecte.AdaugaActor(world[layer][i][j]);
             }
         }
     }
   }
}

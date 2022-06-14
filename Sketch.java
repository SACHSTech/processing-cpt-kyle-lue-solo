import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.xml.datatype.DatatypeConstants.Field;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Sketch extends PApplet {
	int intPlayerX = 200;
  int intPlayerY = 350;
  boolean blnLeft = false;
  boolean blnRight = false;
  boolean playerShoot =false;
  ArrayList <PVector> Bullets = new ArrayList<PVector>();
  ArrayList <PVector> AlienField = new ArrayList<PVector>();
  int[][] Field = new int[10][3];
  int intRowCount = 10;
  int intColumnCount = 4;
  int intX = 0;
  int intY = 0;
  int intScore = 0;
  int start = 0;
  PImage playerShip;
  PImage Background;
  PImage SpaceAlien;

	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(12, 12, 12);
    playerShip = loadImage("SpaceShip.png");
    playerShip.resize(40,40);
    Background = loadImage("Background.jpg");
    Background.resize(400,400);
    SpaceAlien = loadImage("Alien.png");
    SpaceAlien.resize(20,20);
    Bullets.add(new PVector(10, 10));
    AlienField.add(new PVector(20,20));
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if(start == 0){
      title();
    }
    if(start == 1){
      game();
    }
    if(start == 2){
      pause();
    }
   }
  // define other methods down here.

  public void title(){
    background(Background);
    textSize(40);
    textAlign(CENTER);
    fill(255);
    text("Space Invaders",100, 110, 190, 150);
    text("Click to start", 80, 230,240,230);
  }  

  public void mouseClicked(){
    start = 1;
  }
  
  public void game(){
  background(Background);
	   rect(intPlayerX, intPlayerY,40,40);
     image(playerShip,intPlayerX,intPlayerY);
    if (blnLeft) {
      intPlayerX-=2;
    }
    if (blnRight) {
      intPlayerX+=2;
    }
    if(intPlayerX < 0){
      intPlayerX = 0;
    }
    if(intPlayerX + 40 > width){
      intPlayerX = width - 40;
    }
    for(int intRow = 0; intRow < intRowCount; intRow++){
      for(int intColumn = 0; intColumn < intColumnCount; intColumnCount++){
        int intX = 25 * intRow;
        int intY = 25 * intColumn;
        rect(intX,intY,20,20);
        image(SpaceAlien,20,20);
        for(int i = 0; i < Bullets.size(); i++) {
          PVector Bullet = Bullets.get(i);
          Bullet.y -=2;
          ellipse(Bullet.x, Bullet.y, 5, 10);
          if(Bullet.y < 0){
            Bullets.remove(i);
          }
          if(Bullet.x > intX && Bullet.x + 20 < intX + 20 && Bullet.y < intY){
            Field[intRow][intColumn] = 0;
            intScore = intScore + 200;
          }
    }
    }
  }
  }
  //}


  public void pause(){
    background(Background);
    textAlign(CENTER);
    textSize(40);
    fill(255);
    text("PAUSED", 140,150,170,180);
    text("Press q to unpause",150,190,180,230);
  }
  public void keyPressed() {
    if(keyCode == LEFT){
      blnLeft = true;
    }else if(keyCode == RIGHT){
      blnRight = true;
    }else if(key == 'c'){
      playerShoot = true;
      Bullets.add(new PVector(intPlayerX + 20, intPlayerY));
    }else if(key == 'p'){
      start = 2;
    }else if(key == 's'){
      start = 1;
    }else if(key == 'q'){
      start = 1;
    }
  }
  public void keyReleased() {
    if (keyCode == LEFT) {
      blnLeft = false;
    }else if (keyCode == RIGHT) {
      blnRight = false;
    }else if(key == 'c'){
      playerShoot = false;
  }
}

}
//for(int c = 0; c < AlienField.size(); c++){
      //PVector Alien = AlienField.get(c);
      //Alien.y += 0.01;
      //for(int intRow = 0; intRow < intRowCount; intRow++){
        //for(int intColumn = 0; intColumn < intColumnCount; intColumn++){
          //float x = Alien.x +5;
          //float y = Alien.y +5;
          //rect(x, y,20,20);
         // image(SpaceAlien,x,y);
       // }
      //}
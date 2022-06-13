import java.util.ArrayList;

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
  
  int start = 0;
  PImage playerShip;
  PImage Background;

	
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
    playerShip = loadImage("SpaceShip.jpg");
    playerShip.resize(40,40);
    Background = loadImage("Background.jpg");
    Background.resize(400,400);
    Bullets.add(new PVector(10, 10));
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if(start == 0){
      title();
    }else if(start == 1){
      game();
    }
   }
  // define other methods down here.
  public void game()
{
  background(Background);
	   rect(intPlayerX, intPlayerY,40,40);
     image(playerShip,intPlayerX,intPlayerY);
    if (blnLeft) {
      intPlayerX-=2;
    }
    if (blnRight) {
      intPlayerX+=2;
    }
    for(int i = 0; i < Bullets.size(); i++) {
      PVector Bullet = Bullets.get(i);
      Bullet.y -=2;
      ellipse(Bullet.x, Bullet.y, 5, 10);
      if(Bullet.y < 0){
        Bullets.remove(i);
      }
    }
  }
  public void title(){
    background(Background);
    textSize(40);
    textAlign(CENTER);
    fill(255);
    text("Space Invaders",100, 110, 190, 150);
    text("Press s to start", 80, 210,240,210);
  }  
  public void keyPressed() {
    if(keyCode == LEFT){
      blnLeft = true;
    }else if(keyCode == RIGHT){
      blnRight = true;
    }else if(key == 'c'){
      playerShoot = true;
      Bullets.add(new PVector(intPlayerX + 20, intPlayerY));
    }else if(key == 's'){
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
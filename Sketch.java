import java.util.ArrayList;
import java.util.Random;
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
  ArrayList <Boolean> BulletsHidden = new ArrayList<Boolean>();
  float[] MeteorY = new float[15];
  float[] MeteorX = new float[15];
  int MeteorRadius = 25;
  int intX = 200;
  int intY = 20;
  int intBossMovement = 0;
  int intLives = 3;
  int intBossHealth = 200;
  boolean blnBossHide = false;
  int intScore = 0;
  int start = 0;
  PImage playerShip;
  PImage Background;
  PImage SpaceAlien;
  PImage SpaceAlienWell;
  PImage LaserBullet;

	
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
    SpaceAlien = loadImage("SpaceAlien.png");
    SpaceAlien.resize(50,50);
    SpaceAlienWell = loadImage("SpaceAlienWell.png");
    SpaceAlienWell.resize(50,50);
    LaserBullet = loadImage("SpaceInvaderBullet.png");
    LaserBullet.resize(35,40);
    Bullets.add(new PVector(5, 10));
    BulletsHidden.add(false);

    for (int i = 0; i < MeteorY.length; i++) {
      MeteorY[i] = random(height);
    }
    for (int i = 0; i < MeteorX.length; i++) {
      MeteorX[i] = random(width);
    }
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
    if(start == 3){
      win();
    }
    if(start == 4){
      lose();
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
    intBossHealth = 200;
    intLives = 3;
  }
  
  public void game(){
  background(Background);
  Random random = new Random();
  int randomNumber = random.nextInt(400);
  if(intX < 0){
    intBossMovement = 0;
  }
  if(intX +50 > width){
    intBossMovement = 1;
  }
  if(intBossMovement == 0){
    intX += 2;
  }
  if(intBossMovement == 1){
    intX -= 2;
  }
  blnBossHide = false;
  if(blnBossHide == false){
    image(SpaceAlienWell, intX, intY);
  }
  image(playerShip,intPlayerX,intPlayerY);
    if (blnLeft) {
      intPlayerX-=3;
    }
    if (blnRight) {
      intPlayerX+=3;
    }
    if(intPlayerX < 0){
      intPlayerX = 0;
    }
    if(intPlayerX + 40 > width){
      intPlayerX = width - 40;
    }
    textSize(10);
    text("Boss Health: " + intBossHealth,35,20);
    if(intLives == 3){
      fill(255,0,0);
      rect(385,5,10 , 10);
      rect(370, 5, 10, 10);
      rect(355,5,10,10);
    }else if(intLives == 2){
      fill(255,0,0);
      rect(385,5,10 , 10);
      rect(370, 5, 10, 10);
    }else if(intLives ==  1){
      fill(255,0,0);
      rect(385,5,10 , 10);
    }else if(intLives <= 0){
      start = 4;
    }
    for (int i = 0; i < MeteorY.length; i++) {
      fill(160,160,160);
      ellipse(MeteorX[i], MeteorY[i], 20, 20);
    // Collision detection
    if(dist((float)intPlayerX,(float)intPlayerY,MeteorX[i],MeteorY[i]) < 20){
      intLives = intLives - 1;
      MeteorY[i] = 0;
    }
      // Controls the speed of the circles falling
      MeteorY[i]+=2;
      // Resets the position of the circles when they hit the bottom 
      if (MeteorY[i] > height) {
        MeteorY[i] = 0;
        MeteorX[i] = randomNumber;
      }
      if(MeteorX[i] > width){
        MeteorX[i] = 0;
      }
    }
      for(int i = 0; i < Bullets.size(); i++) {
        PVector Bullet = Bullets.get(i);
        BulletsHidden.get(i);
        Bullet.y -= 4 ;
        fill(255);
        if(BulletsHidden.get(i) == false){
          image(LaserBullet,Bullet.x,Bullet.y);
        }
        if(Bullet.y < 0){
          Bullets.remove(i);
        }
        if(Bullet.x > intX && Bullet.x < intX + 50 && Bullet.y < intY && Bullet.y > intY - 50){
          blnBossHide = true;
          image(SpaceAlien, intX, intY);
          intBossHealth -= 2;
          Bullets.remove(i);
          BulletsHidden.remove(i);
        }
    if(intBossHealth <= 0)
      start = 3;
  }
}

  public void pause(){
    background(Background);
    textAlign(CENTER);
    textSize(40);
    fill(255);
    text("PAUSED", 140,150,170,180);
    text("Press q to unpause",150,190,180,230);
  }
  public void win(){
    background(Background);
    textAlign(CENTER);
    textSize(40);
    fill(255);
    text("You Win", 140,150,170,180);
    text("Click to restart", 140,190,170,230);
  }
  public void lose(){
    background(Background);
    textAlign(CENTER);
    textSize(40);
    fill(255);
    text("Game Over", 100,150,170,180);
  }
  public void keyPressed() {
    if(keyCode == LEFT){
      blnLeft = true;
    }else if(keyCode == RIGHT){
      blnRight = true;
    }else if(keyCode == 32){
      playerShoot = true;
      Bullets.add(new PVector(intPlayerX + 3, intPlayerY));
      BulletsHidden.add(false);
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
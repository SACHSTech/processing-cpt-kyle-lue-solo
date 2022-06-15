import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Sketch extends PApplet {
  // Variables
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
  int intBossMaxHealth = 200;
  int intBossCurrentHealth = 200;
  float fltHealthPercent;
  boolean blnBossHide = false;
  int intScore = 0;
  int start = 0;
  PImage playerShip;
  PImage Background;
  PImage SpaceAlien;
  PImage SpaceAlienWell;
  PImage LaserBullet;
  PImage Lives; 
  PImage Meteors;
	
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
    // Background
    background(12, 12, 12);
    // Image resizing and loading
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
    Lives = loadImage("Lives.png");
    Lives.resize(10,10);
    Meteors = loadImage("Meteor.png");
    Meteors.resize(20,20);
    // Adding the Bullets
    Bullets.add(new PVector(5, 10));
    BulletsHidden.add(false);

    // Spawning the meteors at a random x and y value
    for (int i = 0; i < MeteorY.length; i++) {
      MeteorY[i] = random(height);
      MeteorY[i] = MeteorY[i] - 100;
    }
    for (int i = 0; i < MeteorX.length; i++) {
      MeteorX[i] = random(width);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // Title Screen
    if(start == 0){
      title();
    }
    // Game
    if(start == 1){
      game();
    }
    // Pause Menu
    if(start == 2){
      pause();
    }
    // Win Screen
    if(start == 3){
      win();
    }
    // Lose Screen
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
    text("Space Invaders",width/2,height/2);
    text("Click to start",width/2,height/2 + 40);
  }  

  public void mouseClicked(){
    start = 1;
    intBossCurrentHealth = intBossMaxHealth;
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
    fill(255);
    textSize(10);
    text("Boss Health: ",35,10);
    fill(255);
    rect(60,0,intBossMaxHealth,10);
    fill(0,255,0);
    if(intBossCurrentHealth <= intBossMaxHealth*0.75){
      fill(255,255,0);
    }
    if(intBossCurrentHealth <= intBossMaxHealth/2){
      fill(255,128,0);
    }
    if(intBossCurrentHealth <= intBossMaxHealth*0.25){
      fill(255,0,0);
    }
    rect(60,0,intBossCurrentHealth,10);

    if(intLives == 3){
      fill(255,0,0);
      image(Lives,385,5);
      image(Lives,370,5);
      image(Lives,355,5);
    }else if(intLives == 2){
      fill(255,0,0);
      image(Lives,385,5);
      image(Lives,370,5);
    }else if(intLives ==  1){
      fill(255,0,0);
      image(Lives,385,5);
    }else if(intLives <= 0){
      start = 4;
    }
    for (int i = 0; i < MeteorY.length; i++) {
      fill(160,160,160);
      ellipse(MeteorX[i], MeteorY[i], 20, 20);
      image(Meteors,MeteorX[i] - 10,MeteorY[i] - 10);
    // Collision detection
    if(dist((float)intPlayerX,(float)intPlayerY,MeteorX[i],MeteorY[i]) < 20){
      intLives = intLives - 1;
      MeteorY[i] = 0;
    }
      // Controls the speed of the circles falling
      MeteorY[i]+=2;
      if(intBossCurrentHealth <= 25){
        MeteorY[i] += 2.5;
      }
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
          intBossCurrentHealth -= 2;
          Bullets.remove(i);
          BulletsHidden.remove(i);
        }
    if(intBossCurrentHealth <= 0)
      start = 3;
  }
}

  public void pause(){
    background(Background);
    textAlign(CENTER);
    textSize(40);
    fill(255);
    text("PAUSED", width/2,height/2);
    text("Press q to unpause",width/2,height/2 + 40);
  }
  public void win(){
    background(Background);
    textAlign(CENTER);
    textSize(40);
    fill(255);
    text("You Win", width/2,height/2);
    text("Click to restart",width/2, height/2 + 40);
  }
  public void lose(){
    background(Background);
    textAlign(CENTER);
    textSize(40);
    fill(255);
    text("Game Over", width/2, height/2);
    text("Click to restart",width/2, height/2 + 40);
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
    }else if(keyCode == 32){
      playerShoot = false;
  }
}
}
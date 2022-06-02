import processing.core.PApplet;

public class Sketch extends PApplet {
	int intPlayerX = 200;
  int intPlayerY = 350;
  boolean blnLeft = false;
  boolean blnRight = false;
  

	
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
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(12);
	   rect(intPlayerX, intPlayerY,20,20);
    if (blnLeft) {
      intPlayerX--;
    }
    if (blnRight) {
      intPlayerX++;
    }
  }
  // define other methods down here.
  public void keyPressed() {
    if(keyCode == LEFT){
      blnLeft = true;
    }
    if(keyCode == RIGHT){
      blnRight = true;
    }
  }
}
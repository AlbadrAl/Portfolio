import bridges.base.NamedColor;
import bridges.games.NonBlockingGame;

public class Snake extends NonBlockingGame {
  // Game settings
  java.util.Random random = new java.util.Random();
  
  // Set dimensions of the game board
  static int gridColumns = 30;
  static int gridRows = 30;

  // Refresh rate
  final long FRAMERATE = 1000000000 / 15;
  long frameTime;
  long nextFrameTime;

  // Initial snake settings
  int startX = gridColumns / 3;
  int startY = gridRows / 2;
  int startLength = 3;
  
//Similar to a chessboard, the background will alternate in color  
  NamedColor bc1 = NamedColor.gray;        // Board background color
  NamedColor bc2 = NamedColor.gray;        // Secondary board color
  
//Setting up other game colors
  NamedColor sc = NamedColor.green;  // Snake body color
  NamedColor hc = NamedColor.green;  // Snake head color
  NamedColor ac = NamedColor.red;          // Apple color

//TODO: Create an element to represent the snake's head (call it head)
//and tail (call it tail) - is there a class that could help us where
//the location of the head and tail could be encapsulated?
  Block head;
  Block tail;
  
//TODO: Create an element to represent the apple
  Block apple;

//TODO: Keep track of the snake's current and last direction
  Direction dir;
  Direction lastDir;
  
  // Constructor - set bridges credentials, grid size
  public Snake(int assid, String login, String apiKey, int c, int r) {
    super(assid, login, apiKey, c, r);
  }

  // Handle user input
  public void handleInput() {
	// Based on which of the 4 arrow keys is pressed
	// move the snake in the requested particular direction
	// Note: if the snake is moving North, it cannot move South
	// meaning that it cannot turn on itself
	  
    // TODO: Construct an if statement that tests which key is pressed and
    // if the current and last directions do not equal the opposite direction
    // then set the current direction to the direction requested
    if(keyUp()&&dir!= Direction.SOUTH){
        dir= Direction.NORTH;
    } else if(keyDown()&&dir!= Direction.NORTH){
        dir=Direction.SOUTH;
    } else if(keyLeft()&& dir!=Direction.EAST){
        dir=Direction.WEST;
    } else if(keyRight()&& dir!= Direction.WEST){
        dir =Direction.EAST;
    }
  }
  //// ^if condition so the snake does not revert into itself
  
  // Update snake position
  public void updatePosition() {
    int nextX = head.x;
    int nextY = head.y;
    
 // Move the snake one position, based on its direction and update
 // the linked list
    switch (dir) {
      case NORTH: nextY = (head.y - 1 + gridRows) % gridRows; break;
      case SOUTH: nextY = (head.y + 1) % gridRows; break;
      case EAST:  nextX = (head.x + 1) % gridColumns; break;
      case WEST:  nextX = (head.x - 1 + gridColumns) % gridColumns; break;
    }
    
    //// switch statement determines the next position of the snake’s head
   //// While loop iterates through each block of the snake and moves all blocks one position forward in the direction of movement.
    
    Block current = head;
    int prevX, prevY;
    while (current != null) {
      prevX = current.x;
      prevY = current.y;
      current.x = nextX;
      current.y = nextY;
      nextX = prevX;
      nextY = prevY;
      current = current.next;
    }
  }

  ////The snake moves in the direction specified by dir 
  ////The new nextX and nextY values are calculated based on the current direction
  ///The switch statement determines the next position of the snake’s head
////The while loop iterates through each block of the snake and moves blocks one pos forwaard

  // Plant a new apple
  public void plantApple() {
    // TODO: Create an x and y (ints). You will want to get a random 
    // number between 0 and 29 for x and for y. You will want to keep
    // getting random numbers for x and y WHILE there is a collision
    // between the x or y and a block on the snake. You'll have to loop
    // through the snake much like in updatePosition() verifying whether
    // or not the x/y of a block is the same as the random x/y.
    int x, y;
    boolean collision;
    do{
      x= random.nextInt(gridColumns);
      y= random.nextInt(gridRows);
      collision=false;
      Block current=head;
      while(current!=null){
        if(current.x== x &&current.y== y){
          collision=true;
          break;
        }
        current=current.next;
      }
    } while(collision);
    apple.x=x;
    apple.y=y;
  }
  
  // Detect if snake eats an apple
  public void detectApple() {
    // TODO: If the head's x/y equals the apple's x/y, add a new tail
    // and plant an apple
    if(head.x==apple.x&&head.y==apple.y){
      tail.next=new Block(tail.x, tail.y);
      tail=tail.next;
      plantApple();
    }
  }

  // Detect if snake collides with itself
  public void detectDeath(){
    // TODO: Loop through the snake's body and determine if the head's x/y
    // equals any of the current's x/y throughout the loop. If it does, 
    // System.exit(0)
    Block current=head.next;
    while(current!=null){
      if(head.x==current.x&&head.y==current.y){
        System.exit(0);  
      }
      current= current.next;
    }
  }

  // Draw the game elements
  public void paint() {
    // TODO: Draw the board, the apple, and the snake
    // Draw the background
    for(int row = 0; row< gridRows;++row){
      for(int col= 0; col < gridColumns; ++col){
        NamedColor bgColor=(row+col)% 2== 0 ? bc1:bc2;
        setBGColor(row,col,bgColor);
      }
    }
    
    // Draw the snake
    Block current =head;
    while(current!= null){
      setBGColor(current.y, current.x,(current==head)? hc:sc);
      current=current.next;
    }
    
    // Draw the apple
    setBGColor(apple.y,apple.x,ac);
  }

  // Initialize the game
  public void initialize() {
    // TODO: Create the snake of some number of elements,
    // perform all initializations, place the apple
    head=new Block(startX,startY);
    Block current=head;
    for(int i=1; i<startLength;++i){
      current.next =new Block(startX-i,startY);
      current =current.next;
    }
    tail= current;
    
    dir= Direction.EAST;
    lastDir=dir;
    apple=new Block();
    plantApple();

    frameTime= System.nanoTime();
    nextFrameTime= frameTime+ FRAMERATE;
  }

  // Game loop
  public void gameLoop(){
    handleInput();
    
    if (System.nanoTime()>nextFrameTime){
      frameTime=System.nanoTime();
      nextFrameTime=frameTime+FRAMERATE;
   // TODO: set the last direction equal to direction
   // TODO: detect an apple
   // TODO: update the position
   // TODO: paint the screen
   // TODO: detect death
      lastDir=dir;  
      detectApple();  
      updatePosition(); 
      paint();  
      detectDeath();  
    }
  }

  public static void main(String args[]) {
    Snake game = new Snake(3, "Albadr", "917379618169", gridColumns, gridRows);
    game.setTitle("Snake");
    game.start();
  }
}

enum Direction {
  NORTH,
  SOUTH,
  EAST,
  WEST
}

// Block class represents a segment of the snake or apple
class Block {
  public Block next;
  public int x;
  public int y;

  public Block() {
    this(-1, -1, null);
  }

  public Block(int x, int y) {
    this(x, y, null);
  }

  public Block(int x, int y, Block next) {
    this.x = x;
    this.y = y;
    this.next = next;
  }
} 
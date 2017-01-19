package site.noys.scala

/**
  * Created by noy on 19/01/2017.
  */
class TGame {

  // Creating our 'board' which will just look like empty spaces
  val ticTacToeUI = Array(Array("_", "_", "_"),  Array("_", "_", "_"),  Array("_", "_", "_"))

  // Default player is the O
  var player = "O"

  // Start method, returns a boolean to see if game is active
  def start(players: String, position: List[Int]): Boolean = {
    // Checking if player is there and if the board is empty  to fill a space
    if (players == player && ticTacToeUI(position.head)(position(1)) == "_") {
      // Set the space as a player
      ticTacToeUI(position.head)(position(1)) = players
      // If the player is O, it's X's turn and vice versa.
      if (player == "O") {
        player = "X"
      } else { player = "O" }
      // Game is active
      true
    }
    // Game isn't active..
    else false
  }

  /*
    Here is where it was a bit strange, In java, I've always used enums to set states or 'constants' but here it was
    best to use strings so i wont have to call println all the time, probably better to use ints though
   */
  def gameStatus: String = {

    // The Column method which represents each column
    def column(index: Int) = { ticTacToeUI.map { row => row(index) } }

    // The Row, representing the row
    def row(index: Int) = { ticTacToeUI(index) }

    // Method to check if the game is finishing
    def gameIsFinishing(): Boolean = {
      // This loop pretty much checks if there are any empty spots left, if there is, it's not finished
      ticTacToeUI.foreach { row => row.foreach { element => if (element == "_") return false }
      }
      true
    }

    // But.. What if there's a winner before the empty spot?
    def winner(spot: Array[String]) = {
      // Here is the logic for that
      if (!spot(0).equals("_") && spot(0).equals(spot(1)) && spot(1).equals(spot(2))) true
      // No winner yet, carry on
      else false
    }

    // Getting the columns
    val columns = ticTacToeUI.indices.map { index => column(index) }
    // Getting the rows
    val rows = ticTacToeUI.indices.map { index => row(index) }

    // Getting the first section of the board
    val firstSection = Array(ticTacToeUI(0)(0), ticTacToeUI(1)(1), ticTacToeUI(2)(2))
    // Getting second section
    val secondSection = Array(ticTacToeUI(0)(2), ticTacToeUI(1)(1), ticTacToeUI(2)(0))

    // Setting the board area
    val boardArea = rows ++ columns ++ Array(firstSection, secondSection)

    // Iterate through the board
    boardArea.foreach { area =>
      // If there's a winner availible in an area section
      if (winner(area)) {
        // Display that there is, and stop
        return "The winner is " + area(0)
      }
    }

    // If the game is finished, but no winner
    if (gameIsFinishing()) {
      // Display a draw
      return "It's a draw!"
    }
    // 'return' GameState = IN_GAME
    "IN_GAME"
  }

  // This method makes the actual board
  def make(): Unit = { ticTacToeUI.foreach { row => println("" + row(0) + " | " + row(1) + " | " + row(2)) }
  }
}

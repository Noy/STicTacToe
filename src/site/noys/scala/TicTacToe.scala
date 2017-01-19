package site.noys.scala

import scala.util.Random

/**
  * Created by noy on 19/01/2017.
  */
object TicTacToe {

  // This is our random variable
  val random = new Random()
  // Creating an instance of our game
  val ticTacToe = new TGame()

  // play method, not actually needed, you could write all this outside of a method
  def play(): Unit = {
    // Checking if we're in game
    if (ticTacToe.gameStatus.equalsIgnoreCase("IN_GAME")) {
      do {
        // if we are, make the game
        ticTacToe.make()
        // Print who's turn it is
        println("It is " + ticTacToe.player + "'s turn.")
        // Set our session variable to check if it is an active session
        var activeSession = false
        do {
          // Set the row to a random place. We need 3 in order to fill the board (3*3 board)
          val row = random.nextInt(3)
          // Same for the column
          val column = random.nextInt(3)
          // Set the game to our start method which takes a player and a position
          activeSession = ticTacToe.start(ticTacToe.player, List(row, column))
          // Continue with this loop while we're out of our session
        } while (!activeSession)
        // Continue while we're In Game
      } while (ticTacToe.gameStatus.equalsIgnoreCase("IN_GAME"))
    }
  }

  // Main method
  def main(args: Array[String]): Unit = {
    // Call our play method
    this.play()
    // At the end of all our loops, we print out the winner, that's what this is.
    println(ticTacToe.gameStatus)
  }
}


package ScalaHackerEarth.org.example.ScalaPractice

import scala.io.StdIn.readLine

object ZigzagConversion {

    def convert(s: String, numRows: Int): String = {
      if (numRows == 1 || s.length <= numRows) return s

      val rows = Array.fill(math.min(numRows, s.length))("") // Array of strings for each row
      var currentRow = 0
      var goingDown = false

      for (c <- s) {
        rows(currentRow) += c
        // Change direction if we reach the top or bottom
        if (currentRow == 0 || currentRow == numRows - 1) goingDown = !goingDown
        currentRow += (if (goingDown) 1 else -1)
      }

      rows.mkString("") // Concatenate all rows
    }

    def main(args: Array[String]): Unit = {
      println("Enter a string:")
      val s = readLine()
      println("Enter number of rows:")
      val numRows = readLine().toInt

      val result = convert(s, numRows)
      println(s"Zigzag conversion: $result")
    }
  }




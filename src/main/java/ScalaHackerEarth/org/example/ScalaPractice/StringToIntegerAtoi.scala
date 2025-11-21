package ScalaHackerEarth.org.example.ScalaPractice
import scala.io.StdIn.readLine

object StringToIntegerAtoi {

  def myAtoi(s: String): Int = {
      if (s == null || s.isEmpty) return 0

      val str = s.trim
      if (str.isEmpty) return 0

      var i = 0
      var sign = 1
      var result = 0

      // Check for sign
      if (str(i) == '+') {
        sign = 1
        i += 1
      } else if (str(i) == '-') {
        sign = -1
        i += 1
      }

      while (i < str.length && str(i).isDigit) {
        val digit = str(i) - '0'

        // Check for overflow
        if (result > (Int.MaxValue - digit) / 10) {
          return if (sign == 1) Int.MaxValue else Int.MinValue
        }

        result = result * 10 + digit
        i += 1
      }

      sign * result
    }

    def main(args: Array[String]): Unit = {
      println("Enter a string to convert to integer:")
      val input = readLine()
      val result = myAtoi(input)
      println(s"Converted integer: $result")
    }
  }




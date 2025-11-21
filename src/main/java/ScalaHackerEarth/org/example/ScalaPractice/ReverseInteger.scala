package ScalaHackerEarth.org.example.ScalaPractice

import scala.io.StdIn.readLine

object ReverseInteger {

    def reverse(x: Int): Int = {
      var num = x
      var rev = 0

      while (num != 0) {
        val pop = num % 10
        num /= 10

        // Check for overflow before multiplying by 10
        if (rev > Int.MaxValue / 10 || (rev == Int.MaxValue / 10 && pop > 7)) return 0
        if (rev < Int.MinValue / 10 || (rev == Int.MinValue / 10 && pop < -8)) return 0

        rev = rev * 10 + pop
      }

      rev
    }

    def main(args: Array[String]): Unit = {
      println("Enter an integer:")
      val input = readLine().toInt
      val result = reverse(input)
      println(s"Reversed integer: $result")
    }
  }




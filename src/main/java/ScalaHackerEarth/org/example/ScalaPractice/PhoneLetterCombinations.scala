package ScalaHackerEarth.org.example.ScalaPractice
import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer

object PhoneLetterCombinations {

  val phoneMap: Map[Char, String] = Map(
      '2' -> "abc",
      '3' -> "def",
      '4' -> "ghi",
      '5' -> "jkl",
      '6' -> "mno",
      '7' -> "pqrs",
      '8' -> "tuv",
      '9' -> "wxyz"
    )

    def letterCombinations(digits: String): List[String] = {
      if (digits.isEmpty) return List()

      val result = ListBuffer[String]()

      def backtrack(index: Int, path: StringBuilder): Unit = {
        if (index == digits.length) {
          result += path.toString()
          return
        }

        val letters = phoneMap(digits(index))
        for (c <- letters) {
          path.append(c)
          backtrack(index + 1, path)
          path.deleteCharAt(path.length - 1) // backtrack
        }
      }

      backtrack(0, new StringBuilder)
      result.toList
    }

    def main(args: Array[String]): Unit = {
      println("Enter digits (2-9):")
      val digits = readLine()
      val combinations = letterCombinations(digits)
      println(s"Letter combinations: ${combinations.mkString("[", ", ", "]")}")
    }
  }




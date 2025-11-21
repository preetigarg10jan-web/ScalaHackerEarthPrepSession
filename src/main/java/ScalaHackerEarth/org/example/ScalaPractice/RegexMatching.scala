package ScalaHackerEarth.org.example.ScalaPractice

import scala.io.StdIn.readLine

object RegexMatching {

  def isMatch(s: String, p: String): Boolean = {
      val m = s.length
      val n = p.length
      val dp = Array.ofDim[Boolean](m + 1, n + 1)

      dp(0)(0) = true // empty string matches empty pattern

      // Deal with patterns like a*, a*b*, a*b*c* matching empty string
      for (j <- 2 to n by 2) {
        if (p(j - 1) == '*') dp(0)(j) = dp(0)(j - 2)
      }

      for (i <- 1 to m) {
        for (j <- 1 to n) {
          if (p(j - 1) == s(i - 1) || p(j - 1) == '.') {
            dp(i)(j) = dp(i - 1)(j - 1)
          } else if (p(j - 1) == '*') {
            dp(i)(j) = dp(i)(j - 2) ||
              ((p(j - 2) == s(i - 1) || p(j - 2) == '.') && dp(i - 1)(j))
          } else {
            dp(i)(j) = false
          }
        }
      }

      dp(m)(n)
    }

    def main(args: Array[String]): Unit = {
      println("Enter the string s:")
      val s = readLine()
      println("Enter the pattern p:")
      val p = readLine()

      val result = isMatch(s, p)
      println(s"Does the string match the pattern? $result")
    }
  }




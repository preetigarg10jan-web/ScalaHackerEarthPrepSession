package ScalaHackerEarth.org.example.ScalaPractice

import scala.io.StdIn.readLine

object LongestPalindromicSubstring {

  def longestPalindrome(s: String): String = {
      if (s == null || s.length < 1) return ""

      var start = 0
      var end = 0

      def expandAroundCenter(left: Int, right: Int): Int = {
        var l = left
        var r = right
        while (l >= 0 && r < s.length && s(l) == s(r)) {
          l -= 1
          r += 1
        }
        r - l - 1 // length of the palindrome
      }

      for (i <- 0 until s.length) {
        val len1 = expandAroundCenter(i, i)       // odd length palindrome
        val len2 = expandAroundCenter(i, i + 1)   // even length palindrome
        val len = math.max(len1, len2)
        if (len > end - start) {
          start = i - (len - 1) / 2
          end = i + len / 2
        }
      }

      s.substring(start, end + 1)
    }

    def main(args: Array[String]): Unit = {
      println("Enter a string:")
      val input = readLine()
      val result = longestPalindrome(input)
      println(s"Longest palindromic substring: $result")
    }
  }




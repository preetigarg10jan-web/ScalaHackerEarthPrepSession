package ScalaHackerEarth.org.example.ScalaPractice

import scala.io.StdIn._

object SubString {

    def main(args: Array[String]): Unit = {
      println("Enter a string:")
      val s = readLine()

      var charSet = scala.collection.mutable.Set[Char]()
      var start = 0
      var maxLen = 0

      for (end <- 0 until s.length) {
        while (charSet.contains(s(end))) {
          charSet.remove(s(start))
          start += 1
        }
        charSet.add(s(end))
        maxLen = math.max(maxLen, end - start + 1)
      }

      println("Length of longest substring without repeating characters: " + maxLen)
    }
  }




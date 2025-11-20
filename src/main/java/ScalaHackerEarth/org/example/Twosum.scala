package ScalaHackerEarth.org.example

import scala.io.StdIn.{readInt, readLine}

object Twosum {
  def main(args: Array[String]): Unit = {

    // Read number of elements
    println("Enter number of elements:")
    val n = readInt()

      // Read array elements
      println(s"Enter $n integers separated by spaces:")
      val nums = readLine().split(" ").map(_.toInt)

      // Read target
      println("Enter target:")
      val target = readInt()

      // Two Sum logic
      val map = scala.collection.mutable.HashMap[Int, Int]()
      var result = Array(-1, -1)

      for (i <- nums.indices) {
        val complement = target - nums(i)
        if (map.contains(complement)) {
          result = Array(map(complement), i)
          // Found the answer, can break
          println(result.mkString("[", ", ", "]"))
          return
        }
        map(nums(i)) = i
      }

      println("No valid pair found.")
    }


}

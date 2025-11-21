package ScalaHackerEarth.org.example.ScalaPractice

object ThreeSum {
  object ThreeSum {
    def threeSum(nums: Array[Int]): List[List[Int]] = {
      val sortedNums = nums.sorted
      var result = List[List[Int]]()

      for (i <- 0 until sortedNums.length - 2) {
        if (i == 0 || (i > 0 && sortedNums(i) != sortedNums(i - 1))) {
          var left = i + 1
          var right = sortedNums.length - 1

          while (left < right) {
            val sum = sortedNums(i) + sortedNums(left) + sortedNums(right)

            sum match {
              case 0 =>
                result ::= List(sortedNums(i), sortedNums(left), sortedNums(right))
                // Skip duplicates for left and right
                while (left < right && sortedNums(left) == sortedNums(left + 1)) left += 1
                while (left < right && sortedNums(right) == sortedNums(right - 1)) right -= 1
                left += 1
                right -= 1
              case x if x < 0 => left += 1
              case _ => right -= 1
            }
          }
        }
      }

      result
    }

    def main(args: Array[String]): Unit = {
      println("Enter integers separated by spaces:")
      val input = scala.io.StdIn.readLine()
      val nums = input.split(" ").map(_.toInt)

      val triplets = threeSum(nums)
      println(s"Triplets that sum to 0: ${triplets.map(_.mkString("[", ",", "]")).mkString("[", ",", "]")}")
    }
  }


}

package ScalaHackerEarth.org.example.ScalaPractice

object JumpGameII {

    def jump(nums: Array[Int]): Int = {
      var jumps = 0
      var currentEnd = 0
      var farthest = 0

      for (i <- 0 until nums.length - 1) {
        farthest = math.max(farthest, i + nums(i))

        // When we reach the end of the current jump, increase jump count
        if (i == currentEnd) {
          jumps += 1
          currentEnd = farthest
        }
      }

      jumps
    }

    def main(args: Array[String]): Unit = {
      println("Enter the array elements separated by spaces:")
      val input = scala.io.StdIn.readLine()
      val nums = input.split(" ").map(_.toInt)

      val result = jump(nums)
      println(s"Minimum number of jumps to reach the end: $result")
    }
  }




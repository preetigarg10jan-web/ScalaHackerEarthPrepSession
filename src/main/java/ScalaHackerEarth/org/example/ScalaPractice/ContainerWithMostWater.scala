package ScalaHackerEarth.org.example.ScalaPractice

object ContainerWithMostWater {

    def maxArea(height: Array[Int]): Int = {
      var left = 0
      var right = height.length - 1
      var maxArea = 0

      while (left < right) {
        val currentArea = math.min(height(left), height(right)) * (right - left)
        maxArea = math.max(maxArea, currentArea)

        // Move the pointer with the smaller height
        if (height(left) < height(right)) left += 1
        else right -= 1
      }

      maxArea
    }

    def main(args: Array[String]): Unit = {
      println("Enter the heights separated by spaces:")
      val input = scala.io.StdIn.readLine()
      val height = input.split(" ").map(_.toInt)

      val result = maxArea(height)
      println(s"The maximum area of water the container can hold is: $result")
    }
  }




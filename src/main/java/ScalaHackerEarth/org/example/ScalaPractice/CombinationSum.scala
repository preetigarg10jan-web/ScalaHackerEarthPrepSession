package ScalaHackerEarth.org.example.ScalaPractice

object CombinationSum {

    def combinationSum(candidates: Array[Int], target: Int): List[List[Int]] = {
      var result = List[List[Int]]()

      def backtrack(start: Int, current: List[Int], total: Int): Unit = {
        if (total == target) {
          result ::= current
        } else if (total < target) {
          for (i <- start until candidates.length) {
            backtrack(i, current :+ candidates(i), total + candidates(i))
          }
        }
      }

      backtrack(0, List(), 0)
      result
    }

    def main(args: Array[String]): Unit = {
      println("Enter candidates separated by spaces:")
      val input = scala.io.StdIn.readLine()
      val candidates = input.split(" ").map(_.toInt)

      println("Enter target value:")
      val target = scala.io.StdIn.readInt()

      val combinations = combinationSum(candidates, target)
      println("Combinations that sum to target:")
      println(combinations.map(_.mkString("[", ",", "]")).mkString("[", ",", "]"))
    }
  }




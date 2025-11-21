package ScalaHackerEarth.org.example.ScalaPractice

import scala.io.StdIn.readLine

object MedianOfTwoSortedArrays {
  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
      // Ensure nums1 is the smaller array
      val (a, b) = if (nums1.length > nums2.length) (nums2, nums1) else (nums1, nums2)
      val m = a.length
      val n = b.length
      var imin = 0
      var imax = m
      val halfLen = (m + n + 1) / 2

      while (imin <= imax) {
        val i = (imin + imax) / 2
        val j = halfLen - i

        if (i < m && b(j - 1) > a(i)) {
          imin = i + 1 // i too small
        } else if (i > 0 && a(i - 1) > b(j)) {
          imax = i - 1 // i too big
        } else {
          // i is perfect
          val maxOfLeft = if (i == 0) b(j - 1)
          else if (j == 0) a(i - 1)
          else math.max(a(i - 1), b(j - 1))

          if ((m + n) % 2 == 1) return maxOfLeft.toDouble

          val minOfRight = if (i == m) b(j)
          else if (j == n) a(i)
          else math.min(a(i), b(j))

          return (maxOfLeft + minOfRight) / 2.0
        }
      }

      throw new IllegalArgumentException("Input arrays are not valid")
    }

    def main(args: Array[String]): Unit = {
      println("Enter first sorted array (comma separated):")
      val nums1 = readLine().split(",").map(_.trim.toInt)

      println("Enter second sorted array (comma separated):")
      val nums2 = readLine().split(",").map(_.trim.toInt)

      val median = findMedianSortedArrays(nums1, nums2)
      println(f"The median is: $median%.5f")
    }
  }




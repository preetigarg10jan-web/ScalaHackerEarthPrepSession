package ScalaHackerEarth.org.example.ScalaPractice

object PalindromeNumber {
  def main(args: Array[String]): Unit = {

    //withuserinput//
    println("Enter an integer:")
    val x = scala.io.StdIn.readInt()

    // Using String Conversion
    val isPalindrome = if (x < 0) false else x.toString == x.toString.reverse
    println(s"Is $x a palindrome? $isPalindrome")

    //Without String Conversion (Mathematical Approach)
    var original = x
        var reversedHalf = 0
        var isPalindrome1= false

        if (x >= 0 && !(x % 10 == 0 && x != 0)) {
          while (original > reversedHalf) {
            reversedHalf = reversedHalf * 10 + original % 10
            original /= 10
          }
          isPalindrome1 = original == reversedHalf || original == reversedHalf / 10
        }

        println(s"Is $x a palindrome? $isPalindrome1")
      }
    }






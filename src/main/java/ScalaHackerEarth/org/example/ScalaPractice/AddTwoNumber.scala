package ScalaHackerEarth.org.example.ScalaPractice
import scala.io.StdIn._
object AddTwoNumber {
  // Definition for singly-linked list
    class ListNode(var value: Int = 0, var next: ListNode = null)

    def main(args: Array[String]): Unit = {
      // --- Read first linked list ---
      println("Enter first linked list (digits separated by space):")
      val l1Values = readLine().split(" ").map(_.toInt)
      val list1 = new ListNode()
      var current1 = list1
      for (num <- l1Values) {
        current1.next = new ListNode(num)
        current1 = current1.next
      }
      var l1 = list1.next

      // --- Read second linked list ---
      println("Enter second linked list (digits separated by space):")
      val l2Values = readLine().split(" ").map(_.toInt)
      val list2 = new ListNode()
      var current2 = list2
      for (num <- l2Values) {
        current2.next = new ListNode(num)
        current2 = current2.next
      }
      var l2 = list2.next

      // --- Add two numbers ---
      val dummy = new ListNode()
      var current = dummy
      var carry = 0

      while (l1 != null || l2 != null) {
        val x = if (l1 != null) l1.value else 0
        val y = if (l2 != null) l2.value else 0
        val sum = x + y + carry
        carry = sum / 10
        current.next = new ListNode(sum % 10)
        current = current.next
        if (l1 != null) l1 = l1.next
        if (l2 != null) l2 = l2.next
      }

      if (carry > 0) {
        current.next = new ListNode(carry)
      }

      // --- Print result linked list ---
      current = dummy.next
      val sb = new StringBuilder()
      while (current != null) {
        sb.append(current.value)
        if (current.next != null) sb.append(" -> ")
        current = current.next
      }
      println("Result linked list: " + sb.toString())
    }
  }




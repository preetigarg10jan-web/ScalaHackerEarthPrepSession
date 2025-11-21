package ScalaHackerEarth.org.example.ScalaPractice

object RemoveNthNode {
  // Definition for singly-linked list
  class ListNode(var value: Int) {
    var next: ListNode = null
  }

   def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
      val dummy = new ListNode(0)
      dummy.next = head
      var first: ListNode = dummy
      var second: ListNode = dummy

      // Move first pointer n+1 steps ahead
      for (_ <- 0 to n) first = first.next

      // Move both pointers until first reaches the end
      while (first != null) {
        first = first.next
        second = second.next
      }

      // Remove the nth node
      second.next = second.next.next
      dummy.next
    }

    // Helper function to print linked list
    def printList(head: ListNode): Unit = {
      var curr = head
      while (curr != null) {
        print(curr.value + " ")
        curr = curr.next
      }
      println()
    }

    // Helper function to build linked list from array
    def buildList(arr: Array[Int]): ListNode = {
      if (arr.isEmpty) return null
      val nodes = arr.map(new ListNode(_))
      for (i <- 0 until nodes.length - 1) nodes(i).next = nodes(i + 1)
      nodes(0)
    }

    def main(args: Array[String]): Unit = {
      println("Enter linked list elements separated by spaces:")
      val input = scala.io.StdIn.readLine()
      val arr = input.split(" ").map(_.toInt)
      val head = buildList(arr)

      println("Enter n (the position from the end to remove):")
      val n = scala.io.StdIn.readInt()

      println("Original list:")
      printList(head)

      val newHead = removeNthFromEnd(head, n)
      println(s"List after removing $n-th node from end:")
      printList(newHead)
    }
  }




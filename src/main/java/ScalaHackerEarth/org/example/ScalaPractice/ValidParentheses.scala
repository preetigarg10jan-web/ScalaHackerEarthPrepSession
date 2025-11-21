package ScalaHackerEarth.org.example.ScalaPractice
import scala.collection.mutable.Stack

  object ValidParentheses {
    def isValid(s: String): Boolean = {
      val stack = Stack[Char]()

      for (c <- s) {
        c match {
          case '(' | '{' | '[' => stack.push(c)
          case ')' =>
            if (stack.isEmpty || stack.pop() != '(') return false
          case '}' =>
            if (stack.isEmpty || stack.pop() != '{') return false
          case ']' =>
            if (stack.isEmpty || stack.pop() != '[') return false
          case _ => // ignore any other characters
        }
      }

      stack.isEmpty
    }

    def main(args: Array[String]): Unit = {
      println("Enter a string containing parentheses:")
      val s = scala.io.StdIn.readLine()

      val result = isValid(s)
      println(s"Is the string '$s' valid? $result")
    }
  }




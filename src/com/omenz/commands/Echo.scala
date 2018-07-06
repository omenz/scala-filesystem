package com.omenz.commands
import com.omenz.filesystem.State

import scala.annotation.tailrec

/**
  * Created by Alexander Krasovsky on 06.07.2018.
  */
class Echo(args: List[String]) extends Command {

  override def apply(state: State): State = {
    if (args.isEmpty) state
    else if (args.length == 1) state.setMessage(args.head)
    else {
      val operator = args(args.length - 2)
      val filename = args.last
      val contents = createContent(args, args.length - 2)

      if (">>".equals(operator))
      doEcho(state, contents, filename, append = true)
      else if (">".equals(operator))
      doEcho(state, contents, filename, append = false)
      else state.setMessage(createContent(args, args.length))
    }
  }

  def doEcho(state: State, content: String, filename: String, append: Boolean): State = {
    ???
  }

  def createContent(args: List[String], topIndex: Int): String = {
    @tailrec
    def createContentHelper(currentIndex: Int, accumulator: String): String = {
      if (currentIndex >= topIndex) accumulator
      else createContentHelper(currentIndex + 1, accumulator + " " + args(currentIndex))
    }

    createContentHelper(0, "")
  }
}

package com.omenz.filesystem

import java.util.Scanner

import com.omenz.commands.Command
import com.omenz.files.Directory

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
object FileSystem extends App {

  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (true) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }

}

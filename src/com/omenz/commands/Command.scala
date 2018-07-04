package com.omenz.commands

import com.omenz.filesystem.State

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
trait Command {

  def apply(state: State): State

}

object Command {

  val MKDIR = "mkdir"
  val LS = "ls"
  val PWD = "pwd"
  val TOUCH = "touch"

  def emptyCommand: Command = (state: State) => state
  def incompleteCommand(name: String): Command = new Command {
    override def apply(state: State): State =
      state.setMessage(s"$name is an incomplete command")
  }

  def from(input: String): Command = {
    val tokens: Array[String] = input.split(" ")
    if (input.isEmpty || tokens.isEmpty)
        emptyCommand
      else if (MKDIR.equals(tokens(0))) {
        if (tokens.length < 2) incompleteCommand(MKDIR)
        else new Mkdir(tokens(1))
      }
      else if (TOUCH.equals(tokens(0))) {
        if (tokens.length < 2) incompleteCommand(TOUCH)
        else new Touch(tokens(1))
      }
      else if (LS.equals(tokens(0))) {
        new Ls
      }
      else if (PWD.equals(tokens(0))) {
        new Pwd
      }
      else new UnknownCommand
  }

}
package com.omenz.commands

import com.omenz.filesystem.State

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
trait Command {

  def apply(state: State): State

}

object Command {

  def from(input: String): Command =
    new UnknownCommand

}
package com.omenz.commands

import com.omenz.filesystem.State

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
class UnknownCommand extends Command {

  override def apply(state: State): State =
    state.setMessage("Command not found!")

}

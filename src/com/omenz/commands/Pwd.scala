package com.omenz.commands

import com.omenz.filesystem.State

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
class Pwd extends Command {
  override def apply(state: State): State =
    state.setMessage(state.wd.path)
}

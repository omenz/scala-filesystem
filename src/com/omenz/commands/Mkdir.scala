package com.omenz.commands

import com.omenz.files.Directory
import com.omenz.filesystem.State

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
class Mkdir(name: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage(s"Entry $name already exists")
    } else if (name.contains(Directory.SEPARATOR)) {
      //mkdir -p something/somethingElse
      state.setMessage(s"$name must not contain separators!")
    } else if (checkIllegal(name)) {
      state.setMessage(s"$name is illegal name!")
    } else {
      doMkdir(state, name)
    }

    def checkIllegal(name: String): Boolean =
    name.contains(".")

    def doMkdir(state: State, name: String): State = {
      
    }
  }


}

package com.omenz.commands
import com.omenz.filesystem.State

/**
  * Created by Alexander Krasovsky on 07.07.2018.
  */
class Cat(filename: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.wd

    val dirEntry = wd.findEntry(filename)
    if (dirEntry == null || !dirEntry.isFile)
      state.setMessage(s"$filename: no such file")
    else
      state.setMessage(dirEntry.asFile.contents)
  }


}

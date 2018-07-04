package com.omenz.commands

import com.omenz.files.DirEntry
import com.omenz.filesystem.State

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
class Ls extends Command {

  def createNiceOutputFromContents(contents: List[DirEntry]): String = {
    if (contents.isEmpty) ""
    else {
      val entry = contents.head
      s"${entry.name} [${entry.getType}]\n${createNiceOutputFromContents(contents.tail)} "
    }
  }


  override def apply(state: State): State = {
    val contents = state.wd.contents
    val niceOutput = createNiceOutputFromContents(contents)
    state.setMessage(niceOutput)
  }
}

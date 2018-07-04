package com.omenz.commands

import com.omenz.files.DirEntry
import com.omenz.filesystem.State

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
class Mkdir(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State, entryName: String): DirEntry = ???

}

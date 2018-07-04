package com.omenz.commands

import com.omenz.files.{DirEntry, Directory}
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
  }

  def checkIllegal(name: String): Boolean =
    name.contains(".")

  def doMkdir(state: State, name: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    val wd = state.wd

    //1. all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    //2. create new directory entry in the working directory
    val newDirectory = Directory.empty(wd.path, name)

    //3. update the whole directory structure starting from the root
    //(the directory structure is IMMUTABLE)
    val newRoot = updateStructure(state.root, allDirsInPath, newDirectory)

    //4. find new wd INSTANCE given wd full path, in the NEW directory structure
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }

}

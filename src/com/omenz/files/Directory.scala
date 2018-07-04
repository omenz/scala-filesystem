package com.omenz.files

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
class Directory(override val parentPath: String,
                override val name: String,
                val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

  def addEntry(newEntry: DirEntry): Directory = ???

  def findEntry(entryName: String): DirEntry = ???

  def hasEntry(name: String): Boolean = ???

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory = ???

  override def asDirectory: Directory = this

  def getAllFoldersInPath: List[String] =
    path.substring(1)
      .split(Directory.SEPARATOR)
      .toList

  def findDescendant(path: List[String]): Directory = ???
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory =
    new Directory(parentPath, name, List())
}

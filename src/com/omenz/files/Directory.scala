package com.omenz.files

import com.omenz.filesystem.FileSystemException

import scala.annotation.tailrec

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
class Directory(override val parentPath: String,
                override val name: String,
                val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

  def addEntry(newEntry: DirEntry): Directory =
  new Directory(parentPath, name, contents :+ newEntry)

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory =
  new Directory(parentPath, name, contents.filter(e => !e.name.equals(newEntry.name)) :+ newEntry)

  def findEntry(name: String): DirEntry = {
    @tailrec
    def findEntryHelper(name: String, contentList: List[DirEntry]): DirEntry =
    if (contentList.isEmpty) null
    else if (contentList.head.name.equals(name)) contentList.head
    else findEntryHelper(name, contentList.tail)

    findEntryHelper(name, contents)
  }

  def hasEntry(name: String): Boolean =
  findEntry(name) != null

  override def getType: String = "Directory"

  override def asDirectory: Directory = this

  override def asFile: File = throw new FileSystemException("A directory cannot be converted to a file!")

  def getAllFoldersInPath: List[String] =
  path.substring(1)
    .split(Directory.SEPARATOR)
    .filter(s => !s.isEmpty)
    .toList

  def findDescendant(path: List[String]): Directory =
  if (path.isEmpty) this
  else findEntry(path.head)
    .asDirectory
    .findDescendant(path.tail)

  def findDescendant(relativePath: String): Directory =
    if (relativePath.isEmpty) this
    else findDescendant(relativePath.split(Directory.SEPARATOR).toList)

  def removeEntry(entryName: String): Directory =
    if (!hasEntry(entryName)) this
    else new Directory(parentPath, name, contents.filter(x => !x.name.equals(entryName)))

  def isRoot: Boolean = parentPath.isEmpty

  override def isDirectory: Boolean = true

  override def isFile: Boolean = false
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory =
  new Directory(parentPath, name, List())
}

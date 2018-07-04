package com.omenz.files

import com.omenz.filesystem.FileSystemException

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
class File(override val parentPath: String, override val name: String, contents: String)
  extends DirEntry(parentPath, name) {

  override def asDirectory: Directory =
    throw new FileSystemException("File cannot be converted to a directory!")

  override def asFile: File = this

  override def getType: String = "File"
}

object File {

  def empty(parentPath: String, name: String): File = new File(parentPath, name, "")

}
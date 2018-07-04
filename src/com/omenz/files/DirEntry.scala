package com.omenz.files

/**
  * Created by Alexander Krasovsky on 04.07.2018.
  */
abstract class DirEntry(val parentPath: String, val name: String) {

  def path: String = parentPath + Directory.SEPARATOR + name

  def asDirectory: Directory

  def asFile: File

  def getType: String
}

package com.github.MartaSwierczewska

import java.io.File
import java.util.regex.Pattern

import com.intellij.largeFilesEditor.editor.LargeFileEditorImpl
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.fileEditor.FileEditorPolicy.HIDE_DEFAULT_EDITOR
import com.intellij.openapi.fileEditor.{FileEditor, FileEditorPolicy, FileEditorProvider}
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile


class MyLogProvider extends FileEditorProvider{

  private val pathPattern = Pattern.compile("^.*[/\\\\]system[/\\\\]log[/\\\\]idea\\.log")

  override def accept(project: Project, file: VirtualFile): Boolean = pathPattern.matcher(file.getPath).matches()

  override def createEditor(project: Project, file: VirtualFile): FileEditor = new LargeFileEditorImpl(project, file)

  override def getEditorTypeId: String = "com.github.MartaSwierczewska.MyLogProvider"

  override def getPolicy: FileEditorPolicy = HIDE_DEFAULT_EDITOR
}

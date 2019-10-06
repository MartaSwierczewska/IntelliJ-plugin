package com.github.MartaSwierczewska

import java.io.File

import com.intellij.largeFilesEditor.editor.LargeFileEditor
import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.editor.{EditorFactory, LogicalPosition, ScrollType}
import com.intellij.openapi.fileEditor.{FileEditorManager, FileEditorProvider, TextEditor}
import com.intellij.openapi.vfs.VirtualFileManager

class OpenLogEditor extends AnAction{

  def showWarning = {print("File doesn't exist")}

  override def actionPerformed(anActionEvent: AnActionEvent): Unit = {
    val logFolder = new File(PathManager.getSystemPath,"log")
    val file = new File(logFolder,"idea.log")
//    FileEditorProvider
    if(!file.exists()){
      showWarning
    } else{
      Option(VirtualFileManager.getInstance().findFileByUrl(s"file://${file.getAbsolutePath}")).map { vf =>
        FileEditorManager.getInstance(anActionEvent.getProject).openFile(vf,true).map {
          case ftr: TextEditor =>
            val lineCount = ftr.getEditor.getDocument.getLineCount
            ftr.getEditor.getScrollingModel.scrollTo(new LogicalPosition(lineCount - 1, 0), ScrollType.MAKE_VISIBLE)
          case _ =>
        }
      }
    }
  }
}

package sk.wlio.sx2.iplugin.lang

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.{FileTypeConsumer, FileTypeFactory, LanguageFileType}
import javax.swing.Icon
import com.intellij.openapi.util.IconLoader
import org.jetbrains.annotations.{Nullable, NotNull}
import com.intellij.psi.FileViewProvider
import com.intellij.extapi.psi.PsiFileBase

/**
 * @autor: vilo
 */


object SxLanguage extends Language("sx")

object SxFileType extends LanguageFileType(SxLanguage) {

  val FILE = IconLoader.getIcon("/sk/wlio/sx2/iplugin/jar-gray.png")

  @NotNull override def getName = "Sx file"
  @NotNull override def getDescription = "Sx language file"
  @NotNull override def getDefaultExtension = "sx"
  @Nullable override def getIcon : Icon = FILE
}

class SxFileTypeFactory extends FileTypeFactory {

  override def createFileTypes(@NotNull fileTypeConsumer : FileTypeConsumer) {
    fileTypeConsumer.consume(SxFileType , "sx")
  }
}

 class SxFile(@NotNull viewProvider : FileViewProvider) extends PsiFileBase(viewProvider, SxLanguage)  {

  @NotNull override def getFileType() = SxFileType

  override def toString() = "Sx File"

  override def getIcon( flags : Int) = super.getIcon(flags)

}
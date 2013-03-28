package sk.wlio.sx2.iplugin.lang

import com.intellij.lang.{ASTNode, PsiBuilder, PsiParser}
import com.intellij.psi.tree.IElementType

/**
 * @autor: Vilo
 */
class SxParser extends PsiParser {

  def parse(root: IElementType, builder : PsiBuilder): ASTNode = {
    while (!builder.eof() )
      builder.advanceLexer()

    builder.getTreeBuilt()
  }
}

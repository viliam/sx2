package sk.wlio.sx2.iplugin.lang

import com.intellij.lang.{ASTNode, PsiBuilder, PsiParser}
import com.intellij.psi.tree.IElementType

/**
 * @autor: Vilo
 */
class SxParser extends PsiParser {

  def parse(root: IElementType, builder : PsiBuilder): ASTNode = {
    val m = builder.mark()
    while (!builder.eof() ) {
      builder.advanceLexer()
      println(" -> " + builder.getTokenType + "   text -> " + builder.getTokenText )
    }

    m.done( root)
    val a = builder.getTreeBuilt()
    a
  }
}

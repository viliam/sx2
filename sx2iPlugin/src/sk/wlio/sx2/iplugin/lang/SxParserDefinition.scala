package sk.wlio.sx2.iplugin.lang

import com.intellij.psi.{FileViewProvider, TokenType}
import com.intellij.lang.{ASTNode, Language, ParserDefinition}
import com.intellij.psi.tree.{IFileElementType, TokenSet}
import org.jetbrains.annotations.NotNull
import scala.NotNull
import com.intellij.openapi.project.Project
import com.intellij.lang.ParserDefinition.SpaceRequirements
import com.intellij.psi.impl.PsiElementBase
import com.intellij.extapi.psi.ASTWrapperPsiElement

/**
 * @autor: Vilo
 */
class SxParserDefinition extends ParserDefinition{
  val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
  val COMMENTS = TokenSet.create(SxTokenType.COMMENT)

  val FILE = new IFileElementType(SxLanguage)

  override def createLexer(project : Project ) = new SxLexer()

  override def getWhitespaceTokens() = WHITE_SPACES

  override def getCommentTokens() = COMMENTS

  override def getStringLiteralElements() = TokenSet.EMPTY

  override def createParser(project : Project) = new SxParser()

  override def getFileNodeType() = FILE

  override def createFile( viewProvider : FileViewProvider) = new SxFile(viewProvider)

  override def spaceExistanceTypeBetweenTokens(left : ASTNode, right:  ASTNode )=  SpaceRequirements.MAY

  override def createElement(node : ASTNode) = {
    //todo:
    //node.getElementType
    new ASTWrapperPsiElement(node)
  }

}
package sk.wlio.sx2.iplugin.lang

import com.intellij.psi.TokenType
import com.intellij.psi.tree.{TokenSet, IElementType}
import com.intellij.psi.tree.IElementType.Predicate

/**
 * @autor: Vilo
 */
object SxTokenType extends TokenType {

  def find( text : String) : Option[IElementType] = {
    val p = new Predicate {
      def matches(p1: IElementType): Boolean = p1.toString() == text
    }
    val elArray = IElementType.enumerate( p)
    if ( elArray.length > 1) throw new IllegalStateException("Duplicated definicition of element type: " + text)
    if ( elArray.length ==0)
      None
    else
      Some( elArray(1) )
  }

  val WHITE_SPACE = new IElementType("WHITE_SPACE", SxLanguage)
  val UNKNOWN = new IElementType("UNKNOWN", SxLanguage)

  val COMMENT = new IElementType("COMMENT", SxLanguage)
  val DIGIT = new IElementType("DIGIT", SxLanguage)
  val IDENTIFIER = new IElementType("IDENTIFIER", SxLanguage)
  val SYMBOL = new IElementType("SYMBOL", SxLanguage)

  /* ........................... SYMBOLs ................................... */
  //ARITM
  val PLUS = new IElementType("+", SxLanguage)
  val MINUS = new IElementType("-", SxLanguage)
  val TIMES = new IElementType("*", SxLanguage)
  val MODULO = new IElementType("%", SxLanguage)
  val REST = new IElementType("/", SxLanguage)
  //BOOL
  val AND = new IElementType("&", SxLanguage)
  val OR = new IElementType("|", SxLanguage)
  val AND_STRONG = new IElementType("&&", SxLanguage)
  val OR_STRONG = new IElementType("||", SxLanguage)
  //COMPARISON
  val SMALLER = new IElementType("<", SxLanguage)
  val GREATER = new IElementType(">", SxLanguage)
  val SMALLER_EQUAL = new IElementType("<=", SxLanguage)
  val GRATER_EQUAL = new IElementType(">=", SxLanguage)
  val EQUAL = new IElementType("==", SxLanguage)
  val UNEQUAL = new IElementType("!=", SxLanguage)
  val ASSIGN = new IElementType("=", SxLanguage)

  val BRACKET_NORM_OPEN = new IElementType("(", SxLanguage)
  val BRACKET_NORM_CLOSE = new IElementType(")", SxLanguage)
  val PARENTHESIS_BLOCK_OPEN = new IElementType("{", SxLanguage)
  val PARENTHESIS_BLOCK_CLOSE = new IElementType("}", SxLanguage)

  val COMMA = new IElementType(",", SxLanguage)
  val SEMICOLON = new IElementType(";", SxLanguage)
  val DOT = new IElementType(",", SxLanguage)

  /*................................SYMBOL groups......................*/
  val OP_ARITM = TokenSet.create( PLUS, MINUS, TIMES, MODULO, REST)
  val OP_BOOL = TokenSet.create( AND, AND_STRONG, OR, OR_STRONG)
  val OP_COMPARISON = TokenSet.create(SMALLER, SMALLER_EQUAL, GREATER, GRATER_EQUAL, EQUAL, UNEQUAL)

  val OP_EXP = TokenSet.create( PLUS, MINUS, TIMES, MODULO, REST, AND, AND_STRONG, OR, OR_STRONG,
    SMALLER, SMALLER_EQUAL, GREATER, GRATER_EQUAL, EQUAL, UNEQUAL)

  val OP_ASSIGNMENT = TokenSet.create(ASSIGN)
  val COMMAS = TokenSet.create(COMMA, SEMICOLON, DOT)
  val BRACKETS = TokenSet.create(BRACKET_NORM_OPEN, BRACKET_NORM_CLOSE, PARENTHESIS_BLOCK_OPEN, PARENTHESIS_BLOCK_CLOSE)

  val SYMBOLS = TokenSet.orSet( OP_ARITM, OP_BOOL, OP_COMPARISON, OP_EXP, OP_ASSIGNMENT, COMMAS, BRACKETS)
  /*................................RESERVED WORDs.....................*/
  val INT = new IElementType("int" , SxLanguage)
  val BOOL = new IElementType("bool" , SxLanguage)
  val RETURN = new IElementType("return" , SxLanguage)
  val IF = new IElementType("if" , SxLanguage)
  val VOID = new IElementType("void" , SxLanguage)
  val TRUE = new IElementType("true" , SxLanguage)
  val FALSE = new IElementType("false" , SxLanguage)

  /*................................RESERVED WORD groups................*/
  val DATA_TYPE = TokenSet.create(INT, BOOL)
  val INSTRUCTION_WORD = TokenSet.create(RETURN, IF)
  val DATA_VALUE = TokenSet.create( VOID, TRUE, FALSE)

  val RESERVED_WORDS = TokenSet.orSet( DATA_TYPE, INSTRUCTION_WORD, DATA_VALUE)
}

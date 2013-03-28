package sk.wlio.sx2.iplugin.lang

import com.intellij.lexer.{LexerPosition, Lexer}
import com.intellij.psi.tree.{TokenSet, IElementType}

/**
 * @autor: wlio
 */
class SxLexer extends Lexer {

  private var buffer : CharSequence = null
  private var startOffset : Int = -1
  private var endOffset : Int = -1
  private var tokenStart : Int = -1
  private var tokenEnd : Int = -1
  private var tokenType: IElementType = null
  private var bufferPosition = -1

  val EMPTY_CHARACTERS = " \n\r\t"

  override def getBufferEnd() = endOffset
  override def getBufferSequence() = buffer
  override def getCurrentPosition() = new LexerPosition() {
    override def getOffset = bufferPosition
    override def getState = 0
  }
  override def restore( lexerPosition : LexerPosition) = {
      bufferPosition = lexerPosition.getOffset()
  }

  override def start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
    this.buffer = buffer
    this.startOffset = startOffset
    this.endOffset = endOffset
    this.tokenStart = -1
    this.tokenEnd = -1
    this.bufferPosition = startOffset
    this.tokenType = null
  }

  override def getState : Int = 0

  override def getTokenType: IElementType = tokenType

  override def getTokenStart : Int = tokenStart
  override def getTokenEnd : Int = tokenEnd

  override def advance {

    def readChar() = buffer.charAt(bufferPosition)
    def isLetter() = Character.isLetter( readChar() )
    def isDigit() = Character.isDigit( readChar() )
    def isWithSpace() = EMPTY_CHARACTERS.contains( readChar() )
    def readToken( condition :  => Boolean, tSet : Option[TokenSet] ) : Option[IElementType] = {
      tokenStart = bufferPosition
      while( condition  ) bufferPosition += 1
      tokenEnd = bufferPosition

      if (tSet.isDefined) {
        val text = buffer.subSequence( tokenStart, tokenEnd).toString()
        val el = SxTokenType.find( text)

        if ( el.isDefined && tSet.get.contains( el.get))
          return el
      }
      None
    }

    if ( endOffset < startOffset)
      throw new IllegalStateException("Incorrect state of offsets. StartOffset = " + startOffset +
                                      ", EndOffset = " + endOffset)

    if (endOffset == startOffset) return

    if (isWithSpace()) {
      readToken( isWithSpace , None)
      tokenType = SxTokenType.WHITE_SPACE
    }
    //start with number, then read number
    else if (isDigit()) {
      readToken( isDigit, None)
      tokenType = SxTokenType.DIGIT
    }
    //start with letter, then read word
    else if (isLetter()) {
      val tt = readToken( isLetter || isDigit , Some(SxTokenType.RESERVED_WORDS))
      tokenType = tt.getOrElse( SxTokenType.IDENTIFIER )
    }
    //else it is symbol
    else {
      val tt = readToken( !isLetter && !isDigit && !isWithSpace, Some(SxTokenType.SYMBOLS ))
      tokenType = tt.getOrElse( SxTokenType.UNKNOWN)
    }
  }
}

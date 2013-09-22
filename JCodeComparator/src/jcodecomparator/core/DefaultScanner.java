package jcodecomparator.core;

import java.util.Hashtable;

/**
 *
 * Document scanner
 *
 * @author Nicola Pellicano'
 *
 */


public class DefaultScanner {

	    @SuppressWarnings("rawtypes")
		protected Hashtable fgKeys = null;

	    protected StringBuffer fBuffer = new StringBuffer();

	    protected String fDoc;

	    protected int fPos;

	    protected int fEnd;

	    protected int fStartToken;

	    protected boolean fEofSeen = false;

	    protected String[] fgKeywords={};




	    public DefaultScanner() {
	    	initialize();
	    }

		/**
		* Returns the ending location of the current token in the document.
		*/
		public final int getLength() {
			return fPos - fStartToken;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		protected void initialize() {
			fgKeys = new Hashtable();
			Integer k = new Integer(Keywords.KEY.id);
			for (int i = 0; i < fgKeywords.length; i++)
					fgKeys.put(fgKeywords[i], k);
		}


		public final int getStartOffset() {
		return fStartToken;
		}

		/**
		* Returns the next lexical token in the document.
		*/
		public int nextToken() {
		int c;
		fStartToken = fPos;

		while (true) {

			switch (c = read()) {

			case -1:
		    return Keywords.EOF.id;

		  case '*': // continuation of a special comment
			  c=read();
			  while (true) {
			        c = read();
			        if ((c == Keywords.EOF.id) || (c == Keywords.EOL.id)) {
			          unread(c);
			          return Keywords.COMMENT.id;
			        }
			        if(c=='*'){
			        	c=read();
			        	if(c=='/'){
			        		return Keywords.COMMENT.id;
			        	}
			        }
			  }

		  case '/': // comment
		    c = read();
		    if (c == '/' || c== '*') {
		      while (true) {
		        c = read();
		        if ((c == Keywords.EOF.id) || (c == Keywords.EOL.id)) {
		          unread(c);
		          return Keywords.COMMENT.id;
		        }
		        if(c=='*'){
		        	c=read();
		        	if(c=='/'){
		        		return Keywords.COMMENT.id;
		        	}
		        }
		      }
		    } else {
		    		unread(c);
		    	    return Keywords.OTHER.id;
		    	 }

		  case '\'': // char const
		    character: for (;;) {
		      c = read();
		      switch (c) {

		      case '\'':
		        return Keywords.STRING.id;

		      case -1:
		        unread(c);
		        return Keywords.STRING.id;

		      case '\\':
		        c = read();
		        break;
		      }
		    }

		  case '"': // string
		      string: for (;;) {
		        c = read();
		        switch (c) {
		        case '"':
		          return Keywords.STRING.id;
		        case -1:
		          unread(c);
		          return Keywords.STRING.id;
		        case '\\':
		          c = read();
		          break;
		        }
		      }

		    case '0':
		    case '1':
		    case '2':
		    case '3':
		    case '4':
		    case '5':
		    case '6':
		    case '7':
		    case '8':
		    case '9':
		      do {
		        c = read();
		      } while (Character.isDigit((char) c));
		      unread(c);
		      return Keywords.NUMBER.id;

		    default:
		      if (Character.isWhitespace((char) c)) {
		        do {
		          c = read();
		        } while (Character.isWhitespace((char) c));
		        unread(c);
		        return Keywords.WHITE.id;
		      }
		      if (Character.isJavaIdentifierStart((char) c)) {
		        fBuffer.setLength(0);
		        do {
		          fBuffer.append((char) c);
		          c = read();
		        } while (Character.isJavaIdentifierPart((char) c));
		        unread(c);
		        Integer i = (Integer) fgKeys.get(fBuffer.toString());
		        if (i != null)
		          return i.intValue();
		        return Keywords.WORD.id;
		      }
		      return Keywords.OTHER.id;
		    }
		  }
		}


		protected int read() {
		  if (fPos <= fEnd) {
		    return fDoc.charAt(fPos++);
		  }
		  return Keywords.EOF.id;
		}

		public void setRange(String text) {
		  fDoc = text;
		  fPos = 0;
		  fEnd = fDoc.length() - 1;
		}

		protected void unread(int c) {
		  if (c != Keywords.EOF.id)
		    fPos--;
		}
}






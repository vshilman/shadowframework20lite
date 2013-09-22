package jcodecomparator.core;

/**
 * Source code logical division for the line styler
 *
 * @author Nicola Pellicano'
 *
 */

public enum Keywords {

	  EOF(-1),
	  EOL(10),
	  WORD(0),
	  WHITE(1),
	  KEY(2),
	  COMMENT(3),
	  STRING(5),
	  OTHER(6),
	  NUMBER(7),
	  MAXIMUM_TOKEN(8);

	  public int id;


	  private Keywords(int id){
		  this.id=id;
	  }


}

package codeconverter.comparator.ignored;

/**
 *
 * Incapsulates the ignored patterns for each language for a specific type of comparison.
 *
 * @author Nicola Pellicano'
 *
 */


public class IgnoredHolder {

	 private String firstLang;
	 private String secondLang;


	 private IgnoredPatterns firstIgnored;
	 private IgnoredPatterns secondIgnored;


	 public IgnoredHolder(String firstLang, String secondLang,
			IgnoredPatterns firstIgnored, IgnoredPatterns secondIgnored) {
		super();
		this.firstLang = firstLang;
		this.secondLang = secondLang;
		this.firstIgnored = firstIgnored;
		this.secondIgnored = secondIgnored;
	}

	 /**
	  *
	  * @return ignored of first language
	  */


	public IgnoredPatterns getFirstIgnored() {
		return firstIgnored;
	}

	/**
	 *
	 * @return ignored of second language
	 */


	public IgnoredPatterns getSecondIgnored() {
		return secondIgnored;
	}


	/**
	 * Return true if the languages passed as parameter are equals to lang1 and lang2
	 *
	 * @param lang1
	 * @param lang2
	 * @return
	 */

	public boolean isOk(String lang1,String lang2){
	        if((lang1.equals(firstLang) && lang2.equals(secondLang)) ||(lang1.equals(secondLang) && lang2.equals(firstLang))){
	            return true;
	        }
	        return false;
	    }

	/**
	 * Used for ordering
	 *
	 * @param lang1
	 * @param lang2
	 * @return
	 */

		public boolean isInOrder(String lang1,String lang2){
			if(lang1.equals(firstLang) && lang2.equals(secondLang)){
				return true;
			}
			return false;
		}

}

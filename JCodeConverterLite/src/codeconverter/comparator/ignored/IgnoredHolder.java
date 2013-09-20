package codeconverter.comparator.ignored;


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



	public IgnoredPatterns getFirstIgnored() {
		return firstIgnored;
	}



	public IgnoredPatterns getSecondIgnored() {
		return secondIgnored;
	}



	public boolean isOk(String lang1,String lang2){
	        if((lang1.equals(firstLang) && lang2.equals(secondLang)) ||(lang1.equals(secondLang) && lang2.equals(firstLang))){
	            return true;
	        }
	        return false;
	    }

		public boolean isInOrder(String lang1,String lang2){
			if(lang1.equals(firstLang) && lang2.equals(secondLang)){
				return true;
			}
			return false;
		}

}

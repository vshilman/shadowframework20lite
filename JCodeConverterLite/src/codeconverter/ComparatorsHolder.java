package codeconverter;

import tests.tmp.IgnoredPatterns;
import codeconverter.comparator.PatternComparator;

public class ComparatorsHolder {

    private String firstLang;
    private String secondLang;

    private PatternComparator pc;



    public ComparatorsHolder(String firstLang, String secondLang,
			PatternComparator pc) {
		super();
		this.firstLang = firstLang;
		this.secondLang = secondLang;
		this.pc = pc;
	}

	public PatternComparator getPc() {
        return pc;
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

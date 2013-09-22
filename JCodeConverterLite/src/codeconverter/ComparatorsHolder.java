package codeconverter;

import codeconverter.comparator.PatternComparator;

/**
 * Contains the comparators for the specified compare languages
 *
 * @author nicolapelicano
 *
 */

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

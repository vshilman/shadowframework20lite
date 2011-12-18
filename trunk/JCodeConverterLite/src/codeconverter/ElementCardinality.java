package codeconverter;

public enum ElementCardinality {
	ONCE, // [1]
	MORE, // [*]
	AT_LEAST_ONCE, // [1,*]
	NO_MORE_THAN_ONCE
	// [0,1]
}

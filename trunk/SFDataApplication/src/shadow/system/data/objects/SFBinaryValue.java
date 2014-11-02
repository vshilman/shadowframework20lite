package shadow.system.data.objects;

import shadow.system.data.SFCharsetObject;

// Memorizza un numero intero 
// Nell'inputstream e output mi permette di salvare un intero con un numero di bit a piacere.
// Base di tutte le strutture dati che possono salvare con un numero di byte a piacere.
//E' l'elemento base, cioè l'astrazione sull'elemento binario che può essere interpeatato in qualche modo.

public abstract class SFBinaryValue implements SFCharsetObject{

	protected int value;

	public abstract SFBinaryValue copyDataObject();

	protected abstract int getBitSize();

	public SFBinaryValue() {
		super();
		this.value = getBitSize();
	}

	public int getValue() {
		return value;
	}

	protected void setValue(int value) {
		this.value = value;
	}
}

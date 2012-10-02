package shadow.math;

public class SFRandomizer {

	private static final int a = 40;
	private static final int b = 1000000;
	private static final int size = b+1;
	private static final int beginTimes = 4;
	private static final float step = 1.0f / b;

	private int seed, value;

	public SFRandomizer(int seed) {
		super();
		this.seed = seed % size;
		reset();
		for (int i = 0; i < beginTimes; i++) {
			randomInt();
		}
	}

	
	public int randomInt() {
		//Linear congruential generator
		value = (a * value + b) % size;
		return value;
	}

	public float randomFloat() {
		return randomInt() * step;
	}

	public void reset() {
		value = seed;
	}

	public static void main(String[] args) {

		SFRandomizer randomizer = new SFRandomizer(9000);

		for (int i = 0; i < 1000; i++) {
			System.out.println(randomizer.randomFloat());
		}
	}
}

package shadow.animation;

public enum SFStandardTweeners implements SFTweener{
	LINEAR {
		@Override
		public double tweenValue(double value) {
			return value;
		}
	},
	CUBIC{
		@Override
		public double tweenValue(double value) {
			return (-2*value+3)*value*value;
		}
	};
}

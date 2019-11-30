package ftc.electronvolts.util;

public interface AlivenessTester {
	boolean isAlive();

	public static final AlivenessTester ALWAYS = new AlivenessTester() {
		@Override
		public boolean isAlive() {
		return true;
		}
	};
}
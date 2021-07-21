package ftc.electronvolts.statemachine;

public class BehaviorFactory {
	public static Behavior drive() {
		return new Behavior() {

			@Override
			public void init() {
				System.out.println("Start driving");
			}

			@Override
			public void run() {
				System.out.println("... driving ...");
			}

			@Override
			public void dispose() {
				System.out.println("Stop driving");
			}
		};
	}
	

	public static Behavior print(String s) {
		return new Behavior() {

			@Override
			public void init() {
			}

			@Override
			public void run() {
				System.out.println(s);
			}

			@Override
			public void dispose() {
			}
		};
	}

}

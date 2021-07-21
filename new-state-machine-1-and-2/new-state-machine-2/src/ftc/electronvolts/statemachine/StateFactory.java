package ftc.electronvolts.statemachine;

import java.util.Scanner;

public class StateFactory {
	public static State stop() {
		return new State() {
			@Override
			public StateName act() {
				System.out.println("stop");
				return null;
			}
		};
	}
	
	public static State print(StateName n) {
		return new State() {
			@Override
			public StateName act() {
				System.out.println("blah blah i like pie");
				return n;
			}
		};
	}
	
//	public static State printTwice(StateName n) {
//		return new State() {
//			int i = 0;
//			@Override
//			public StateName act() {
//				i++;
//				System.out.println("blah blah i like pie");
//				if (i==1) {
//					return null;
//				} else {
//					i = 0;
//					return n;
//				}
//			}
//		};
//	}
	
	public static State printTwice(StateName next, String printWhat) {
		return new BasicAbstractState() {

			@Override
			public void init() {
			}

			@Override
			public boolean isDone() {
				System.out.println(printWhat);
				return cyclesSinceInit == 1;
			}

			@Override
			public StateName getNextStateName() {
				return next;
			}};
	}
	
	public static State branch(StateName yesState, StateName noState) {
		return new State() {
	        Scanner in = new Scanner(System.in);

			@Override
			public StateName act() {
				System.out.println("branch (yes/no)");
		        String s = in.nextLine();
		        System.out.println("You entered string "+s);
				if (s.equals("yes")) {
					return yesState;
				} else {
					return noState;
				}
			}
		};
	}
}

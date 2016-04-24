package norma;

import java.util.HashMap;
import java.util.Map;


public class NormaMachine {
	
	private static class Registers {
		private static Map<Register, Integer> registers = new HashMap<Register, Integer>();
		
		public static int getRegisterValue(String registerName) {
			if (registers.containsKey(new Register(registerName))) {
				return registers.get(new Register(registerName));
			} else {
				registers.put(new Register(registerName), 0);
				return 0;
			}
		}
		
		public static void incrementRegisterValue(String registerName) {
			if (registers.containsKey(new Register(registerName))) {
				registers.put(new Register(registerName), getRegisterValue(registerName) + 1);
			} else {
				registers.put(new Register(registerName), 1);
			}
		}
		
		public static void decrementRegisterValue(String registerName) {
			if (registers.containsKey(new Register(registerName))) {
				if (getRegisterValue(registerName) > 0) {
					registers.put(new Register(registerName), getRegisterValue(registerName) - 1);
				} else {
					registers.put(new Register(registerName), 0);
				}
			} else {
				registers.put(new Register(registerName), 0);
			}
		}
		
		public static void ifRegisterIsEqualToZero(String registerName, Runnable ifTrue, Runnable ifFalse) {
			if (getRegisterValue(registerName) == 0) {
				ifTrue.run();
			} else {
				ifFalse.run();
			}
		}
		
		private static class Register {
			
			private String name;
			
			public Register(String name) {
				this.name = name;
			}
			
			@Override
			public boolean equals(Object obj) {
				return name.equals(obj.toString());
			}
			
			@Override
			public int hashCode() {
				return name.hashCode();
			}
			
			@Override
			public String toString() {
				return this.name;
			}
		}
	}
	
	public static void main(String... args) {
		Registers.ifRegisterIsEqualToZero("A", () -> {
			System.out.println("É zero");
		}, () -> {
			System.out.println("Não é zero");
		});
	}
}

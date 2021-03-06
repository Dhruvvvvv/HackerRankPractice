package practice.java.advance;

import java.security.Permission;
import java.util.Scanner;

class Cake implements Food {

	@Override
	public String getType() {
		return "Someone ordered a Dessert!";
	}
}

class Do_Not_Terminate {

	public static class ExitTrappedException extends SecurityException {

		private static final long serialVersionUID = 1L;
	}

	public static void forbidExit() {
		final SecurityManager securityManager = new SecurityManager() {
			@Override
			public void checkPermission(Permission permission) {
				if (permission.getName().contains("exitVM")) {
					throw new ExitTrappedException();
				}
			}
		};
		System.setSecurityManager(securityManager);
	}
}

interface Food {
	public String getType();
}

class FoodFactory {
	public Food getFood(String order) {

		if ("pizza".equals(order)) {
			return new Pizza();
		} else if ("cake".equals(order)) {
			return new Cake();
		} else {
			return null;
		}
		//Write your code here

	}// End of getFood method

}// End of factory class

public class JavaFactoryPattern {

	public static void main(String args[]) {
		Do_Not_Terminate.forbidExit();

		try {

			Scanner sc = new Scanner(System.in);
			// creating the factory
			FoodFactory foodFactory = new FoodFactory();

			// factory instantiates an object
			Food food = foodFactory.getFood(sc.nextLine());

			System.out.println("The factory returned " + food.getClass());
			System.out.println(food.getType());
		} catch (Do_Not_Terminate.ExitTrappedException e) {
			System.out.println("Unsuccessful Termination!!");
		}
	}

}

class Pizza implements Food {
	@Override
	public String getType() {
		return "Someone ordered a Fast Food!";
	}
}

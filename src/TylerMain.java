import ObjectLabelling.ObjectLabelling;
import UserInterface.UserInterface;

public class TylerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][]testArray;     // 2nd Dilation test array
		testArray = new int [][] { 
			{1,1,1,0,1,1,1,1,1},
			{0,1,1,0,1,1,1,0,1},
			{0,0,0,1,1,1,1,0,1},
			{0,0,0,0,0,0,0,0,1},
			{1,0,1,1,1,1,1,0,1},
			{1,0,0,1,1,1,1,0,1},
			{1,1,1,1,0,1,0,0,1},
			{0,0,0,0,0,1,0,0,1},
		};
		
		ObjectLabelling tester = new ObjectLabelling();
		tester.countGroups(testArray);
		
	}

}

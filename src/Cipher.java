import java.util.Scanner;

public class Cipher {

	//Creates all needed global variables
	static Scanner in = new Scanner(System.in); 
	static Scanner i = new Scanner(System.in); 
	static String cipherText;
	static String alph = "abcdefghijklmnopqrstuvwxyz";
	static char[] alphabet;
	static char[] cipherTextArray;
	static int[] cipherTextNum;
	static int[] newNum;
	static boolean choicetype;
	
	public static void main(String[] args){
		
		//gets the input from user
		System.out.println("Ceasars Cipher");
		System.out.println("Enter cipherText:");
		cipherText = in.nextLine();
		System.out.println("Enter Frame Shift Number:");
		int frameShift = in.nextInt();
		System.out.println("Encrypt or Decrypt");
		String choice = i.nextLine();
		
		//true = encrypt, false = decrypt
		//checks if the user chose to decrypt or encrypt a message
		if(choice.equalsIgnoreCase("encrypt")){
			choicetype = true;
		}else if(choice.equalsIgnoreCase("decrypt")){
			choicetype = false;
		}
		
		//calls the setup method, with the paramaters of the text, the number by which the letters will shift and what option the user chose
		//frameShift is the number by which the letters will shift over right
		setup(cipherText, frameShift, choicetype);
		
	}
	

	
	public static void setup(String input, int frameShift, boolean type){
		
		//true = encrypt, false = decrypt
		
		//makes the alph string into a character array which also makes each letter have a number to identify them by
		alphabet = alph.toCharArray();
		
		//takes the input text and makes it all lowercase and then puts it into a character array so that you can operate on only one letter at a time
		cipherTextArray = input.toLowerCase().toCharArray();
		
		//makes a integer array the size of the input to store the numbers that go with the letters in the input
		cipherTextNum = new int[input.length()];
		
		//makes a integer array the size of the input to store the new numbers to call the new letters that go with that number
		newNum = new int[input.length()];
		
		//this for loop goes through each letter and then goes through each letter in the alphabet until it has found that letter, 
		//then it stores that letters number in the array cipherTextNum and breaks out of the second for loop and goes onto the next letter
		for(int i = 0; i < input.length(); i++){
			for(int x = 0; x < 26; x++){
				if(cipherTextArray[i] == alphabet[x]){
					cipherTextNum[i] = x;
					break;
				}
			}
		}

		//this takes the boolean type, which represents the choice of encrypt or decrypt
		//true = encrypt, false = decrypt
		//then based of the boolean, it calls whichever method is needed and passes along the frameShift number for it to use
		if(type == false){
			decrypt(frameShift);
		}else if(type == true){
			encrypt(frameShift);
		}
		
		
	}
	
	public static void encrypt(int frameShift){
		
		//this for loop adds the frameShift to the cipherTextNum which is the numbers for each letter in the input text
		//then is takes this new number and adds it to the array newNum so that the code can call the new numbers based of the new numbers
		for(int c = 0; c < cipherText.length(); c++){
			newNum[c] = cipherTextNum[c] + frameShift;
			//since the alphabet only has 26 letters, you can't call a letter that has a number greater than 26, therefore you subtract 26 from this 
			//number so that you end up with the letters in the beggining of the alpabet
			//example: c(3) + 40 = 43, there is no 43rd number, so 43 - 26 = 17, which is q, therefore c would be q in an encryption with a frameshift of 40
			if(newNum[c] > 25){
				newNum[c] = newNum[c] - 26;
			}
		}
		
		//this for loop prints out a letter based on the numbers you get after you add the frameShift
		for(int d = 0; d < cipherText.length(); d++){
			int s = newNum[d];
			System.out.print(alphabet[s]);
		}
		
	}
	
	public static void decrypt(int frameShift){
		
		//this for loop does the opposite of the encrypt method, instead of adding the frameshift from each letters number, it subtracts it so that you 
		//can go back from the encrypted message to the original message
		for(int i = 0; i < cipherText.length(); i++){
			newNum[i] = cipherTextNum[i] - frameShift;
			if(newNum[i] < 0){
				newNum[i] = newNum[i] + 26;
			}
		}

		//this for loop prints out a letter based on the numbers you get after you subtract the frameShift
		for(int d = 0; d < cipherText.length(); d++){
			int s = newNum[d];
			System.out.print(alphabet[s]);
		}
		
	}
}

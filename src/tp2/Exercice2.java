package tp2;

public class Exercice2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Début de la recherche de nombre mystérieux");
		for(int i=0; i<100 ; i++) {
			if(rechNbMyst(i)) {
				System.out.println(i);
			}
		}
		System.out.println("Fin");
	}
	
	public static boolean rechNbMyst(int n) {
		String nombre = String.valueOf(n*n) + String.valueOf(n*n*n);
		//on parse le carré et le cube en String pour les concaténer 
		boolean[] tabbool = new boolean[10];
		//on déclare un tableau de 10 indices qui représentent les nb de 0 à 9
		for(int i=0; i  < nombre.length(); ++i) {
			if(tabbool[nombre.charAt(i) - '0' ]) {
				return false;//on recherche en fonction du caractère ASCII
			}
			tabbool[nombre.charAt(i) - '0'] = true;
		}
		
		for(boolean b : tabbool) {
			if(!b) return false;
		}
		return true;
	}
	
}

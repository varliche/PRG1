package tp2;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tab[]= {1,2,3,4,5,6,7,8,9};
		int a=2;
		for ( int i=0; i< tab.length; i++) {
			System.out.print(tab[i]+",");
		}
		System.out.println("");
		rechDicho(tab,a);

	}
	
	//ALGORITHME DE TRI NAIF
	static void triNaif(int[]tab) {
		for(int i=0;i<tab.length-2;i++) {
			int rangmin=i;
			for(int j=i+1;j<tab.length-1;j++) {
				if(tab[j]<tab[rangmin]) {
					rangmin=j;
				}
			}
			int aux = tab[i];//valeur "tampon" pour la permutation
			tab[i]=tab[rangmin];
			tab[rangmin] = aux;
		}
	}
	
	//ALGORITHME DE RECHERCHE DICHOTOMIQUE
	static boolean rechDicho(int tab[], int a) {
		int deb=0;
		int fin=tab.length-1;
		int millieu=(deb+fin)/2;
		boolean tr = false;
		while(deb<=fin && tr == false) {
			if(a<tab[millieu]) {
				fin=millieu-1;
			}else if(a>tab[millieu]) {
				deb=millieu+1;
			}else if (a==tab[millieu]) {
				tr = true;
			}
			millieu=(deb+fin/2);
		}
		System.out.println(tr);
		return deb<=fin;
	}
}

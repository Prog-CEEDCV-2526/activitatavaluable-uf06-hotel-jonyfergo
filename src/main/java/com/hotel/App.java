package com.hotel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Gestió de reserves d'un hotel.
 */
public class App {

    // --------- CONSTANTS I VARIABLES GLOBALS ---------

    // Tipus d'habitació
    public static final String TIPUS_ESTANDARD = "Estàndard";
    public static final String TIPUS_SUITE = "Suite";
    public static final String TIPUS_DELUXE = "Deluxe";

    // Serveis addicionals
    public static final String SERVEI_ESMORZAR = "Esmorzar";
    public static final String SERVEI_GIMNAS = "Gimnàs";
    public static final String SERVEI_SPA = "Spa";
    public static final String SERVEI_PISCINA = "Piscina";

    // Capacitat inicial
    public static final int CAPACITAT_ESTANDARD = 30;
    public static final int CAPACITAT_SUITE = 20;
    public static final int CAPACITAT_DELUXE = 10;

    // IVA
    public static final float IVA = 1.21f;

    // Scanner únic
    public static Scanner sc = new Scanner(System.in);

    // HashMaps de consulta
    public static HashMap<String, Float> preusHabitacions = new HashMap<String, Float>();
    public static HashMap<String, Integer> capacitatInicial = new HashMap<String, Integer>();
    public static HashMap<String, Float> preusServeis = new HashMap<String, Float>();

    // HashMaps dinàmics
    public static HashMap<String, Integer> disponibilitatHabitacions = new HashMap<String, Integer>();
    public static HashMap<Integer, ArrayList<String>> reserves = new HashMap<Integer, ArrayList<String>>();

    // Generador de nombres aleatoris per als codis de reserva
    public static Random random = new Random();




















    // --------- MÈTODE MAIN ---------

    /**
     * Mètode principal. Mostra el menú en un bucle i gestiona l'opció triada
     * fins que l'usuari decideix eixir.
     */
    public static void main(String[] args) {
        inicialitzarPreus();
        float totalReserva = 0;


        int opcio = 0;
        do {
            mostrarMenu();
            opcio = llegirEnter("Seleccione una opció: ");
            gestionarOpcio(opcio);
        } while (opcio != 6);

        System.out.println("Eixint del sistema... Gràcies per utilitzar el gestor de reserves!");
    }

















    // --------- MÈTODES DEMANATS ---------

    /**
     * Configura els preus de les habitacions, serveis addicionals i
     * les capacitats inicials en els HashMaps corresponents.
     */
    public static void inicialitzarPreus() {
        // Preus habitacions
        preusHabitacions.put(TIPUS_ESTANDARD, 50f);
        preusHabitacions.put(TIPUS_SUITE, 100f);
        preusHabitacions.put(TIPUS_DELUXE, 150f);

        // Capacitats inicials
        capacitatInicial.put(TIPUS_ESTANDARD, CAPACITAT_ESTANDARD);
        capacitatInicial.put(TIPUS_SUITE, CAPACITAT_SUITE);
        capacitatInicial.put(TIPUS_DELUXE, CAPACITAT_DELUXE);

        // Disponibilitat inicial (comença igual que la capacitat)
        disponibilitatHabitacions.put(TIPUS_ESTANDARD, CAPACITAT_ESTANDARD);
        disponibilitatHabitacions.put(TIPUS_SUITE, CAPACITAT_SUITE);
        disponibilitatHabitacions.put(TIPUS_DELUXE, CAPACITAT_DELUXE);

        // Preus serveis
        preusServeis.put(SERVEI_ESMORZAR, 10f);
        preusServeis.put(SERVEI_GIMNAS, 15f);
        preusServeis.put(SERVEI_SPA, 20f);
        preusServeis.put(SERVEI_PISCINA, 25f);
    }

    /**
     * Mostra el menú principal amb les opcions disponibles per a l'usuari.
     */
    public static void mostrarMenu() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Reservar una habitació");
        System.out.println("2. Alliberar una habitació");
        System.out.println("3. Consultar disponibilitat");
        System.out.println("4. Llistar reserves per tipus");
        System.out.println("5. Obtindre una reserva");
        System.out.println("6. Ixir");
    }










    /**
     * Processa l'opció seleccionada per l'usuari i crida el mètode corresponent.
     */
    public static void gestionarOpcio(int opcio) {
       //TODO:
        int opcion = opcio;
       switch (opcion) {
        case 1:  reservarHabitacio();          
            break;
        case 2: alliberarHabitacio();            
            break;
        case 3: consultarDisponibilitat();        
            break;
        case 4: obtindreReservaPerTipus();
            break;
        case 5: obtindreReserva();            
            break;
        case 6:            
            break;
        default:
            break;
       }
    }











    /**
     * Gestiona tot el procés de reserva: selecció del tipus d'habitació,
     * serveis addicionals, càlcul del preu total i generació del codi de reserva.
     */
    public static void reservarHabitacio() {
        System.out.println("\n===== RESERVAR HABITACIÓ =====");
        //TODO:

        
        seleccionarTipusHabitacioDisponible();

        String tipusHabitacio = seleccionarTipusHabitacio();
        ArrayList<String> serveis = seleccionarServeis();
        float total = calcularPreuTotal(tipusHabitacio, serveis);
        System.out.println("El preu total és: " + total);
        int codigo = generarCodiReserva();
        
        //ahora que tenemos el numero en disponible voy a restar en 
        ArrayList<String> datos = new ArrayList<>();
        datos.add(tipusHabitacio);
        for (String servi : serveis) {
            datos.add(String.valueOf("Servici: " + servi));}
        datos.add(String.valueOf("Precio total: " + total + " euros."));


     


        
        System.out.println();

        //sumar al hashmap de reserves el codigo primero y los datos despues
        reserves.put(codigo, datos);
        System.out.println("============TU RESERVA ES============");

        System.out.println(reserves);

        //restar disponibilitatHabitacion
        int disponibles = disponibilitatHabitacions.get(tipusHabitacio);
        disponibles = disponibles -1;
        disponibilitatHabitacions.put(tipusHabitacio, disponibles);
        System.out.println(disponibilitatHabitacions);

      //pedir reserva ( es una comprobacion a ver si se guarda)
      //  borrar cuando acabe de comprobar que funciona
       // System.out.println("Dame el codigo de la reserva: ");
     //   int pedir = sc.nextInt();

       // System.out.println();

       // System.out.println("Tu reserva es " + reserves.get(pedir));

        

       

       
        }
        
    










    /**
     * Pregunta a l'usuari un tipus d'habitació en format numèric i
     * retorna el nom del tipus.
     */
    public static String seleccionarTipusHabitacio() {
        //TODO:

         int tipo = 0;
         String precioFinalHabitacion = "";
         

                tipo = llegirEnter("Selecciona el tipo de habitacion que desee: ");

                switch (tipo) {
                    case 1:{
                        precioFinalHabitacion = TIPUS_SUITE;

                    }
                        break;

                    case 2:{
                        precioFinalHabitacion = TIPUS_ESTANDARD;
                    }
                        break;

                    case 3:{
                        precioFinalHabitacion = TIPUS_DELUXE;
                    }
                        break;
                
                    default:
                        break;
                }

    return precioFinalHabitacion;
    
}
        
     
    


















    /**
     * Mostra la disponibilitat i el preu de cada tipus d'habitació,
     * demana a l'usuari un tipus i només el retorna si encara hi ha
     * habitacions disponibles. En cas contrari, retorna null.
     */
    public static String seleccionarTipusHabitacioDisponible() {
        //TODO:

        int contador = 1;
            for (Map.Entry<String, Float> entrada : preusHabitacions.entrySet()){
            String habitacion = entrada.getKey();
            float precio = entrada.getValue();
            int disponible = disponibilitatHabitacions.get(habitacion);
            System.out.println(contador + ". " + habitacion + " - " + precio + " - disponibles: " + disponible );
            
            contador ++;
    }
        
        return null;
    }






















    /**
     * Permet triar serveis addicionals (entre 0 i 4, sense repetir) i
     * els retorna en un ArrayList de String.
     */
    public static ArrayList<String> seleccionarServeis() {

    sc.nextLine();
    String opcionServicio ="";
    int opcionSeleccion = 0;
    ArrayList<String> servicios = new ArrayList<>();
    int numero = 1;
    boolean salir=true;

    System.out.println("------------Servicios adicionales (0-4)------------- ");
    System.out.println("0. Finalitzar");
    System.out.println("1. Esmorzar (10€)");
    System.out.println("2. Gimnas (15€)");
    System.out.println("3. Spa (20€)");
    System.out.println("4. Piscina (25€)");
           

        do{
        System.out.print("Vol afegir un servei? (s/n): ");
        opcionServicio = sc.nextLine();
        System.out.print("");


            if (opcionServicio.equalsIgnoreCase("s")){

                if (numero > 5) {System.out.println("Ya has seleccionado 4");
                    salir = true;
                }

                if (numero < 4) {
                opcionSeleccion =  llegirEnter("Selecciona un servicio: ");
                sc.nextLine(); 

                switch (opcionSeleccion) {
                case 1: servicios.add("Esmorzar");
                    break;
                case 2: servicios.add("Gimnàs");
                    break;
                case 3: servicios.add("Spa");
                    break;
                case 4: servicios.add("Piscina");
                    break;
            
                default:
                    break;
            }       
                 numero ++;

        }
        }        else if (opcionServicio.equalsIgnoreCase("n")) salir = false ;

}

        while (salir);

        return servicios ;
    }






















    /**
     * Calcula i retorna el cost total de la reserva, incloent l'habitació,
     * els serveis seleccionats i l'IVA.
     */
    public static float calcularPreuTotal(String tipusHabitacio, ArrayList<String> serveisSeleccionats) {
        
    
    float precioServicios = 0;

    float precioHabitacion = preusHabitacions.get(tipusHabitacio);

    for (String servei : serveisSeleccionats) {
        precioServicios += preusServeis.get(servei);
    }

    float precioTotal = (precioHabitacion + precioServicios) * IVA;

    return precioTotal;
    }
















    /**
     * Genera i retorna un codi de reserva únic de tres xifres
     * (entre 100 i 999) que no estiga repetit.
     */
    public static int generarCodiReserva() {
        //TODO:
        int codigo = (int)(Math.random() * 900) + 100;

        return codigo;
    }






















    /**
     * Permet alliberar una habitació utilitzant el codi de reserva
     * i actualitza la disponibilitat.
     */
    public static void alliberarHabitacio() {
        System.out.println("\n===== ALLIBERAR HABITACIÓ =====");
         // TODO: Demanar codi, tornar habitació i eliminar reserva

         System.out.println("Introdueix el codi de reserva: ");
         int codi = sc.nextInt();
        
           //Liberar
        
         //Esto para actualizar la disponibilidad
        ArrayList <String> habitacionLiberar = new ArrayList<>();
        habitacionLiberar = reserves.get(codi);
        String libe = habitacionLiberar.get(0);
        System.out.println("======================================");

        int numero = disponibilitatHabitacions.get(libe);
        numero = numero +1;
        disponibilitatHabitacions.put(libe, numero);

        // Y esto para eliminar la reserva
        reserves.remove(codi);
        System.out.println("Reserrva alliverada correctamente");
    }

















    /**
     * Mostra la disponibilitat actual de les habitacions (lliures i ocupades).
     */
    public static void consultarDisponibilitat() {
        // TODO: Mostrar lliures i ocupades

        int libres;
        int ocupadas;
        System.out.println();

        for (Map.Entry<String, Integer> consulta: disponibilitatHabitacions.entrySet()){
            String tipo = consulta.getKey();
            int dispo = consulta.getValue();
            int ocu = capacitatInicial.get(tipo) - dispo;
            System.out.println(tipo + ": " + dispo + " disponibles. " + ocu + " ocupadas.");
        }
    }







































    /**
     * Funció recursiva. Mostra les dades de totes les reserves
     * associades a un tipus d'habitació.
     */
    public static void llistarReservesPerTipus(int[] codis, String tipus) {
         // TODO: Implementar recursivitat
         if (codis.length == 0) {
        System.out.println("(No hi ha més reserves d’aquest tipus.)");
        return;
        }

        int codi = codis[codis.length - 1];
        ArrayList<String> reserva = reserves.get(codi);

        if (reserva.get(0).equals(tipus)) {
        System.out.println("Codi: " + codi);
        for (String dada : reserva) {
            System.out.println(dada);
            }
        }

        int[] codis2 = new int[codis.length - 1];
        for (int i = 0; i < codis2.length; i++) {
            codis2[i] = codis[i];
        }

        llistarReservesPerTipus(codis2, tipus);


    }































    /**
     * Permet consultar els detalls d'una reserva introduint el codi.
     */
    public static void obtindreReserva() {
        System.out.println("\n===== CONSULTAR RESERVA =====");
        // TODO: Mostrar dades d'una reserva concreta

       int codis = 0;
       codis = llegirEnter("Introdueix el codi de reserva: ");
       mostrarDadesReserva(codis);

 
    }













    /**
     * Mostra totes les reserves existents per a un tipus d'habitació
     * específic.
     */
    public static void obtindreReservaPerTipus() {
        System.out.println("\n===== CONSULTAR RESERVES PER TIPUS =====");
        // TODO: Llistar reserves per tipus
        System.out.println("Seleccione tipus: ");
        System.out.println("1. Estandard");
        System.out.println("2. Suite");
        System.out.println("3. Deluxe");
        int opcio = llegirEnter("Opcio: ");
        String tipus = "";

        switch (opcio) {
            case 1:  tipus = "Estandard";
                break;

            case 2:  tipus = "Suite";
                break;

            case 3:  tipus = "Deluxe";
                break;
        
            default:
                break;
        }


        int[] codis = new int[reserves.size()];
        int i = 0;
        for (int codi : reserves.keySet()) {
        codis[i++] = codi;
        }

        llistarReservesPerTipus(codis, tipus);


    }

















    /**
     * Consulta i mostra en detall la informació d'una reserva.
     */
    public static void mostrarDadesReserva(int codi) {
       // TODO: Imprimir tota la informació d'una reserva

       ArrayList <String> reserva = new ArrayList<>();
       reserva = reserves.get(codi);

       for (String rese : reserva) {
        System.out.println(rese);
       }


    }



















    // --------- MÈTODES AUXILIARS (PER MILLORAR LEGIBILITAT) ---------

    /**
     * Llig un enter per teclat mostrant un missatge i gestiona possibles
     * errors d'entrada.
     */
    static int llegirEnter(String missatge) {
        int valor = 0;
        boolean correcte = false;
        while (!correcte) {
                System.out.print(missatge);
                valor = sc.nextInt();
                correcte = true;
        }
        return valor;
    }

    /**
     * Mostra per pantalla informació d'un tipus d'habitació: preu i
     * habitacions disponibles.
     */
    static void mostrarInfoTipus(String tipus) {
        int disponibles = disponibilitatHabitacions.get(tipus);
        int capacitat = capacitatInicial.get(tipus);
        float preu = preusHabitacions.get(tipus);
        System.out.println("- " + tipus + " (" + disponibles + " disponibles de " + capacitat + ") - " + preu + "€");
    }

    /**
     * Mostra la disponibilitat (lliures i ocupades) d'un tipus d'habitació.
     */
    static void mostrarDisponibilitatTipus(String tipus) {
        int lliures = disponibilitatHabitacions.get(tipus);
        int capacitat = capacitatInicial.get(tipus);
        int ocupades = capacitat - lliures;

        String etiqueta = tipus;
        if (etiqueta.length() < 8) {
            etiqueta = etiqueta + "\t"; // per a quadrar la taula
        }

        System.out.println(etiqueta + "\t" + lliures + "\t" + ocupades);
    }
}

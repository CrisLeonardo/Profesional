using System;
using System.Xml.Schema;
using System.Timers;

class Program
{
    static bool mostrarBarcos = true;
    static int[,] tablero;

    static int vidas = 3;
    static int cantidadbarcos = 0;
    static int punteo = 0;






    static void Paso1_CrearTablero(int tamaño)
    {

        tablero = new int[tamaño, tamaño];
        for (int f = 0; f < tablero.GetLength(0); f++)
        {
            for (int c = 0; c < tablero.GetLength(1); c++)
            {
                tablero[f, c] = 0;
            }
        }
    }

    static void Paso2_ColocarBarcos()
    {
          Random rnd = new Random();

            for (int i = 0; i < cantidadbarcos; i++)
            {
                int fila = rnd.Next(0, tablero.GetLength(0));
                int columna = rnd.Next(0, tablero.GetLength(1));
                tablero[fila, columna] = 1;
            }

        

    }


    static void Paso3_ImprimirTablero()
    {
        Console.Clear();
        Console.ForegroundColor = ConsoleColor.DarkRed;
        Console.WriteLine("Vidas: " + vidas);
        Console.WriteLine("Cantidad de Barcos: " + cantidadbarcos);
        Console.WriteLine("Puntiacion: " + punteo + "pts\n\n");

        Console.ForegroundColor = ConsoleColor.Black;

        // Imprime la primera fila con números de columna
        Console.Write("  ");
        for (int c = 0; c < tablero.GetLength(1); c++)
        {
            Console.Write("  " + (c) + " ");
        }
        Console.WriteLine("");

        // Imprime el resto del tablero
        for (int f = 0; f < tablero.GetLength(0); f++)
        {
            Console.Write((f) + " "); // Imprime el número de fila
            for (int c = 0; c < tablero.GetLength(1); c++)
            {
                Console.Write("|"); // Celda izquierda
                switch (tablero[f, c])
                {
                    case 0:
                        Console.Write(" ~ ");
                        break;
                    case 1:
                        if (mostrarBarcos)
                        {
                            Console.Write(" ~ ");
                        }
                        else
                        {
                            Console.Write(" 0 ");
                        }
                        
                        break;
                    case -1:
                        Console.Write(" * ");
                        break;
                    case -2:
                        Console.Write(" X ");
                        break;
                    default:
                        Console.Write(" | ");
                        break;
                }
            }
            Console.Write("|"); // Celda derecha
            Console.WriteLine();
        }


    }

    static void Paso5_SonidoDerrota()
    {
        punteo -= 25;
        Console.WriteLine("\nFallaste   -25pts");
        Console.WriteLine("¡Has perdido una vida!");
        for (int i = 0; i < 3; i++) // Reproduce el sonido de derrota 3 veces
        {
            Console.Beep(500, 200); // Frecuencia: 500 Hz, Duración: 200 ms
            System.Threading.Thread.Sleep(200); // Pausa de 100 ms entre cada sonido
        }
    }

    static void Paso4_IngresoCoordenadas()
    {
        int fila, columna = 0;
        Console.Write("Ingrese la Fila: ");
        bool correcto6;
        string filaF = (Console.ReadLine());

        correcto6 = int.TryParse(filaF, out fila);

        while (!correcto6 || fila < 0 || fila >= tablero.GetLength(1))
        {
            Console.WriteLine("Ingrese una coordenada valida en la fila la cual no supere el tamaño del tablero");
            filaF = Console.ReadLine();
            correcto6 = int.TryParse(filaF, out fila);
        }

        while (fila < 0 || fila >= tablero.GetLength(0))
        {
            Console.WriteLine("Ingrese una coordenada valida en la fila la cual no supere el tamaño del tablero");
            fila = Convert.ToInt32(Console.ReadLine());
        }
        Console.Write("Ingresa la Columna: ");
        
        bool correcto5;
        string columnaF = (Console.ReadLine());

        correcto5 = int.TryParse(columnaF, out columna);

        while (!correcto5 || columna < 0 || columna >= tablero.GetLength(1))
        {
            Console.WriteLine("Ingrese una coordenada valida en la columna la cual no supere el tamaño del tablero");
            columnaF = Console.ReadLine();
            correcto5 = int.TryParse(columnaF, out columna);
        }

        if (tablero[fila, columna] == 1)
        {
            punteo += 100;
            Console.Beep();
            tablero[fila, columna] = -1;
            cantidadbarcos--;
            Console.WriteLine("\nLe diste a un Barco  +100pts");
            System.Threading.Thread.Sleep(1000);
            if (cantidadbarcos == 0)
            {
                Console.WriteLine("\n\t\t\t\"Felcidades Ganaste el Juego\"");
                
                int[] notes = { 523, 659, 784 };

                // Definir la duración de cada nota en milisegundos
                int duration = 200;

                // Reproducir la secuencia de notas para el sonido de victoria clásico con duración más larga
                Console.WriteLine("\t\t\t\t¡Victoria!");
                Console.WriteLine("\t\t\tPuntuacion total: " + punteo+"pts");
                for (int i = 0; i < 4; i++) // Repetir la secuencia de notas dos veces
                {
                    foreach (int note in notes)
                    {
                        Console.Beep(note, duration);

                    }
                }
                Console.Beep(800, 1000);
                
                Environment.Exit(0);

            }


        }
        else
        {
            tablero[fila, columna] = -2;
            vidas--;
            
            
            
                if (vidas > 0)
            {
                Paso5_SonidoDerrota(); // Llama a la función para reproducir el sonido de derrota
            }
        }

        Paso3_ImprimirTablero();
    }



    static void Main(string[] args)
    {
        int N;
        bool correcto;
        
            Console.BackgroundColor = ConsoleColor.DarkCyan;
            Console.ForegroundColor = ConsoleColor.Black;
            Console.WriteLine("Seleccione el nivel de dificultad:");
            Console.WriteLine("1. Fácil (3x3)");
            Console.WriteLine("2. Normal (5x5)");
            Console.WriteLine("3. Difícil (8x8)");
            Console.WriteLine("4. Perzonalizado");

            string opcion = (Console.ReadLine());
            
            correcto = int.TryParse(opcion, out N);

        while (!correcto || N < 1 || N > 4)
        {
            Console.WriteLine("Ingrese un valor válido.");
            opcion = Console.ReadLine();
            correcto = int.TryParse(opcion, out N);
        }
        switch (N)
            {
                case 1:
                    Paso1_CrearTablero(3);
                    cantidadbarcos = 3;
                    break;
                case 2:
                    Paso1_CrearTablero(5);
                    cantidadbarcos = 4;
                    vidas = 4;
                    break;
                case 3:
                    Paso1_CrearTablero(8);
                    cantidadbarcos = 4;
                    vidas = 6;
                    break;
                case 4:
                    Console.WriteLine("Ingrese el tamaño de su tablero, ejemplo 2 será igual a uno de 2x2. pero que no se mayor que 9x9");

                int N2;
                bool correcto2;
                string per = (Console.ReadLine());

                correcto2 = int.TryParse(per, out N2);

                while (!correcto2 || N2 < 1 || N2 > 9)
                {
                    Console.WriteLine("Necio el tamaño máximo es de 9. Inténtalo de nuevo");
                    per = Console.ReadLine();
                    correcto2 = int.TryParse(per, out N2);
                }
                    Console.WriteLine("Ingrese la cantidad de barcos");
                    //cantidadbarcos = Convert.ToInt32(Console.ReadLine());
                
                bool correcto3;
                string cantidadbarcosF = (Console.ReadLine());

                correcto3 = int.TryParse(cantidadbarcosF, out cantidadbarcos);

                while (!correcto3 || cantidadbarcos < 1)
                {
                    Console.WriteLine("Ingresa una cantidad de Barcos valida. Inténtalo de nuevo");
                    cantidadbarcosF = Console.ReadLine();
                    correcto3 = int.TryParse(cantidadbarcosF, out cantidadbarcos);
                }

                    Paso1_CrearTablero(N2);
                    Console.WriteLine("Ingrese la cantidad de vidas");
                    //vidas = Convert.ToInt32(Console.ReadLine());
                bool correcto4;
                string vidasF = (Console.ReadLine());

                correcto4 = int.TryParse(vidasF, out vidas);

                while (!correcto4 || vidas < 1)
                {
                    Console.WriteLine("Ingresa una cantidad de vidas valida. Inténtalo de nuevo");
                    vidasF = Console.ReadLine();
                    correcto4 = int.TryParse(vidasF, out vidas);
                }
                Console.WriteLine("1. Ocultar barcos \n2. Mostrar barcos");
                    int mostrarOcultar = Convert.ToInt32(Console.ReadLine());
                    while (mostrarOcultar <= 0 || mostrarOcultar >= 3)
                    {
                        Console.WriteLine("Ingresa una opcion valida ya sea 1 o 2 ");
                        mostrarOcultar = Convert.ToInt32(Console.ReadLine());
                    }
                    if (mostrarOcultar == 2) // Si el usuario elige ocultar barcos
                    {
                        mostrarBarcos = false;
                    }
                    else
                    {
                        mostrarBarcos = true;
                    }
                    break;
               
            }
        
      
       

        

        Paso2_ColocarBarcos();
        Paso3_ImprimirTablero();

        while (vidas > 0)
        {
            Paso4_IngresoCoordenadas();
        }

        if (vidas == 0)
        {
            punteo -= 25;
            Console.WriteLine("\n\t\t\t\tGame Over");
            Console.WriteLine("\t\t\t\tPuntuacion total: "+punteo+"pts");
            Console.Beep(523, 500);
            Console.Beep(466, 500);
            Console.Beep(392, 500);
            Console.Beep(280, 1000);
            Environment.Exit(0);
        }






    }


}






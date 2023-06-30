
using System.Security.Cryptography;


static void contrasena()
{
    byte oportunidades, TienePermiso;
    string clave;
    TienePermiso = 0;
    oportunidades = 0;
    do
    {
        Console.WriteLine("Digite la Clave: ");

        //clave = Console.ReadLine(intercept: true);
        //clave = Console.ReadLine();
        clave = HideCharacter().Replace("\r","");
        Console.WriteLine();
        if (clave.ToUpper() == "444")
        {
            Console.WriteLine("Genial tiens permiso");
            TienePermiso = 1;
        }
        else
        {
            oportunidades++;
        }
    } while (((oportunidades < 3) & (TienePermiso == 0)));
    if (TienePermiso == 1)
    {
        Console.WriteLine("Bienvenido" + clave);
        desglose();
    }
    else
    {
        Console.WriteLine("Oportunidades Terminadas\nVuelva a ejecutar el programa");
        Console.Read();
        Environment.Exit(0);
    }

}
contrasena();
HideCharacter();

static void desglose()
{
   
    
   
    Console.Write("Ingrese la cantidad en Q: ");
    double Can = Convert.ToDouble(Console.ReadLine());
     

    //cantidades billetes:
    int C100 = (int)(Can / 100);
    Can -= C100 * 100;

    int C50 = (int)(Can / 50);
    Can -= C50 * 50;

    int C20 = (int)(Can / 20);
    Can -= C20 * 20;

    int C10 = (int)(Can / 10);
    Can -= C10 * 10;

    int C5 = (int)(Can / 5);
    Can -= C5 * 5;

    int C1 = (int)(Can / 1);
    Can -= C1 * 1;


   
    int cen50 = ((int)(Can / 0.5));
    Can -= cen50 * 0.5;

    int cen25 = ((int)(Can / 0.25));
    Can -= cen25 * 0.25;

    int cen10 = ((int)(Can / 0.1));
    Can -= cen10 * 0.10;

    int cen5 = ((int)(Can / 0.05));
    Can -= cen5 * 0.05;

    int cen1 = ((int)(Can / 0.01));
    Can -= cen1 * 0.01;


    Console.WriteLine($"\nTotal billetes de 100: {C100}");
    Console.WriteLine($"\nTotal billetes de 50: {C50}");
    Console.WriteLine($"\nTotal billetes de 20: {C20}");
    Console.WriteLine($"\nTotal billetes de 10: {C10}");
    Console.WriteLine($"\nTotal billetes de 5: {C5}");
    Console.WriteLine($"\nTotal billetes de 1: {C1}");
    //centavos
    Console.WriteLine($"\nTotal centavos de 50: {cen50}");
    Console.WriteLine($"\nTotal centavos de 25: {cen25}");
    Console.WriteLine($"\nTotal centavos de 10: {cen10}");
    Console.WriteLine($"\nTotal centavos de 5: {cen5}");
    Console.WriteLine($"\nTotal centavos de 1: {cen1}");

}




    desglose();

static string HideCharacter()
{
    ConsoleKeyInfo Key;
    string code = "";
    do
    {
        Key = Console.ReadKey(true);

        if (Char.IsNumber(Key.KeyChar))
        {
            Console.Write("*");
        }
        code += Key.KeyChar;
    } while (Key.Key != ConsoleKey.Enter);
    return code;
}
HideCharacter();
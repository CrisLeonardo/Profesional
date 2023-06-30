using ProyectoEnClase_27_05_2023.Clases;
using System;
using System.Data;
using System.Data.SQLite;
using static ProyectoEnClase_27_05_2023.Clases.GestorEmpleados;

namespace ProyectoEnClase_27_05_2023
{
    class Program
    {
        static void Main(string[] args)
        {
            string databasePath = @"C:\Users\minec\Desktop\Programacion1\ProyectoEnClase 27-05-2023\database.sqlite";
            GestorEmpleados gestorEmpleados = new GestorEmpleados(databasePath);
            gestorEmpleados.CrearTabla();
            int opcion = 0;
            DateTime fechaIngreso = DateTime.Now;
            while (true)
            {
                Console.Clear();
                Console.WriteLine("Ingrese la opción deseada");
                Console.WriteLine("1. Agregar Empleado");
                Console.WriteLine("2. Obtener Empleados");
                Console.WriteLine("3. Eliminar Empleado");
                Console.WriteLine("4. Actualizar Empleado");
                Console.WriteLine("5. Buscar Empleado por su ID");
                Console.WriteLine("6. Salir");

                opcion = int.Parse(Console.ReadLine());

                if (opcion < 1 || opcion > 6)
                {
                   
                    Console.WriteLine("Opción inválida\nPresione una tecla para continuar.....");
                    Console.ReadKey();
                    continue; // Vuelve al inicio del bucle
                }

                if (opcion == 6)
                {
                    Console.WriteLine("Saliendo del programa...");
                    break; // Sale del bucle while y finaliza el programa
                }

                switch (opcion)
                {
                    case 1:
                        Console.WriteLine("Ingrese el nombre del empleado");
                        string nombre = Console.ReadLine();
                        Console.WriteLine("Ingrese el apellido del empleado");
                        string apellido = Console.ReadLine();
                        Console.WriteLine("Ingrese la edad del empleado");
                        int edad = int.Parse(Console.ReadLine());
                        Console.WriteLine("Ingrese el cargo del empleado");
                        string cargo = Console.ReadLine();
                        Console.WriteLine("Ingrese el salario del empleado");
                        decimal salario = decimal.Parse(Console.ReadLine());
                        
                        Empleado nuevoEmpleado = new Empleado(0, nombre, apellido, edad, cargo, salario, fechaIngreso);
                        gestorEmpleados.AgregarEmpleado(nuevoEmpleado);
                        break;

                    case 2:
                        List<Empleado> empleados = gestorEmpleados.ObtenerEmpleados();
                        foreach (Empleado empleado in empleados)
                        {
                            Console.WriteLine(empleado);
                            
                        }
                        Console.ReadKey();
                        break;

                    case 3:
                        Console.WriteLine("Ingrese el id del empleado a eliminar");
                        int id = int.Parse(Console.ReadLine());
                        gestorEmpleados.EliminarEmpleado(id);
                        break;

                    case 4:
                        Console.WriteLine("Ingrese el id del empleado a actualizar");
                        int idActualizar = int.Parse(Console.ReadLine());
                        Console.WriteLine("Ingrese el nombre del empleado");
                        string nombreActualizar = Console.ReadLine();
                        Console.WriteLine("Ingrese el apellido del empleado");
                        string apellidoActualizar = Console.ReadLine();
                        Console.WriteLine("Ingrese la edad del empleado");
                        int edadActualizar = int.Parse(Console.ReadLine());
                        Console.WriteLine("Ingrese el cargo del empleado");
                        string cargoActualizar = Console.ReadLine();
                        Console.WriteLine("Ingrese el salario del empleado");
                        decimal salarioActualizar = decimal.Parse(Console.ReadLine());
                        Empleado empleadoActualizar = new Empleado(idActualizar, nombreActualizar, apellidoActualizar, edadActualizar, cargoActualizar, salarioActualizar, fechaIngreso);
                        gestorEmpleados.ActualizarEmpleado(empleadoActualizar);
                        break;

                    case 5:
                        Console.WriteLine("Ingrese el id del empleado a buscar");
                        int idBuscar = int.Parse(Console.ReadLine());

                        bool empleadoEncontrado = false;

                        foreach (Empleado empleado in gestorEmpleados.BuscarEmpleado(idBuscar))
                        {
                            Console.WriteLine(empleado);
                            Console.ReadKey();
                            empleadoEncontrado = true;
                        }

                        if (!empleadoEncontrado)
                        {
                            Console.WriteLine("No se encontró ningún empleado con el ID especificado.\nPresione una tecla para continuar.....");
                            Console.ReadKey();
                            
                        }
                       
                        break;
                    case 6:
                        Console.WriteLine("Saliendo del programa...");
                        break;
                }

            }

        }

    }
}

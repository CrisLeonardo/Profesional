using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoEnClase_27_05_2023.Clases
{
    internal class Empleado
    {
        
            public int Id { get; set; }
            public string Nombre { get; set; }
            public string Apellido { get; set; }

            public int Edad { get; set; }
            public string Cargo { get; set; }
            public decimal Salario { get; set; }
            public DateTime FeIngreso { get; set; }

            public Empleado(int id, string nombre, string apellido, int edad, string cargo, decimal salario, DateTime feIngreso)
            {
                Id = id;
                Nombre = nombre;
                this.Apellido = apellido;
                Edad = edad;
                Cargo = cargo;
                Salario = salario;
                FeIngreso = feIngreso;
            }
            public override string ToString()
            {
                return $"Id: {Id} Nombre: {Nombre} Apellido: {Apellido} Edad: {Edad} Cargo: {Cargo} Salario: ${Salario} Fecha De Ingreso: {FeIngreso}";
            }       
    }
}
 
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SQLite;

namespace ProyectoEnClase_27_05_2023.Clases
{
    internal class GestorEmpleados
    {

        private string connectionString;

        public GestorEmpleados(string databasePath)
        {
            connectionString = $"Data Source={databasePath};Version=3;";
        }

        //estoy usando sqlite y la libreria System.Data.SQLite
        public void CrearTabla()
        {
            using (SQLiteConnection connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                string sql = "CREATE TABLE IF NOT EXISTS Empleados (Id INTEGER PRIMARY KEY AUTOINCREMENT, Nombre VARCHAR(100), Apellido VARCHAR(100), Edad INTEGER, Cargo VARCHAR(100), Salario decimal, FeIngreso DataTime)";
                SQLiteCommand command = new SQLiteCommand(sql, connection);
                command.ExecuteNonQuery();
                connection.Close();
            }
        }

        public void AgregarEmpleado(Empleado empleado)
        {
            using (SQLiteConnection connection = new SQLiteConnection(connectionString))
            {
                connection.Open();

                string query = "INSERT INTO Empleados (Nombre, Apellido, Edad, Cargo, Salario, FeIngreso) " +
                               "VALUES (@Nombre, @Apellido, @Edad, @Cargo, @Salario, @FeIngreso)";

                using (SQLiteCommand command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@Nombre", empleado.Nombre);
                    command.Parameters.AddWithValue("@Apellido", empleado.Apellido);
                    command.Parameters.AddWithValue("@Edad", empleado.Edad);
                    command.Parameters.AddWithValue("@Cargo", empleado.Cargo);
                    command.Parameters.AddWithValue("@Salario", empleado.Salario);
                    command.Parameters.AddWithValue("@FeIngreso", empleado.FeIngreso);

                    command.ExecuteNonQuery();
                }
            }
        }

        public void EliminarEmpleado(int id)
        {
            using (SQLiteConnection connection = new SQLiteConnection(connectionString))
            {
                connection.Open();

                string query = "DELETE FROM Empleados WHERE Id = @Id";

                using (SQLiteCommand command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@Id", id);

                    command.ExecuteNonQuery();
                }
            }
        }

        public void ActualizarEmpleado(Empleado empleado)
        {
            using (SQLiteConnection connection = new SQLiteConnection(connectionString))
            {
                connection.Open();

                string query = "UPDATE Empleados SET " +
                               "Nombre = @Nombre, " +
                               "Apellido = @Apellido, " +
                               "Edad = @Edad, " +
                               "Cargo = @Cargo, " +
                               "Salario = @Salario, " +
                               "FeIngreso = @FeIngreso " +
                               "WHERE Id = @Id";

                using (SQLiteCommand command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@Nombre", empleado.Nombre);
                    command.Parameters.AddWithValue("@Apellido", empleado.Apellido);
                    command.Parameters.AddWithValue("@Edad", empleado.Edad);
                    command.Parameters.AddWithValue("@Cargo", empleado.Cargo);
                    command.Parameters.AddWithValue("@Salario", empleado.Salario);
                    command.Parameters.AddWithValue("@FeIngreso", empleado.FeIngreso);
                    command.Parameters.AddWithValue("@Id", empleado.Id);

                    command.ExecuteNonQuery();
                }
            }
        }

        public List<Empleado> ObtenerEmpleados()
        {
            List<Empleado> empleados = new List<Empleado>();
            using (SQLiteConnection connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                string query = "SELECT * FROM Empleados";
                using (SQLiteCommand command = new SQLiteCommand(query, connection))
                {
                    using (SQLiteDataReader reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            int id = reader.GetInt32(0);
                            string nombre = reader.GetString(1);
                            string apellido = reader.GetString(2);
                            int edad = reader.GetInt32(3);
                            string cargo = reader.GetString(4);
                            decimal salario = reader.GetDecimal(5);
                            DateTime feIngreso = reader.GetDateTime(6);
                            Empleado empleado = new Empleado(id, nombre, apellido, edad, cargo, salario, feIngreso);
                            empleados.Add(empleado);
                        }
                    }
                }
            }
            return empleados;
        }

        public List<Empleado> BuscarEmpleado(int id)
        {
            List<Empleado> empleadosEncontrados = new List<Empleado>();

            using (SQLiteConnection connection = new SQLiteConnection(connectionString))
            {
                connection.Open();

                string query = "SELECT * FROM Empleados WHERE Id = @Id";

                using (SQLiteCommand command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@Id", id);

                    using (SQLiteDataReader reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            int empleadoId = reader.GetInt32(0);
                            string nombre = reader.GetString(1);
                            string apellido = reader.GetString(2);
                            int edad = reader.GetInt32(3);
                            string cargo = reader.GetString(4);
                            decimal salario = reader.GetDecimal(5);
                            DateTime fechaIngreso = reader.GetDateTime(6);

                            Empleado empleado = new Empleado(empleadoId, nombre, apellido, edad, cargo, salario, fechaIngreso);
                            empleadosEncontrados.Add(empleado);
                        }
                    }
                }
            }

            return empleadosEncontrados;
        }
    }
}


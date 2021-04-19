using Salon_de_infrumusetare.Entities;
using Salon_de_infrumusetare.Interfaces;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Salon_de_infrumusetare.DAL
{
    class ProgramareDAO : IProgramareDAO
    {
        private String _connectionString = @"Data Source=DESKTOP-1IJ584I\SQLEXPRESS;Initial Catalog=Tema1;Integrated Security=True";
        SqlConnection _conn = null;

        public ProgramareDAO()
        {
            try
            {
                _conn = new SqlConnection(_connectionString);
            }
            catch (SqlException e)
            {
                //de facut ceva error handling, afisat mesaj, etc..
                _conn = null;
            }
        }

        public string addProgramare(string data, string ora, string numeClient, string telefon, string services, int cost)
        {
            String sql = "insert into appointment (clientName,telefon,ora,appointmentDate,services,price) values('" + numeClient + "','" + telefon + "','" + ora + "','" + data + "','" + services + "','" + cost + "')";
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                cmd.ExecuteNonQuery();
                _conn.Close();

            }
            catch (Exception e)
            {
                return null;
            }
            return "SUCCESS";
        }

        public List<Programare> getListaProgramari()
        {
            List<Programare> listaProgramari = new List<Programare>();
            Programare u = null;
            String sql = "SELECT * FROM appointment";
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    u = new Programare(Int32.Parse(reader["id"].ToString()), reader["appointmentDate"].ToString(), reader["ora"].ToString(), reader["clientName"].ToString(), reader["telefon"].ToString(), reader["services"].ToString(), Int32.Parse(reader["price"].ToString()));
                    listaProgramari.Add(u);
                }
                _conn.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return null;
            }
            return listaProgramari;
        }

        public List<Programare> getRaport(string startDate, string endDate)
        {
            List<Programare> listaProgramari = new List<Programare>();
            Programare u = null;
            String sql = "select * from appointment where appointmentDate between '" + startDate + "' and '" + endDate + "'";
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    u = new Programare(Int32.Parse(reader["id"].ToString()), reader["appointmentDate"].ToString(), reader["ora"].ToString(), reader["clientName"].ToString(), reader["telefon"].ToString(), null, Int32.Parse(reader["price"].ToString()));
                    listaProgramari.Add(u);
                }
                _conn.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return null;
            }
            return listaProgramari;
        }

    }
}

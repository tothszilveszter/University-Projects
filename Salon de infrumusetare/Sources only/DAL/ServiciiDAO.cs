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
    class ServiciiDAO : IServiciiDAO
    {
        private String _connectionString = @"Data Source=DESKTOP-1IJ584I\SQLEXPRESS;Initial Catalog=Tema1;Integrated Security=True";
        SqlConnection _conn = null;

        public ServiciiDAO()
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

        public string addService(string nume, string price)
        {
            String sql = "insert into service (name,price) values('" + nume + "','" + price +"')";
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

        public string deleteService(string name)
        {
            String sql = "delete from service where name='"+name+"'";
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

        public Serviciu getService(string name)
        {
            Serviciu u = null;
            String sql = "SELECT * FROM service WHERE name='" + name + "'";
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                reader.Read();
                u = new Serviciu(Int32.Parse(reader["id"].ToString()), reader["name"].ToString(), Int32.Parse(reader["price"].ToString()));
                _conn.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return null;
            }
            return u;
        }

        public List<Serviciu> getServicii()
        {
            List<Serviciu> listaServicii = new List<Serviciu>();
            Serviciu u = null;
            String sql = "SELECT * FROM service";
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    u = new Serviciu(Int32.Parse(reader["id"].ToString()), reader["name"].ToString(), Int32.Parse(reader["price"].ToString()));
                    listaServicii.Add(u);
                }
                _conn.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return null;
            }
            return listaServicii;
        }

        public string updateService(string oldName, string newName, string price)
        {
            String sql = "update service set name='" + newName + "', price='" + price + "' where name='"+oldName+"'";
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
    }
}

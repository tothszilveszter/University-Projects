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
    class UserDAO : IUserDAO
    {
        private String _connectionString = @"Data Source=DESKTOP-1IJ584I\SQLEXPRESS;Initial Catalog=Tema1;Integrated Security=True";
        SqlConnection _conn = null;

        public UserDAO()
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

        public User getUser(String username, String password)
        {
            User u = null;
            String sql = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                reader.Read();
                u = new User(reader["username"].ToString(), reader["password"].ToString(), reader["name"].ToString(), reader["role"].ToString());
                _conn.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return null;
            }
            return u;
        }

        public String createEmployee(string name, string userName, string password)
        {
            String sql = "insert into users values('" + name + "','" + userName + "','" + password + "','employee')";
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

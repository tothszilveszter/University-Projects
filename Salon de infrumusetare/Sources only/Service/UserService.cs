using Salon_de_infrumusetare.DAL;
using Salon_de_infrumusetare.Entities;
using Salon_de_infrumusetare.Interfaces;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Reflection;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace Salon_de_infrumusetare.Service
{
    class UserService
    {
        IUserDAO _repository;

        public UserService(IUserDAO repository)
        {
            _repository = repository;
        }

        static string getMd5Hash(string input)
        {
            // Create a new instance of the MD5CryptoServiceProvider object.
            MD5 md5Hasher = MD5.Create();

            // Convert the input string to a byte array and compute the hash.
            byte[] data = md5Hasher.ComputeHash(Encoding.Default.GetBytes(input));

            // Create a new Stringbuilder to collect the bytes
            // and create a string.
            StringBuilder sBuilder = new StringBuilder();

            // Loop through each byte of the hashed data 
            // and format each one as a hexadecimal string.
            for (int i = 0; i < data.Length; i++)
            {
                sBuilder.Append(data[i].ToString("x2"));
            }

            // Return the hexadecimal string.
            return sBuilder.ToString();
        }
        public String login(String userName, String password)
        {

            String hashedPassword = getMd5Hash(password);
            User user = _repository.getUser(userName, hashedPassword);
            try
            {
                if (!user.Equals(null))
                {
                    if (user.getRole().Equals("admin"))
                    {
                        return "admin";
                    }
                    else
                    {
                        return "employee";
                    }
                }
            }
            catch (System.NullReferenceException error)
            {
                return "ERROR";
            }
            return null;
        }

        public String addEmployee(String name, String username, String password)
        {
            String hashedPassword = getMd5Hash(password);
            return _repository.createEmployee(name, username, hashedPassword);
        }
    }
}

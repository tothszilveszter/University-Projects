using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Salon_de_infrumusetare.Entities
{
    class User
    {
        String username = null;
        String password = null;
        String name = null;
        String role = null;

        public User(string username, string password, string name, string role)
        {
            this.username = username;
            this.password = password;
            this.name = name;
            this.role = role;
        }

        public String getRole()
        {
            return this.role;
        }
    }
}

using Salon_de_infrumusetare.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Salon_de_infrumusetare.Interfaces
{
    interface IUserDAO
    {
        User getUser(String userName, String password);
        String createEmployee(String name, String userName, String password);
    }
}

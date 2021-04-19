using Salon_de_infrumusetare.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Salon_de_infrumusetare.Interfaces
{
    interface IServiciiDAO
    {
        String addService(string nume, String price);
        String updateService(String oldName, String newName, String price);
        String deleteService(String name);
        Serviciu getService(String name);

        List<Serviciu> getServicii();
    }
}

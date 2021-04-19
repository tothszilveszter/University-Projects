using Salon_de_infrumusetare.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Salon_de_infrumusetare.Interfaces
{
    interface IProgramareDAO
    {
        String addProgramare(String data, String ora, String numeClient, String telefon, String services, int cost);

        List<Programare> getListaProgramari();

        List<Programare> getRaport(String data1, String data2);

    }
}

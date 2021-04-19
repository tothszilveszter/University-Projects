using Salon_de_infrumusetare.Entities;
using Salon_de_infrumusetare.Interfaces;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace Salon_de_infrumusetare.Service
{
    class ProgramareService
    {
        IProgramareDAO _repository;

        public ProgramareService(IProgramareDAO repository)
        {
            _repository = repository;
        }

        public String addProgramare(String date, String name, String telefon, String services, String ora)
        {
            Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryServicii"]);
            ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
            IServiciiDAO serviciiRepository = (IServiciiDAO)constructor.Invoke(null);

            String[] s = services.Split(',');

            int cost = 0;

            foreach (String w in s)
            {
                if (verifIfTaken(w,date,ora))
                {
                    return "ERROR! Service: " + w + " is taken. Choose another!";
                }
                Serviciu u= serviciiRepository.getService(w);
                if (u == null)
                {
                    return null;
                }
                else
                cost += u.getPrice();
            }

            String result = _repository.addProgramare(date, ora, name, telefon, services, cost);

            return result ;
        }

        public Boolean verifIfTaken(String service, string date, string hour)
        {
            
            List<Programare> listaProgramari = _repository.getListaProgramari();
            foreach (Programare p in listaProgramari)
            {
                String[] s = p.getServices().Split(',');
                foreach (String w in s)
                {
                    if (w.Equals(service))
                    {
                        if (p.getDate().Substring(0,8).Equals(date) && p.getOra().Equals(hour+":00"))
                        return true;
                    }
                }
            }
            return false;
        }

        public String getProgramari()
        {
            List<Programare> listaProgramari = _repository.getListaProgramari();
            String result = "Lista cu toate programarile:\n";
            foreach(Programare p in listaProgramari)
            {
                result += p.myToString();
            }
            return result;
        }

        public String getRaportByName(String clientName)
        {
            List<Programare> listaProgramari = _repository.getListaProgramari();

            string result = null;

            if (listaProgramari == null)
            {
                result = "Nu exista programari pentru clientul cerut!";
            }
            else
            {
                result = "Programarile pentru clientul cerut:\n";

                foreach (Programare p in listaProgramari)
                {
                    if (p.getClientName().Equals(clientName))
                        result += p.myToString();
                }
            }
            return result;
        }

        public String getRaportByDates(String startDate, String endDate)
        {
            List<Programare> listaProgramari = _repository.getRaport(startDate,endDate);

            string result = null;

            if (listaProgramari == null)
            {
                result = "Nu exista programari intre datile cerute!";
            }
            else
            {
                result = "Programarile intre datile cerute:\n";

                foreach (Programare p in listaProgramari)
                {
                    result += p.myToString();
                }
            }
            return result;
        }

    }
}

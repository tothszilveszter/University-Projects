using Salon_de_infrumusetare.Entities;
using Salon_de_infrumusetare.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Salon_de_infrumusetare.Service
{
    class ServiciiService
    {
        IServiciiDAO _repository;

        public ServiciiService(IServiciiDAO repository)
        {
            _repository = repository;
        }

        public String addService(String numeServiciu,String price)
        {
            return _repository.addService(numeServiciu,price);
        }

        public String deleteService(String numeServiciu)
        {
            return _repository.deleteService(numeServiciu);
        }

        public String updateService(String oldName, String newName, String price)
        {
            return _repository.updateService(oldName, newName, price);
        }

        public Serviciu getService(String numeServiciu)
        {
            return _repository.getService(numeServiciu);
        }

        public String getServicii(String name)
        {
            List<Serviciu> listaServicii = _repository.getServicii();

            String result = "Lista cu serviciile actuale:\n";

            foreach (Serviciu s in listaServicii)
            {
                if (!s.getName().Equals(name))
                result += s.getId() + " | " + s.getName() + " | " + s.getPrice() + "\n";
            }

            return result;
        }
    }
}

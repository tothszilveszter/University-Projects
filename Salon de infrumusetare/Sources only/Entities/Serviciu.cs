using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Salon_de_infrumusetare.Entities
{
    class Serviciu
    {
        int id;
        String name=null;
        int price;

        public Serviciu(int id, string name, int price)
        {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId()
        {
            return this.id;
        }

        public int getPrice()
        {
            return this.price;
        }

        public String getName()
        {
            return this.name;
        }

        public void setName(String newName)
        {
            this.name = newName;
        }

        public void setPrice(int newPrice)
        {
            this.price = newPrice;
        }
    }
}

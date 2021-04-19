using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Salon_de_infrumusetare.Entities
{
    class Programare
    {
        int id;
        String data = null;
        String ora = null;
        String numeClient = null;
        String telefon = null;
        String servicii = null;
        int pret;

        public Programare(int id, string data, string ora, string numeClient, string telefon, string servicii, int pret)
        {
            this.id = id;
            this.data = data;
            this.ora = ora;
            this.numeClient = numeClient;
            this.telefon = telefon;
            this.servicii = servicii;
            this.pret = pret;
        }

        public int getId()
        {
            return this.id;
        }

        public String myToString()
        {
            String s = null;
            s += "ID=" + this.id + " | Client name=" + this.numeClient + " | Telefon=" + this.telefon + " | Data="+ this.data.Substring(0,8) + " | Ora=" + this.ora + " | Servicii="+ this.servicii +" | Cost=" + this.pret + "\n\n";
            return s;
        }

        public String getClientName()
        {
            return this.numeClient;
        }

        public String getServices()
        {
            return this.servicii;
        }

        public String getDate()
        {
            return this.data;
        }

        public String getOra()
        {
            return this.ora;
        }

    }
}

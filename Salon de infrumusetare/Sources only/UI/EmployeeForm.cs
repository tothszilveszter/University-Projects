using Salon_de_infrumusetare.Interfaces;
using Salon_de_infrumusetare.Service;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Salon_de_infrumusetare.UI
{
    public partial class EmployeeForm : Form
    {
        public EmployeeForm()
        {
            InitializeComponent();
        }

        private void monthCalendar1_DateChanged(object sender, DateRangeEventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            String date = monthCalendar1.SelectionRange.Start.ToShortDateString();
            String name = textBox1.Text;
            String telefon = textBox2.Text;
            String services = textBox3.Text;
            String ora = textBox4.Text;

            String result = null;

            Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryProgramari"]);
            ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
            IProgramareDAO defaultRepository = (IProgramareDAO)constructor.Invoke(null);

            ProgramareService programareService = new ProgramareService(defaultRepository);

            Type obj1 = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryServicii"]);
            ConstructorInfo constructor1 = obj1.GetConstructor(new Type[] { });
            IServiciiDAO defaultRepository1 = (IServiciiDAO)constructor1.Invoke(null);

            ServiciiService serviciiService = new ServiciiService(defaultRepository1);

            String serviciiAvailable = null;


            if (!(name == "" || telefon == "" || services == "" || ora == ""))
            {
                result = programareService.addProgramare(date, name, telefon, services, ora);

                if (result == null)
                {
                    serviciiAvailable = serviciiService.getServicii(null);
                    MessageBox.Show("Something went wrong!\n\n" + serviciiAvailable);
                }
                else if (result.Substring(0,6).Equals("ERROR!"))
                {
                    String[] s = result.Split(' ');
                    serviciiAvailable = serviciiService.getServicii(s[2]);
                    MessageBox.Show(result + "\n" + serviciiAvailable);
                }
                else
                {
                    MessageBox.Show("Appointment booked successfully!");
                }
            }
            else
            {
                MessageBox.Show("Fields must not be empty!");
            }    

            

        }

        private void button2_Click(object sender, EventArgs e)
        {
            Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryProgramari"]);
            ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
            IProgramareDAO defaultRepository = (IProgramareDAO)constructor.Invoke(null);

            ProgramareService programareService = new ProgramareService(defaultRepository);

            String result = programareService.getProgramari();

            if (result == null)
            {
                MessageBox.Show("Something went wrong!");
            }
            else
            {
                
                MessageBox.Show(result);
            }
        }

        private void label8_Click(object sender, EventArgs e)
        {
            
        }
    }
}

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
    public partial class ServiceCRUDForm : Form
    {
        public ServiceCRUDForm()
        {
            InitializeComponent();
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            String newName = textBox2.Text;
            String price = textBox3.Text;

            String result = null;

            if (!(newName == "" || price == ""))
            {
                Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryServicii"]);
                ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
                IServiciiDAO defaultRepository = (IServiciiDAO)constructor.Invoke(null);

                ServiciiService serviciiService = new ServiciiService(defaultRepository);

                result = serviciiService.addService(newName, price);

                
            }

            if (result == null)
            {
                MessageBox.Show("Something went wrong");
            }
            else
            {
                MessageBox.Show("Service added successfully!");
            }
            this.Close();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            String oldName = textBox1.Text;
            String result = null;
            if (oldName != "")
            {
                Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryServicii"]);
                ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
                IServiciiDAO defaultRepository = (IServiciiDAO)constructor.Invoke(null);

                ServiciiService serviciiService = new ServiciiService(defaultRepository);

                result = serviciiService.deleteService(oldName);
            }
            if (result == null)
            {
                MessageBox.Show("Something went wrong");
            }
            else
            {
                MessageBox.Show("Service deleted successfully!");
            }
            this.Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            String oldName = textBox1.Text;
            String newName = textBox2.Text;
            String price = textBox3.Text;

            String result = null;

            if (!(newName == "" || price == "" || price == ""))
            {
                Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryServicii"]);
                ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
                IServiciiDAO defaultRepository = (IServiciiDAO)constructor.Invoke(null);

                ServiciiService serviciiService = new ServiciiService(defaultRepository);

                result = serviciiService.updateService(oldName, newName, price);

            }

            if (result == null)
            {
                MessageBox.Show("Something went wrong");
            }
            else
            {
                MessageBox.Show("Service updated successfully!");
            }
            this.Close();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryServicii"]);
            ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
            IServiciiDAO defaultRepository = (IServiciiDAO)constructor.Invoke(null);

            ServiciiService serviciiService = new ServiciiService(defaultRepository);

            String result = serviciiService.getServicii(null);
            MessageBox.Show(result);
        }
    }
}

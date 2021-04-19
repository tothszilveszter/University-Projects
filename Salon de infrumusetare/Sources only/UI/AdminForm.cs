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
    public partial class AdminForm : Form
    {
        //IUserDAO defaultRepository;

        public AdminForm()
        {
            InitializeComponent();
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void AdminForm_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            String name = textBox1.Text;
            String userName = textBox2.Text;
            String password = textBox3.Text;

            String status = null;

            if (!(name == "" || userName == "" || password == ""))
            {
                Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryUser"]);
                ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
                IUserDAO defaultRepository = (IUserDAO)constructor.Invoke(null);

                UserService userService = new UserService(defaultRepository);

                status = userService.addEmployee(name, userName, password);
            }

            if (status==null)
            {
                MessageBox.Show("Something went wrong");
            }
            else
            {
                MessageBox.Show("Employee added successfully!");
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            ServiceCRUDForm serviceCRUD = new ServiceCRUDForm();
            serviceCRUD.Show();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            String clientName = textBox4.Text;

            Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryProgramari"]);
            ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
            IProgramareDAO defaultRepository = (IProgramareDAO)constructor.Invoke(null);

            ProgramareService programareService = new ProgramareService(defaultRepository);

            String result = programareService.getRaportByName(clientName);

            if (result == null)
            {
                MessageBox.Show("Something went wrong");
            }
            else
            {
                MessageBox.Show(result);
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            String startDate = monthCalendar1.SelectionRange.Start.ToShortDateString();
            String endDate = monthCalendar2.SelectionRange.Start.ToShortDateString();

            Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryProgramari"]);
            ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
            IProgramareDAO defaultRepository = (IProgramareDAO)constructor.Invoke(null);

            ProgramareService programareService = new ProgramareService(defaultRepository);

            String result = programareService.getRaportByDates(startDate,endDate);

            if (result == null)
            {
                MessageBox.Show("Something went wrong");
            }
            else
            {
                MessageBox.Show(result);
            }
        }
    }
}

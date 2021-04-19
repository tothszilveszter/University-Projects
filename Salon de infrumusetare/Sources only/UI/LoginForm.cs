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
    public partial class LoginForm : Form
    {
        public LoginForm()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            String userName = textBox1.Text;
            String password = textBox2.Text;

            Type obj = Type.GetType(ConfigurationManager.AppSettings["DefaultRepositoryUser"]);
            ConstructorInfo constructor = obj.GetConstructor(new Type[] { });
            IUserDAO defaultRepository = (IUserDAO)constructor.Invoke(null);

            UserService userService = new UserService(defaultRepository);

            String choice = userService.login(userName, password);
            if (choice.Equals("admin"))
            {
                AdminForm adminForm = new AdminForm();
                adminForm.Show();
            }
            else if (choice.Equals("employee"))
            {
                EmployeeForm employeeForm = new EmployeeForm();
                employeeForm.Show();
            }
            else
            {
                MessageBox.Show("Something went wrong! Try another username or password!");
            }
        }
    }
}

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CourseworkOfTheThirdSemester
{
    public partial class Form1 : Form
    {

        List<Particle> particles = new List<Particle>();
        public Form1()
        {
            InitializeComponent();

            pictureBox1.Image = new Bitmap(pictureBox1.Width, pictureBox1.Height);

            for (var i = 0; i < 500; ++i)
            {
                var particle = new Particle();
                // переношу частицы в центр изображения
                particle.X = pictureBox1.Image.Width / 2;
                particle.Y = pictureBox1.Image.Height / 2;
                // добавляю список
                particles.Add(particle);
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }

        private void pictureBox1_Tick(object sender, EventArgs e)
        {

        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CourseworkOfTheThirdSemester
{
    class Particle
    {

        public int Radius; // радуис частицы
        public float X; // X координата положения частицы в пространстве
        public float Y;

        public float Direction; // направление движения

        public float Speed;

        public static Random rand = new Random();

        public Particle()
        {
            // я не трогаю координаты X, Y потому что хочу, чтобы все частицы возникали из одного места
            Direction = rand.Next(360);
            Speed = 1 + rand.Next(10);
            Radius = 2 + rand.Next(10);
        }

    }
}

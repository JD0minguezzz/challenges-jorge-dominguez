using System;
using static System.Math;

namespace CSharpChallenge
{
    public class Shape
    {
        
    }

    public class Circle: Shape{
        public double Radio { get; set; }        
    }

    public class MathHelper
    {
        public static (double Area, double Length) Calculate(Shape shape){
            double area = 0; //tuples
            double length = 0; //using static, pattern matching

            if (shape is Circle circle)
            {
                var radio = circle.Radio;
                area = PI * Pow(radio, 2);
                length = 2 * PI * radio;
            }
            return (area, length);
        }
    }
}
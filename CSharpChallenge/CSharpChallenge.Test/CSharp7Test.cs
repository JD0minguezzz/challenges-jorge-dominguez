using System;
using NUnit.Framework;
using CSharpChallenge;
using static System.Math;

namespace Tests
{
    public class CSharp7Test
    {
        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void Calculate_WhenCircleIsPassed_ThenCalculateAreaAndLenghtWithPI(){
            // Arrange
            var radio = 3D;
            var circle = new Circle {
                Radio = radio
            };

            // Act
            var res = MathHelper.Calculate(circle);

            // Assert
            Assert.AreEqual(PI * Pow(radio, 2), res.Area);
            Assert.AreEqual(2 * PI * radio, res.Length);
        }

        [Test]
        public void TryParsePercentage_WhenStringIsPassed_ThenParseTheNumber(){
            // Arrange
            var percentage = "94.5%";

            // Act
            var result = StringHelper.TryParsePercentage(percentage);

            // Assert
            Assert.AreEqual(0.945D, result.Percentage);
        }

        [Test]
        public void TryParsePercentage_WhenStringIsNull_ThenParseTheNumber(){
            // Arrange
            //var percentage = "94.5%";

            // Act
            //var result = 0D;
            var result = StringHelper.TryParsePercentage(null);

            // Assert
            Assert.AreEqual(0.0D, result.Percentage);
        }

        [Test]
        public void TryParsePercentage_WhenStringIsNotNumber_ThenParseTheNumber(){
            // Arrange
            var percentage = "Hello World!";

            // Act
            //var result = 0D;
            var result = StringHelper.TryParsePercentage(percentage);

            // Assert
            Assert.AreEqual(0.0D, result.Percentage);
        }
    }
}
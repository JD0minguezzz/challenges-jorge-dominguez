using System;

namespace CSharpChallenge
{
    /// <summary>
    /// Define a class helper
    /// </summary>
    public class StringHelper
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="email"></param>
        /// <returns></returns>
        public static (bool Flag, double Percentage) TryParsePercentage(String value) {
            double percentage = 0D;
            value = value ?? "";
            value = value.Replace("%", "");
            var result = 0D; //out variables
            if (!double.TryParse(value, out result))
            {
                return (false, percentage);
            }
            percentage = result/100D;
            return (true, percentage);
        }
    }
}

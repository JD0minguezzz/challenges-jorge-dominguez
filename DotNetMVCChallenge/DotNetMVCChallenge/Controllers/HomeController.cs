using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using DotNetMVCChallenge.Models;
using System.Net;
using Newtonsoft.Json;
using MyLibrary;


namespace DotNetMVCChallenge.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Asteroids(string startDate, string endDate)
        {
            //var astrItems = new List<Asteroid>();
            //Dictionary<float, AsteroidInformation.Asteroid> astrItems = new Dictionary<float, AsteroidInformation.Asteroid>();
            var astrItems = new List<Tuple<AsteroidInformation.Asteroid, float>>();
            var webClient = new WebClient();
            string json;
            AsteroidInformation.RootObject deserializedJson;
            if (String.IsNullOrEmpty(startDate) || String.IsNullOrEmpty(endDate))
            {
                string start = DateTime.Today.AddMonths(-1).ToString("yyyy-MM-dd");
                json = webClient.DownloadString(@"https://api.nasa.gov/neo/rest/v1/feed?start_date=" + start + "&api_key=RKPwe2Bns2ICWHTj890M6y0tEisOw4J5uN25rurq");
                deserializedJson = JsonConvert.DeserializeObject<AsteroidInformation.RootObject>(json);
            } else
            {
                json = webClient.DownloadString(@"https://api.nasa.gov/neo/rest/v1/feed?start_date=" + startDate + "&end_date=" + endDate + "&api_key=RKPwe2Bns2ICWHTj890M6y0tEisOw4J5uN25rurq");
                deserializedJson = JsonConvert.DeserializeObject<AsteroidInformation.RootObject>(json);
            }
            foreach (KeyValuePair<string, AsteroidInformation.Asteroid[]> astrDictionary in deserializedJson.near_earth_objects)
            {
                foreach (AsteroidInformation.Asteroid asteroid in astrDictionary.Value)
                {
                    float timeToReach = float.Parse(asteroid.close_approach_data[0].miss_distance.kilometers) / 299792;
                    astrItems.Add(new Tuple<AsteroidInformation.Asteroid, float>(asteroid, timeToReach));
                }
            }
            return View(astrItems);
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}

# WaterLogging

Water logging has been disrupting livelihoods of about one million people in Bangladesh during past two decades.We propose a solution to redesign a city's drainage system using algorithmic approach.Location based water-logging measurement data are collected from users via a mobile-application.These data are processed into a graph and the total volume of water is calculated by convex-hull algorithm.Then,the area-to-area rate of flow is optimized by Ford-Fulkerson's algorithm to draw out maximum amount of water compared to the pipeline's’ capacity.There is also a re-designed and optimized pipeline-placement-map for the city.
 
1) We would collect data of water-logging events from the user-survey via mobile application or web application. Data including the height of logged water as well as the garbage level at different points of the city (ie. How many cm of water has been logged in Dhanmondi 8A?) will be stored in a database.
 
2) The Govt. has a placement map of pipelines of the city with proper measurements (i.e. length, radius etc.).These measurements will help us to assume how much water should be removed after the capacity of the pipeline is full. To accommodate all the water in a certain pipeline, the change in the pipeline measurements will be calculated and proper actions can be taken. 
 
3) The rate of flow of water along with time will work as a great parameter. To remove maximum amount of water in an optimal time, Ford-Fulkerson's maximum-flow algorithm will be applied. 
 
4) Data yielding from the calculations will provide us with a plan for : i)designing properly measured pipelines (with maximum-capacity) in a minimum budget ,ii)drowning out the extra water (from calculation) to places with less water logging, iii)cross-referencing Google map with user data to formulate actual flooded area (with the help of image-processing).

package me.evyatar.balls;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * 
 * @author Evyatar Itzhaki - Bulletproof
 * @category Bukkit Libraries 
 * @see <a href="https://github.com/evyatar18">My GitHub</a>
 */
public class BallLib {
	
	static {
		Bukkit.getLogger().log(Level.INFO, "BallLib by Bulletproof v1.0 is running on this server.");
	}
	
	/**
	 * Returns locations which will create a ball with given center location, radius and parts
	 * @param center - center location of the ball
	 * @param radius - radius of the ball
	 * @param parts - number of parts on the circle. (<b>the more parts, the better looking ball!</b>)
	 * @return a list which contains the ball locations
	 */
	public static List<Location> getBallLocations(Location center, double radius, int parts) {
		if (parts <= 0) {
			throw new IllegalArgumentException("'parts' must be a natural number.");
		}
		
		double angle = 360/parts;
		
		List<Location> ball = new ArrayList<Location>();
		
		for (double yAngle = -90; yAngle <= 90; yAngle = angle + yAngle) {
			double y = Math.sin(Math.toRadians(yAngle))*radius;
			
			double a = Math.sqrt(Math.pow(radius, 2) - Math.pow(y, 2));
			
			for (double xzAngle = 0; xzAngle <= 360; xzAngle = angle + xzAngle) {
				double ang = Math.toRadians(xzAngle);
				
				double x = Math.sin(ang)*a;
				double z = Math.cos(ang)*a;
				
				ball.add(center.clone().add(x, y, z));
			}
		}
		
		return ball;
	}
	
	/**
	 * Returns a specific location which is in a given distance from a location and lying in specific angles
	 * @param center - the returned location will be in a distance relative to this location
	 * @param radius - distance
	 * @param y_angle - angle among the y-axis and z-axis
	 * @param xz_angle - angle among the x-axis and z-axis
	 * @return the location
	 */
	public static Location getBallLocation(Location center, double radius, double y_angle, double xz_angle) {
		y_angle = Math.toRadians(y_angle); // transform angle to radians
		xz_angle = Math.toRadians(xz_angle); // transform angle to radians
		
		double y = Math.sin(y_angle)*radius; // gets the y of the point on the ball at (0, 0, 0)
		
		double a = Math.sqrt(Math.pow(radius, 2) - Math.pow(y, 2)); // Pythagoras to get radius of circle of x and z.
		
		double x = Math.sin(xz_angle)*a; // gets the x of the point on the ball at (0, 0, 0)
		double z = Math.cos(xz_angle)*a; // gets the z of the point on the ball at (0, 0, 0)
		
		
		return center.clone().add(x, y, z); // adds the x, y, z found to the location
	}
}

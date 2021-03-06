package net.piemaster.artemoids;

import net.piemaster.artemoids.components.Asteroid;
import net.piemaster.artemoids.components.Expires;
import net.piemaster.artemoids.components.Health;
import net.piemaster.artemoids.components.Player;
import net.piemaster.artemoids.components.Respawn;
import net.piemaster.artemoids.components.Score;
import net.piemaster.artemoids.components.SpatialForm;
import net.piemaster.artemoids.components.Transform;
import net.piemaster.artemoids.components.Velocity;

import com.artemis.Entity;
import com.artemis.World;

public class EntityFactory
{
	public static Entity createPlayerShip(World world)
	{
		Entity player = world.createEntity();
		player.setGroup("SHIPS");
		player.setTag("PLAYER");
		player.addComponent(new Transform());
		player.addComponent(new Velocity());
		player.addComponent(new SpatialForm("PlayerImageShip"));
		player.addComponent(new Health(1));
		player.addComponent(new Player());
		player.addComponent(new Score());
		player.addComponent(new Respawn(2000));

		return player;
	}

	public static Entity createMissile(World world)
	{
		Entity missile = world.createEntity();
		missile.setGroup("BULLETS");

		missile.addComponent(new Transform());
		missile.addComponent(new SpatialForm("Missile"));
		missile.addComponent(new Velocity());
		missile.addComponent(new Expires(2000));

		return missile;
	}

	public static Entity createMissile(World world, Transform parent)
	{
		Entity missile = world.createEntity();
		missile.setGroup("BULLETS");

		missile.addComponent(new Transform(parent.getX(), parent.getY(), parent.getRotation()));
		missile.addComponent(new SpatialForm("Missile"));
		missile.addComponent(new Velocity());
		missile.addComponent(new Expires(2000));

		return missile;
	}

	public static Entity createAsteroid(World world, float x, float y, int size)
	{
		Entity e = world.createEntity();
		e.setGroup("ASTEROIDS");

		e.addComponent(new Transform(x, y));
		e.addComponent(new SpatialForm("Asteroid"));
		e.addComponent(new Health(1));
		e.addComponent(new Asteroid(size));
		e.addComponent(new Velocity());

		return e;
	}

	public static Entity createBulletExplosion(World world, float x, float y)
	{
		Entity e = world.createEntity();

		e.setGroup("EFFECTS");

		e.addComponent(new Transform(x, y));
		e.addComponent(new SpatialForm("BulletExplosion"));
		e.addComponent(new Expires(1000));

		return e;
	}

	public static Entity createShipExplosion(World world, float x, float y)
	{
		Entity e = world.createEntity();

		e.setGroup("EFFECTS");

		e.addComponent(new Transform(x, y));
		e.addComponent(new SpatialForm("ShipExplosion"));
		e.addComponent(new Expires(1000));

		return e;
	}

}

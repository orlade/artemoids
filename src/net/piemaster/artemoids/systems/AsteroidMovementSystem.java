package net.piemaster.artemoids.systems;

import net.piemaster.artemoids.components.Asteroid;
import net.piemaster.artemoids.components.Transform;
import net.piemaster.artemoids.components.Velocity;

import org.newdawn.slick.GameContainer;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;

public class AsteroidMovementSystem extends EntityProcessingSystem
{
	private GameContainer container;
	private ComponentMapper<Transform> transformMapper;
	private ComponentMapper<Velocity> velocityMapper;

	@SuppressWarnings("unchecked")
	public AsteroidMovementSystem(GameContainer container)
	{
		super(Transform.class, Asteroid.class, Velocity.class);
		this.container = container;
	}

	@Override
	public void initialize()
	{
		transformMapper = new ComponentMapper<Transform>(Transform.class, world);
		velocityMapper = new ComponentMapper<Velocity>(Velocity.class, world);
	}

	@Override
	protected void process(Entity e)
	{
		Transform transform = transformMapper.get(e);
		Velocity velocity = velocityMapper.get(e);

		if (transform.getX() > container.getWidth() || transform.getX() < 0)
		{
			velocity.addAngle(180 - 2*(velocity.getAngle() % 180));
		}
		if (transform.getY() > container.getHeight() || transform.getY() < 0)
		{
			velocity.setAngle(360 - velocity.getAngle());
		}
	}

}

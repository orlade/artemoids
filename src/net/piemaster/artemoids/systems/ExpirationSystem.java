package net.piemaster.artemoids.systems;

import net.piemaster.artemoids.components.Expires;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;

public class ExpirationSystem extends EntityProcessingSystem
{

	private ComponentMapper<Expires> expiresMapper;

	public ExpirationSystem()
	{
		super(Expires.class);
	}

	@Override
	public void initialize()
	{
		expiresMapper = new ComponentMapper<Expires>(Expires.class, world);
	}

	@Override
	protected void process(Entity e)
	{
		Expires expires = expiresMapper.get(e);
		expires.reduceLifeTime(world.getDelta());

		if (expires.isExpired())
		{
			world.deleteEntity(e);
		}

	}
}

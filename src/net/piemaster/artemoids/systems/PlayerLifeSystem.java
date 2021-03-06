package net.piemaster.artemoids.systems;

import net.piemaster.artemoids.EntityFactory;
import net.piemaster.artemoids.components.Health;
import net.piemaster.artemoids.components.Player;
import net.piemaster.artemoids.components.Respawn;
import net.piemaster.artemoids.components.SpatialForm;
import net.piemaster.artemoids.components.Transform;
import net.piemaster.artemoids.components.Velocity;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;

public class PlayerLifeSystem extends EntitySystem
{
	private ComponentMapper<Health> healthMapper;
	private ComponentMapper<Velocity> velocityMapper;
	private ComponentMapper<SpatialForm> spatialMapper;

	@SuppressWarnings("unchecked")
	public PlayerLifeSystem()
	{
		super(Player.class, Health.class, Transform.class, Velocity.class, SpatialForm.class, Respawn.class);
	}

	@Override
	public void initialize()
	{
		healthMapper = new ComponentMapper<Health>(Health.class, world);
		velocityMapper = new ComponentMapper<Velocity>(Velocity.class, world);
		spatialMapper = new ComponentMapper<SpatialForm>(SpatialForm.class, world);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities)
	{
		for (int i = 0; entities.size() > i; i++)
		{
			Entity player = entities.get(i);
			Health health = healthMapper.get(player);
			if(!health.isAlive())
			{
				Respawn respawn = player.getComponent(Respawn.class);
				if(!respawn.isActive())
				{
					velocityMapper.get(player).setVelocity(0);
					spatialMapper.get(player).setVisible(false);
					respawn.resetTimer();
					respawn.setActive(true);
					
					Transform tp = player.getComponent(Transform.class);
					EntityFactory.createShipExplosion(world, tp.getX(), tp.getY()).refresh();
				}
			}
		}
	}

	@Override
	protected boolean checkProcessing()
	{
		return true;
	}

}

package net.piemaster.artemoids.spatials;

import net.piemaster.artemoids.components.Expires;
import net.piemaster.artemoids.components.Transform;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;

public class Explosion extends Spatial
{
	private Transform transform;
	private Expires expires;
	private int initialLifeTime;
	private Color color;
	private int radius;

	public Explosion(World world, Entity owner, int radius)
	{
		super(world, owner);
		this.radius = radius;
	}

	@Override
	public void initalize()
	{
		ComponentMapper<Transform> transformMapper = new ComponentMapper<Transform>(
				Transform.class, world);
		transform = transformMapper.get(owner);

		ComponentMapper<Expires> expiresMapper = new ComponentMapper<Expires>(Expires.class,
				world);
		expires = expiresMapper.get(owner);
		initialLifeTime = expires.getLifeTime();

		color = new Color(Color.yellow);
	}

	@Override
	public void render(Graphics g)
	{

		color.a = (float) expires.getLifeTime() / (float) initialLifeTime;

		g.setColor(color);
		g.setAntiAlias(true);
		g.fillOval(transform.getX() - radius, transform.getY() - radius, radius * 2, radius * 2);
	}

}

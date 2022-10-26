package bolt.sytems;
import java.util.Map;
import java.util.UUID;
import bolt.components.Animation;
import bolt.components.SpriteRenderer;
import bolt.Entity;
import bolt.World;

public class AnimationSystem extends System{

  public AnimationSystem(World world){
    super(world);
  }
	@Override
	public void update(long dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      Animation animation = world.getComponent(entity.id,Animation.class);
      SpriteRenderer spriteRenderer = world.getComponent(entity.id,SpriteRenderer.class);
      if(animation != null && spriteRenderer != null){
        if(animation.spriteSheet == null) continue;
          if(animation.currentFrame == animation.frames){
            animation.finished = true;
            if(animation.loop){
              animation.currentFrame = animation.currentFrame % animation.frames;
            }
            else{
              animation.currentFrame--;
            }
          }
          spriteRenderer.sprite = animation.spriteSheet.sprites.get(animation.currentFrame);
          //sprite.sprite = animation.sprites[2];
          if(animation.timeElapse >= animation.interval){
            animation.currentFrame++;
            animation.timeElapse =0l;
          }
          animation.timeElapse += dt;
        }
    }
	}
}
